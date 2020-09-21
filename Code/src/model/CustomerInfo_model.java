/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import MainPackage.AccessDatabase;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerInfo_model 
{
    ResultSet rs=null;
    Connection con=null;
    PreparedStatement ps=null,ps2=null;
    int newcID;
    String dob;
    public static void main(String[] args) {
        CustomerInfo_model ci=new CustomerInfo_model();
        
        ci.setCustomerInfo("Hrishi", Long.parseLong("9766954261"), "Kirloskar Wadi", "1998-03-26","Hrishi@gmail.com", "A3265");
    }
    public ResultSet getCustomerInfo()
    {
        try {
            con=new AccessDatabase().getConnectionObject();
            System.out.println("Connetion got");
           
            ps=con.prepareStatement("select * from customer;");
            rs=ps.executeQuery();
            System.out.println("Retrived From database");
            
            
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CustomerInfo_model.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
               //     if(con!=null)
                //        con.close();
       //             if(ps!=null)
         //               ps.close();
                    if(ps2!=null)
                        ps2.close();
 //                   if(rs!=null)
   //                     rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerInfo_model.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         return rs;
    }
    public int setCustomerInfo(String name,Long phone,String address,String dob,String email,String IDProof) 
    {
        try {
            con=new AccessDatabase().getConnectionObject();
            System.out.println("Connetion got");
            ps2=con.prepareStatement("select cID from customer;");
            rs=ps2.executeQuery();
            if(rs.next())
            {
                do
                    {
                        newcID=rs.getInt(1);
                        System.out.println("newcID: "+ newcID);
                    }
                    while(rs.next());
            }
            else
                newcID=2017000;
            newcID++;                
            ps=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?)");
            ps.setInt(1, newcID);
            ps.setString(2, name);
            ps.setLong(3, phone);
            ps.setString(4,address);
            ps.setString(5, dob);
            ps.setString(6, email);
            ps.setString(7,IDProof);
            boolean res=ps.execute();
            System.out.println("Result: "+res);
            System.out.println("Added to Database");
            } catch (ClassNotFoundException | SQLException ex) {                
                Logger.getLogger(CustomerInfo_model.class.getName()).log(Level.SEVERE, null, ex);
                return -99;
            }
            finally
            {
                try {
                    if(con!=null)
                        con.close();
                    if(ps!=null)
                        ps.close();
                    if(ps2!=null)
                        ps2.close();
                    if(rs!=null)
                        rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerInfo_model.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return newcID;
        }
    
    public ResultSet getCustomerInfo(String name)
    {
        try {
            con=new AccessDatabase().getConnectionObject();
            System.out.println("Connetion got");
           
            ps=con.prepareStatement("select * from customer where cName like '%"+name+"%';");
            rs=ps.executeQuery();
            System.out.println("Retrived From database");
            
            
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CustomerInfo_model.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
               //     if(con!=null)
                //        con.close();
       //             if(ps!=null)
         //               ps.close();
                    if(ps2!=null)
                        ps2.close();
 //                   if(rs!=null)
   //                     rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerInfo_model.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         return rs;
    }
    
     public ResultSet getCustomerInfo(int id)
    {
        try {
            con=new AccessDatabase().getConnectionObject();
            System.out.println("Connetion got");
           
            ps=con.prepareStatement("select * from customer where cID like '%"+id+"%';");
            rs=ps.executeQuery();
            System.out.println("Retrived From database");
            
            
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CustomerInfo_model.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
               //     if(con!=null)
                //        con.close();
       //             if(ps!=null)
         //               ps.close();
                    if(ps2!=null)
                        ps2.close();
 //                   if(rs!=null)
   //                     rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerInfo_model.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         return rs;
    }
}
