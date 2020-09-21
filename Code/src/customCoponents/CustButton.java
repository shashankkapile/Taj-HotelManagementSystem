/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customCoponents;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Hrishikesh
 */
public class CustButton extends JButton
{

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public CustButton(String text) {
        setText(text);
    }
    
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public CustButton(String text,int x,int y,int width,int height,ActionListener action) {
        setText(text);
        setBounds(x, y, width, height);
        addActionListener(action);
       setFont(new Font("Lucida Grande",Font.BOLD,16));
    }
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public CustButton(String text,int x,int y,int width,int height) {
        setText(text);
        setBounds(x, y, width, height);
        setFont(new Font("Lucida Grande",Font.BOLD,16));
    }
    
}
