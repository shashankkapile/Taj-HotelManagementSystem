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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import MainPackage.AccessDatabase;

public class CheckOut_model {

    Connection con;
    PreparedStatement ps = null, ps1 = null, ps2 = null;
    ResultSet rs = null, rs1 = null, rs2 = null;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    Date currentDate = new Date();
    Date checkInDate;
    AccessDatabase db=new AccessDatabase();

    private PreparedStatement createStatement(String query) throws ClassNotFoundException, SQLException {
        
        con = db.getConnectionObject();
        PreparedStatement ps = con.prepareStatement(query);
        return ps;
    }

    private void closeResources() {
        System.err.println("Finally");
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (ps1 != null) {
                ps1.close();
            }
            if (ps2 != null) {
                ps2.close();
            }

            if (rs != null) {
                rs.close();
            }
            if (rs1 != null) {
                rs1.close();
            }
            if (rs2 != null) {
                rs2.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomAvil_model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector getCheckOutInfo(int roomNo) {
        long diff;
        int days = 0, people = 0, roomCost = 0, service = 0, food = 0, totalBill = 00, payable = 00, advance = 0;
        Vector<Object> data = new Vector<Object>();
        int totalDays, bookedDays, cID;
        String s1 = "";
        String billNo = "", cName = "", rType = "", checkInTime = "", checkOutTime = "";
        

        try {
            
            ps = createStatement("select * from currentRecords where rNo=?");
            ps.setInt(1, roomNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                s1 = dateFormat.format(currentDate) + rs.getInt(4);
            } else {
                System.out.println("null 1");
                return null;
            }
            cID = rs.getInt("cID");
            people = rs.getInt("noOfPeople");
            billNo = s1.replaceAll("/", "").replaceAll(":", "").replaceAll(" ", "");
            checkInDate = rs.getDate("chkIn_time");
            currentDate = dateFormat.parse(dateFormat.format(currentDate));
            checkInTime = dateFormat.format(checkInDate);
            checkOutTime = dateFormat.format(currentDate);
            advance = rs.getInt("advancePayment");
            bookedDays = rs.getInt("noOfDays");
            diff = currentDate.getTime() - checkInDate.getTime();
            totalDays = (int) diff / (24 * 60 * 60 * 1000);
            if (totalDays < bookedDays) {
                totalDays = bookedDays;
            }
            service = totalDays * 500;//RoomService
            if (rs.getInt("food") == 1) {
                food = totalDays * 1000;//Food
            }

            //---------------------------------------------------------------
            ps1 = createStatement("select cName from customer where cID=?");
            ps1.setInt(1, rs.getInt("cID"));
            rs1 = ps.executeQuery();
            rs1 = ps1.executeQuery();

            if (rs1.next()) {
                cName = rs1.getString(1);
            }

            //-----------------------------------------------------------------
            ps2 = createStatement("select rType,rate from room where rNo=?");
            ps2.setInt(1, roomNo);
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                rType = rs2.getString(1);
                System.out.println("Days=" + totalDays + " " + rs2.getInt(2));

                roomCost = (totalDays * rs2.getInt(2));//RoomCost
                System.out.println("RCost=" + roomCost);
            } else {
                System.out.println("null 2");
                return null;
            }
            totalBill = roomCost + food + service;
            payable = totalBill - advance;

            data.add(0, billNo);//BillNo
            data.add(1, cID);
            data.add(2, cName);//Added cName to set
            data.add(3, roomNo);
            data.add(4, rType);//added rType to set
            data.add(5, checkInTime);//Added checkin time
            data.add(6, checkOutTime);//Added checkOut Time
            data.add(7, totalDays);//Added No of days
            data.add(8, people);//Added np of people
            data.add(9, roomCost);
            data.add(10, service);
            data.add(11, food);
            data.add(12, totalBill);//Added total bill
            data.add(13, advance);//Added Advance payment
            data.add(14, payable);//Added Payable amount

            ps = createStatement("insert into bills(billNo,cID,cName,rNo,rType,chkIn_time,chkOut_time,noOfDays,noOfPeople,roomCost,serviceCost,foodCost,totalBill,advance,payable) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, billNo);
            ps.setInt(2, cID);
            ps.setString(3, cName);
            ps.setInt(4, roomNo);
            ps.setString(5, rType);
            ps.setString(6, checkInTime);
            ps.setString(7, checkOutTime);
            ps.setInt(8, totalDays);
            ps.setInt(9, people);
            ps.setInt(10, roomCost);
            ps.setInt(11, service);
            ps.setInt(12, food);
            ps.setInt(13, totalBill);
            ps.setInt(14, advance);
            ps.setInt(15, payable);
            ps.execute();
           // con.commit();
            return data;
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(CheckOut_model.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("null 3");
            return null;
        } finally {
            closeResources();
        }

    }

    public boolean CheckOut(String billNo) {

        try {
            ps=createStatement("delete from bills where paid=?");
            ps.setInt(1, 0);
            ps.executeUpdate();
            System.out.println("Bill No----------------:"+billNo);
            ps = createStatement("update bills set paid=1 where billNo=?");
            ps.setString(1, billNo);
            ps.executeUpdate();
            int rNo = Integer.parseInt(billNo.substring(billNo.length() - 3));
            System.out.println("Room No----------------:"+rNo);
            ps1= createStatement("delete from currentRecords where rNo=?");
            ps1.setInt(1, rNo);
            ps1.executeUpdate();
            ps2 = createStatement("update room set rAvail=1 where rNo=?");
            ps2.setInt(1, rNo);
            ps2.executeUpdate();
            //con.commit()createStatement;
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckOut_model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources();
        }
    }

    public ArrayList getCurrentRecords() {
        try {
            ps = createStatement("select * from currentRecords");
            rs = ps.executeQuery();
            ArrayList al = new ArrayList<Vector[]>();
            Vector[] v = new Vector[50];

            for (int i = 0; i < 50; i++) {
                v[i]=new Vector();
            }
            for (int i = 0; rs.next(); i++)
            {
                v[i].add(rs.getString(1));
                v[i].add(rs.getDate(2).toString());
                v[i].add(rs.getInt(3));
                v[i].add(rs.getInt(4));
                v[i].add(rs.getInt(5));
                v[i].add(rs.getInt(6));
                v[i].add(rs.getInt(7));
                v[i].add(rs.getInt(8));
                al.add(v[i]);
            }

            return al;
        } catch (ClassNotFoundException | SQLException ex) {
            try {
                con.rollback();
                Logger.getLogger(CheckOut_model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (SQLException ex1) {
                Logger.getLogger(CheckOut_model.class.getName()).log(Level.SEVERE, null, ex1);
                return null;
            }
        }
    }

    public ArrayList getOldRecords() {
        try {
            ps = createStatement("select * from bills");
            rs = ps.executeQuery();
            ArrayList al = new ArrayList<Vector[]>();
            Vector[] v = new Vector[50];

            for (int i = 0; i < 50; i++) {
                v[i]=new Vector();
            }
            for (int i = 0; rs.next(); i++) {
                v[i].add(rs.getString(1));
                v[i].add(rs.getInt(2));
                v[i].add(rs.getString(3));
                v[i].add(rs.getInt(4));
                v[i].add(rs.getString(5));
                v[i].add(rs.getString(6));
                v[i].add(rs.getString(7));
                v[i].add(rs.getInt(8));
                v[i].add(rs.getInt(9));
                v[i].add(rs.getInt(10));
                v[i].add(rs.getInt(11));
                v[i].add(rs.getInt(12));
                v[i].add(rs.getInt(13));
                v[i].add(rs.getInt(14));
                v[i].add(rs.getInt(15));
                v[i].add(rs.getInt(16));
                al.add(v[i]);
            }

            return al;
        } catch (ClassNotFoundException | SQLException ex) {
            try {
                con.rollback();
                Logger.getLogger(CheckOut_model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (SQLException ex1) {
                Logger.getLogger(CheckOut_model.class.getName()).log(Level.SEVERE, null, ex1);
                return null;
            }
        }
    }

}
