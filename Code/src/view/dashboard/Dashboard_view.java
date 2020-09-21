/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dashboard;

import MainPackage.AccessDatabase;
import control.DemoClient;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import view.Login_view;

public class Dashboard_view extends JFrame implements ActionListener
{
    private JTabbedPane tabbedPane=new JTabbedPane();
    private JButton sout;
    private JLabel id;
    private String username;
    private JLabel welcome;
    private BufferedImage myPicture;
    private JLabel logo;
    private  DateFormat dateFormat;
    private Date d;
    JLabel date;
    checkOutTab_view checkOutTab;
    checkInTab_view checkInTab;
    custInfoTab_view custInfoTab;
    JScrollPane panelScroll;
    JPanel roomAvail;
    RecordsTab_view recordTab;
    
    public static void main(String[] args) throws IOException {
     //   new Dashboard_view().initDashboardR();      
    }

    public Dashboard_view()  throws IOException{
        JButton refresh=new JButton ("Refresh");
        add(refresh);
        refresh.setBounds(1220,100,100,50);
        refresh.addActionListener(this);
        
        this.myPicture = ImageIO.read(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\HotelManagement Combined\\src\\logo_2.png"));
        this.date = new JLabel();
        this.d = new Date();
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm ");   
        this.logo = new JLabel(new ImageIcon(myPicture));
        
        this.id = new JLabel();
        this.sout = new JButton("SignOut");
            roomAvail=new RoomAvailability().initRoomAvailablity();
      
    }
    
    public void loadMain() throws IOException{ 
        new Login_view();
    }
    public  void initDashboardR(String username) throws SQLException, ClassNotFoundException
    {
        this.username=username;
        setSize(1360,25);
        setLayout(null);
        setVisible(true);
        setSize(1360,725);
       
        
    
        welcome = new JLabel("WELCOME : "+new AccessDatabase().getEmployeeData(username));
        add(logo);
        logo.setBounds(0,10, 200,125);
        add(welcome);
      welcome.setFont(new Font("Lucida Grande",Font.BOLD,18));
        welcome.setBounds(850,80,300,100);
        add(sout);
        sout.setBounds(1220,25,100,50);
        sout.addActionListener(this);
       /* BufferedImage out1= ImageIO.read(new File("C:\\Users\\Student\\Documents\\NetBeansProjects\\shawshank\\src\\logout.png"));
        JLabel signout=new JLabel(new ImageIcon(out1));
        hml.add(signout);
        signout.setBounds(1200,125,100,100);*/
        
        id.setFont(new Font("Lucida Grande",Font.BOLD,18));
        //id.setText("ID :");
        add(id);
        id.setBounds(1150,80,300,100);
        date.setFont(new Font("Lucida Grande",Font.BOLD,16));
        date.setText("Login Time: "+dateFormat.format(d));
        date.setBounds(900,7,300,100);
        add(date);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setFont(new Font("Lucida Grande",Font.PLAIN,30));
        add(tabbedPane);
        
        setTabbedPane();
       // tabbedPane.addKeyListener(this);
  
    }

    public void setTabbedPane() throws SQLException, ClassNotFoundException 
    {
        checkInTab = new checkInTab_view();
        checkOutTab=new checkOutTab_view();
        custInfoTab=new custInfoTab_view();
        recordTab=new RecordsTab_view();
        panelScroll=new JScrollPane(custInfoTab, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       
        tabbedPane.setFont(new Font("Lucida Grande", Font.BOLD, 16));
      //  custInfoTab.setBounds(0, 0, 60, 70);
        // tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tabbedPane.setBounds(10, 130,1300,575);
        tabbedPane.addTab("Check In", checkInTab);
        tabbedPane.addTab("Room Availability", new RoomAvailability().initRoomAvailablity());
        tabbedPane.addTab("Check Out", checkOutTab);
        tabbedPane.addTab("Records", recordTab);
        tabbedPane.addTab("Add/Get Customer Info", panelScroll);
        if(username.equals("admin"))
            tabbedPane.addTab("Admin Control",new adminControlTab_view());
        
        tabbedPane.setSelectedIndex(5);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SignOut"))
       {
           this.dispose();
            try {
                
                loadMain();
            } catch (IOException ex) {
                Logger.getLogger(Dashboard_view.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
        else if(e.getActionCommand().equals("Refresh"))
        {
            int selectedIndexOfTabbedPane = tabbedPane.getSelectedIndex();
            tabbedPane.removeAll();
            try {
                setTabbedPane();
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard_view.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Dashboard_view.class.getName()).log(Level.SEVERE, null, ex);
            }
            tabbedPane.setSelectedIndex(selectedIndexOfTabbedPane);
            
        }
    }
   
}
