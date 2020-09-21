package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Vector;


public class DemoClient {
    
   
  int empID;
   String empName;
 
    public boolean sendToServer(String username, char[] pass) throws IOException {
        
        Socket s=new Socket("localhost",1997);  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        DataInputStream dis=new DataInputStream(s.getInputStream());  

        String password=new String(pass);
    
        dout.writeUTF(username);  
       dout.writeUTF(password); 

       empID=dis.readInt();
       empName=dis.readUTF();
     
        Vector v = new Vector();
        v.add(empID);
        v.add(empName);

    //System.out.println((String)v.elementAt(2));
       boolean yesOrNot=dis.readBoolean();
       
       System.out.println(empID+" "+ empName+" "+yesOrNot);
//     System.out.println(dis.readInt()+ dis.readUTF());
       
        dout.flush();  
        dout.close();  
        s.close();  
        
        
      if(yesOrNot)
      {
           return true;
      }
       
      else
      {
          return false;
      }
    }

  
    
}  
