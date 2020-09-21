
import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class HotelServer {

    String loginID = "root";
    String loginPass = "root";
    String conn_url = "jdbc:mysql://localhost:3306/hotel";
    Connection conn = null;
    String gotPassword = null;
    Statement stm = null;
    ResultSet rs = null;
    private String username;
       static final int PORT = 1997;

    private void establishDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = (Connection) DriverManager.getConnection(conn_url, loginID, loginPass);
        if (!conn.isClosed()) {
            System.out.println("Database Connection Established");
        } else {
            System.out.println("Database  Connection could not Established");
        }
        stm = (Statement) conn.createStatement();
    }

    private void closeDBConnection() throws SQLException {
        //closing the connection
        conn.close();
        if (conn.isClosed()) {
            System.out.println("Database Connection Closed");
        } else {
            System.out.println("Database Connection not Closed");
        }
    }

    public boolean checkCredentials(String username, String password) throws SQLException, ClassNotFoundException {
        establishDBConnection();
        this.username = username;
        rs = (ResultSet) stm.executeQuery("select password from emp where username=" + "'" + username + "'");
        while (rs.next()) {
            gotPassword = rs.getString(1);
            System.out.println("Checked Credentials");
        }

        closeDBConnection();

        //returning valid or invalid credentials status
        if (gotPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public Vector getEmployeeData(String username) throws SQLException, ClassNotFoundException {
        String name = null;
        int id;
        Vector empData = new Vector();
        establishDBConnection();
        rs = (ResultSet) stm.executeQuery("select name,id from emp where username=" + "'" + username + "'");
        while (rs.next()) {

            empData.add(rs.getInt(2));
            empData.add(rs.getString(1));

            System.out.println("Got Employee Data");
        }
        closeDBConnection();
        return empData;
    }

  
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocketObject = null;
        Socket socketObject = null;//establishes connection   
     
          try {
            serverSocketObject = new ServerSocket(PORT);
           

          }catch (IOException e) {
              System.out.println(e);
            }
          
        while (true) {
            try {
                socketObject = serverSocketObject.accept();//establishes connection   
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
           
            new EchoThread(socketObject).start();
            
        


        }
    }
}
