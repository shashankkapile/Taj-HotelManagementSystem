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
import java.util.logging.Logger;
public class RoomAvil_model
{
    
    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    public static void main(String[] args) {
        RoomAvil_model ra=new RoomAvil_model();
        ra.getAvailableRooms(1, 0);
        ra.getAvailableRooms(2, 1);
        ra.getAvailableRooms(1, 0);
        ra.getAvailableRooms(2, 1);
        
    }
    
    public boolean bookRoom(int roomNo) 
    {
        try {
            ps=createStatement("select rAvail from room where rNo=?;");
            ps.setInt(1, roomNo);
            rs=ps.executeQuery();
            
            if(rs.next())
            {
                if(rs.getInt(1)==1)
                {
                    ps=createStatement("update room set rAvail=0 where rNo=?;");
                    ps.setInt(1, roomNo);
                    System.out.println("Res: "+ps.executeUpdate());
                    System.out.println(" ");
                    return true;
                }
                else
                {
                    
                    System.err.println("Room already booked ");
                    return false;
                }
            }
            else
            {
                System.err.println("Room Not found");
            }
            
            
            return false;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomAvil_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {   closeResources();   }
        return false;
    }
    public ResultSet getAvailableRooms(int roomType, int acType)
    {
        try {
            ps=createStatement("select rNo,rAvail from room where rType=? and rAc=?;");
            ps.setInt(1, roomType);
            ps.setInt(2, acType);
            rs=ps.executeQuery();
//            if(rs.next())
            System.out.println("Result(1): "+rs);
            while( rs.next())
                System.out.println("Res: "+rs.getInt(1)+" "+rs.getInt(2));
            return rs;
//            else
//                return rs;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomAvil_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {   closeResources();   }
            
        System.out.println("Result(): "+rs);
        return rs;
    }
    
    private PreparedStatement createStatement(String query)throws ClassNotFoundException, SQLException
    {
        con=new MainPackage.AccessDatabase().getConnectionObject();
        ps=con.prepareStatement(query);
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

    public boolean isRoomAvailable(int roomNo) {
         try {
            ps=createStatement("select rAvail from room where rNo=?;");
            ps.setInt(1, roomNo);
            rs=ps.executeQuery();
            
            if(rs.next())
            {
                if(rs.getInt(1)==1)
                {
                    return true;
                }
                else
                {
                    
                    System.err.println("Room already booked ");
                    return false;
                }
            }
            else
            {
                System.err.println("Room Not found");
            }
            
            
            return false;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomAvil_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {   closeResources();   }
        return false;
    }

    public int getCostOfRoom(int roomNo) {
        try {
            ps=createStatement("select rate from room where rNo=?;");
            ps.setInt(1, roomNo);
            rs=ps.executeQuery();
            if(rs.next())
                return rs.getInt(1);
        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoomAvil_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {   closeResources();   }
        return 0;
        
    }
}
