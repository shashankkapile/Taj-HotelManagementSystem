
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoThread extends Thread {

    protected Socket socket;
    String username = null;
    String password = null;
 InputStream inp = null;
        DataInputStream dis = null;
        DataOutputStream out = null;
        Vector empData;
    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public void run() {
       
        try {

            dis = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            return;
        }

                try {

            username = (String) dis.readUTF();
            password = (String) dis.readUTF();
        } catch (IOException ex) {
            // Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (new HotelServer().checkCredentials(username, password)) {
                empData = new HotelServer().getEmployeeData(username);
//                dout.writeInt((int) empData.elementAt(0));
//                dout.writeUTF((String) empData.elementAt(1));
                out.writeInt((int) empData.elementAt(0));
                out.writeUTF((String) empData.elementAt(1));
                out.writeBoolean(true);
            } else {

                out.writeInt(0);
                out.writeUTF("");
                out.writeBoolean(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                  
            
        
    }
}
