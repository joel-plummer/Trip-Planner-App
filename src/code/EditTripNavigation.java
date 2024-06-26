package code;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This represents the screen used to locate & manipulate a desired trip
 */

public class EditTripNavigation extends JFrame {

    private JPanel disPnl;
    private JPanel topPnl, bttmPnl;
    private JPanel dPnl1, dPnl2, dPnl3;
    private JPanel btnPnl;

    private JButton bDelete;
    private JButton bEdit;
    private JButton bCancel;

    private JLabel errorMsg;
    private JLabel daytxt, triptxt;
    private JComboBox<String> days, trips;

    private ArrayList<Trip> tripList;

    private Account thisAcc;
    private TripDisplayScreen thisTDS;
    private EditTripNavigation thisETN;
    private EditTripScreen thisETS;

    /**
     * This allows the user to select a trip to them be referred to the Edit trip screen for editing and deletion of the trip.
     * @param tds is the Trip Display screen 
     * @param acc is the user Account 
     */

    public EditTripNavigation(TripDisplayScreen tds, Account acc)
    {

        /**
         * This sets up user information to enssure that the windows share same data for user 
         */    
        thisAcc = acc;
        thisTDS = tds;
        thisETN = this;

        //Labelling the frame/window   
        setTitle("Select A Trip");      
        //Setting up program icon
        Image icon = thisTDS.getIconImage();    
        setIconImage(icon);
        //Declaring layout
        setLayout(new BorderLayout());

        //=================================================//
        //=                SETTING UP PANELS              =//
        //=================================================//
        disPnl = new JPanel(new BorderLayout()); //For inputting area
        btnPnl = new JPanel(new GridBagLayout()); //For buttons


        //============================================//
        //=   STRUCTURING & CREATING DISPLAY PANEL   =//
        //============================================// 
        disPnl.setPreferredSize(new Dimension(360,135));
        disPnl.setBackground(new Color(195,195,195)); 

        topPnl = new JPanel(new FlowLayout(10, 15, 9));
        topPnl.setOpaque(false);
        bttmPnl = new JPanel(new FlowLayout(10, 14, 7));
        bttmPnl.setOpaque(false);


        dPnl1 = new JPanel();
        dPnl1.setOpaque(false);
        daytxt = new JLabel("Enter Day: ");
        String [] dayOpts = {"<<Select Day>>","DAY 1", "DAY 2", "DAY 3", "DAY 4", 
        "DAY 5", "DAY 6", "DAY 7"};
        days = new JComboBox<>(dayOpts);  
        dPnl2 = new JPanel();
        dPnl2.setOpaque(false);
        trips = new JComboBox<>();  
        trips.addItem("<<Select Trip>>");
        days.addActionListener(new ActionListener() 
        {
            /**
             * This populates the trip menu based on a selected day
            */

            public void actionPerformed(ActionEvent e)
            {   String s = (String) days.getSelectedItem();
                
                switch (s) {
                    case "DAY 1":
                        trips.removeAllItems();
                        trips.addItem("<<Select Trip>>");
                        tripList=thisAcc.getDayTrips(1);    
                        Collections.sort(tripList);                 
                        for (Trip i:tripList){
                            trips.addItem("Trip ID#"+i.getID()+" : " +i.getName());
                        }                     
                        break;
                    case "DAY 2":
                        trips.removeAllItems();
                        trips.addItem("<<Select Trip>>");
                        tripList=thisAcc.getDayTrips(2);    
                        Collections.sort(tripList);                    
                        for (Trip i:tripList){
                            trips.addItem("Trip ID#"+i.getID()+" : " +i.getName());
                        } 
                        break;
                    case "DAY 3":
                        trips.removeAllItems();
                        trips.addItem("<<Select Trip>>");
                        tripList=thisAcc.getDayTrips(3);  
                        Collections.sort(tripList);                      
                        for (Trip i:tripList){
                            trips.addItem("Trip ID#"+i.getID()+" : " +i.getName());
                        } 

                        break;
                    case "DAY 4":
                        trips.removeAllItems();
                        trips.addItem("<<Select Trip>>");
                        tripList=thisAcc.getDayTrips(4);          
                        Collections.sort(tripList);              
                        for (Trip i:tripList){
                            trips.addItem("Trip ID#"+i.getID()+" : " +i.getName());
                        } 
                        break;
                    case "DAY 5":
                        trips.removeAllItems();
                        trips.addItem("<<Select Trip>>");
                        tripList=thisAcc.getDayTrips(5);   
                        Collections.sort(tripList);                     
                        for (Trip i:tripList){
                            trips.addItem("Trip ID#"+i.getID()+" : " +i.getName());
                        }   
                        break;
                    case "DAY 6":
                        trips.removeAllItems();
                        trips.addItem("<<Select Trip>>");
                        tripList=thisAcc.getDayTrips(6);     
                        Collections.sort(tripList);                   
                        for (Trip i:tripList){
                            trips.addItem("Trip ID#"+i.getID()+" : " +i.getName());
                        } 
                        break;
                    case "DAY 7":
                        trips.removeAllItems();
                        trips.addItem("<<Select Trip>>");
                        tripList=thisAcc.getDayTrips(7);     
                        Collections.sort(tripList);                   
                        for (Trip i:tripList){
                            trips.addItem("Trip ID#"+i.getID()+" : " +i.getName());
                        } 
                        break;

                }

            }
        });
        
        
        dPnl1.add(daytxt);
        dPnl1.add(days);
        topPnl.add(dPnl1); 

        dPnl2 = new JPanel();
        dPnl2.setOpaque(false);   
        triptxt = new JLabel("Select Trip on Day: "); 
        dPnl2.add(triptxt);
        dPnl2.add(trips);
        bttmPnl.add(dPnl2); 
        
        dPnl3 = new JPanel();
        dPnl3.setOpaque(false);

        /**
         * This provides infratructure for displaying error messages when needed.
         */
        errorMsg = new JLabel("");
        errorMsg.setForeground(new Color(237,28,36)); 
        errorMsg.setOpaque(false);       
        dPnl3.add(errorMsg);
        bttmPnl.add(dPnl3); 


        /**
         * Adding to panels to the display
         */
        disPnl.add(topPnl, BorderLayout.NORTH);
        disPnl.add(bttmPnl, BorderLayout.CENTER);


        //========================================//
        //=   CREATING THE BUTTONS AT BOTTOM     =//
        //========================================//      
        btnPnl.setBackground(new Color(230,177,0));
        btnPnl.setSize(780,10);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);

        bDelete = new JButton("Delete");
        bDelete.addActionListener(new DeleteBtnListener()); 

        bEdit = new JButton("Edit");
        bEdit.addActionListener(new EditBtnListener()); 

        bCancel = new JButton("Cancel");
        bCancel.addActionListener(new CloseBtnListener()); 

        btnPnl.add(bDelete, gbc);
        btnPnl.add(bEdit, gbc);
        btnPnl.add(bCancel, gbc);


        /**
         * Adding main panels to frame/window
         */
        add(disPnl, BorderLayout.NORTH);
        add(btnPnl, BorderLayout.SOUTH);


        //=========================================//
        //=       MAINTAINING SELECTED THEME      =//
        //=========================================//       
        switch (thisAcc.getTheme()) {
            case "PINK":
                disPnl.setBackground(new Color(210,143,218));
                daytxt.setForeground(Color.BLACK);
                triptxt.setForeground(Color.BLACK);
                break;
            case "RED":
                disPnl.setBackground(new Color(142,49,80));
                daytxt.setForeground(Color.WHITE);
                triptxt.setForeground(Color.WHITE);
                break; 
            case "LIGHTBLUE":
                disPnl.setBackground(new Color(152,182,248));
                daytxt.setForeground(Color.BLACK);
                triptxt.setForeground(Color.BLACK);
                break;
            case "GREEN":
                disPnl.setBackground(new Color(85,111,111));
                daytxt.setForeground(Color.WHITE);
                triptxt.setForeground(Color.WHITE);
                break;
            case "PURPLE":
                disPnl.setBackground(new Color(104,54,137));
                daytxt.setForeground(Color.WHITE);
                triptxt.setForeground(Color.WHITE);
                break;
            case "GRAY":
                disPnl.setBackground(new Color(145,143,156));
                daytxt.setForeground(Color.BLACK);
                triptxt.setForeground(Color.BLACK);
                break;
            case "Default":
                disPnl.setBackground(new Color(55,73,136));
                daytxt.setForeground(Color.WHITE);
                triptxt.setForeground(Color.WHITE);
        }


        /**
         * Extra frame set up things
         */
        pack();
        setResizable(false);
        setVisible(true); 

    } //public EditTripNavigation(TripDisplayScreen tds, Account acc) end (constructor)


    //=========================================================//
    //=           BUTTON LISTENING FUNCTIONALITIES            =//
    //=========================================================//

    /**
     * This button listener deletes a selected trip from the user's trip list
     */    
    private class DeleteBtnListener implements ActionListener
    {   
        Trip trip = new Trip();
        public void actionPerformed(ActionEvent e) {
                        
            /**
             * This checks if the user has selected day and trip
             */
            if (days.getSelectedItem().toString() == "<<Select Day>>")
                errorMsg.setText("Please Select a Day & Trip.");

            else if (trips.getSelectedItem().toString() == "<<Select Trip>>")
                errorMsg.setText("Please Select a Day & Trip.");

                
            /**
             * If user does select a day, all the selected trips for that day are loaded
             */
            else {
                int dayselected=0;
                switch(days.getSelectedItem().toString()){
                    case "DAY 1":
                    dayselected=1;
                        tripList=thisAcc.getDayTrips(1);
                        Collections.sort(tripList);
                        for (int i=0; i<tripList.size(); i++)
                            if (i+1==trips.getSelectedIndex())
                                trip=tripList.get(i);                                 
                    break;
                case "DAY 2":
                    dayselected=2;
                    tripList=thisAcc.getDayTrips(2);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);                    
                    break;
                case "DAY 3":
                    dayselected=3;
                    tripList=thisAcc.getDayTrips(3);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);
                    break;
                case "DAY 4":
                    dayselected=4;
                    tripList=thisAcc.getDayTrips(4);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);                    
                    break;
                case "DAY 5":
                    dayselected=5;
                    tripList=thisAcc.getDayTrips(5);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);
                    break;
                case "DAY 6":
                    dayselected=6;
                    tripList=thisAcc.getDayTrips(6);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);
                    break;
                case "DAY 7":
                    dayselected=7;
                    tripList=thisAcc.getDayTrips(7);    
                    Collections.sort(tripList);                 
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);
                    break;

                }
                
                double cost = 0;
                switch(trip.getBus().getType().toString()){
                    case ("Small"):
                        cost = 5000;            
                        break;
                    case("Medium"):
                        cost = 7000;
                        break;
                    case("Luxurious"):
                        cost = 12000;
                        break;
                }  

                double newBudget = thisAcc.getBudget();
                //newBudget = thisAcc.getBudget() + (trip.getBus().calcBus(trip.getBus().getType(), trip.getNumOfPpl()));
                newBudget = thisAcc.getBudget() + cost;

                /**
                 * The user is asked to confirm if they wish to remove the selected trip. 
                 * If yes, the trip is removed from the trip list and the current window is closed.
                 */

                int confirm = JOptionPane.showConfirmDialog(thisETN,
                "Are you sure you want to delete? \nYour budget will be increased \nto: $" + newBudget);  

                if (confirm == JOptionPane.YES_OPTION) {                 
                    thisAcc.removeTripfromDay(dayselected,trip);
                    thisAcc.setBudget(newBudget);
                    thisTDS.getDayTable(dayselected).getModel().setRowCount(0);
                    thisTDS.showTable(thisAcc.getDayTrips(dayselected), thisTDS.getDayTable(dayselected));
                    thisTDS.updateInfo();
                    setVisible(false);
                }  
            }
        
        }
    }


    /**
     * This allows the user to edit a specified trip's details 
     */
    private class EditBtnListener implements ActionListener
    {
        Trip trip = new Trip();
        public void actionPerformed(ActionEvent e) {

            /**
             * This checks if user has selected day and trip
             */
            if (days.getSelectedItem().toString() == "<<Select Day>>")
                errorMsg.setText("Please Select a Day & Trip.");

            else if (trips.getSelectedItem().toString() == "<<Select Trip>>")
                errorMsg.setText("Please Select a Day & Trip.");

            /**
             * If user does select a day and trip, the information loads into an edit trip screen
             */
            else {

                int dayselected=0;
                switch(days.getSelectedItem().toString()){
                    case "DAY 1":
                    dayselected=1;
                    tripList=thisAcc.getDayTrips(1);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);                                   
                    break;
                case "DAY 2":
                    dayselected=2;
                    tripList=thisAcc.getDayTrips(2);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);                    
                    break;
                case "DAY 3":
                    dayselected=3;
                    tripList=thisAcc.getDayTrips(3);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);
                    break;
                case "DAY 4":
                    dayselected=4;
                    tripList=thisAcc.getDayTrips(4);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);                    
                    break;
                case "DAY 5":
                    dayselected=5;
                    tripList=thisAcc.getDayTrips(5);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);
                    break;
                case "DAY 6":
                    dayselected=6;
                    tripList=thisAcc.getDayTrips(6);
                    Collections.sort(tripList);
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);
                    break;
                case "DAY 7":
                    dayselected=7;
                    tripList=thisAcc.getDayTrips(7);    
                    Collections.sort(tripList);                   
                    for (int i=0; i<tripList.size(); i++)
                        if (i+1==trips.getSelectedIndex())
                            trip=tripList.get(i);
                    break;

                }
                
                thisETS = new EditTripScreen(dayselected, thisTDS, thisETN, thisAcc, trip);
        
            }
        }

    }
    

    /**
     * This closes the current screen and returns the user to the previous screen
     */
    private class CloseBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            /**
             * This stops displaying the window/frame
             */

            setVisible(false); 
        }

    }


} //public class EditTripNavigation() end

