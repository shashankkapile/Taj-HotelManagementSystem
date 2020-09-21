package view.dashboard;

import MainPackage.AccessDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class RoomAvailability {

    public static void main(String[] args) {
        JFrame f=new JFrame();
        f.setVisible(true);
        f.setBounds(50, 50, 500, 500);
            f.add(new RoomAvailability().initRoomAvailablity());
       
    }
    private JButton[] roomArray = new JButton[20];
    JButton refreshButton;
    JPanel roomAvailbilityPanel;
    JPanel showRooms;
    JButton Hall = new JButton("100");

    
    public JPanel initRoomAvailablity() {
        showRooms = new JPanel();
        //JScrollPane jScrollPane = new JScrollPane(showRooms, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        showRooms.setBounds(30, 0, 1500, 700);
        roomAvailbilityPanel = new JPanel();
        refreshButton = new JButton("<html> "
                + "<body>"
                + "<h1>"
                + "Refresh"
                + "</h1>"
                + "</body>"
                + "</html>");

        roomAvailbilityPanel.setLayout(new BorderLayout());
        //roomAvailbilityPanel componenets

        //jScrollPane.getViewport().setPreferredSize(new Dimension(160, 200));
        //jScrollPane.getViewport().setMinimumSize(new Dimension(160, 200));

        showRooms.setLayout(null);
        JLabel floor1 = new JLabel("Floor 1:");
        floor1.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        showRooms.add(floor1);
        floor1.setBounds(0, 0, 150, 50);


        //System.out.print(roomArray.length);




        for (int i = 0; i < roomArray.length; i++) {
            //init rooms
            roomArray[i] = new JButton();
            //set room size
            roomArray[i].setSize(75, 50);
            //set room numbers to buttons
            roomArray[i].setText("" + (100 + i + 1));
            //check and set room colors according to availability
            new RoomAvailability().loadRoomAvailablePanel(Hall);
            new RoomAvailability().loadRoomAvailablePanel(roomArray[i]);
            //add rooms to the panel
            showRooms.add(roomArray[i]);
        }
        roomArray[0].setLocation(75, 50 + 63);
        roomArray[1].setLocation(200, 50);
        roomArray[2].setLocation(200, 150);
        roomArray[3].setLocation(350,50);
        roomArray[4].setLocation(350, 150);


        roomArray[5].setLocation(750, 50);
        roomArray[6].setLocation(750, 150);
        roomArray[7].setLocation(900, 50);
        roomArray[8].setLocation(900, 150);
        roomArray[9].setLocation(1025, 50 + 63);


        roomArray[10].setLocation(75, 300 + 63);
        roomArray[11].setLocation(200, 300);
        roomArray[12].setLocation(200, 425);
        roomArray[13].setLocation(350, 300);
        roomArray[14].setLocation(350, 425);


        roomArray[15].setLocation(750, 300);
        roomArray[16].setLocation(750, 425);
        roomArray[17].setLocation(900, 300);
        roomArray[18].setLocation(900, 425);
        roomArray[19].setLocation(1025, 300 + 63);




        JLabel floor2 = new JLabel("Floor 2:");
        floor2.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        showRooms.add(floor2);
        floor2.setBounds(0, 290, 150, 50);

        JLabel Non_AC = new JLabel("Non AC (Deluxe)");
        Non_AC.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        showRooms.add(Non_AC);
        Non_AC.setBounds(200, 220, 250, 50);

        JLabel AC = new JLabel(" AC (Non Deluxe)");
        AC.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        showRooms.add(AC);
        AC.setBounds(800, 220, 250, 50);

        JLabel Deluxe = new JLabel("Non AC (Non Deluxe)");
        Deluxe.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        showRooms.add(Deluxe);
        Deluxe.setBounds(200, 535-50, 250, 50);

        JLabel hallLabel = new JLabel("Hall");
        hallLabel.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        showRooms.add(hallLabel);
        hallLabel.setBounds(585, 485, 250, 50);

        JLabel TajSpecial = new JLabel("Taj Premium");
        TajSpecial.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        showRooms.add(TajSpecial);
        TajSpecial.setBounds(800, 535-50, 250, 50);

        JButton FloorSplit = new JButton();
        showRooms.add(FloorSplit);
        FloorSplit.setBounds(0, 280, 1300, 5);
        FloorSplit.setEnabled(false);

        

        showRooms.add(Hall);
        Hall.setBounds(575, 325, 75, 150);

        //roomAvailbilityPanel.add(jScrollPane, BorderLayout.CENTER);
        return showRooms;

    }

    public void loadRoomAvailablePanel(JButton roomObject)  {
        try {
            if (new AccessDatabase().loadRooms(Integer.parseInt(roomObject.getText()))) {
                roomObject.setBackground(new Color(122, 228, 122));
            } else {
                roomObject.setBackground(new Color(228, 122, 122));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomAvailability.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoomAvailability.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}