

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;

public  class DashboardR  extends JFrame {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        DashboardR d=new DashboardR();
        d.initDashboardR("Hrishi");
    }

    JTabbedPane tabbedPane=new JTabbedPane();
//    public void loadMain() throws IOException
//    { 
//        HMLogin hml=new HMLogin();
//        
//    }
    public  void initDashboardR(String username) throws IOException, SQLException, ClassNotFoundException
    {
        Container c= this.getContentPane();
        c.setBackground(new Color(216,223,229));
        c.setLayout(null);
        setSize(1500,900);
        setVisible(true);
        setSize(1360,725);
        
        BufferedImage myPicture= ImageIO.read(new File("C:\\Users\\Hrishikesh\\Documents\\NetBeansProjects\\shawshank\\src\\logo_2.png"));
        JLabel logo=new JLabel(new ImageIcon(myPicture));
        add(logo);
        logo.setBounds(0,10, 200,125);
        JLabel w=new JLabel();
        w.setFont(new Font("Lucida Grande",Font.BOLD,16));
        //System.out.println(new AccessDatabase().getEmployeeData(username).EName);
        w.setText("Welcome: "+username);
        add(w);
        w.setBounds(50,125,300,100);

       /* BufferedImage out1= ImageIO.read(new File("C:\\Users\\Student\\Documents\\NetBeansProjects\\shawshank\\src\\logout.png"));
        JLabel signout=new JLabel(new ImageIcon(out1));
        hml.add(signout);
        signout.setBounds(1200,125,100,100);*/
        JButton sout=new JButton("SignOut");
        add(sout);
        sout.setBounds(1220,25,100,50);
         JLabel id=new JLabel();
        id.setFont(new Font("Lucida Grande",Font.BOLD,16));
        //id.setText("ID :"+new AccessDatabase().getEmployeeData(username).EID);
        add(id);
        id.setBounds(50,175,300,100);
        Date d=new Date();
        JLabel date=new JLabel();
        date.setFont(new Font("Lucida Grande",Font.BOLD,16));
        date.setText("Login Time: "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()+"  "+d.getDate()+"/"+d.getMonth()+"/"+d.getYear());
        date.setBounds(900,7,300,100);
        add(date);
        setVisible(true);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel CheckInPanel= new JPanel();
        //CheckInPanels.setBorder(new TitledBorder("Title"););

        CheckInPanel.setLayout(null);
       // tabbedPane.addKeyListener(this);
        tabbedPane.setFont(new Font("Lucida Grande",Font.BOLD,16));
       // tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tabbedPane.setBounds(300, 250,1500,700);
        tabbedPane.addTab("Check In",CheckInPanel);
        tabbedPane.addTab("Room Availability", new JPanel());
        tabbedPane.addTab("Check Out", new JPanel());
        tabbedPane.addTab("Records", new JPanel());
        tabbedPane.addTab("Customer Info", new JPanel());
         add(tabbedPane);
        //setFont(new Font("Lucida Grande",Font.PLAIN,30));
      JLabel panelWelcomeMessage= new JLabel("Enter Customer Details" );
      panelWelcomeMessage.setFont(new Font("Lucida Grande",Font.BOLD,25));
      CheckInPanel.add(panelWelcomeMessage);
      panelWelcomeMessage.setBounds(500,10,300,50);

      JLabel customerFirstName= new JLabel("First Name: ");
      customerFirstName.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerFirstName);
      customerFirstName.setBounds(20,80,150,50);
      JTextField customerFirstNameField=new JTextField();
      CheckInPanel.add(customerFirstNameField);
      customerFirstNameField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerFirstNameField.setBounds(250,90,155,28);

      JLabel customerMiddleName= new JLabel("Middle Name: ");
      customerMiddleName.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerMiddleName);
      customerMiddleName.setBounds(20,125,150,50);
      JTextField customerMiddleNameField=new JTextField();
      CheckInPanel.add(customerMiddleNameField);
      customerMiddleNameField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerMiddleNameField.setBounds(250,135,155,28);

      JLabel customerLastName= new JLabel("Last Name: ");
      customerLastName.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerLastName);
      customerLastName.setBounds(20,170,150,50);
      JTextField customerLastNameField=new JTextField();
      CheckInPanel.add(customerLastNameField);
      customerLastNameField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerLastNameField.setBounds(250,180,155,28);

      JLabel customerAge= new JLabel("Age: ");
      customerAge.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerAge);
      customerAge.setBounds(20,215,150,50);
      JTextField customerAgeField=new JTextField();
      CheckInPanel.add(customerAgeField);
      customerAgeField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerAgeField.setBounds(250,225,155,28);


      JLabel customerDOB= new JLabel("DOB: ");
      customerDOB.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerDOB);
      customerDOB.setBounds(20,260,150,50);
      JTextField customerDOBField=new JTextField();
      CheckInPanel.add(customerDOBField);
      customerDOBField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerDOBField.setBounds(250,270,155,28);

      JLabel customerPhone= new JLabel("Phone: ");
      customerPhone.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerPhone);
      customerPhone.setBounds(20,305,150,50);
      JTextField customerPhoneField=new JTextField();
      CheckInPanel.add(customerPhoneField);
      customerPhoneField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerPhoneField.setBounds(250,315,155,28);

      JLabel customerAddress= new JLabel("Address: ");
      customerAddress.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerAddress);
      customerAddress.setBounds(20,350,150,50);
      JTextField customerAddressField=new JTextField();
      CheckInPanel.add(customerAddressField);
      customerAddressField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerAddressField.setBounds(250,360,155,28);
      


      JLabel customerIDProof= new JLabel("ID Proof: ");
      customerIDProof.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerIDProof);
      customerIDProof.setBounds(20,395,150,50);
       JTextField customerIDProofField=new JTextField();
      CheckInPanel.add(customerIDProofField);
      customerIDProofField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerIDProofField.setBounds(250,405,155,28);

JLabel customerEmail= new JLabel("Email: ");
      customerEmail.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerEmail);
      customerEmail.setBounds(20,440,200,50);
      JTextField customerEmailField=new JTextField();
      CheckInPanel.add(customerEmailField);
      customerEmailField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerEmailField.setBounds(250,450,155,28);

     
     
       JLabel customerNumberOfPeople= new JLabel("Number Of People: ");
      customerNumberOfPeople.setFont(new Font("Lucida Grande",Font.BOLD,20));
      CheckInPanel.add(customerNumberOfPeople);
      customerNumberOfPeople.setBounds(20,485,200,50);
      JTextField customerNumberOfPeopleField=new JTextField();
      CheckInPanel.add(customerNumberOfPeopleField);
      customerNumberOfPeopleField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      customerNumberOfPeopleField.setBounds(250,495,155,28);

     
    
       
    
       //JTextArea customerAddressField=new JTextArea();
     // CheckInPanel.add(customerAddressField);
     // customerAddressField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      //customerAddressField.setBounds(250,360,155,75);
      /*usernameField=new JTextField();
      this.add(usernameField);
      usernameField.setText("admin");
     // usernameField.setFocusable(true)
      usernameField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      usernameField.setBounds(650,350,150,25);

      JLabel passwordLabel= new JLabel("Password: ");
      this.add(passwordLabel);
      passwordLabel.setBounds(550,400,150,50);
      passwordField = new JPasswordField();
      this.add(passwordField);
      passwordField.setText("admin");
      passwordField.setBounds(650,412,150,25);

      
      JButton submitButton = new JButton("Submit");
      submitButton.setFont(new Font("Lucida Grande",Font.BOLD,16));
      submitButton.setBackground(Color.LIGHT_GRAY);
      this.add(submitButton);
      submitButton.setBounds(580,500,100,40);
      submitButton.addActionListener(this);
      
      JButton cancelButton = new JButton("Cancel");
      cancelButton.setFont(new Font("Lucida Grande",Font.BOLD,16));
      cancelButton.setBackground(Color.LIGHT_GRAY);
      this.add(cancelButton);
      cancelButton.setBounds(700,500,100,40);
      cancelButton.addActionListener(this);*/
    }
//
//    public void actionPerformed(ActionEvent e) {
//       if(e.getActionCommand().equals("SignOut"))
//       {
//           this.dispose();
//            try {
//               // loadMain();
//            } catch (IOException ex) {
//                Logger.getLogger(DashboardR.class.getName()).log(Level.SEVERE, null, ex);
//            }
//       }
//    }
   
}



