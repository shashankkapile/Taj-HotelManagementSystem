package view.dashboard;

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
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class checkOutTab_view extends JPanel implements ActionListener,KeyListener
{
    private JTextField no;
    private JButton chkOut;
    String billNo=null; 
        Integer c=000,s=0,f=0,a=0,t=0,pa=0;
        JLabel cID=new CustLabel("Customer ID: --ID--", 25, 50, 200, 50);
        JLabel rNoLabel=new CustLabel("Room No", 235, 100, 200, 50 );
        JLabel roomno=new CustLabel("Room NO:",25, 5, 100, 50 );
        JLabel p=new JLabel(" * " );
        JLabel billno=new JLabel("Bill NO:  ----Bill No----" );
        JLabel cName=new JLabel("Customer Name: ----name----");
     //   JLabel d=new CustLabel("Room Details :",225, 100, 200, 50 );
        JLabel type=new CustLabel("Room Type- -----type-----",235, 150, 350, 50,Font.PLAIN );
        JLabel intime=new CustLabel("Check IN Time- ----time-----",235, 200, 350, 50 ,Font.PLAIN);
        JLabel outtime=new CustLabel("Check Out Time- ----time--",235, 250, 350, 50 ,Font.PLAIN);
        JLabel days=new CustLabel("No.of Days- ----days--",235, 300, 300, 50,Font.PLAIN );
        JLabel person=new CustLabel("no. of peoples- ---no---",235, 350, 350, 50,Font.PLAIN );
        JLabel cost=new CustLabel("Room cost: ",650, 100, 300, 50,Font.PLAIN );
        JLabel c1=new CustLabel(c.toString(),850, 100, 200, 50,Font.PLAIN );
        JLabel ser=new CustLabel("Service: ",650, 150, 300, 50,Font.PLAIN );
        JLabel food=new CustLabel("Food :",650, 200, 200, 50,Font.PLAIN );
        JLabel f1=new CustLabel(f.toString(),850, 200, 200, 50,Font.PLAIN );
        JLabel pay=new CustLabel("Payable amount: ",650, 380, 300, 50);
        JLabel total=new CustLabel("Total Bill :",650, 260, 300, 50);
        JLabel ad=new CustLabel("Advance paid: ",650, 310,300, 50,Font.PLAIN );
        JLabel t1=new CustLabel(t.toString(),850, 260, 200, 50,Font.PLAIN );
        JLabel ser1=new CustLabel(s.toString(),850, 150, 200, 50,Font.PLAIN );
        JLabel a1=new CustLabel(a.toString(),850, 310, 200, 50,Font.PLAIN );
    JLabel p1=new CustLabel(pa.toString(),850, 380, 200, 50,Font.PLAIN );
    public checkOutTab_view() {
        setLayout(null);
        add(roomno);
       add(cID);
       add(rNoLabel);
        no=new CustTextField(115,15,75,25);
        add(no);
        no.addKeyListener(this);
        
        add(p);
        p.setFont(new Font("Lucida Grande",Font.BOLD,16));
        p.setBounds(7, 5, 50, 50);
       p.setForeground(Color.red);
        add(billno);
        billno.setFont(new Font("Lucida Grande",Font.BOLD,16));
        billno.setBounds(550, 15, 200, 50);
        
        add(cName);
        cName.setFont(new Font("Lucida Grande",Font.PLAIN,18));
        cName.setBounds(200, 50, 500, 50);
        
        cID.setFont(new Font("Lucida Grande",Font.PLAIN,18));
        rNoLabel.setFont(new Font("Lucida Grande",Font.PLAIN,16));
        
        //add(d);
 
        
        
        add(type);
 
        add(intime);
 
        add(outtime);
 
        add(days);
 
        add(person);

        add(cost);

        add(c1);
        c1.setAlignmentX(RIGHT_ALIGNMENT);
        c1.setAlignmentY(RIGHT_ALIGNMENT);
 
        add(ser);

        add(ser1);
        ser1.setAlignmentX(RIGHT_ALIGNMENT);
        ser1.setAlignmentY(RIGHT_ALIGNMENT);
        
        add(food);

        add(f1);
 
        JButton s1 = new JButton();
        add(s1);
        s1.setBounds(750, 250, 200, 2);
        s1.setEnabled(false);

        add(total);

        add(t1);
 
        add(ad);

        add(a1);
 
        JButton s2 = new JButton();
        add(s2);
       s2.setBounds(750, 360, 200, 2);
       s2.setEnabled(false);

        
        add(pay);
       
        add(p1);
        
        chkOut=new CustButton("Pay", 850, 450, 300, 50, this);
        add(chkOut);
      
        
    }
    
 public static void main(String[] args) {
        JFrame frm=new JFrame();
        checkOutTab_view c=new checkOutTab_view();
//        frm.setLayout(null);
        frm.setVisible(true);
        frm.setBounds(50, 60, 1200, 700);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frm.add(c);
        
    }
    Login_controller controller =new Login_controller();

    @Override
    public void actionPerformed(ActionEvent e) {
        if(billNo!=null)
        {
            if(controller.checkOut(billNo))
            {
                JOptionPane.showMessageDialog(this, "Chech Out Successful!!");
            }
            else 
                JOptionPane.showMessageDialog(this, "Chech Out Failed!!");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Enter Proper Room Number!!");        
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(e.getSource().equals(no))
        {
            if (!((c >= '0') && (c <= '9') ||(c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE)||(c==KeyEvent.VK_ENTER))) 
            {
                e.consume();
            }
            else if(e.getKeyChar()==KeyEvent.VK_ENTER)
            {
                String s="";
                boolean avail=true;
                
                    s=no.getText();
                    if(!(Integer.parseInt(s)>99) ||  !(Integer.parseInt(s)<121))
                    {
                        JOptionPane.showMessageDialog(this, "Please Enter valid room no.");
                        return;
                    }
                if(!s.equals(""))
                    avail=controller.isRoomAvailable(Integer.parseInt(s));
                    System.out.println("Avail "+avail);
                if(!avail)
                {
                    Vector data= controller.getCheckOutInfo(Integer.parseInt(s));
                    if(data!=null   )
                    {
                        billNo=data.get(0).toString();
                        billno.setText("Bill NO: "+data.get(0));
                        cID.setText("Customer ID: "+data.get(1));
                        cName.setText("Customer Name: "+data.get(2));
                        rNoLabel.setText("Room No "+data.get(3));
                        type.setText("Room Type: "+data.get(4));
                        intime.setText("Check In Time: "+data.get(5));
                        outtime.setText("Check Out Time: "+data.get(6));
                        days.setText("No Of days: "+data.get(7));
                        person.setText("No Of People: "+data.get(8));
                        c1.setText(data.get(9).toString());
                        ser1.setText(data.get(10).toString());
                        f1.setText(data.get(11).toString());
                        t1.setText(data.get(12).toString());
                        a1.setText(data.get(13).toString());
                        p1.setText(data.get(14).toString());
                        
                    }
                }
                else
                    JOptionPane.showMessageDialog(this, "Room Not Booked!");
            }
            
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
       
}
