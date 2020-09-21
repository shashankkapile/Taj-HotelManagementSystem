/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dashboard;

import MainPackage.AccessDatabase;
import control.Login_controller;
import customCoponents.CustButton;
import customCoponents.CustLabel;
import customCoponents.CustTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.CustomerInfo_model;


public class checkInTab_view extends JPanel implements ActionListener,KeyListener,MouseListener
{
     public static void main(String[] args) {
        checkInTab_view c=new checkInTab_view();
        JFrame frm=new JFrame();
        frm.setLayout(null);
        frm.setVisible(true);
        frm.setBounds(50, 60, 1200, 700);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frm.add(c);
    }
 JPanel showRooms = new JPanel();
    JLabel selectedOrNot=new JLabel();
    JComboBox roomType1, conditionType,roomType2,roomNumbers;
    String[] nonACRoomType = {"Select", "Non-Deluxe","Deluxe"};
    String[] sampleConditionType = {"Select","AC", "Non-AC"};
    String[] ACRoomType = {"Select Type","Hall", "Non-Deluxe", "Taj Premium"};
    String[] roomArray;
    String roomCondition, roomType;
    Vector rooms = new Vector();
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    Login_controller controller =new Login_controller();
    JLabel id;
    JLabel name;
    JRadioButton currentTime,customeTime;
    JLabel people;
    JLabel days;
    JLabel adv;
    JLabel cost;
    
    JTextField cID;
    JTextField cName;
    JTextField time;
    JTextField noOfPeople;
    JTextField noOfDays;
    JTextField advance;
    JTextField roomCost;
    
    JButton checkIn;
   
    int x=20,width=190,height=25,y=-10,span=45;
    
    JScrollPane tableScroll;
    JTable custInfoTable;
    DefaultTableModel custModel;
    DefaultTableModel rateModel;
//    JButton refresh;
    JTable rateTable;
    JLabel room;
    JTextField roomNo;
    JCheckBox food;
    int selectedCost;
    Integer minAdvance;
    
    public void createTable()
    {
//        refresh=new CustButton("Refresh", 400, 40, 125, 25, this);
        Object [][]custData={};
        String []custColumnName={"ID","Name","Phone","Address","DOB","Email","ID Proof"};
        custInfoTable=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        custModel=new DefaultTableModel(custData, custColumnName);
        
        custInfoTable.setModel(custModel);
        custInfoTable.addMouseListener( this);
        tableScroll=new JScrollPane(custInfoTable);
        tableScroll.setBounds(350,40,500, 350);
        
        rateTable =new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object [][]rateData={};
        String []rateColumnName={"Room Type","Rate"};
        rateModel=new DefaultTableModel(rateData,rateColumnName);
        rateTable.setModel(rateModel);
        rateTable.setBounds(925, 80, 225, 300);
        
        ResultSet rs=null;
        
            rs=controller.getCustomerInfo();
        if(rs!=null)
            loadCustomerDetails(rs);  
        
    }
    int incY()
    {
        return y=y+span;
    }
    public checkInTab_view()
    {
        setBounds(0, 0, 1800, 700);
        setLayout(null);
        id=new CustLabel("Customer ID", x, incY(), width, height);
        name =new CustLabel(" Name",x,incY(),width,height);
        name.setAlignmentX(RIGHT_ALIGNMENT);
        currentTime=new JRadioButton("Current Time", true);
        currentTime.setBounds(x, incY(), 110, height);
        customeTime=new JRadioButton("Custome Time", false);
        customeTime.setBounds(x, incY(), 110, height);
        people=new CustLabel("No of people", x, incY(), width, height);
        room=new CustLabel("Room No", x, incY(), width, height);
        days=new CustLabel("No of days", x, incY(), width, height);
        cost=new CustLabel("Room Cost", x, incY(), width, height);
        adv=new CustLabel("Advance", x, incY(), width, height);
        
        food=new JCheckBox("Ammenities (Rs.500 per day)");
        food.setBounds(x, incY(), width+60, height);
        food.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        
        this.y=35;  this.x=140;
        cID=new CustTextField(x, y, width, height);
        cName=new CustTextField(x, incY(), width, height);
        
       time=new CustTextField(x, incY(), width, height);
       time.setEnabled(false);
       time.setText(dateFormat.format(date));  
        incY();
       noOfPeople=new CustTextField(x, incY(), width, height);
       roomNo=new CustTextField(x, incY(), width, height);
       roomNo.setText("<Select>");
       roomNo.setEnabled(false);
       noOfDays=new CustTextField(x, incY(), width, height);
       roomCost=new CustTextField(x, incY(), width, height);
       roomCost.setEnabled(false);
       advance=new CustTextField(x, incY(), width, height);
       
        width=65;
       
        
        x=x+70;
        width=55;
        
        width=190;
        x=140;
        incY();
        checkIn = new CustButton("Check In",x,incY(),width,height);
        checkIn.addActionListener(this);
        cID.addKeyListener(this);
        cName.addKeyListener(this);
        currentTime.addActionListener(this);
        customeTime.addActionListener(this);
        noOfPeople.addKeyListener(this);
        noOfDays.addKeyListener(this);
        addKeyListener(this);
        createTable();
        intiComponents();
        //---------------------------------------------
        JLabel roomTypeLabel = new JLabel("Room Type: ");
        
        roomTypeLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        add(roomTypeLabel);
        roomTypeLabel.setBounds(875, 40, 300, 40);
       
        conditionType = new JComboBox(sampleConditionType);
        conditionType.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        conditionType.setBounds(875,80,150,30);
        add(conditionType);
        roomType1 = new JComboBox(ACRoomType);
        roomType2 = new JComboBox(nonACRoomType);
        roomNumbers=new JComboBox();
        roomType1.addActionListener(this);
        roomType2.addActionListener(this);
        conditionType.addActionListener(this);
        roomNumbers.addActionListener(this);
//        showRooms.setBounds(875, 150, 300, 500);
//        CheckInPanel.add(showRooms);

//        
    }
    
    //-------------------------------------------------------------------
    
    public void printAvailble()
    {
        
                            selectedOrNot.setForeground(Color.GREEN);
                            selectedOrNot.setFont(new Font("Lucida Grande", Font.BOLD, 20));
                            selectedOrNot.setBounds(875, 200, 300, 40);
                            selectedOrNot.setText("✔Selected!");
                            add(selectedOrNot);
    }
    public void printNotAvailable()
    {
                            selectedOrNot.setForeground(Color.RED);
                            selectedOrNot.setFont(new Font("Lucida Grande", Font.BOLD, 20));
                            selectedOrNot.setBounds(875, 200, 300, 40);
                            selectedOrNot.setText("✘Not Available!");
                            add(selectedOrNot);
    }

    //-------------------------------------------------------------------
    public void intiComponents()
    {
        add(id);
        add(name);add(cName);
        add(cID);
        add(currentTime);
        add(customeTime);
        add(time);
        add(people);
        add(adv);
        add(days);
        add(cost);
        
        add(noOfPeople);
        add(checkIn);
//        add(refresh);
        add(tableScroll);
        add(room);
        add(roomNo);
        add(food);
        add(advance);
        add(roomCost);
        add(noOfDays);
        
       // add(rateTable);
    }
    
    
    public void loadCustomerDetails( ResultSet rs)
    {
        while(custInfoTable.getRowCount()>0)
            custModel.removeRow(0);
        
        try {
            Long id,phone;
            String name,add,idProof,dob,email;
            while(rs.next())
            {
                id=rs.getLong(1);
                name=rs.getString(2);
                phone=rs.getLong(3);
                add=rs.getString(4);
                dob=rs.getDate(5).toString();
                email=rs.getString(6);
                idProof=rs.getString(7);
                custModel.addRow(new Object[]{id,name,phone,add,dob,email,idProof});
            }
        } catch (SQLException ex) {
            Logger.getLogger(custInfoTab_view.class.getName()).log(Level.SEVERE, null, ex);
        }  finally
            {
                try {
                    if(rs!=null)
                        rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerInfo_model.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("view.dashboard.checkInTab_view.actionPerformed()");
        if(e.getSource().equals(checkIn))
        {
            checkInAction();
            ResultSet rs=null;
            rs=controller.getCustomerInfo();
            System.out.println("view.dashboard.checkInTab_view.actionPerformed()");
            loadCustomerDetails(rs);
        }
//        else if(e.getSource().equals(refresh))
//        {
//            ResultSet rs=null;
//            rs=controller.getCustomerInfo();
//            loadCustomerDetails(rs);
//        }
        else if(e.getSource().equals(currentTime))
        {
            customeTime.setSelected(false);
            currentTime.setSelected(true);
            time.setEnabled(false);
            time.setText(dateFormat.format(date));  
        }
        else if(e.getSource().equals(customeTime))
        {                                   
        currentTime.setSelected(false);
        customeTime.setSelected(true);
        time.setEnabled(true);
        }
        //------------------------------------------------------
        if (e.getSource().equals(conditionType)) {

            if ((conditionType.getSelectedItem()).equals("AC")) {

                remove(selectedOrNot);
                roomNumbers.removeAllItems();
                remove(roomNumbers);
                remove(roomType2);
                roomType1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
                roomType1.setBounds(875, 120, 150, 30);
                add(roomType1);

            } else if ((conditionType.getSelectedItem()).equals("Non-AC")) {
                remove(selectedOrNot);
                remove(roomType1);
                roomNumbers.removeAllItems();
                remove(roomNumbers);
                roomType2.setFont(new Font("Lucida Grande", Font.BOLD, 16));
                roomType2.setBounds(875, 120, 150, 30);
                add(roomType2);

            } else if ((conditionType.getSelectedItem()).equals("Select")) {
                remove(roomType1);
                remove(roomType2);
                remove(selectedOrNot);
                remove(roomNumbers);
            }

        } 
        if (e.getSource().equals(roomType1)) {
           
            if (!roomType1.getSelectedItem().equals("Select")) {
                //System.out.println(roomType1.getSelectedItem());
                if (roomType1.getSelectedItem().equals("Hall")) {
                    try {
                        if (new AccessDatabase().isHallAvailable("Hall")) {
                            selectedCost=controller.getCostOfRoom(100);
                            roomNo.setText("100");
                            if(!noOfDays.getText().equals(""))
                            {
                                Integer res=selectedCost*Integer.parseInt(noOfDays.getText());
                                roomCost.setText(res.toString());
                                minAdvance=(Integer)res*25/100;
                                advance.setText(minAdvance.toString());
                                
                            }
                            printAvailble();
                        } else {
                            printNotAvailable();
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    remove(roomNumbers);
                } else if (roomType1.getSelectedItem().equals("Non-Deluxe")) {
                    roomNumbers.removeAllItems();
                    remove(selectedOrNot);
                    try {
                        rooms = new AccessDatabase().isAvailable(roomType1.getSelectedItem().toString(), true);
                        if (rooms.isEmpty()) {
                            printNotAvailable();
                        } else {


                            roomNumbers.addItem("Select Room ");

                            for (Object room : rooms) {
                                roomNumbers.addItem(room + "");
                                roomNumbers.setFont(new Font("Lucida Grande", Font.BOLD, 16));
                                roomNumbers.setBounds(875, 160, 150, 30);
                                add(roomNumbers);
                            }


                            //  System.out.println(roomArray[0]);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (roomType1.getSelectedItem().equals("Taj Premium")) {
                    roomNumbers.removeAllItems();
                    remove(selectedOrNot);
                    try {
                        rooms = new AccessDatabase().isAvailable("Deluxe", true);
                        if (rooms.isEmpty()) {
                            printNotAvailable();
                        } else {


                            roomNumbers.addItem("Select Room ");

                            for (Object room : rooms) {
                                roomNumbers.addItem(room + "");
                                roomNumbers.setFont(new Font("Lucida Grande", Font.BOLD, 16));
                                roomNumbers.setBounds(875, 160, 150, 30);
                                add(roomNumbers);
                            }


                            //  System.out.println(roomArray[0]);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } 
        if(e.getSource().equals(roomType2))  {
            System.out.println("Event 1");
            roomNumbers.removeAllItems();
            remove(roomNumbers);
           if (!roomType2.getSelectedItem().equals("Select")) {
                //System.out.println(roomType1.getSelectedItem());
               if (roomType2.getSelectedItem().equals("Non-Deluxe")) {
                   
                    roomNumbers.removeAllItems();
                    remove(selectedOrNot);
                  
                    try {
                        rooms = new AccessDatabase().isAvailable("Non-Deluxe", false);
                        if (rooms.isEmpty()) {
                            printNotAvailable();
                        } else {


                            roomNumbers.addItem("Select Room ");

                            for (Object room : rooms) {
                                roomNumbers.addItem(room + "");
                                roomNumbers.setFont(new Font("Lucida Grande", Font.BOLD, 16));
                                roomNumbers.setBounds(875, 160, 150, 30);
                                add(roomNumbers);
                            }


                            //  System.out.println(roomArray[0]);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (roomType2.getSelectedItem().equals("Deluxe")) {
                    roomNumbers.removeAllItems();
                    remove(selectedOrNot);
                    try {
                        rooms = new AccessDatabase().isAvailable("Deluxe", false);
                        if (rooms.isEmpty()) {
                            printNotAvailable();
                        } else {


                            roomNumbers.addItem("Select Room ");

                            for (Object room : rooms) {
                                roomNumbers.addItem(room + "");
                                roomNumbers.setFont(new Font("Lucida Grande", Font.BOLD, 16));
                                roomNumbers.setBounds(875, 160, 150, 30);
                                add(roomNumbers);
                            }


                            //  System.out.println(roomArray[0]);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(checkInTab_view.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        if(e.getSource().equals(roomNumbers)) {
            try
                {
            if(!roomNumbers.getSelectedItem().equals("Select Room"))
            {
                roomNo.setText(roomNumbers.getSelectedItem().toString());
                
                    selectedCost=controller.getCostOfRoom(Integer.parseInt(roomNo.getText()));
                
                if(!noOfDays.getText().equals(""))
                            {
                                Integer res=selectedCost*Integer.parseInt(noOfDays.getText());
                                roomCost.setText(res.toString());
                                minAdvance=res*25/100;
                                advance.setText(minAdvance.toString());
                                
                            }
                printAvailble();
            }
            else
               remove(selectedOrNot);
                }catch(NumberFormatException  nfe){}
            catch(NullPointerException  nfe){}
        }
        
        //------------------------------------------------------
    }
    public boolean validateChekIn()
    {
        if(cID.getText().isEmpty()||noOfPeople.getText().isEmpty()||roomNo.getText().equals("<Select>")
                ||noOfDays.getText().isEmpty())
        {
                 JOptionPane.showMessageDialog(this, "Enter all Fields");
            return false;
        }
        if(Integer.parseInt(advance.getText())<minAdvance)
        {
            JOptionPane.showMessageDialog(this, "Must pay atleast 25% advance!!");
            return false;
        }
        else
            return true;
    }
    public void checkInAction()
    {
       validateChekIn();
       boolean res=controller.checkIn(time.getText(),Integer.parseInt(cID.getText()),Integer.parseInt(roomNo.getText()),Integer.parseInt(noOfPeople.getText()),Integer.parseInt(noOfDays.getText()),food.isSelected(),Integer.parseInt(advance.getText()));
       if(!res)
            JOptionPane.showMessageDialog(this, "Check In Failed!!");
       else
            JOptionPane.showMessageDialog(this, "Check In SuccessFull!!");
           
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("Key: "+c);
        if(e.getSource().equals(cID)||e.getSource().equals(noOfPeople)||e.getSource().equals(noOfDays))
            if (!((c >= '0') && (c <= '9') ||(c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE))) 
                e.consume();
        if(e.getSource().equals(cID))
        {
            if (!((c >= '0') && (c <= '9') ||(c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE))) 
                e.consume();
            else // if(((c>='0')||(c<='9')))
            {
                String s="";
                ResultSet rs=null;
                
                if(!((c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE)))
                    s=cID.getText()+e.getKeyChar();
                else
                    s=cID.getText();
                
                if(!s.equals("")&&!s.isEmpty())
                    rs=controller.getCustomerInfo(Integer.parseInt(s));
                if(rs!=null)
                loadCustomerDetails(rs);                
            }
        }
        if(e.getSource().equals(noOfDays))
        {
             if (!((c >= '0') && (c <= '9') ||(c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE))) 
                e.consume();
            else
            {
                String s="";
                if(!((c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE)))
                    s=noOfDays.getText()+e.getKeyChar();
                else 
                    s=noOfDays.getText();
                if(!s.equals("")&&!roomNo.getText().equals("<Select>"))
                {
                    Integer res=Integer.parseInt(s)*controller.getCostOfRoom(Integer.parseInt(roomNo.getText()));
                    roomCost.setText(res.toString());
                    minAdvance=res*25/100;
                    advance.setText(minAdvance.toString());
                    
                }
            }
        }
        if(e.getSource().equals(cName))
        {
            if (!((c=='\'')||(c==' ')||(c >= 'A') && (c <= 'Z') ||(c >= 'a') && (c <= 'z') ||(c == KeyEvent.VK_BACK_SPACE)||(c == KeyEvent.VK_DELETE))) 
                e.consume();
            
            else
            {
                String s="";
                ResultSet rs=null;
                if(!((c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE)))
                    s=cName.getText()+e.getKeyChar();
                else 
                    s=cName.getText();
                if(!s.equals(""))
                    rs=controller.getCustomerInfo(s);
                if(rs!=null)
                loadCustomerDetails(rs);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row=custInfoTable.getSelectedRow();
        cID.setText(custInfoTable.getModel().getValueAt(row, 0).toString());
        cName.setText(custInfoTable.getModel().getValueAt(row, 1).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
