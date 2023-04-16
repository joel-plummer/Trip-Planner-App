import javax.swing.*; 
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Collections;

/**
 * This class displays the Trips associated to a particular user account
 * @param bNewTrip allows the user to create a new trip with valid trip details 
 * @param bEditTrip allows the user to edit trip details
 * @param bSortBy allows the user to sort the trip data by special parameters 
 */


public class TripDisplayScreen extends JFrame {

    private JPanel contsPnl;
    private JPanel disPnl;
    private JPanel btnPnl;
    private JPanel infoPnl;

    private JButton bNewTrip;
    private JButton bEditTrip;
    private JButton bClose;
    JComboBox<String> bSortBy;
    private String selected;

    //For tabs
    private JTabbedPane tripTabs;
    private TableData daysTable;
    private TableData day1Table, day2Table, day3Table, day4Table, day5Table, day6Table, day7Table;
    private ArrayList<Trip> d1TripList, d2TripList, d3TripList, d4TripList, d5TripList, d6TripList, d7TripList;
    
    //For settings
    private JLabel bdgtxt, usertxt, passtxt;
    private JTextField bdgtChange, userChange;
    private JPasswordField passChange;
    private JButton bdgtSave, userSave, passSave;
    
    //For info at bottom
    private JTextArea info;
    private double accBudget;
    private double accRemaining;
    private int accTotBus;
    private int accTotPpl;

    private WelcomeScreen thisWS; //previous screen
    private Account thisAcc;
    private AddTripScreen thisATS;
    private EditTripNavigation thisETN;
    private TripDisplayScreen thisTDS;

    public TripDisplayScreen(WelcomeScreen ws, Account acc) {

        /*Setting up user info (Making sure windows share same data for user)*/
        thisWS = ws;
        thisAcc = acc;
        thisTDS = this;

        ws.setVisible(false); /*Turns off welcome screen while trip screen is open*/

        //Labelling the frame/window   
        setTitle("User " + thisAcc.getUsername() + "'s Plan");      
        //Setting up program icon
        Image icon = ws.getIconImage();    
        setIconImage(icon);      
        
        
        //SAMPLE DATA (TO BE DELETED LATER)
        //public void addTripToDay(int dayNum, Trip t)
        /*
        thisAcc.addTripToDay(2, new Trip("Quick Trip 1", new Bus(BusType.Small), 8, "13", "30"));
        thisAcc.addTripToDay(2, new Trip("Holland Students", new Bus(BusType.Medium), 17, "03", "30"));
        thisAcc.addTripToDay(2, new Trip("Ochi Tourists", new Bus(BusType.Luxurious), 36,"03", "00"));
        thisAcc.addTripToDay(2, new Trip("Mobay Tourists", new Bus(BusType.Luxurious), 40,"00", "43"));
        thisAcc.addTripToDay(2, new Trip("Quick Trip 2", new Bus(BusType.Medium), 23,"00", "10"));
        thisAcc.addTripToDay(2, new Trip("Quick Trip 4", new Bus(BusType.Medium), 16,"03", "56"));
        */
        //thisAcc.setTheme("RED");
        //thisWS.updateAccData(thisAcc);

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
        switch (thisAcc.getTheme()) {
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
            case "LIGHTBLUE":
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



        //=========================================//
        //=    CREATING THE TRIPS' TABBEDPANE     =//
        //=========================================//
        //**NOTE: Created the class TableData to prevent longer repetitive code. 
        //        It creates a table.

        //================//
        //=   TAB/DAY 1  =//
        //================//
        //SETTING UP TAB 1 (DAY 1)
        JPanel tab1Pnl = new JPanel(new BorderLayout());
        tab1Pnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
        "DAY 1 TRIPS", TitledBorder.LEFT, TitledBorder.BELOW_BOTTOM));        
        tab1Pnl.setBackground(Color.LIGHT_GRAY); 

        //SETTING UP TABLE FOR TAB 1 (DAY 1)
        //Creates a table and adds it to this tab
        day1Table = new TableData(tab1Pnl); 

        //SETTING UP LIST OF TRIPS
        d1TripList = thisAcc.getDayTrips(1);        
        
        //ADDING ELEMENTS TO TABLE
        //This adds each trip to the table for that day
        showTable(d1TripList, day1Table);
        
    
        //================//
        //=   TAB/DAY 2  =//
        //================//
        //SETTING UP TAB 2 (DAY 2)
        JPanel tab2Pnl = new JPanel(new BorderLayout());
        tab2Pnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
        "DAY 2 TRIPS", TitledBorder.LEFT, TitledBorder.BELOW_BOTTOM));
        tab2Pnl.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 2 (DAY 2)
        day2Table = new TableData(tab2Pnl);

        //SETTING UP LIST OF TRIPS
        d2TripList = thisAcc.getDayTrips(2);        
        
        //ADDING ELEMENTS TO TABLE
        //This adds each trip to the table for that day
        showTable(d2TripList, day2Table);


        //================//
        //=  TAB/DAY 3   =//
        //================//
        //SETTING UP TAB 3 (DAY 3)        
        JPanel tab3Pnl = new JPanel(new BorderLayout());
        tab3Pnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
        "DAY 3 TRIPS", TitledBorder.LEFT, TitledBorder.BELOW_BOTTOM));        
        tab3Pnl.setBackground(Color.LIGHT_GRAY); 

        //SETTING UP TABLE FOR TAB 3 (DAY 3)
        //Creates a table and adds it to this tab
        day3Table = new TableData(tab3Pnl); 

        //SETTING UP LIST OF TRIPS
        d3TripList = thisAcc.getDayTrips(3);        
        
        //ADDING ELEMENTS TO TABLE
        //This adds each trip to the table for that day
        showTable(d3TripList, day3Table);


        //================//
        //=  TAB/DAY 4   =//
        //================//        
        //SETTING UP TAB 4 (DAY 4)
        JPanel tab4Pnl = new JPanel(new BorderLayout());
        tab4Pnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
        "DAY 4 TRIPS", TitledBorder.LEFT, TitledBorder.BELOW_BOTTOM));        
        tab4Pnl.setBackground(Color.LIGHT_GRAY); 

        //SETTING UP TABLE FOR TAB 4 (DAY 4)
        //Creates a table and adds it to this tab
        day4Table = new TableData(tab4Pnl); 

        //SETTING UP LIST OF TRIPS
        d4TripList = thisAcc.getDayTrips(4);        
        
        //ADDING ELEMENTS TO TABLE
        //This adds each trip to the table for that day
        showTable(d4TripList, day4Table);
        

        //================//
        //=  TAB/DAY 5   =//
        //================//
        //SETTING UP TAB 5 (DAY 5)
        JPanel tab5Pnl = new JPanel(new BorderLayout());
        tab5Pnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
        "DAY 5 TRIPS", TitledBorder.LEFT, TitledBorder.BELOW_BOTTOM));        
        tab5Pnl.setBackground(Color.LIGHT_GRAY); 

        //SETTING UP TABLE FOR TAB 5 (DAY 5)
        //Creates a table and adds it to this tab
        day5Table = new TableData(tab5Pnl); 

        //SETTING UP LIST OF TRIPS
        d5TripList = thisAcc.getDayTrips(5);        
        
        //ADDING ELEMENTS TO TABLE
        //This adds each trip to the table for that day
        showTable(d5TripList, day5Table);  
        

        //================//
        //=  TAB/DAY 6   =//
        //================//
        //SETTING UP TAB 6 (DAY 6)
        JPanel tab6Pnl = new JPanel(new BorderLayout());
        tab6Pnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
        "DAY 6 TRIPS", TitledBorder.LEFT, TitledBorder.BELOW_BOTTOM));        
        tab6Pnl.setBackground(Color.LIGHT_GRAY); 

        //SETTING UP TABLE FOR TAB 6 (DAY 6)
        //Creates a table and adds it to this tab
        day6Table = new TableData(tab6Pnl); 

        //SETTING UP LIST OF TRIPS
        d6TripList = thisAcc.getDayTrips(6);        
        
        //ADDING ELEMENTS TO TABLE
        //This adds each trip to the table for that day
        showTable(d6TripList, day6Table);


        //================//
        //=  TAB/DAY 7   =//
        //================//
        //SETTING UP TAB 7 (DAY 7)
        JPanel tab7Pnl = new JPanel(new BorderLayout());
        tab7Pnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
        "DAY 7 TRIPS", TitledBorder.LEFT, TitledBorder.BELOW_BOTTOM));        
        tab7Pnl.setBackground(Color.LIGHT_GRAY); 

        //SETTING UP TABLE FOR TAB 7 (DAY 7)
        //Creates a table and adds it to this tab
        day7Table = new TableData(tab7Pnl); 

        //SETTING UP LIST OF TRIPS
        d7TripList = thisAcc.getDayTrips(7);        
        
        //ADDING ELEMENTS TO TABLE
        //This adds each trip to the table for that day
        showTable(d7TripList, day7Table);

        


        //======================//
        //=   TAB 8/SETTINGS   =//
        //======================//
        //SETTING UP SETTINGS TAB
        JPanel tab8Pnl = new JPanel();
        tab8Pnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
        "SETTINGS", TitledBorder.LEFT, TitledBorder.TOP));
        tab8Pnl.setBackground(new Color(201,203,205));  

        //=================================//
        //=  IMPLEMENTING SELECTED THEME  =//
        //=================================//
        JLabel themetxt = new JLabel("Theme: ");
        String [] themeOpts = {"GRAY", "PINK", "RED", "GREEN", "LIGHT BLUE", "PURPLE", "Default"};
        JComboBox<String> themes = new JComboBox<>(themeOpts);
        if(!(thisAcc.getTheme().equals("LIGHTBLUE"))){
            themes.setSelectedItem(thisAcc.getTheme());  
        } else {
            themes.setSelectedItem("LIGHT BLUE");  
        }
        JPanel themeArea = new JPanel();
        themeArea.setPreferredSize(new Dimension(590,30));
        themeArea.setOpaque(false);

        themeArea.add(themetxt);
        themeArea.add(themes);
        
        /*Functionality for color customization*/
        themes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String s = (String) themes.getSelectedItem();
                switch (s) {
                    case "PINK":
                        contsPnl.setBackground(new Color(210,143,218));
                        disPnl.setBackground(new Color(210,143,218));
                        btnPnl.setBackground(new Color(210,143,218));
                        thisAcc.setTheme("PINK");
                        break;
                    case "RED":
                        contsPnl.setBackground(new Color(142,49,80));
                        disPnl.setBackground(new Color(142,49,80));
                        btnPnl.setBackground(new Color(142,49,80));
                        thisAcc.setTheme("RED");
                        break; 
                    case "LIGHT BLUE":
                        contsPnl.setBackground(new Color(152,182,248));
                        disPnl.setBackground(new Color(152,182,248));
                        btnPnl.setBackground(new Color(152,182,248));
                        thisAcc.setTheme("LIGHTBLUE");
                        break;
                    case "GREEN":
                        contsPnl.setBackground(new Color(85,111,111));
                        disPnl.setBackground(new Color(85,111,111));
                        btnPnl.setBackground(new Color(85,111,111));
                        thisAcc.setTheme("GREEN");
                        break;
                    case "PURPLE":
                        contsPnl.setBackground(new Color(104,54,137));
                        disPnl.setBackground(new Color(104,54,137));
                        btnPnl.setBackground(new Color(104,54,137));
                        thisAcc.setTheme("PURPLE");
                        break;
                    case "GRAY":
                        contsPnl.setBackground(new Color(145,143,156));
                        disPnl.setBackground(new Color(145,143,156));
                        btnPnl.setBackground(new Color(145,143,156));
                        thisAcc.setTheme("GRAY");
                        break;
                    case "Default":
                        contsPnl.setBackground(new Color(55,73,136));
                        disPnl.setBackground(new Color(55,73,136));
                        btnPnl.setBackground(new Color(55,73,136));
                        thisAcc.setTheme("Default");
                }

                thisWS.deleteAccData(thisAcc);
                thisWS.createAccData(thisAcc);
            }
        });

        /*Account info adjustment/change settings*/
        JPanel settPnl = new JPanel(new BorderLayout());
        settPnl.setPreferredSize(new Dimension(768,315));

        JPanel settingsArea = new JPanel(new FlowLayout(10, 25, 8));
        settingsArea.setPreferredSize(new Dimension(215,35));
        settingsArea.setOpaque(false);

        bdgtxt = new JLabel("Adjust Budget $: ");
        bdgtChange = new JTextField(15);
        bdgtSave = new JButton("Save");
        //budget save (to account) listener here
        bdgtSave.addActionListener(new BdgtSaveListener());
         

        usertxt = new JLabel("Change Username: ");
        userChange = new JTextField(15);
        userSave = new JButton("Save");
        //username save (to account) listener here
        userSave.addActionListener(new UserSaveListener());

        passtxt = new JLabel("Change Password: ");
        passChange = new JPasswordField(15);
        passSave = new JButton("Save");
        //password save (to account) listener here
        passSave.addActionListener(new PassSaveListener());

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
        tab8Pnl.add(settPnl);


        //===============================//
        //=  ADDING TABS TO TABBEDPANE  =//
        //===============================//
        tripTabs = new JTabbedPane();
        //tripTabs.setBounds(10,500,760,330); 
        tripTabs.add("DAY 1", tab1Pnl);
        tripTabs.add("DAY 2", tab2Pnl);  
        tripTabs.add("DAY 3", tab3Pnl); 
        tripTabs.add("DAY 4", tab4Pnl);
        tripTabs.add("DAY 5", tab5Pnl);
        tripTabs.add("DAY 6", tab6Pnl);
        tripTabs.add("DAY 7", tab7Pnl);          
        tripTabs.add("SETTINGS", tab8Pnl);   

        //Adding the whole tabbedpane to display panel
        disPnl.add(tripTabs);




        //=======================================================//
        //=           CREATING THE USER INFO AT BOTTOM          =//
        //=======================================================//
        //The user info should come from account and be displayed here

        info = new JTextArea();
        updateInfo(); //makes sure info displayed is what is currently in account
        info.setOpaque(false);       

        //Adding info to its panel
        infoPnl.add(info);       


                //========================================//
        //=   CREATING THE BUTTONS AT BOTTOM     =//
        //========================================//
        bNewTrip = new JButton("New Trip");
        bNewTrip.addActionListener(new AddTBtnListener());

        bEditTrip = new JButton("Edit Trip");
        bEditTrip.addActionListener(new EditTBtnListener());

        bClose = new JButton("Back to Login");
        bClose.addActionListener(new CloseBtnListener()); 
        
        String [] sortOpts = {"<<Sort By>>", "Trip ID", "Trip Name", "# of People", "Time"};
        JComboBox<String> bSortBy = new JComboBox<String>(sortOpts);
        selected = (String)bSortBy.getSelectedItem();
        bSortBy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
        {
            int thisTab = (int)tripTabs.getSelectedIndex();

            String s = (String) bSortBy.getSelectedItem();
                switch (s) {
                    case "Trip ID":                   
                        SortByID(thisTab);                      
                        break;

                    case "Trip Name":
                        SortByName(thisTab);
                        break; 

                    case "# of People":
                        SortByPpl(thisTab);
                        break;

                    case "Time":
                        SortByTime(thisTab);
                        break;

                    default:
                        SortByID(thisTab);                      
                        break;

                }

            //showTable(d1TripList, day1Table);

        }});


        
        //Adding the buttons to button panel
        btnPnl.add(bSortBy, gbc);
        btnPnl.add(bNewTrip, gbc);
        btnPnl.add(bEditTrip, gbc);
        btnPnl.add(bClose, gbc);


        //========================================================//
        //=          ADDING MAIN PANELS TO CONTENT PANEL         =//
        //========================================================//
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



    //====================================================//
    //=             METHODS FOR THIS SCREEN              =//
    //====================================================// 
    /*Method to update account info at bottom of screen*/
    public void updateInfo(){
        accBudget = thisAcc.getBudget();
        accRemaining = thisAcc.getRemaining();
        accTotBus = thisAcc.getTotBuses(); //needs to reflect total buses that have been scheduled in account
        accTotPpl = thisAcc.getTotPpl(); //needs to reflect total people that have been scheduled in account

        info.setText("Total Budget: $" + accBudget + "\tRemaining: $" + accRemaining + 
        "\tBuses Scheduled: " + accTotBus + "\tTotal Persons Booked: " + accTotPpl);

        Font boldFont = new Font(info.getFont().getName(), Font.BOLD, info.getFont().getSize());
        info.setFont(boldFont);    

        thisWS.deleteAccData(thisAcc);
        thisWS.createAccData(thisAcc);

    }
    
    
    /*Ensures that each trip in the list of trips are added to the table for the given day*/
    public void showTable(ArrayList<Trip> tripList, TableData dayTable)
    {
        if (tripList.size() > 0){
            for (Trip t: tripList) {
                makeTripRecord(t, dayTable);
            }
        }      
    }


    /*Stores data from trip as an array and passes it to a method to add it to that day's table*/
    public void makeTripRecord(Trip t, TableData dayTable) {
        // format: "Trip Name", "Bus Type", "Bus ID#", "# of People", "Trip ID#",
        // "Time", "Trip Completed?"
        String[] singleTrip = { t.getName(), "" + t.getBus().getType() + "",
                "#B" + t.getBus().getID(), "" + t.getNumOfPpl(),
                "#T" + t.getID(), "" + t.getHrs() + ":" + t.getMins(), "" + t.isCompleted() };

        addTripRecord(singleTrip, dayTable);
        
        thisWS.deleteAccData(thisAcc);
        thisWS.createAccData(thisAcc);
    }


    /*Adds a single trip to given day's table*/
    public void addTripRecord(String[] trip, TableData dayTable){
        dayTable.getModel().addRow(trip);
    }

     
    public TableData getDayTable(int day){
        switch(day){
            case 1:
                daysTable = day1Table;
                break;

            case 2:
                daysTable = day2Table;
                break;

            case 3:
                daysTable = day3Table;
                break;

            case 4:
                daysTable = day4Table;
                break;

            case 5:
                daysTable = day5Table;
                break;

            case 6:
                daysTable = day6Table;
                break;

            case 7:
                daysTable = day7Table;
        }
        
        return daysTable;
    }

    //=========================================================//
    //=           BUTTON LISTENING FUNCTIONALITIES            =//
    //=========================================================//

    //=====================================//
    //=     SETTINGS BUTTON LISTENERS     =//
    //=====================================//
    /*Budget save (to account) listener saves a new budget to the associated account*/
    private class BdgtSaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {   
            /*Checking to see if input is a valid double*/
            boolean isDouble;
            try {
                Double.parseDouble(bdgtChange.getText());
                isDouble = true;
            }
            catch(Exception ex) {
                isDouble = false;
            }

            if (bdgtChange.getText() != null && isDouble) {
                int confirm = JOptionPane.showConfirmDialog(thisATS,"Are you sure? \nYour new Budget will be: \n$" 
                + bdgtChange.getText());  
                if (confirm == JOptionPane.YES_OPTION) {  
                    thisAcc.setBudget(Double.parseDouble(bdgtChange.getText()));
                    updateInfo(); //updating the displayed info at bottom
                    bdgtChange.setText(""); //clears budget textfield
                }
            } else {
                bdgtChange.setText("Please enter valid number.");              
            }

            thisWS.deleteAccData(thisAcc);
            thisWS.createAccData(thisAcc);
        }

    }

    /*Username save (to account) listener*/
    private class UserSaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           String [] userCheck = userChange.getText().split(" ");

            if (userChange.getText() != null && userCheck.length == 1) {
                int confirm = JOptionPane.showConfirmDialog(thisATS,"Are you sure? \nYour new Username will be: \n" 
                + userChange.getText());  
                if (confirm == JOptionPane.YES_OPTION) {  
                    thisWS.deleteAccData(thisAcc);
                    thisWS.updateAccounts(" " + thisAcc.getUsername() + " ", " " + userChange.getText() + " ");
                    thisAcc.setUsername(userChange.getText());

                    updateInfo(); //updating the displayed info at bottom
                    userChange.setText(""); //clears username textfield
                    //Changing the frame/window name  
                    setTitle("User " + thisAcc.getUsername() + "'s Plan");
                   
                }
            } else {
                userChange.setText("Please enter valid username."); //clears budget textfield                
            }
            
            thisWS.createAccData(thisAcc);
        }

    }

    /*Password save (to account) listener*/
    private class PassSaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int confirm = JOptionPane.showConfirmDialog(thisATS,"Are you sure? \nYour new Password will be: \n" 
            + String.valueOf(passChange.getPassword()));  
            if(confirm == JOptionPane.YES_OPTION) {  
                thisWS.deleteAccData(thisAcc);
                thisWS.updateAccounts(" " + thisAcc.getPassword(), " " + String.valueOf(passChange.getPassword()));
                thisAcc.setPassword(String.valueOf(passChange.getPassword()));

                passChange.setText(""); //clears password textfield
            }  

            thisWS.createAccData(thisAcc);
        }

    }



    //=======================================//
    //=    TRIP DISPLAY BUTTON LISTENERS    =//
    //=======================================//

    //SORT BY ID
    public void SortByID(int day){
        switch(day+1){
            case 1:
                day1Table.getModel().setRowCount(0);
                Collections.sort(d1TripList);
                showTable(d1TripList, day1Table);
                break;
            case 2:
                day2Table.getModel().setRowCount(0);
                Collections.sort(d2TripList);
                showTable(d2TripList, day2Table);
                break;
            case 3:
                day3Table.getModel().setRowCount(0);
                Collections.sort(d3TripList);
                showTable(d3TripList, day3Table);
                break;
            case 4:
                day4Table.getModel().setRowCount(0);
                Collections.sort(d4TripList);
                showTable(d4TripList, day4Table);
                break;
            case 5:
                day5Table.getModel().setRowCount(0);
                Collections.sort(d5TripList);
                showTable(d5TripList, day5Table);
                break;
            case 6:
                day6Table.getModel().setRowCount(0);
                Collections.sort(d6TripList);
                showTable(d6TripList, day6Table);
                break;
            case 7:
                day7Table.getModel().setRowCount(0);
                Collections.sort(d7TripList);
                showTable(d7TripList, day7Table);
        }
    }

    //SORT BY NAME
    public void SortByName(int day){
        NameComparator NameCompare = new NameComparator();

        switch(day+1){
            case 1:
                day1Table.getModel().setRowCount(0);
                Collections.sort(d1TripList, NameCompare);
                showTable(d1TripList, day1Table);
                break;
            case 2:
                day2Table.getModel().setRowCount(0);
                Collections.sort(d2TripList, NameCompare);
                showTable(d2TripList, day2Table);
                break;
            case 3:
                day3Table.getModel().setRowCount(0);
                Collections.sort(d3TripList, NameCompare);
                showTable(d3TripList, day3Table);
                break;
            case 4:
                day4Table.getModel().setRowCount(0);
                Collections.sort(d4TripList, NameCompare);
                showTable(d4TripList, day4Table);
                break;
            case 5:
                day5Table.getModel().setRowCount(0);
                Collections.sort(d5TripList, NameCompare);
                showTable(d5TripList, day5Table);
                break;
            case 6:
                day6Table.getModel().setRowCount(0);
                Collections.sort(d6TripList, NameCompare);
                showTable(d6TripList, day6Table);
                break;
            case 7:
                day7Table.getModel().setRowCount(0);
                Collections.sort(d7TripList, NameCompare);
                showTable(d7TripList, day7Table);
        }
    }

    //SORT BY NO. OF PEOPLE
    public void SortByPpl(int day){
        PplComparator PplCompare = new PplComparator();

        switch(day+1){
            case 1:
                day1Table.getModel().setRowCount(0);
                Collections.sort(d1TripList, PplCompare);
                showTable(d1TripList, day1Table);
                break;
            case 2:
                day2Table.getModel().setRowCount(0);
                Collections.sort(d2TripList, PplCompare);
                showTable(d2TripList, day2Table);
                break;
            case 3:
                day3Table.getModel().setRowCount(0);
                Collections.sort(d3TripList, PplCompare);
                showTable(d3TripList, day3Table);
                break;
            case 4:
                day4Table.getModel().setRowCount(0);
                Collections.sort(d4TripList, PplCompare);
                showTable(d4TripList, day4Table);
                break;
            case 5:
                day5Table.getModel().setRowCount(0);
                Collections.sort(d5TripList, PplCompare);
                showTable(d5TripList, day5Table);
                break;
            case 6:
                day6Table.getModel().setRowCount(0);
                Collections.sort(d6TripList, PplCompare);
                showTable(d6TripList, day6Table);
                break;
            case 7:
                day7Table.getModel().setRowCount(0);
                Collections.sort(d7TripList, PplCompare);
                showTable(d7TripList, day7Table);
        }
    }

    //SORT BY TIME
    public void SortByTime(int day){
        TimeComparator TimeCompare = new TimeComparator();

        switch(day+1){
            case 1:
                day1Table.getModel().setRowCount(0);
                Collections.sort(d1TripList, TimeCompare);
                showTable(d1TripList, day1Table);
                break;
            case 2:
                day2Table.getModel().setRowCount(0);
                Collections.sort(d2TripList, TimeCompare);
                showTable(d2TripList, day2Table);
                break;
            case 3:
                day3Table.getModel().setRowCount(0);
                Collections.sort(d3TripList, TimeCompare);
                showTable(d3TripList, day3Table);
                break;
            case 4:
                day4Table.getModel().setRowCount(0);
                Collections.sort(d4TripList, TimeCompare);
                showTable(d4TripList, day4Table);
                break;
            case 5:
                day5Table.getModel().setRowCount(0);
                Collections.sort(d5TripList, TimeCompare);
                showTable(d5TripList, day5Table);
                break;
            case 6:
                day6Table.getModel().setRowCount(0);
                Collections.sort(d6TripList, TimeCompare);
                showTable(d6TripList, day6Table);
                break;
            case 7:
                day7Table.getModel().setRowCount(0);
                Collections.sort(d7TripList, TimeCompare);
                showTable(d7TripList, day7Table);
        }
    }

  

    public class NameComparator implements Comparator<Trip> 
    {        
        public int compare(Trip t1, Trip t2)
        {
            String [] name1 = t1.getName().split(" ");
            String [] name2 = t2.getName().split(" ");
            return name1[0].compareTo(name2[0]);   
        }
        
    }

    /*Compares the number of persons */
    public class PplComparator implements Comparator<Trip> 
    {        
        public int compare(Trip t1, Trip t2)
        {
            return t1.getNumOfPpl() - t2.getNumOfPpl();   
        }
        
    }

    public class TimeComparator implements Comparator<Trip> {

        public int compare(Trip t1, Trip t2) {
            int t1Hrs = Integer.parseInt(t1.getHrs());     
            int t1Mins = Integer.parseInt(t1.getMins());
            int t2Hrs = Integer.parseInt(t2.getHrs());
            int t2Mins = Integer.parseInt(t2.getMins());

            if (t1Hrs == t2Hrs) {
                return Integer.compare(t1Mins, t2Mins);
            }
            return Integer.compare(t1Hrs, t2Hrs);
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
            if(thisAcc.getBudget() == 0){
                JOptionPane.showMessageDialog(thisTDS, "You cannot afford this.\nPlease adjust budget in Settings.");
            
            } else {
                thisATS = new AddTripScreen(thisTDS, thisAcc);
            }
        }

    }

    //Close (Bk To Welcome Screen)
    private class CloseBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            thisWS.deleteAccData(thisAcc);
            thisWS.createAccData(thisAcc);
            setVisible(false); //stops displaying window/frame
            thisWS.setWelcomeTheme(thisAcc.getTheme()); //changes welcome to fit account theme
            thisWS.setVisible(true); //makes welcome screen visible again
        }

    }

} //public class TripDisplayScreen() end 
