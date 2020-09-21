/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customCoponents;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Hrishikesh
 */
public class CustLabel extends JLabel
{
    public CustLabel(String text,int x,int y,int width,int height) {
        setText(text);
        setBounds(x, y, width, height);
        setFont(new Font("Lucida Grande",Font.BOLD,16));
  }

    public CustLabel(String text, int x, int y, int width, int height, int font) {
        setText(text);
        setBounds(x, y, width, height);
        setFont(new Font("Lucida Grande",font,16));
    }
}
