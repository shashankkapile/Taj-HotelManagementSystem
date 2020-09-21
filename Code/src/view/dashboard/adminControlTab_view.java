
package view.dashboard;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.runtime.JSType.isNumber;

/**
 *
 * @author ShawShank
 */
class adminControlTab_view extends JPanel implements ActionListener{
    private Object username;
 
    
    
    JLabel changePassword = new JLabel("Change Admin Password: ");
    JLabel typePassword = new JLabel("Type Password: ");
        JLabel typePassword2 = new JLabel("Type Password: ");

    JTextField passwordField1 = new JTextField();
    JTextField passwordField2 = new JTextField();
    JLabel reTypePassword = new JLabel("ReType Password: ");
        JLabel reTypePassword2 = new JLabel("ReType Password: ");

    JButton changePassButton = new JButton("Change Password");
    
    JButton viewEmp = new JButton("View Employee");
    JButton removeEmp = new JButton("Remove Employee");


    JLabel addUser=new JLabel("Add New User: ");
    JLabel nameOfUserLabel=new JLabel("Name: ");
    JTextField nameOfUserField=new JTextField();
    JLabel usernameOfUserLabel = new JLabel("Username: ");
    JTextField usernameOfUserField=new JTextField();
    JLabel passwordOfUserLabel=new JLabel("Password: ");
    JTextField passwordOfUserField=new JTextField();
    JLabel roleLabel=new JLabel("Role: ");
    JTextField roleField=new JTextField();
    JButton addEmp = new JButton("Add User");
    
    JLabel removeUser=new JLabel("Remove User: ");
    JLabel removeID=new JLabel("ID: ");
    JTextField removeIDField=new JTextField();
    JButton remove = new JButton("Remove User");
    
    JLabel changeUserPassword = new JLabel("Change User Password: ");
    JLabel changePassID= new JLabel("ID: ");
    JTextField changePassIDField=new JTextField();
    JButton changePass=new JButton("Change User Password");
   
    JTextField changePassword1 = new JTextField();
    JTextField changePasswordField2 = new JTextField();
   
    public  adminControlTab_view() throws SQLException, ClassNotFoundException {
        setLayout(null);
        
      //  JButton changePassword = new JButton("Change Password");

        changePassword.setBounds(10,10,150,50);
        add(changePassword);
        typePassword.setBounds(10,50,150,50);
        passwordField1.setBounds(130,60,150,35);
        add(passwordField1);     
        add(typePassword);
        reTypePassword.setBounds(10,110,150,50);
        passwordField2.setBounds(130,120,150,35);
        add(reTypePassword);     
        add(passwordField2);
        changePassButton.setBounds(130, 160, 150, 35);
        add(changePassButton);

        changePassButton.addActionListener(this);

        addUser.setBounds(1000, 10, 150, 50);
        add(addUser);
        nameOfUserLabel.setBounds(1000,50,150,50);
        add(nameOfUserLabel);
        nameOfUserField.setBounds(1100, 60, 150, 35);
        add(nameOfUserField);
        usernameOfUserLabel.setBounds(1000, 120, 150, 50);
        add(usernameOfUserLabel);
        usernameOfUserField.setBounds(1100, 130, 150, 35);
        add(usernameOfUserField);
        passwordOfUserLabel.setBounds(1000, 180, 150, 50);
        add(passwordOfUserLabel);
        passwordOfUserField.setBounds(1100, 190, 150, 35);
        add(passwordOfUserField);
        roleLabel.setBounds(1000, 240, 150, 50);
        add(roleLabel);
        roleField.setBounds(1100, 250, 150, 35);
        add(roleField);
        addEmp.setBounds(1100,310,150,50);
        add(addEmp);
        addEmp.addActionListener(this);
//        
        removeUser.setBounds(10,250,150,50);
        add(removeUser);
        removeID.setBounds(10,300,150,50);
        add(removeID);
        removeIDField.setBounds(110,310,150,35);
        add(removeIDField);
        remove.setBounds(110,360,150,35);
        add(remove);
        remove.addActionListener(this);
      
         changeUserPassword.setBounds(430,250,150,50);
         add(changeUserPassword);
         changePassID.setBounds(430,300,150,50);
         add(changePassID);
         changePassIDField.setBounds(550,310,150,35);
         add(changePassIDField);
         typePassword2.setBounds(430,360,150,50);
         add(typePassword2);
         changePassword1.setBounds(550,370,150,35);
         add(changePassword1);
         reTypePassword2.setBounds(430,410,150,50);
         add(reTypePassword2);
         changePasswordField2.setBounds(550,420,150,35);
         add(changePasswordField2);
         changePass.setBounds(530,460,170,50);
         add(changePass);
         changePass.addActionListener(this);
        
        String column[] = {"ID", "Name", "Role", "Username", "Password"};
        JTable jt;

        DefaultTableModel empModel;
        Object[][] empData = {};
        empModel = new DefaultTableModel(empData, column);
        jt = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jt.setModel(empModel);
        JScrollPane scrollPane = new JScrollPane(jt);
        scrollPane.setBounds(400, 50, 500, 200);
        add(scrollPane);
        
        
//        while (jt.getRowCount() > 0) {
//            empModel.removeRow(0);
//        }
        
        ResultSet rs = new MainPackage.AccessDatabase().getEmpData();
        int id;
        String name, role, username, password;
        while (rs.next()) {
            id = rs.getInt(1);
            name = rs.getString(2);
            role = rs.getString(3);
            username = rs.getString(4);
            password = rs.getString(5);
         
            empModel.addRow(new Object[]{id, name, role, username, password});
        }

        this.setVisible(true);
        
  
 }
    public static boolean isNumeric(String str)
{
  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
}
   public void actionPerformed(ActionEvent e) {
       if (e.getActionCommand().equals("Change Password")) {
           if (!passwordField1.getText().equals("") && !passwordField2.getText().equals("")) {
               if (passwordField1.getText().equals(passwordField2.getText())) {
                   try {
                       new MainPackage.AccessDatabase().changeAdminPass(passwordField1.getText());
                   } catch (SQLException ex) {
                       Logger.getLogger(adminControlTab_view.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (ClassNotFoundException ex) {
                       Logger.getLogger(adminControlTab_view.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   JOptionPane.showMessageDialog(this, "Password Changed!");
               } else {

                   JOptionPane.showMessageDialog(this, "Password Did not match!");
               }
           } else {
               JOptionPane.showMessageDialog(this, "Please enter in both password fields");

           }
       }
       else if(e.getActionCommand().equals("Add User")){
           if(!usernameOfUserField.getText().equals("") && !passwordOfUserField.getText().equals("") && !roleField.getText().equals("") && !nameOfUserField.getText().equals(""))
           {
               try {
                   new MainPackage.AccessDatabase().addUser(nameOfUserField.getText(),roleField.getText(),usernameOfUserField.getText(),passwordOfUserField.getText());
               } catch (SQLException ex) {
                   Logger.getLogger(adminControlTab_view.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(adminControlTab_view.class.getName()).log(Level.SEVERE, null, ex);
               }
                JOptionPane.showMessageDialog(this, "User Added, Please Refresh!");
           }
           else
           {
             JOptionPane.showMessageDialog(this, "Please fill all Fields");
           }
       }
       else if (e.getActionCommand().equals("Remove User")) {
           if (!removeIDField.getText().equals("")) {
               if (isNumeric(removeIDField.getText())) {
                   try {
                       new MainPackage.AccessDatabase().removeUser(removeIDField.getText());
                   } catch (SQLException ex) {
                       Logger.getLogger(adminControlTab_view.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (ClassNotFoundException ex) {
                       Logger.getLogger(adminControlTab_view.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   JOptionPane.showMessageDialog(this, "User Removed, Please Refresh!");
               } else {
                   JOptionPane.showMessageDialog(this, "Fill only Numeric Values!");
               }
           } else {
               JOptionPane.showMessageDialog(this, "Please fill ID");
           }

       }
       else if(e.getActionCommand().equals("Change User Password"))
       {
           if(!changePassIDField.getText().equals("") &&  !changePassword1.getText().equals("") && !changePasswordField2.getText().equals(""))
           {
               if(isNumeric(changePassIDField.getText()))
               {
                   if(changePassword1.getText().equals(changePasswordField2.getText()))
                   {
                       try {
                           new MainPackage.AccessDatabase().changeUserPass(changePassIDField.getText(),changePassword1.getText());
                       } catch (SQLException ex) {
                           Logger.getLogger(adminControlTab_view.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (ClassNotFoundException ex) {
                           Logger.getLogger(adminControlTab_view.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       JOptionPane.showMessageDialog(this, "Password Changed Successfully!");
                   }
                    else
                      JOptionPane.showMessageDialog(this, "Password did not match");  
               }
               else if(!isNumeric(changePassIDField.getText()))
                    JOptionPane.showMessageDialog(this, "ID should be Numeric");
               else if(!passwordField1.getText().equals(passwordField2.getText()))
                 JOptionPane.showMessageDialog(this, "Password did not match");  
           }
           else
              JOptionPane.showMessageDialog(this, "Please fill  all fields");  

       }
}
}
