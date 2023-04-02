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
    private JButton bExit;
    JComboBox<String> bSortBy;

    public TripDisplayScreen() {

        //This will soon be based on the welcome screen's/account info
        String username = "admin34";
        //Labelling the frame   
        setTitle("User " + username + "'s Plan");

        //=================================================//
        //=                SETTING UP PANELS              =//
        //=================================================//
        contsPnl = new JPanel();
        contsPnl.setLayout(new BorderLayout());
        contsPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        
        disPnl = new JPanel();//For the tabbedpane and tables
        btnPnl = new JPanel();//For buttons
        infoPnl = new JPanel();//For user data @ bottom

        disPnl.setLayout(new FlowLayout());
        disPnl.setBackground(new Color(152,182,248));

        btnPnl.setBackground(new Color(152,182,248));
        //btnPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        //btnPnl.setMaximumSize(new Dimension(700,380));
        btnPnl.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        infoPnl.setBackground(new Color(230,186,13));
        //infoPnl.setBorder(BorderFactory.createLineBorder(Color.black));

      

        //=====================================================//
        //=           CREATING THE TRIPS' TABBEDPANE          =//
        //=====================================================//

        //================//
        //=   TAB/DAY 1  =//
        //================//
        //SETTING UP TAB 1 (DAY 1)
        JPanel pnl1 = new JPanel(new BorderLayout());
        pnl1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 1 TRIPS", TitledBorder.LEFT,TitledBorder.TRAILING));        
        pnl1.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 1 (DAY 1)
        TableData pnl1Table = new TableData(pnl1);

        //ADDING ELEMENTS TO TABLE
        String[] item = {"Quick Trip 1", "Small - $5000", "#SM30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item1 = {"Holland Students", "Medium - $7000", "#MD23", "" + 17, "#T106", "09 : 00", "true"};
        String[] item12 = {"Ochi Tourists", "Luxurious - $12000", "#LX21", "" + 32, "#T103", "10 : 00", "true"};
        pnl1Table.model.addRow(item12);
        pnl1Table.model.addRow(item);
        pnl1Table.model.addRow(item1);
              
        
        //================//
        //=   TAB/DAY 2  =//
        //================//
        //SETTING UP TAB 2 (DAY 2)
        JPanel pnl2 = new JPanel(new BorderLayout());
        pnl2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 2 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl2.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 2 (DAY 2)
        TableData pnl2Table = new TableData(pnl2);

        //ADDING ELEMENTS TO TABLE
        String[] item2 = {"Quick Trip 1", "Small - $5000", "#SM30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item21 = {"Holland Students", "Medium - $7000", "#MD23", "" + 17, "#T106", "09 : 00", "true"};
        pnl2Table.model.addRow(item21);
        pnl2Table.model.addRow(item2);


        //================//
        //=  TAB/DAY 3   =//
        //================//
        //SETTING UP TAB 3 (DAY 3)        
        JPanel pnl3 = new JPanel(new BorderLayout());
        pnl3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 3 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl3.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 3 (DAY 3)
        TableData pnl3Table = new TableData(pnl3);

        //ADDING ELEMENTS TO TABLE
        String[] item3 = {"Quick Trip 1", "Small - $5000", "#SM30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item31 = {"Holland Students", "Medium - $7000", "#MD23", "" + 17, "#T106", "09 : 00", "true"};
        pnl3Table.model.addRow(item3);
        pnl3Table.model.addRow(item31);        


        //================//
        //=  TAB/DAY 4   =//
        //================//        
        //SETTING UP TAB 4 (DAY 4)
        JPanel pnl4 = new JPanel(new BorderLayout());
        pnl4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 4 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING)); 
        pnl4.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 4 (DAY 4)
        TableData pnl4Table = new TableData(pnl4);

        //ADDING ELEMENTS TO TABLE
        String[] item4 = {"Quick Trip 1", "Small - $5000", "#SM30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item41 = {"Holland Students", "Medium - $7000", "#MD23", "" + 17, "#T106", "09 : 00", "true"};
        pnl4Table.model.addRow(item41);
        pnl4Table.model.addRow(item4);  
        

        //================//
        //=  TAB/DAY 5   =//
        //================//
        //SETTING UP TAB 5 (DAY 5)
        JPanel pnl5 = new JPanel(new BorderLayout());
        pnl5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 5 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl5.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 5 (DAY 5)
        TableData pnl5Table = new TableData(pnl5);

        //ADDING ELEMENTS TO TABLE
        String[] item5 = {"Quick Trip 1", "Small - $5000", "#SM30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item51 = {"Holland Students", "Medium - $7000", "#MD23", "" + 17, "#T106", "09 : 00", "true"};
        pnl5Table.model.addRow(item5);
        pnl5Table.model.addRow(item51);  
        

        //================//
        //=  TAB/DAY 6   =//
        //================//
        //SETTING UP TAB 6 (DAY 6)
        JPanel pnl6 = new JPanel(new BorderLayout());
        pnl6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 6 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl6.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 6 (DAY 6)
        TableData pnl6Table = new TableData(pnl6);

        //ADDING ELEMENTS TO TABLE
        String[] item6 = {"Quick Trip 1", "Small - $5000", "#SM30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item61 = {"Holland Students", "Medium - $7000", "#MD23", "" + 17, "#T106", "09 : 00", "true"};
        pnl6Table.model.addRow(item61);
        pnl6Table.model.addRow(item6);  


        //================//
        //=  TAB/DAY 7   =//
        //================//
        //SETTING UP TAB 7 (DAY 7)
        JPanel pnl7 = new JPanel(new BorderLayout());
        pnl7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DAY 7 TRIPS", TitledBorder.LEFT, TitledBorder.TRAILING));
        pnl7.setBackground(Color.LIGHT_GRAY);

        //SETTING UP TABLE FOR TAB 7 (DAY 7)
        TableData pnl7Table = new TableData(pnl7);

        //ADDING ELEMENTS TO TABLE
        String[] item7 = {"Quick Trip 1", "Small - $5000", "#SM30", "" + 8, "#T104", "01 : 30", "false"};
        String[] item71 = {"Holland Students", "Medium - $7000", "#MD23", "" + 17, "#T106", "09 : 00", "true"};
        pnl7Table.model.addRow(item7);
        pnl7Table.model.addRow(item71);  


        //================//
        //=   SETTINGS   =//
        //================//
        //SETTING UP SETTINGS TAB
        JPanel pnl8 = new JPanel();
        pnl8.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "SETTINGS", TitledBorder.LEFT, TitledBorder.TOP));
        pnl8.setBackground(new Color(201,203,205));  

        JLabel text1 = new JLabel("Theme: ");
        String [] themeOpts = {"Default", "GRAY", "PINK", "RED", "LIGHT GREEN", "DARK GREEN", "DARK BLUE", "LIGHT PURPLE", "DARK PURPLE"};
        JComboBox<String> themes = new JComboBox<>(themeOpts);
        //JLabel text2 = new JLabel("Adjust Budget: ");
        //JTextField budget = new JTextField();
        
        //Functionality for color customization
        themes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String s = (String) themes.getSelectedItem();
                switch (s) {//check for a match
                    case "PINK":
                        disPnl.setBackground(Color.PINK);
                        btnPnl.setBackground(Color.PINK);
                        break;
                    case "RED":
                        disPnl.setBackground(Color.RED);
                        btnPnl.setBackground(Color.RED);
                        break; 
                    case "DARK BLUE":
                        disPnl.setBackground(Color.BLUE);
                        btnPnl.setBackground(Color.BLUE);
                        break;
                    case "LIGHT GREEN":
                        disPnl.setBackground(new Color(9430201));
                        btnPnl.setBackground(new Color(9430201));
                        break;
                    case "DARK GREEN":
                        disPnl.setBackground(new Color(85,111,111));
                        btnPnl.setBackground(new Color(85,111,111));
                        break;
                    case "LIGHT PURPLE":
                        disPnl.setBackground(new Color(199,196,236));
                        btnPnl.setBackground(new Color(199,196,236));
                        break;
                    case "DARK PURPLE":
                        disPnl.setBackground(new Color(55,32,142));
                        btnPnl.setBackground(new Color(55,32,142));
                        break;
                    case "GRAY":
                        disPnl.setBackground(Color.GRAY);
                        btnPnl.setBackground(Color.GRAY);
                        break;
                    case "Default":
                        disPnl.setBackground(new Color(152,182,248));
                        btnPnl.setBackground(new Color(152,182,248));
                }
            }
        });


        pnl8.add(text1);
        pnl8.add(themes);
        //pnl8.add(text2);
        //pnl8.add(budget);


        //===================//
        //=   ADDING TABS   =//
        //===================//
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

        //Adding the whole tabbedpane to its own panel
        disPnl.add(tripTabs);
        //disPnl.setPreferredSize(new Dimension(700,380));



        //===================================================//
        //=         CREATING THE BUTTONS AT BOTTOM          =//
        //===================================================//

        //There should be a listener for when this is changed, similar to customization in settings
        String [] sortOpts = {"Sort By", "Trip ID", "Trip Name", "# People", "Time"};
        JComboBox<String> bSortBy = new JComboBox<>(sortOpts);

        bNewTrip = new JButton("New Trip");
        bEditTrip = new JButton("Edit Trip");
        bExit = new JButton("Exit");       
        
        //Adding the buttons to their own panel
        btnPnl.add(bSortBy, gbc);
        btnPnl.add(bNewTrip, gbc);
        btnPnl.add(bEditTrip, gbc);
        btnPnl.add(bExit, gbc);


        //=======================================================//
        //=           CREATING THE USER INFO AT BOTTOM          =//
        //=======================================================//
        //The user info should come from account and be displayed here        
        JTextArea info = new JTextArea("Total Budget: $50,000\t" + "Remaining: $30,000\t" + "Buses Scheduled: 56\t" + "Total Persons Booked: 122");
        Font boldFont = new Font(info.getFont().getName(), Font.BOLD, info.getFont().getSize());
        info.setFont(boldFont);
        info.setOpaque(false);
        infoPnl.add(info);


        //=========================================================//
        //=           ADDING MAIN PANELS TO CONTENT PANEL         =//
        //=========================================================//
        contsPnl.add(disPnl, BorderLayout.PAGE_START);
        contsPnl.add(btnPnl, BorderLayout.CENTER);
        contsPnl.add(infoPnl, BorderLayout.PAGE_END);


        //Extra frame set up things
        setContentPane(contsPnl); 
        setSize(820,485);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
    }


    //=========================================================//
    //=           BUTTON LISTENING FUNCTIONALITIES            =//
    //=========================================================//

    //Sort By

    //Edit Trip

    //New Trip

    //Exit (Bk To Welcome Screen)



}  
