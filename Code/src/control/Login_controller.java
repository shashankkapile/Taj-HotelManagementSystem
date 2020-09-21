/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import view.Login_view;
import model.*;
public class Login_controller 
{
    public Object temp(String ...a)
    {
        int flag=1;
        switch(flag)
        {
            case 1:
                return isAuthentic( "username", "Pass".toCharArray() );
            case 2:
                return getCheckOutInfo( 0);
        }
        return null;
    }
    Login_view view;
    public void startApplication() throws IOException 
    {
        this.view = new Login_view();
    }
    public boolean isAuthentic(String username, char[] password) 
    {
        Login_model model=new Login_model();
        return model.checkUserPass(username, password);
    }
    public int addNewCustomer(String name,Long phone,String address,String dob,String email,String IDProof)
    {
        CustomerInfo_model model=new CustomerInfo_model();
        return model.setCustomerInfo(name, phone, address, dob,email, IDProof);
    }
    public ResultSet getCustomerInfo()
    {
        CustomerInfo_model model=new CustomerInfo_model();
        ResultSet rs=model.getCustomerInfo();
        return rs;
    }
    
    public ResultSet getCustomerInfo(String name)
    {
        CustomerInfo_model model=new CustomerInfo_model();
        ResultSet rs=model.getCustomerInfo(name);
        return rs;
    }
    
    public ResultSet getCustomerInfo(int id)
    {
        CustomerInfo_model model=new CustomerInfo_model();
        ResultSet rs=model.getCustomerInfo(id);
        return rs;
    }
    
    public boolean isRoomAvailable(int roomNo)
    {
        RoomAvil_model model=new RoomAvil_model();
        
        return model.isRoomAvailable(roomNo);
    }

    public int getCostOfRoom(int roomNo) {
     RoomAvil_model model=new RoomAvil_model();
     return model.getCostOfRoom(roomNo);
     
    }

    public boolean checkIn(String time, int cID , int roomNo, int people, int days, boolean food, int advance) 
    {
        CheckInModel model=new CheckInModel();
        return model.checkIn(time, cID, roomNo, people, days, food, advance);
    }
    


    public Vector getCheckOutInfo(int roomNo) {
        CheckOut_model model=new CheckOut_model();
        return model.getCheckOutInfo(roomNo);

    }

    public boolean checkOut(String roomNo) {
        CheckOut_model model=new CheckOut_model();
        return model.CheckOut(roomNo);

    }

    @SuppressWarnings("UseOfObsoleteCollectionType")
    public ArrayList getCurrentRecords() {
        CheckOut_model model= new CheckOut_model();
        return model.getCurrentRecords();
    }
    @SuppressWarnings("UseOfObsoleteCollectionType")
    public ArrayList getOldRecords() {
        CheckOut_model model= new CheckOut_model();
        return model.getOldRecords();
    }

    
}
