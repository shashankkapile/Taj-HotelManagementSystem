/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dashboard;
import control.Login_controller;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import customCoponents.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import model.CustomerInfo_model;

public final class custInfoTab_view extends JPanel implements ActionListener,ItemListener,KeyListener
        
{
    public static void main(String[] args) {
        custInfoTab_view c=new custInfoTab_view();
        JFrame frm=new JFrame();
        frm.setLayout(null);
        frm.setVisible(true);
        frm.setBounds(50, 60, 1200, 700);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frm.add(c);
    }
    Login_controller controller =new Login_controller();
    //JLabel mandatory;
    JLabel firstName;
    JLabel middleName;
    JLabel lastName;
    JLabel phone;
    JLabel email;
    JLabel add;
    JLabel dob;
    JLabel idproof;
    JTextField cFirstName;
    JTextField cMiddleName;
    JTextField cLastName;
    JTextField cPhone;
    JTextArea cAdd;
    JScrollPane addressScroll;
    JTextField cDOB;
    JTextField cIDProof;
    JTextField cEmail;
    JButton submit;
    Calendar cal;
    Date BirthDate;
    JComboBox date31,date30,date29,date28;
    JComboBox month;
    JComboBox year;
    JComboBox IDType;
    String monthArray[];
    String dateArray31[];
    String dateArray30[];
    String dateArray29[];
    String dateArray28[];
    String yearArray[];
    String IDTypeArray[];
    int x=20,width=190,height=25,y=-20,span=45;
    
    JScrollPane tableScroll;
    JTable custInfoTable;
    JLabel search;
    JTextField serchText;
    DefaultTableModel model;
    JButton refresh;
    
    public void createTable()
    {
        refresh=new CustButton("Refresh", 400, 20, 125, 25, this);
        Object [][]data={};
        String []columnName={"ID","Name","Phone","Address","DOB","Email","ID Proof"};
        custInfoTable=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model=new DefaultTableModel(data, columnName);
        custInfoTable.setModel(model);
        tableScroll=new JScrollPane(custInfoTable);
        tableScroll.setBounds(400, 60,600, 400);
    //    custInfoTable.setEnabled(false);
    }
    int incY()
    {
        return y=y+span;
    }
    public custInfoTab_view()
    {
//        setBorder(BorderFactory.createTitledBorder(this.getBorder(), "Customer Info", 2, 0, new Font("Lucida Grande",Font.BOLD,16)));
        setBounds(0, 0, 1800, 700);
        setLayout(null);
        cal = Calendar.getInstance();
//        mandatory = new CustLabel("Mandatory",x,incY(),width,height);
//        mandatory.setFont(new Font(TOOL_TIP_TEXT_KEY, Font.ITALIC, 12));
//        mandatory.setVisible(false);
        firstName = new CustLabel("First Name",x,incY(),width,height);
        middleName = new CustLabel("Middle Name",x,incY(),width,height);
        lastName = new CustLabel("Last Name",x,incY(),width,height);
        dob = new CustLabel("DOB",x,incY(),width,height);
        add = new CustLabel("Address",x,incY(),width,height);incY();
        phone = new CustLabel("Phone",x,incY(),width,height);
        email=new CustLabel("E-Mail", x, incY(), width, height);
        idproof = new CustLabel("ID Proof",x,incY(),width,height);
        this.y=-20;  this.x=140;
        cFirstName=new CustTextField(x, incY(), width, height);
        cMiddleName=new CustTextField(x, incY(), width, height);;
        cLastName=new CustTextField(x, incY(), width, height);
        incY();
        initArrays();
        width=65;
        year=new CustComboBox().getComboBox(yearArray,x,y, width, height);
        x=x+70;
        month=new CustComboBox().getComboBox(monthArray,x, y, width, height);
        month.setEnabled(false);
        x=x+70;
        width=55;
        date31=new CustComboBox().getComboBox(dateArray31,x, y, width, height);
        date31.setEnabled(false);
        date30=new CustComboBox().getComboBox(dateArray30,x, y, width, height);
        date30.setVisible(false);
        date29=new CustComboBox().getComboBox(dateArray29,x, y, width, height);
        date29.setVisible(false);
        date28=new CustComboBox().getComboBox(dateArray28,x, y, width, height);
        date28.setVisible(false);
        width=190;
        x=140;
        cAdd=new  JTextArea(6,30);
        addressScroll=new JScrollPane(cAdd, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        addressScroll.setBounds(x, incY(), width, height+40);
        cAdd.setLineWrap(true);
        cAdd.setFont(new Font("Lucida Grande",Font.PLAIN,14));
      ///  cAdd.setBounds(x, incY(), width, height+40);
        incY();
        cPhone=new CustTextField(x, incY(), width, height);
        cEmail=new CustTextField(x, incY(), width, height);
        IDTypeArray=new String[]{"<IDType>","Aadhar","PAN","Passport"};
        IDType= new CustComboBox().getComboBox(IDTypeArray,x, incY(), 80, height);
        
        cIDProof=new CustTextField(x+90, y, width-90, height);
        cIDProof.setEnabled(false);
        submit = new CustButton("Submit",x,incY(),width,height);
        submit.addActionListener(this);
        month.addItemListener(this);
        year.addItemListener(this);
        IDType.addItemListener(this);
        cFirstName.addKeyListener(this);
        cMiddleName.addKeyListener(this);
        cLastName.addKeyListener(this);
        cPhone.addKeyListener(this);
        createTable();
        intiComponents();
    }
    public void intiComponents()
    {
//        mandatory.setVisible(true);
//        mandatory.setForeground(Color.red);
//        add(mandatory);
        add(firstName); add(cFirstName);
        add(middleName);add(cMiddleName);
        add(lastName);add(cLastName);
        add(phone); add(cPhone);
        add(add);   add(addressScroll);
        add(email); add(cEmail);
        add(dob);  add(year);  add(month);add(date31);add(date30); add(date29); add(date28);
        add(idproof);  add(IDType); add(cIDProof);    
        add(submit);
       // add(refresh);
        add(tableScroll);
        loadCustomerDetails();
    }
    public void loadCustomerDetails()
    {
        
        while(custInfoTable.getRowCount()>0)
        {
            System.out.println("Removed: ");
            model.removeRow(0);
        }
        
        ResultSet rs=null;
        try {
            rs=controller.getCustomerInfo();
            System.out.println("Res: "+rs);
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
                model.addRow(new Object[]{id,name,phone,add,dob,email,idProof});
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
    private void initArrays()
    {
        this.yearArray = new String[88];
        this.dateArray28 = new String[29];
        this.dateArray29 = new String[30];
        this.dateArray30 = new String[31];
        this.dateArray31 = new String[32];
        this.monthArray = new String[]{"<Month>", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
        dateArray31[0]=dateArray28[0]=dateArray30[0]=dateArray29[0]="<Date>";
        yearArray[0]="<Year>";
        Integer temp;
        for (Integer i = 1; i < 32; i++)
            dateArray31[i]=i.toString();
        for (Integer i = 1; i < 31; i++)
            dateArray30[i]=i.toString();
        for (Integer i = 1; i < 30; i++)
            dateArray29[i]=i.toString();
        for (Integer i = 1; i < 29; i++)
            dateArray28[i]=i.toString();
        for (Integer i = 1; i < 88; i++)
        {
            temp=i+1929;
            yearArray[i]=temp.toString();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(submit))
        {
            submitAction();
            loadCustomerDetails();
        }
        if(e.getSource().equals(refresh));
        {
            loadCustomerDetails();
        }
    }
    public void submitAction()
    {
        if(validateData())
        {
            int newID=controller.addNewCustomer(cFirstName.getText()+" "+cMiddleName.getText()+" "+cLastName.getText(), Long.parseLong(cPhone.getText()), cAdd.getText(),  getDOB(),cEmail.getText(),IDType.getSelectedItem()+cIDProof.getText());
            if(newID!=-99)
                    JOptionPane.showMessageDialog(this, "Succeessfuly added new custtomer info. New Customer Id is :"+newID);
            else
                JOptionPane.showMessageDialog(this, "Problem in adding new customer!!","Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    private boolean validateData() 
    {
        System.out.println("Validating");
        StringBuilder s=new StringBuilder("");
        if(cFirstName.getText().isEmpty()||cMiddleName.getText().isEmpty()||cLastName.getText().isEmpty()||
                cPhone.getText().isEmpty()||cAdd.getText().isEmpty()||cEmail.getText().isEmpty()||
                getSelectedDate()==0 || month.getSelectedIndex()==0||year.getSelectedIndex()==0||
                cIDProof.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Enter All Fields", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
     
    private int getSelectedDate()
    {
        int visibleDate=31;
        if(date31.isVisible())
            visibleDate=31;
        if(date30.isVisible())
            visibleDate=30;
        if(date29.isVisible())
            visibleDate=29;
        if(date28.isVisible())
            visibleDate=28;
        switch(visibleDate)
        {
            case 28:
                return date28.getSelectedIndex();
            case 29:
                return date29.getSelectedIndex();
            case 30:
                return date30.getSelectedIndex();
            case 31:
                return date31.getSelectedIndex();
        }
        return 0;
    }

    private String getDOB() 
    {
        String yr=year.getSelectedItem().toString();
        Integer mon=month.getSelectedIndex();
        Integer dt=getSelectedDate();
        return yr+"-"+mon.toString()+"-"+dt.toString();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("Source: "+e.getItem());
        if(e.getSource().equals(IDType))
        {
            if(IDType.getSelectedIndex()!=0)
            {
                cIDProof.setText("");
                cIDProof.setEnabled(true);
            }
            else
            {
                cIDProof.setText("");
                cIDProof.setEnabled(false);
            }
        }
        if(e.getSource().equals(year)){
            if(year.getSelectedIndex()!=0)
            {
                month.setSelectedIndex(0);
                month.setEnabled(true);
            }
            else{
                month.setSelectedIndex(0);
                month.setEnabled(false);
            }
        }
        if(e.getSource().equals(month)){
            System.out.println("Mon: "+month.getSelectedItem());
            if(month.getSelectedIndex()==0){
                date31.setVisible(true);
                date31.setEnabled(false);
                date29.setVisible(false);
                date30.setVisible(false);
                date28.setVisible(false);
            }
            else{
                int index=month.getSelectedIndex();
                switch(index)
                {
                    case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                        date31.setEnabled(true);
                        date30.setVisible(false);
                        date28.setVisible(false);
                        date29.setVisible(false);
                        date31.setVisible(true);
                        break;
                    case 2:
                        int selectedYear=Integer.parseInt(year.getSelectedItem().toString());
                        date29.setVisible(false);
                        date28.setVisible(false);
                        if(selectedYear%4==0)
                            date29.setVisible(true);
                        else
                            date28.setVisible(true);
                        
                        date30.setVisible(false);
                        date31.setVisible(false);
                        break;
                    case 4:case 6:case 9:case 11:
                        date28.setVisible(false);
                        date29.setVisible(false);
                        date31.setVisible(false);
                        date30.setVisible(true);
                        date30.setEnabled(true);
                        break;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(e.getSource().equals(cPhone))
            if (!((c >= '0') && (c <= '9') ||(c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE))) 
                e.consume();
        if(e.getSource().equals(cFirstName)||e.getSource().equals(cMiddleName)||e.getSource().equals(cLastName))
            if (!((c=='\'')||(c==' ')||(c >= 'A') && (c <= 'Z') ||(c >= 'a') && (c <= 'z') ||(c == KeyEvent.VK_BACK_SPACE) |(c == KeyEvent.VK_DELETE))) 
                e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}