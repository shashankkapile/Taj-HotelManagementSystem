/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import MainPackage.AccessDatabase;
import java.util.logging.Logger;

public class CheckInModel {

    
    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    AccessDatabase db=new AccessDatabase();
    private PreparedStatement createStatement(String query)throws ClassNotFoundException, SQLException
    {
        con=new MainPackage.AccessDatabase().getConnectionObject();
        ps=con.prepareStatement(query);
        con.setAutoCommit(false);
        return ps;
    }
    
    private void closeResources()
    {
        System.err.println("Finally");
        try {
            if(con!=null)
                con.close();
            if(rs!=null)
                rs.close();
            if(ps!=null)
                ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(RoomAvil_model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkIn(String time, int cID, int roomNo, int people, int days, boolean food, int advance) 
    {
        try {
            String recordId=time.replace('/','-').replace(":", "-").replace(' ', '-')+"-"+String.valueOf(cID);
            System.out.println("recordId: "+recordId);
            AccessDatabase db=new AccessDatabase();
            con=db.getConnectionObject();
            ps=con.prepareStatement("select rAvail from room where rNo=?;");
            ps.setInt(1, roomNo);            
            con.setAutoCommit(false);
            rs=ps.executeQuery();
            if(rs.next())
            {
                if(rs.getInt(1)==0)
                    return false;
                else
                {
                    ps=createStatement("insert into currentRecords values(?,?,?,?,?,?,?,?);");
                    ps.setString(1, recordId);  
                    ps.setString(2, time);
                    ps.setInt(3, cID);
                    ps.setInt(4, roomNo);
                    ps.setInt(5, people);
                    ps.setInt(6, days);
                    ps.setBoolean(7, food);
                    ps.setInt(8, advance);
                    ps.execute();
                    if(new RoomAvil_model().bookRoom(roomNo))
                    {
                        con.commit();
                        closeResources();
                        return true;
                    }
                }
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            try {
                if(!con.getAutoCommit())
                        con.rollback();
                Logger.getLogger(CheckInModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(CheckInModel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        closeResources();
        return false;
    }
}