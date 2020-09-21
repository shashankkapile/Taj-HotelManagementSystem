/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import view.Login_view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login_model 
{
    
    public boolean checkUserPass( String username, char [] password) 
    {
         //  String username;
      
         //  username=usernameField.getText();
           Connection con=null;
           ResultSet rs=null;
           PreparedStatement ps=null;
           
           try
           {
               con=new MainPackage.AccessDatabase().getConnectionObject();
               System.out.println("Connected to Datebase!!");
               ps=con.prepareStatement("select pass from user where uID=?");
               ps.setString(1, username);
               rs=ps.executeQuery();
               if(rs.next())
               {
                   //System.out.println("Pass: "+ rs.getString(1));
                   //System.out.println("Pass: "+passwordField.getPassword());
                   //System.out.println("Result: "+ (passwordField.getPassword()).equals(rs.getString(1)));
                   //if(passwordField.getPassword().equals(rs.getString(1)))
                   if(Arrays.equals(password, rs.getString(1).toCharArray()))
                   {
                 //      System.out.println("Correct");
                      return true;
                   }
                   else
                   {
                    return false;
                   }
               }
               else
               {
                   return false;
               }   
           } catch (ClassNotFoundException | SQLException ex) {   
               Logger.getLogger(Login_view.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally
           {
               try{
                   if(con!=null)
                       con.close();
                   if(rs!=null)
                       rs.close();
                   if(ps!=null)
                       ps.close();
               } catch (SQLException ex) {
                   Logger.getLogger(Login_view.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        return false;
    }
}
