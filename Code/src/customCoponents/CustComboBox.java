/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customCoponents;

import javax.swing.JComboBox;

/**
 *
 * @author Hrishikesh
 */
public class CustComboBox extends JComboBox{
    public JComboBox getComboBox(Object []items,int x,int y , int width ,int height) {
        JComboBox combobox=new JComboBox(items);
        combobox.setBounds(x, y, width, height);        
        return combobox;
    }
}
