import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class EditTripScreen extends JFrame {

    private JPanel disPnl;
    private JPanel lftPnl, rgtPnl;
    private JPanel dPnl1, dPnl2, dPnl3, dPnl4, dPnl5, dPnl6, dPnl7, dPnl8;
    private JPanel btnPnl;

    private JButton bSaveEdit;
    private JButton bCancel;

    private JLabel errorMsg;
    private JLabel daytxt, nametxt, bustxt, genIDtxt, ppltxt, timetxt, busIDtxt, completed, colontxt, formattxt;
    private JTextField nameBox, IDBox, pplBox, hrBox, minBox, busIDBox;
    private JCheckBox isComplete;
    private JComboBox<String> buses,days;

    private Account thisAcc;
    private Trip thistrip;
    private Bus thisbus;
    private int thisday;
    private EditTripNavigation thisETN; //previous screen
    private EditTripScreen thisETS;
    private TripDisplayScreen thisTDS;

    public EditTripScreen(int day, TripDisplayScreen tds, EditTripNavigation etn, Account acc, Trip trip){

        //Setting up user info (Making sure windows share same data for user)
        thisAcc = acc;
        thisETN = etn;
        thistrip =trip;
        thisTDS = tds;
        thisday=day;
        thisbus = thistrip.getBus();
        thisETS = this;
        

        etn.setVisible(false); //Turns off edittripnavgiation screen while edittrip screen is open

        //Labelling the frame/window   
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
        //Main panel for input section
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
        days = new JComboBox<>(dayOpts); 
        //placeholder data
        days.setSelectedItem(""+dayOpts[day]); //Should be set to whatever day trip is on 
        dPnl1.add(daytxt);
        dPnl1.add(days);
        lftPnl.add(dPnl1); 

        dPnl2 = new JPanel();
        dPnl2.setOpaque(false);
        nametxt = new JLabel("Trip Name: ");
        nameBox = new JTextField(13);
        nameBox.setText(thistrip.getName()); //Trip ID
        dPnl2.add(nametxt);
        dPnl2.add(nameBox);
        lftPnl.add(dPnl2);

        dPnl3 = new JPanel();
        dPnl3.setOpaque(false);
        bustxt = new JLabel("Bus Type:  ");
        //should be set to whatever data the trip previously selected has
        String [] busOpts = {"<<Select Bus>>", "Small - $5000", "Medium - $7000", "Luxurious - $12000"};
        buses = new JComboBox<>(busOpts); 
        //placeholder data
        switch (thisbus.getType()){
            case Small:
                buses.setSelectedItem("Small - $5000");
                break;
            case Medium:
                buses.setSelectedItem("Medium - $7000");
                break;
            case Luxurious:
                buses.setSelectedItem("Luxurious - $12000");
                break;
        }
         //Should be set to whatever bus type the trip has
        //Listener for when this is changed to be here
        dPnl3.add(bustxt);
        dPnl3.add(buses);
        lftPnl.add(dPnl3); 

        dPnl4 = new JPanel();
        dPnl4.setOpaque(false);
        busIDtxt = new JLabel("Generated Bus ID: ");
        busIDBox = new JTextField(5);
        Bus thisbus = trip.getBus();
        busIDBox.setText("#B"+thisbus.getID()); //bus ID
        busIDBox.setEditable(false);
        dPnl4.add(busIDtxt);
        dPnl4.add(busIDBox);
        lftPnl.add(dPnl4);
        
        dPnl5 = new JPanel();
        dPnl5.setOpaque(false);
        ppltxt = new JLabel("# of Persons: ");
        pplBox = new JTextField(8);
        //placeholder data
        pplBox.setText(""+thistrip.getNumOfPpl());//should be set to whatever data the trip previously selected has
        dPnl5.add(ppltxt);
        dPnl5.add(pplBox);
        rgtPnl.add(dPnl5);
        
        dPnl6 = new JPanel();
        dPnl6.setOpaque(false);
        timetxt = new JLabel("Time:  ");
        //placeholder data
        hrBox = new JTextField(6);
        minBox = new JTextField(6);
        hrBox.setText(""+thistrip.getHrs());
        colontxt= new JLabel(":");
        minBox.setText(""+thistrip.getMins());
        formattxt = new JLabel("(24hr)");
    
        dPnl6.add(timetxt);       
        dPnl6.add(hrBox);
        dPnl6.add(colontxt);
        dPnl6.add(minBox);
        dPnl6.add(formattxt);
        rgtPnl.add(dPnl6);

        dPnl7 = new JPanel();
        dPnl7.setOpaque(false);
        genIDtxt = new JLabel("Generated Trip ID: ");
        IDBox = new JTextField(5);
        //placeholder data
        IDBox.setText("#T"+thistrip.getID()); //should be set to whatever data the trip previously selected has
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

        //To display error messages when needed
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


        //Adding main panels to frame/window
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
    //Save Trip Edit

    public Boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
    private class SaveBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            //if input for time and # of persons is not valid, if statement needs to be added
            //if time has already been booked for that day, another error message here
            //if bus cost exceeds the budget, another error message here to warn user to update it 
            //("You can't afford this") or smth

             /*Gets the type of bus selected*/

             Bus bus = new Bus();
             double cost=0;
             switch(buses.getSelectedItem().toString()){
                 case ("Small - $5000"):
                     bus= new Bus(thisbus.getID(),BusType.Small);
                     cost = bus.calcBus(bus.getType(), Integer.parseInt(pplBox.getText()));
                     break;
                 case("Medium - $7000"):
                     bus= new Bus(thisbus.getID(),BusType.Medium);
                     cost = 7000;
                     break;
                 case("Luxurious - $12000"): 
                     bus= new Bus(thisbus.getID(),BusType.Luxurious);
                     cost = 12000;
                     break;
             }                  

            /*Check if a day was selected*/
             if (days.getSelectedItem().toString()=="<<Select Day>>")
             {
                errorMsg.setText("Please select a day.");
             }

            /* Checks if Name is entered */
            else if (nameBox.getText().isEmpty())
            {
                errorMsg.setText("Please enter a valid Name.");
            }

            /*Checks if a Bus was selected*/
             else if (buses.getSelectedItem().toString()=="<<Select Bus>>")
             {
                errorMsg.setText("Please select a Bus.");
             }

            /*Checks if the time is valid*/
            else if ((isInteger(hrBox.getText())==false) || (isInteger(minBox.getText())==false) || (hrBox.getText().isEmpty()) 
            || (minBox.getText().isEmpty()) || (Integer.parseInt(hrBox.getText()) > 24) || (Integer.parseInt(hrBox.getText()) < 0) ||
            ((Integer.parseInt(minBox.getText()) > 59)) || ((Integer.parseInt(minBox.getText()) < 0)))
            {
                errorMsg.setText("Please enter a valid Time.");
            }

            /* Checks if Number of Passengers is valid*/
             else if (pplBox.getText().isEmpty())
             {
                errorMsg.setText("Please enter a valid number of persons");
             }

            /* Checks if Number of Passengers is valid*/
             else if ((pplBox.getText()=="") || (isInteger(pplBox.getText())==false) || (Integer.parseInt(pplBox.getText())<=0))
             {
                errorMsg.setText("Please enter a valid number of persons");
             }         
             
            /* Checks if Number of Passengers is within capacity of the selected bus*/
             else if (Integer.parseInt(pplBox.getText()) > bus.getCapacity())
             {
                errorMsg.setText("Too many passengers!");
             }

            /*Checks if the selected bus is within the budget */
            else if (thisAcc.getBudget()< cost)
            {
                errorMsg.setText("Insufficient Budget");
            }

            else
            {
                /*Modifies the budget to accommodate the new trip */
                double newBudget = thisAcc.getBudget();
                newBudget = thisAcc.getBudget() - (cost-bus.calcBus(thistrip.getBus().getType(), thistrip.getNumOfPpl()));                
                int confirm = JOptionPane.showConfirmDialog(thisETS,"Are you sure you want to \nkeep these changes? ");  
                if(confirm == JOptionPane.YES_OPTION) { 
                    thisAcc.removeTripfromDay(thisday, thistrip);
                    thisTDS.getDayTable(thisday).getModel().setRowCount(0);
                    thisTDS.showTable(thisAcc.getDayTrips(thisday), thisTDS.getDayTable(thisday));
                    Trip trip = new Trip(thistrip.getID(),nameBox.getText(), bus,Integer.parseInt(pplBox.getText()), hrBox.getText(), minBox.getText());
                    if (isComplete.isSelected()==true)
                        trip.setCompletedTrip(true);
                    else if(isComplete.isSelected()==false)
                        trip.setCompletedTrip(false);
                    
                    //supposed to edit the data for trip in arraylist for the selected day
                    switch(days.getSelectedItem().toString())
                    {
                        case ("DAY 1"):
                            thisAcc.addTripToDay(1,trip);
                            thisTDS.getDayTable(1).getModel().setRowCount(0);
                            thisTDS.showTable(thisAcc.getDayTrips(1), thisTDS.getDayTable(1));
                            
                            break;

                        case ("DAY 2"):
                            thisAcc.addTripToDay(2,trip);
                            thisTDS.getDayTable(2).getModel().setRowCount(0);
                            thisTDS.showTable(thisAcc.getDayTrips(2), thisTDS.getDayTable(2));
                            break;

                        case ("DAY 3"):
                            thisAcc.addTripToDay(3,trip);
                            thisTDS.getDayTable(3).getModel().setRowCount(0);
                            thisTDS.showTable(thisAcc.getDayTrips(3), thisTDS.getDayTable(3));
                            break;
                        case ("DAY 4"): 
                            thisAcc.addTripToDay(4,trip);
                            thisTDS.getDayTable(4).getModel().setRowCount(0);
                            thisTDS.showTable(thisAcc.getDayTrips(4), thisTDS.getDayTable(4));
                            break;
                        case ("DAY 5"):
                            thisAcc.addTripToDay(5,trip);
                            thisTDS.getDayTable(5).getModel().setRowCount(0);
                            thisTDS.showTable(thisAcc.getDayTrips(5), thisTDS.getDayTable(5));
                            break;
                        case ("DAY 6"):
                            thisAcc.addTripToDay(6,trip);
                            thisTDS.getDayTable(6).getModel().setRowCount(0);
                            thisTDS.showTable(thisAcc.getDayTrips(6), thisTDS.getDayTable(6));
                            break;
                        case ("DAY 7"):
                            thisAcc.addTripToDay(7,trip);
                            thisTDS.getDayTable(7).getModel().setRowCount(0);
                            thisTDS.showTable(thisAcc.getDayTrips(7), thisTDS.getDayTable(7));
                            break;  
                    }
                    thisAcc.setBudget(newBudget);
                    thisTDS.updateInfo();
                    setVisible(false); //stops displaying window/frame
                }  
            }
        
        }
    }

    //Close (Bk To Welcome Screen)
    private class CloseBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false); //stops displaying window/frame
            thisETN.setVisible(true); //makes edittripnavigation screen visible again
        }

    }

} //public class EditTripScreen() end
