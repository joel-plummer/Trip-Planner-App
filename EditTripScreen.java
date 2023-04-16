import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

/**
 * This class generates a screen for the user to edit trip data.
 * @param bSaveEdit is a button that allows the user to save any edits made to a trip.
 * @param bCancel brings the user back to the pervious screen.
 */

public class EditTripScreen extends JFrame {

    private JPanel disPnl;
    private JPanel lftPnl, rgtPnl;
    private JPanel dPnl1, dPnl2, dPnl3, dPnl4, dPnl5, dPnl6, dPnl7, dPnl8;
    private JPanel btnPnl;

    private JButton bSaveEdit;
    private JButton bCancel;

    private JLabel errorMsg;
    private JLabel daytxt, nametxt, bustxt, genIDtxt, ppltxt, timetxt, busIDtxt, completed;
    private JTextField nameBox, IDBox, pplBox, timeBox, busIDBox;
    private JCheckBox isComplete;

    private Account thisAcc;
    private EditTripNavigation thisETN; //previous screen
    private EditTripScreen thisETS;

    public EditTripScreen(EditTripNavigation etn, Account acc){

        /*Setting up user info (Making sure windows share same data for user)*/
        thisAcc = acc;
        thisETN = etn;
        thisETS = this;

        etn.setVisible(false); /*Turns off edittripnavgiation screen while edittrip screen is open*/

        /*Labelling the frame/window*/
        setTitle("Edit Trip");      
        //Setting up program icon
        Image icon = thisETN.getIconImage();    
        setIconImage(icon);
        setLayout(new BorderLayout());

        //=================================================//
        //=                SETTING UP PANELS              =//
        //=================================================//
        disPnl = new JPanel(new BorderLayout());
        btnPnl = new JPanel(new GridBagLayout());


        //============================================//
        //=   STRUCTURING & CREATING DISPLAY PANEL   =//
        //============================================// 
        /*Main panel for input section*/
        disPnl.setPreferredSize(new Dimension(520,215));
        disPnl.setBackground(new Color(195,195,195)); 
        lftPnl = new JPanel(new FlowLayout(10, 25, 12));
        lftPnl.setPreferredSize(new Dimension(285,35)); 
        lftPnl.setOpaque(false);
        rgtPnl = new JPanel(new FlowLayout(10, 25, 12));
        rgtPnl.setPreferredSize(new Dimension(270,35)); 
        rgtPnl.setOpaque(false);

        dPnl1 = new JPanel();
        dPnl1.setOpaque(false);
        daytxt = new JLabel("Day: ");
        String [] dayOpts = {"<<Select Day>>","DAY 1", "DAY 2", "DAY 3", "DAY 4", "DAY 5", "DAY 6", "DAY 7"};
        JComboBox<String> days = new JComboBox<>(dayOpts); 
        //placeholder data
        days.setSelectedItem("DAY 1"); //Should be set to whatever day trip is on 
        dPnl1.add(daytxt);
        dPnl1.add(days);
        lftPnl.add(dPnl1); 

        dPnl2 = new JPanel();
        dPnl2.setOpaque(false);
        nametxt = new JLabel("Trip Name: ");
        nameBox = new JTextField(13);
        //placeholder data
        nameBox.setText("Quick Trip 1");//should be set to whatever data the trip previously selected has
        dPnl2.add(nametxt);
        dPnl2.add(nameBox);
        lftPnl.add(dPnl2);

        dPnl3 = new JPanel();
        dPnl3.setOpaque(false);
        bustxt = new JLabel("Bus Type:  ");
        //should be set to whatever data the trip previously selected has
        String [] busOpts = {"<<Select Bus>>", "Small - $5000", "Medium - $7000", "Luxurious - $12000"};
        JComboBox<String> buses = new JComboBox<>(busOpts); 
        //placeholder data
        days.setSelectedItem("Small - $5000"); //Should be set to whatever bus type the trip has
        
        dPnl3.add(bustxt);
        dPnl3.add(buses);
        lftPnl.add(dPnl3); 

        dPnl4 = new JPanel();
        dPnl4.setOpaque(false);
        busIDtxt = new JLabel("Generated Bus ID: ");
        busIDBox = new JTextField(5);
        //placeholder data
        busIDBox.setText("#B30"); //should be set to whatever data the trip previously selected has
        busIDBox.setEditable(false);
        dPnl4.add(busIDtxt);
        dPnl4.add(busIDBox);
        lftPnl.add(dPnl4);
        
        dPnl5 = new JPanel();
        dPnl5.setOpaque(false);
        ppltxt = new JLabel("# of Persons: ");
        pplBox = new JTextField(8);
        //placeholder data
        pplBox.setText("8");//should be set to whatever data the trip previously selected has
        dPnl5.add(ppltxt);
        dPnl5.add(pplBox);
        rgtPnl.add(dPnl5);
        
        dPnl6 = new JPanel();
        dPnl6.setOpaque(false);
        timetxt = new JLabel("Time:  ");
        timeBox = new JTextField(12);
        //placeholder data
        timeBox.setText("9 : 00");//should be set to whatever data the trip previously selected has
        dPnl6.add(timetxt);
        dPnl6.add(timeBox);
        rgtPnl.add(dPnl6);

        dPnl7 = new JPanel();
        dPnl7.setOpaque(false);
        genIDtxt = new JLabel("Generated Trip ID: ");
        IDBox = new JTextField(5);
        //placeholder data
        IDBox.setText("#T104"); //should be set to whatever data the trip previously selected has
        IDBox.setEditable(false);
        dPnl7.add(genIDtxt);
        dPnl7.add(IDBox);
        rgtPnl.add(dPnl7);

        dPnl8 = new JPanel();
        dPnl8.setOpaque(false);
        completed = new JLabel("Trip Completed?");
        isComplete = new JCheckBox();
        isComplete.setSelected(true); //should be set to whatever data the trip previously selected has
        isComplete.setOpaque(false);
        dPnl8.add(completed);
        dPnl8.add(isComplete);
        rgtPnl.add(dPnl8);

        /*To display error messages when needed*/
        errorMsg = new JLabel("");
        errorMsg.setForeground(new Color(237,28,36)); 
        errorMsg.setOpaque(false);       
        rgtPnl.add(errorMsg);

        disPnl.add(lftPnl, BorderLayout.WEST);
        disPnl.add(rgtPnl, BorderLayout.EAST);



        //========================================//
        //=   CREATING THE BUTTONS AT BOTTOM     =//
        //========================================//     
        btnPnl.setBackground(new Color(195,195,195));
        btnPnl.setSize(780,10);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 15, 12, 15);

        bSaveEdit = new JButton("Save Edit");
        bSaveEdit.addActionListener(new SaveBtnListener());

        bCancel = new JButton("Cancel");
        bCancel.addActionListener(new CloseBtnListener()); 

        btnPnl.add(bSaveEdit, gbc);
        btnPnl.add(bCancel, gbc);


        /*Adding main panels to frame/window*/
        add(disPnl, BorderLayout.NORTH);
        add(btnPnl, BorderLayout.SOUTH);


        //=========================================//
        //=       MAINTAINING SELECTED THEME      =//
        //=========================================//       
        switch (acc.getTheme()) {
            case "PINK":
                disPnl.setBackground(new Color(210,143,218));
                daytxt.setForeground(Color.BLACK);
                nametxt.setForeground(Color.BLACK);
                bustxt.setForeground(Color.BLACK);
                genIDtxt.setForeground(Color.BLACK);
                ppltxt.setForeground(Color.BLACK);
                timetxt.setForeground(Color.BLACK);
                busIDtxt.setForeground(Color.BLACK);
                completed.setForeground(Color.BLACK);
                break;
            case "RED":
                disPnl.setBackground(new Color(142,49,80));
                daytxt.setForeground(Color.WHITE);
                nametxt.setForeground(Color.WHITE);
                bustxt.setForeground(Color.WHITE);
                genIDtxt.setForeground(Color.WHITE);
                ppltxt.setForeground(Color.WHITE);
                timetxt.setForeground(Color.WHITE);
                busIDtxt.setForeground(Color.WHITE);
                completed.setForeground(Color.WHITE);
                break; 
            case "LIGHT BLUE":
                disPnl.setBackground(new Color(152,182,248));
                daytxt.setForeground(Color.BLACK);
                nametxt.setForeground(Color.BLACK);
                bustxt.setForeground(Color.BLACK);
                genIDtxt.setForeground(Color.BLACK);
                ppltxt.setForeground(Color.BLACK);
                timetxt.setForeground(Color.BLACK);
                busIDtxt.setForeground(Color.BLACK);
                completed.setForeground(Color.BLACK);
                break;
            case "GREEN":
                disPnl.setBackground(new Color(85,111,111));
                daytxt.setForeground(Color.WHITE);
                nametxt.setForeground(Color.WHITE);
                bustxt.setForeground(Color.WHITE);
                genIDtxt.setForeground(Color.WHITE);
                ppltxt.setForeground(Color.WHITE);
                timetxt.setForeground(Color.WHITE);
                busIDtxt.setForeground(Color.WHITE);
                completed.setForeground(Color.WHITE);
                break;
            case "PURPLE":
                disPnl.setBackground(new Color(104,54,137));
                daytxt.setForeground(Color.WHITE);
                nametxt.setForeground(Color.WHITE);
                bustxt.setForeground(Color.WHITE);
                genIDtxt.setForeground(Color.WHITE);
                ppltxt.setForeground(Color.WHITE);
                timetxt.setForeground(Color.WHITE);
                busIDtxt.setForeground(Color.WHITE);
                completed.setForeground(Color.WHITE);
                break;
            case "GRAY":
                disPnl.setBackground(new Color(145,143,156));
                daytxt.setForeground(Color.BLACK);
                nametxt.setForeground(Color.BLACK);
                bustxt.setForeground(Color.BLACK);
                genIDtxt.setForeground(Color.BLACK);
                ppltxt.setForeground(Color.BLACK);
                timetxt.setForeground(Color.BLACK);
                busIDtxt.setForeground(Color.BLACK);
                completed.setForeground(Color.BLACK);
                break;
            case "Default":
                disPnl.setBackground(new Color(55,73,136));
                daytxt.setForeground(Color.WHITE);
                nametxt.setForeground(Color.WHITE);
                bustxt.setForeground(Color.WHITE);
                genIDtxt.setForeground(Color.WHITE);
                ppltxt.setForeground(Color.WHITE);
                timetxt.setForeground(Color.WHITE);
                busIDtxt.setForeground(Color.WHITE);
                completed.setForeground(Color.WHITE);
        }


        //Extra frame set up things
        setSize(780,460);
        pack();
        setResizable(false);
        setVisible(true); 

    } //public EditTripScreen(EditTripNavigation etn, Account acc) end (constructor)


    
    //=========================================================//
    //=           BUTTON LISTENING FUNCTIONALITIES            =//
    //=========================================================//
    /*Saves Trip Edit*/
    private class SaveBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            //if input for time and # of persons is not valid, if statement needs to be added
            errorMsg.setText("Please recheck the Time & Person fields.");
            //if time has already been booked for that day, another error message here
            //if bus cost exceeds the budget, another error message here to warn user to update it 
            //("You can't afford this") or smth

            //if input is valid, creates a popup asking for confirmation
            //reduce budget here with thisAcc.calcRemaining()
            int confirm = JOptionPane.showConfirmDialog(thisETS,"Are you sure you want to \nkeep these changes? ");  
            if(confirm == JOptionPane.YES_OPTION) {  
                //supposed to edit the data for trip in arraylist for the selected day
                setVisible(false); //stops displaying window/frame
            }  
        }
        
    }

    /*Close button bring the user back To the Welcome Screen)*/
    private class CloseBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false); //stops displaying window/frame
            thisETN.setVisible(true); //makes edittripnavigation screen visible again
        }

    }

} //public class EditTripScreen() end
