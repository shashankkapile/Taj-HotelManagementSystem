/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dashboard;

import control.Login_controller;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RecordsTab_view extends JPanel implements ActionListener
{
    JRadioButton b1,b2;
    JScrollPane tableScroll;
    JScrollPane tableScroll2;
    JTable t1,t2;
    DefaultTableModel currentModel=null,oldModel=null;
    Object [][]data={};
    String []columnName={"Bill No","ID","Name","Room No","Room Type","In time","Out time","Days","People","Room Cost","Room Service","Food","Total Bill","Advance","Payable","Paid"};
    String []columnName2={"Record ID","In time","ID","Room no","People","Days","Food","Advance"};
    public RecordsTab_view() 
    {
        setLayout(null);
        b1=new JRadioButton("Current Records");
        b1.setLocation(20,20);
        b1.setSize(200, 50);
        b1.setFont(new Font("Lucida Grande",Font.BOLD,16));
        b2=new JRadioButton("Old Records");
        b2.setLocation(250,20);
        b2.setSize(200, 50);
        b2.setFont(new Font("Lucida Grande",Font.BOLD,16));
        b1.addActionListener(this);
        b2.addActionListener(this);
        b1.setSelected(true);
        
        oldModel=new DefaultTableModel(data, columnName);
        t1=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        t1.setModel(oldModel);
        
        tableScroll=new JScrollPane(t1);
        tableScroll.setLocation(50, 100);
        tableScroll.setSize(1200, 400);
        tableScroll.setVisible(false);
         currentModel=new DefaultTableModel(data, columnName2);

        t2=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableScroll2=new JScrollPane(t2);
        tableScroll2.setLocation(50, 100);
        tableScroll2.setSize(1200, 400);
        t2.setModel(currentModel);
        initPanel();
    }
    public void initPanel()
    {
        add(b1);
        add(b2);
        add(tableScroll);
        add(tableScroll2);
        Login_controller controller=new Login_controller();
        ArrayList al=controller.getCurrentRecords();
        ArrayList al1=controller.getOldRecords();
        for (Iterator iterator = al1.iterator(); iterator.hasNext();) {
            oldModel.addRow((Vector) iterator.next());
            
        }
        for (Iterator iterator = al.iterator(); iterator.hasNext();) {
            currentModel.addRow((Vector) iterator.next());
            
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(b1))
        {
            b2.setSelected(false);
            b1.setSelected(true);
            tableScroll.setVisible(false);
            tableScroll2.setVisible(true);
        }
        else if(e.getSource().equals(b2))
        {                                   
        b1.setSelected(false);
        b2.setSelected(true);
            tableScroll.setVisible(true);
            tableScroll2.setVisible(false);
        }
    }
    
    
}
