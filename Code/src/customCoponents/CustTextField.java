/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customCoponents;

import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author Hrishikesh
 */
public class CustTextField extends JTextField
{
    public CustTextField(int x,int y,int width,int height) 
    {
        setFont(new Font("Lucida Grande",Font.PLAIN,14));
        setBounds(x, y, width, height);
    }
}