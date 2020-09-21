/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.DemoClient;
import view.dashboard.Dashboard_view;
import control.Login_controller;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Vector;

public final class Login_view extends JFrame implements ActionListener//,KeyListener
{
    Login_controller controller =new Login_controller();
    JTextField usernameField;
    JPasswordField passwordField;
    JLabel  loginError=new JLabel();
    public Login_view() throws IOException
    {
        BufferedImage myImage = ImageIO.read(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\HotelManagement Combined\\src\\h5.jpg"));
        
        setContentPane(new ImagePanel(myImage));
        /*Frame Initialize Start*/
        setSize(1360,725);
        setVisible(true);
        /*Frame Initialize End*/

        /*Logo Start*/
        BufferedImage myPicture= ImageIO.read(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\HotelManagement Combined\\src\\logo.png"));
        JLabel logo=new JLabel(new ImageIcon(myPicture));
        add(logo).setBounds(454, 70, 454,145);
        setFont(new Font("Lucida Grande",Font.PLAIN,24));
        /*Logo End*/
        
     //   addKeyListener(this);
        
        Container c= this.getContentPane();
        c.setBackground(new Color(216,223,229));
        c.setLayout(null);
        setLoginPanel(); //sets the login panel components 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void loadDashboard(String username) throws IOException, SQLException, ClassNotFoundException
    {
      /*  javax.swing.JDialog dialog=new JDialog(this,"Wel-Come");
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(Login_view.EXIT_ON_CLOSE);*/
        Dashboard_view dr=new Dashboard_view();
        dr.initDashboardR(username);
    }
    void setLoginPanel()
    {
     
      JLabel userNameLabel= new JLabel("User Name: ");
      this.add(userNameLabel);
      userNameLabel.setBounds(550,338,150,50);
      userNameLabel.setFont(new Font("Lucida Grande",Font.BOLD,16));
      userNameLabel.setForeground(Color.BLACK);
      usernameField=new JTextField();
      this.add(usernameField);
      
     // usernameField.setFocusable(true)
      usernameField.setFont(new Font("Lucida Grande",Font.PLAIN,16));
      usernameField.setBounds(650,350,150,25);

      JLabel passwordLabel= new JLabel("Password: ");
      this.add(passwordLabel);
      passwordLabel.setBounds(550,400,150,50);
      passwordLabel.setForeground(Color.BLACK);
      passwordLabel.setFont(new Font("Lucida Grande",Font.BOLD,16));
      passwordField = new JPasswordField();
      this.add(passwordField);
//      usernameField.addKeyListener(this);
//      passwordField.addKeyListener(this);
      passwordField.setBounds(650,412,150,25);

      
      JButton submitButton = new JButton("Submit");
      submitButton.setFont(new Font("Lucida Grande",Font.BOLD,16));
      submitButton.setBackground(Color.LIGHT_GRAY);
      submitButton.setForeground(Color.BLACK);
      this.add(submitButton);
     // submitButton.addKeyListener(this);
      submitButton.setBounds(580,500,100,40);
      submitButton.addActionListener(this);
      JButton cancelButton = new JButton("Cancel");
      cancelButton.setFont(new Font("Lucida Grande",Font.BOLD,16));
      cancelButton.setBackground(Color.LIGHT_GRAY);
      cancelButton.setForeground(Color.BLACK);
      this.add(cancelButton);
      cancelButton.setBounds(700,500,100,40);
      cancelButton.addActionListener(this);

     usernameField.setText("admin");
     passwordField.setText("admin");
      
    }

    public static void main(String s[]) throws IOException
    {
        Login_view hml = new Login_view();
  
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       if(e.getActionCommand().equals("Submit"))
       {
           try {
               submitAction();
           } catch (IOException ex) {
               Logger.getLogger(Login_view.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SQLException ex) {
               Logger.getLogger(Login_view.class.getName()).log(Level.SEVERE, null, ex);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(Login_view.class.getName()).log(Level.SEVERE, null, ex);
           }
           
        }
       else if(e.getActionCommand().equals("Cancel"))
       {
            /*Reset all textboxes*/
           loginError.setText("");
           usernameField.setText("");
           passwordField.setText("");
       }
    }
    

//    @Override
//    public void keyTyped(KeyEvent e) {
//        if(0==Character.compare(e.getKeyChar(), '\n'))
//            submitAction();
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if(0==Character.compare(e.getKeyChar(), '\n'))
//            submitAction();
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        if(0==Character.compare(e.getKeyChar(), '\n'))
//            submitAction();
//                
//    }

    private void submitAction() throws IOException, SQLException, ClassNotFoundException {
     // Vector v=new DemoClient().sendToServer(usernameField.getText(), passwordField.getPassword());
     // System.out.println(v.elementAt(2));
       if(new DemoClient().sendToServer(usernameField.getText(), passwordField.getPassword())){
           this.dispose();
           try {
               loadDashboard(usernameField.getText());
           } catch (IOException ex) {
               Logger.getLogger(Login_view.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       else
       {
           loginError.setText("Invalid Credentials!");
           loginError.setFont(new Font("Lucida Grande",Font.BOLD,16));
           loginError.setForeground(Color.red);
           this.add(loginError).setBounds(650,300,150,50);
       }
    }
}


    class ImagePanel extends JComponent 
    {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

