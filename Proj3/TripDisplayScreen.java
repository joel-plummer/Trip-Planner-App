import javax.swing.*; 
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class TripDisplayScreen extends JFrame {

    private JPanel contsPnl;
    private JPanel disPnl;
    private JPanel btnPnl;
    private JPanel infoPnl;

    private JButton bNewTrip;
    private JButton bEditTrip;
    private JButton bClose;
    JComboBox<String> bSortBy;

    private WelcomeScreen thisWS; //previous screen
    private Account thisAcc;
    private AddTripScreen thisATS;
    private EditTripNavigation thisETN;
    private TripDisplayScreen thisTDS;

    public TripDisplayScreen(WelcomeScreen ws, Account acc) {

        //Setting up user info (Making sure windows share same data for user)
        thisWS = ws;
        thisAcc = acc;
        thisTDS = this;

        ws.setVisible(false); //Turns off welcome screen while trip screen is open

        String username = acc.getUsername();

        //Labelling the frame/window   
        setTitle("User " + username + "'s Plan");      
        //Setting up program icon
        Image icon = ws.getIconImage();    
        setIconImage(icon);


        //=================================================//
        //=                SETTING UP PANELS              =//
        //=================================================//
        contsPnl = new JPanel(); //Holds all panels
        contsPnl.setLayout(new BorderLayout());
        
        disPnl = new JPanel();//For the tabbedpane and tables
        disPnl.setLayout(new FlowLayout()); 

        btnPnl = new JPanel();//For buttons
        btnPnl.setLayout(new GridBagLayout());    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        infoPnl = new JPanel();//For user data at bottom
        infoPnl.setBackground(new Color(230,177,0));



        //=========================================//
        //=       MAINTAINING SELECTED THEME      =//
        //=========================================//       
        switch (acc.getTheme()) {
            case "PINK":
                contsPnl.setBackground(new Color(210,143,218));
                disPnl.setBackground(new Color(210,143,218));
                btnPnl.setBackground(new Color(210,143,218));
                break;
            case "RED":
                contsPnl.setBackground(new Color(142,49,80));
                disPnl.setBackground(new Color(142,49,80));
                btnPnl.setBackground(new Color(142,49,80));
                break; 
            case "LIGHT BLUE":
                contsPnl.setBackground(new Color(152,182,248));
                disPnl.setBackground(new Color(152,182,248));
                btnPnl.setBackground(new Color(152,182,248));
                break;
            case "GREEN":
                contsPnl.setBackground(new Color(85,111,111));
                disPnl.setBackground(new Color(85,111,111));
                btnPnl.setBackground(new Color(85,111,111));
                break;
            case "PURPLE":
                contsPnl.setBackground(new Color(104,54,137));
                disPnl.setBackground(new Color(104,54,137));
                btnPnl.setBackground(new Color(104,54,137));
                break;
            case "GRAY":
                contsPnl.setBackground(new Color(145,143,156));
                disPnl.setBackground(new Color(145,143,156));
                btnPnl.setBackground(new Color(145,143,156));
                break;
            case "Default":
                contsPnl.setBackground(new Color(55,73,136));
                disPnl.setBackground(new Color(55,73,136));
                btnPnl.setBackground(new Color(55,73,136));
        }



        //========================================//
        //=   CREATING THE BUTTONS AT BOTTOM     =//
        //========================================//
        //There should be a listener for when this is changed, similar to customization in settings
        String [] sortOpts = {"<<Sort By>>", "Trip ID", "Trip Name", "# People", "Time"};
        JComboBox<String> bSortBy = new JComboBox<>(sortOpts);
        bSortBy.addActionListener(new SortBtnListener());

        bNewTrip = new JButton("New Trip");
        bNewTrip.addActionListener(new AddTBtnListener());

        bEditTrip = new JButton("Edit Trip");
        bEditTrip.addActionListener(new EditTBtnListener());

        bClose = new JButton("Back to Login");
        bClose.addActionListener(new CloseBtnListener());  
        
        //Adding the buttons to button panel
        btnPnl.add(bSortBy, gbc);
        btnPnl.add(bNewTrip, gbc);
        btnPnl.add(bEditTrip, gbc);
        btnPnl.add(bClose, gbc);



        //=========================================//
        //=    CREATING THE TRIPS' TABBEDPANE     =//
        //=========================================//

        //================//
        //=   TAB/DAY 1  =//
        //================//
        //SETTING UP TAB 1 (DAY 1)
        JPanel pnl1 = new JPanel(new BorderLayout());
        pnl1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 1 TRIPS", TitledBorder.LEFT,TitledBorder.TRAILING));        
        pnl1.setBackground(Color.LIGHT_GRAY); 

        //SETTING UP TABLE FOR TAB 1 (DAY 1)
        TableData day1Table = new TableData(pnl1); //created this class to prevent chunky repetitive code. Creates a table

        //ADDING ELEMENTS TO TABLE
        //format: "Trip Name", "Bus Type", "Bus ID#", "# of People", "Trip ID#", "Time", "Trip Completed?"
        //placeholder data to fill up table, should come from the account instead maybe from an arraylist
        String[] item = {"Quick Trip 1", "Small - $5000", "#B30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item1 = {"Holland Students", "Medium - $7000", "#B23", "" + 17, "#T106", "09 : 00", "true"};
        String[] item12 = {"Ochi Tourists", "Luxurious - $12000", "#B21", "" + 32, "#T103", "10 : 00", "true"};
        day1Table.addTripRecord(item12);//this created method adds record to table for that day 
        day1Table.addTripRecord(item);
        day1Table.addTripRecord(item1);
              
        
        //================//
        //=   TAB/DAY 2  =//
        //================//
        //SETTING UP TAB 2 (DAY 2)
        JPanel pnl2 = new JPanel(new BorderLayout());
        pnl2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 2 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl2.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 2 (DAY 2)
        TableData day2Table = new TableData(pnl2); //created this class to prevent chunky repetitive code. Creates a table

        //ADDING ELEMENTS TO TABLE
        //format: "Trip Name", "Bus Type", "Bus ID#", "# of People", "Trip ID#", "Time", "Trip Completed?"
        //placeholder data to fill up table, should come from the account instead maybe from an arraylist
        String[] item2 = {"Quick Trip 1", "Small - $5000", "#B30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item21 = {"Holland Students", "Medium - $7000", "#B23", "" + 17, "#T106", "09 : 00", "true"};
        day2Table.addTripRecord(item21);
        day2Table.addTripRecord(item2);


        //================//
        //=  TAB/DAY 3   =//
        //================//
        //SETTING UP TAB 3 (DAY 3)        
        JPanel pnl3 = new JPanel(new BorderLayout());
        pnl3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 3 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl3.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 3 (DAY 3)
        TableData day3Table = new TableData(pnl3); //created this class to prevent chunky repetitive code. Creates a table

        //ADDING ELEMENTS TO TABLE
        //format: "Trip Name", "Bus Type", "Bus ID#", "# of People", "Trip ID#", "Time", "Trip Completed?"
        //placeholder data to fill up table, should come from the account instead maybe from an arraylist
        String[] item3 = {"Quick Trip 1", "Small - $5000", "#B30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item31 = {"Holland Students", "Medium - $7000", "#B23", "" + 17, "#T106", "09 : 00", "true"};
        day3Table.addTripRecord(item3);
        day3Table.addTripRecord(item31);        


        //================//
        //=  TAB/DAY 4   =//
        //================//        
        //SETTING UP TAB 4 (DAY 4)
        JPanel pnl4 = new JPanel(new BorderLayout());
        pnl4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 4 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING)); 
        pnl4.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 4 (DAY 4)
        TableData day4Table = new TableData(pnl4); //created this class to prevent chunky repetitive code. Creates a table

        //ADDING ELEMENTS TO TABLE
        //format: "Trip Name", "Bus Type", "Bus ID#", "# of People", "Trip ID#", "Time", "Trip Completed?"
        //placeholder data to fill up table, should come from the account instead maybe from an arraylist
        String[] item4 = {"Quick Trip 1", "Small - $5000", "#B30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item41 = {"Holland Students", "Medium - $7000", "#B23", "" + 17, "#T106", "09 : 00", "true"};
        day4Table.addTripRecord(item41);
        day4Table.addTripRecord(item4);  
        

        //================//
        //=  TAB/DAY 5   =//
        //================//
        //SETTING UP TAB 5 (DAY 5)
        JPanel pnl5 = new JPanel(new BorderLayout());
        pnl5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 5 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl5.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 5 (DAY 5)
        TableData day5Table = new TableData(pnl5); //created this class to prevent chunky repetitive code. Creates a table

        //ADDING ELEMENTS TO TABLE
        //format: "Trip Name", "Bus Type", "Bus ID#", "# of People", "Trip ID#", "Time", "Trip Completed?"
        //placeholder data to fill up table, should come from the account instead maybe from an arraylist
        String[] item5 = {"Quick Trip 1", "Small - $5000", "#B30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item51 = {"Holland Students", "Medium - $7000", "#B23", "" + 17, "#T106", "09 : 00", "true"};
        day5Table.addTripRecord(item5);
        day5Table.addTripRecord(item51);  
        

        //================//
        //=  TAB/DAY 6   =//
        //================//
        //SETTING UP TAB 6 (DAY 6)
        JPanel pnl6 = new JPanel(new BorderLayout());
        pnl6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 6 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl6.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 6 (DAY 6)
        TableData day6Table = new TableData(pnl6); //created this class to prevent chunky repetitive code. Creates a table

        //ADDING ELEMENTS TO TABLE
        //format: "Trip Name", "Bus Type", "Bus ID#", "# of People", "Trip ID#", "Time", "Trip Completed?"
        //placeholder data to fill up table, should come from the account instead maybe from an arraylist
        String[] item6 = {"Quick Trip 1", "Small - $5000", "#B30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item61 = {"Holland Students", "Medium - $7000", "#B23", "" + 17, "#T106", "09 : 00", "true"};
        day6Table.addTripRecord(item61);
        day6Table.addTripRecord(item6);  


        //================//
        //=  TAB/DAY 7   =//
        //================//
        //SETTING UP TAB 7 (DAY 7)
        JPanel pnl7 = new JPanel(new BorderLayout());
        pnl7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 7 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl7.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 7 (DAY 7)
        TableData day7Table = new TableData(pnl7); //created this class to prevent chunky repetitive code. Creates a table

        //ADDING ELEMENTS TO TABLE
        //format: "Trip Name", "Bus Type", "Bus ID#", "# of People", "Trip ID#", "Time", "Trip Completed?"
        //placeholder data to fill up table, should come from the account instead maybe from an arraylist
        String[] item7 = {"Quick Trip 1", "Small - $5000", "#B30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item71 = {"Holland Students", "Medium - $7000", "#B23", "" + 17, "#T106", "09 : 00", "true"};
        day7Table.addTripRecord(item7);
        day7Table.addTripRecord(item71);  


        //======================//
        //=   TAB 8/SETTINGS   =//
        //======================//
        //SETTING UP SETTINGS TAB
        JPanel pnl8 = new JPanel();
        pnl8.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "SETTINGS", TitledBorder.LEFT, TitledBorder.TOP));
        pnl8.setBackground(new Color(201,203,205));  

        //=================================//
        //=  IMPLEMENTING SELECTED THEME  =//
        //=================================//
        JLabel themetxt = new JLabel("Theme: ");
        String [] themeOpts = {"GRAY", "PINK", "RED", "GREEN", "LIGHT BLUE", "PURPLE", "Default"};
        JComboBox<String> themes = new JComboBox<>(themeOpts);
        themes.setSelectedItem(acc.getTheme());  
        
        JPanel themeArea = new JPanel();
        themeArea.setPreferredSize(new Dimension(590,30));
        themeArea.setOpaque(false);

        themeArea.add(themetxt);
        themeArea.add(themes);
        
        //Functionality for color customization
        themes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String s = (String) themes.getSelectedItem();
                switch (s) {
                    case "PINK":
                        contsPnl.setBackground(new Color(210,143,218));
                        disPnl.setBackground(new Color(210,143,218));
                        btnPnl.setBackground(new Color(210,143,218));
                        acc.setTheme("PINK");
                        break;
                    case "RED":
                        contsPnl.setBackground(new Color(142,49,80));
                        disPnl.setBackground(new Color(142,49,80));
                        btnPnl.setBackground(new Color(142,49,80));
                        acc.setTheme("RED");
                        break; 
                    case "LIGHT BLUE":
                        contsPnl.setBackground(new Color(152,182,248));
                        disPnl.setBackground(new Color(152,182,248));
                        btnPnl.setBackground(new Color(152,182,248));
                        acc.setTheme("LIGHT BLUE");
                        break;
                    case "GREEN":
                        contsPnl.setBackground(new Color(85,111,111));
                        disPnl.setBackground(new Color(85,111,111));
                        btnPnl.setBackground(new Color(85,111,111));
                        acc.setTheme("GREEN");
                        break;
                    case "PURPLE":
                        contsPnl.setBackground(new Color(104,54,137));
                        disPnl.setBackground(new Color(104,54,137));
                        btnPnl.setBackground(new Color(104,54,137));
                        acc.setTheme("PURPLE");
                        break;
                    case "GRAY":
                        contsPnl.setBackground(new Color(145,143,156));
                        disPnl.setBackground(new Color(145,143,156));
                        btnPnl.setBackground(new Color(145,143,156));
                        acc.setTheme("GRAY");
                        break;
                    case "Default":
                        contsPnl.setBackground(new Color(55,73,136));
                        disPnl.setBackground(new Color(55,73,136));
                        btnPnl.setBackground(new Color(55,73,136));
                        acc.setTheme("Default");
                }
            }
        });

        //Account info adjustment/change settings
        JPanel settPnl = new JPanel(new BorderLayout());
        settPnl.setPreferredSize(new Dimension(768,315));

        JPanel settingsArea = new JPanel(new FlowLayout(10, 25, 8));
        settingsArea.setPreferredSize(new Dimension(215,35));
        settingsArea.setOpaque(false);

        JLabel bdgtxt = new JLabel("Adjust Budget: ");
        JTextField bdgtChange = new JTextField(15);
        JButton bdgtSave = new JButton("Save");
        //budget save (to account) listener here 

        JLabel usertxt = new JLabel("Change Username: ");
        JTextField userChange = new JTextField(15);
        JButton userSave = new JButton("Save");
        //username save (to account) listener here

        JLabel passtxt = new JLabel("Change Password: ");
        JPasswordField passChange = new JPasswordField(15);
        JButton passSave = new JButton("Save");
        //password save (to account) listener here

        settingsArea.add(bdgtxt);
        settingsArea.add(bdgtChange);
        settingsArea.add(bdgtSave);
        settingsArea.add(usertxt);
        settingsArea.add(userChange);
        settingsArea.add(userSave);
        settingsArea.add(passtxt);
        settingsArea.add(passChange);
        settingsArea.add(passSave);

        //Adding stuff to settings tab
        settPnl.add(themeArea, BorderLayout.NORTH);
        settPnl.add(settingsArea, BorderLayout.WEST);
        pnl8.add(settPnl);


        //===============================//
        //=  ADDING TABS TO TABBEDPANE  =//
        //===============================//
        JTabbedPane tripTabs = new JTabbedPane();
        //tripTabs.setBounds(10,500,760,330); 
        tripTabs.add("DAY 1", pnl1);
        tripTabs.add("DAY 2", pnl2);  
        tripTabs.add("DAY 3", pnl3); 
        tripTabs.add("DAY 4", pnl4);
        tripTabs.add("DAY 5", pnl5);
        tripTabs.add("DAY 6", pnl6);
        tripTabs.add("DAY 7", pnl7);          
        tripTabs.add("SETTINGS", pnl8);   

        //Adding the whole tabbedpane to display panel
        disPnl.add(tripTabs);



        //=======================================================//
        //=           CREATING THE USER INFO AT BOTTOM          =//
        //=======================================================//
        //The user info should come from account and be displayed here
        double accBudget = acc.getBudget();
        double accRemaining = acc.getRemaining();
        int accTotBus = 56; //needs to reflect total buses that have been scheduled in account
        int accTotPpl = 122; //needs to reflect total people that have been scheduled in account

        JTextArea info = new JTextArea("Total Budget: $" + accBudget + "\tRemaining: $" + accRemaining + 
        "\tBuses Scheduled: " + accTotBus + "\tTotal Persons Booked: " + accTotPpl);
        Font boldFont = new Font(info.getFont().getName(), Font.BOLD, info.getFont().getSize());
        info.setFont(boldFont);
        info.setOpaque(false);

        //Adding info to its panel
        infoPnl.add(info);



        //=======================================================//
        //=         ADDING MAIN PANELS TO CONTENT PANEL         =//
        //=======================================================//
        contsPnl.add(disPnl, BorderLayout.PAGE_START);
        contsPnl.add(btnPnl, BorderLayout.CENTER);
        contsPnl.add(infoPnl, BorderLayout.PAGE_END);



        //Extra frame set up things
        setContentPane(contsPnl); 
        setSize(820,485);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true); 

    } //public TripDisplayScreen(WelcomeScreen ws, Account acc) end (constructor)

    
    //=========================================================//
    //=           BUTTON LISTENING FUNCTIONALITIES            =//
    //=========================================================//

    //=====================================//
    //=     SETTINGS BUTTON LISTENERS     =//
    //=====================================//
    //budget save (to account) listener


    //username save (to account) listener


    //password save (to account) listener



    //=======================================//
    //=    TRIP DISPLAY BUTTON LISTENERS    =//
    //=======================================//
    //Sort By
    private class SortBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //switch statement for each sorting option selected maybe
        }

    }

    //Edit Trip
    private class EditTBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            thisETN = new EditTripNavigation(thisTDS, thisAcc);
        }

    }

    //New Trip
    private class AddTBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            thisATS = new AddTripScreen(thisTDS, thisAcc);
        }

    }

    //Close (Bk To Welcome Screen)
    private class CloseBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false); //stops displaying window/frame
            thisWS.setWelcomeTheme(thisAcc.getTheme()); //changes welcome to fit account theme
            thisWS.setVisible(true); //makes welcome screen visible again
        }

    }

} //public class TripDisplayScreen() end 
