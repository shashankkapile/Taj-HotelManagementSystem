package MainPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

public class AccessDatabase {

    String loginID = "root";
    String loginPass = "root";
    String conn_url = "jdbc:mysql://localhost:3306/hotel";
    Connection conn = null;
    String gotPassword = null;
    Statement stm = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    private void establishDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(conn_url, loginID, loginPass);
        if (!conn.isClosed()) {
            System.out.println("Database Connection Established");
        } else {
            System.out.println("Database  Connection could not Established");
        }
        stm = (Statement) conn.createStatement();

    }
     public Connection getConnectionObject() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(conn_url, loginID, loginPass);
        if (!conn.isClosed()) {
            System.out.println("Database Connection Established");
        } else {
            System.out.println("Database  Connection could not Established");
        }
        return conn;

    }

    private void closeDBConnection() throws SQLException {
        //closing the connection
        conn.close();
        //stm.close();
        //rs.close();
        if (conn.isClosed()) {
            System.out.println("Database Connection Closed");
        } else {
            System.out.println("Database Connection not Closed");
        }
    }

    public boolean checkCredentials(String username, char[] password) throws SQLException, ClassNotFoundException {
        establishDBConnection();

        rs = (ResultSet) stm.executeQuery("select password from emp where username=" + "'" + username + "'");
        while (rs.next()) {
            gotPassword = rs.getString(1);
            System.out.println("Checked Credentials");
        }

        closeDBConnection();

        //returning valid or invalid credentials status
        if (Arrays.equals(gotPassword.toCharArray(), password)) {
            return true;
        } //            else if(rs==null)
        //                 return false;
        else {
            return false;
        }
    }

    public String getEmployeeData(String username) throws SQLException, ClassNotFoundException {
        String name = null;
        int id;
        establishDBConnection();
        rs = (ResultSet) stm.executeQuery("select name,id from emp where username=" + "'" + username + "'");
        while (rs.next()) {
            name = rs.getString(1);
            //id=rs.getInt(2);
            System.out.println("Got Employee Data");
        }
        closeDBConnection();
        return (name);
    }

    public boolean loadRooms(int roomID) throws SQLException, ClassNotFoundException {
        establishDBConnection();
        ps = (PreparedStatement) conn.prepareStatement("select rAvail from room where rNo=?;");
        ps.setInt(1, roomID);
        rs = ps.executeQuery();
        rs.next();
        System.out.println("Rooms Fetched!");
        int isAvailable = rs.getInt(1);
        closeDBConnection();
        if (isAvailable == 1) {
            return true;
        } else {
            return false;
        }
        //  return ();
    }

    public boolean isHallAvailable(String rType) throws SQLException, ClassNotFoundException {
        establishDBConnection();
        ps = (PreparedStatement) conn.prepareStatement("select rAvail from room where rType=?");
        ps.setString(1, rType);
        rs = ps.executeQuery();
        rs.next();
        System.out.println("Checked Hall Availablibity ");
        int isAvailable = rs.getInt(1);
        closeDBConnection();
        if (isAvailable == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Vector isAvailable(String rType, boolean isAC) throws SQLException, ClassNotFoundException {
        int rNo;
        Vector rooms = new Vector();
        establishDBConnection();
        // System.out.println("isAC"+isAC);
        ps = (PreparedStatement) conn.prepareStatement("select rNo from room where rType=? and rAvail=true and rAC=?");
        ps.setString(1, rType);
        ps.setBoolean(2, isAC);
        rs = ps.executeQuery();
        while (rs.next()) {
            rNo = rs.getInt(1);
            // System.out.println(rNo);
            rooms.add(rNo);
        }
        System.out.println("Checked room Availablibity ");
        closeDBConnection();
        return rooms;

    }

    public void changeAdminPass(String text) throws SQLException, ClassNotFoundException {
       // System.out.println(text);
       establishDBConnection();
       conn.setAutoCommit(false);
       //rs = (ResultSet) stm.executeUpdate("update emp from emp where password=" + "'" + text + "'"+"where username='admin'");
       ps = (PreparedStatement) conn.prepareStatement("update emp set password=? where username=?");
       ps.setString(1, text);
       ps.setString(2, "admin");

       ps.executeUpdate();
       conn.commit();
       closeDBConnection();
    }

    public ResultSet getEmpData() throws SQLException, ClassNotFoundException
    {
      establishDBConnection();
        ps = (PreparedStatement) conn.prepareStatement("select * from emp");
        int id;
        String name,username,password,role;
        rs = ps.executeQuery();
      
           
     return rs;
    }

    public void addUser(String text, String text0, String text1, String text2) throws SQLException, ClassNotFoundException {
      establishDBConnection();
        conn.setAutoCommit(false);
       ps = (PreparedStatement) conn.prepareStatement("insert into emp values(null,?,?,?,?)");
        ps.setString(1, text);
        ps.setString(2, text0);
        ps.setString(3, text1);
        ps.setString(4, text2);
      
        ps.execute();
        conn.commit();
        closeDBConnection();
    }

    public void removeUser(String text) throws SQLException, ClassNotFoundException {
          establishDBConnection();
        conn.setAutoCommit(false);
       ps = (PreparedStatement) conn.prepareStatement("delete from emp where id=?");
        ps.setInt(1, Integer.parseInt(text));
       
        ps.execute();
        conn.commit();
        closeDBConnection();
    }

    public void changeUserPass(String text, String text0) throws SQLException, ClassNotFoundException {
        establishDBConnection();
        conn.setAutoCommit(false);
        ps = (PreparedStatement) conn.prepareStatement("update emp set password=? where id=?");
        ps.setString(1, text0);
        ps.setInt(2, Integer.parseInt(text));
       

        ps.executeUpdate();
        conn.commit();
        closeDBConnection();
    
}
}
