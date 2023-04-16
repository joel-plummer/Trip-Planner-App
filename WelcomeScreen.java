import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Main Window upon loading the program.
 */


/*Setting up the frame */
public class WelcomeScreen extends JFrame {

    private JPanel disPnl;
    private JPanel btnPnl;
    private JPanel inptPnl;
    private JPanel innerPnl;

    private JTextField username;
    private JPasswordField pass;
    private JLabel userLbl;
    private JLabel passLbl;
    private JLabel errorMsg;

    private JButton btnLogin;
    private JButton btnSignup;
    private JButton btnExit;

    private ArrayList<Account> accList = new ArrayList<Account>();// list of all accounts in system
    private String path = "Database/Account_sheet\\Accounts.txt";
    private File accounts = new File(path);
    private File accData;
    private boolean accEmpty = accounts.length() == 0;

    private static WelcomeScreen thisUserData;
    private TripDisplayScreen tds;

    public WelcomeScreen() {

        /*Adding account from file to account list*/
        if (accEmpty == false) {
            String accFile = path;
            loadAccount(accFile);
        }

        /*Setting a up window*/
        setTitle("Login Or Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting up program icon (make sure the 'pics' folder is downloaded and in the
        // active folder)

        Image icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("pics\\planner-icon.png"));
        setIconImage(icon);
        // Declaring layout
        setLayout(new BorderLayout());

        // ==================================================//
        // = SETTING UP PANELS =//
        // ==================================================//
        // For Welcome picture (random image I found that likely needs changing)
        disPnl = new JPanel();
        try {
            BufferedImage banner = ImageIO.read(this.getClass().getResource("pics\\banner.png"));
            JLabel picLbl = new JLabel(new ImageIcon(banner));
            disPnl.add(picLbl);
        } catch (IOException ioe) {
            System.out.println("Welcome banner not found.");
        }
        disPnl.setBackground(new Color(195, 195, 195));
        disPnl.setPreferredSize(new Dimension(590, 220));

        // ============================================//
        // = STRUCTURING & CREATING DISPLAY PANEL =//
        // ============================================//
        // For login/register text fields
        inptPnl = new JPanel(new BorderLayout());
        inptPnl.setBackground(new Color(55, 73, 136));

        // Creating and alligning text fields to left below welcome pic
        innerPnl = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        innerPnl.setPreferredSize(new Dimension(500, 100));
        innerPnl.setOpaque(false);

        JPanel i1Pnl = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        i1Pnl.setOpaque(false);
        i1Pnl.setPreferredSize(new Dimension(300, 70));
        userLbl = new JLabel("Username:  ");
        userLbl.setForeground(Color.WHITE);
        username = new JTextField(15);
        i1Pnl.add(userLbl);
        i1Pnl.add(username);
        innerPnl.add(i1Pnl);

        passLbl = new JLabel("Password:  ");
        passLbl.setForeground(Color.WHITE);
        pass = new JPasswordField(15);
        i1Pnl.add(passLbl);
        i1Pnl.add(pass);
        innerPnl.add(i1Pnl);

        /* Errom message display when needed */
        JPanel i2Pnl = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        i2Pnl.setOpaque(false);
        errorMsg = new JLabel("");
        errorMsg.setForeground(new Color(237, 28, 36));
        i2Pnl.add(errorMsg);
        innerPnl.add(i2Pnl);

        inptPnl.add(innerPnl, BorderLayout.WEST);

        // ========================================//
        // = CREATING THE BUTTONS AT BOTTOM =//
        // ========================================//
        btnPnl = new JPanel(new GridBagLayout());
        btnPnl.setBackground(new Color(195, 195, 195));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 10, 7, 10);

        /*Button set up*/
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new LoginBtnListener());

        btnSignup = new JButton("Sign Up");
        btnSignup.addActionListener(new SignUpBtnListener());

        btnExit = new JButton("Exit");
        btnExit.addActionListener(new ExitBtnListener());

        // Adding to button panel
        btnPnl.add(btnLogin, gbc);
        btnPnl.add(btnSignup, gbc);
        btnPnl.add(btnExit, gbc);

        // ==============================================//
        // = ADDING MAIN PANELS TO FRAME =//
        // ==============================================//
        add(disPnl, BorderLayout.NORTH);
        add(inptPnl, BorderLayout.CENTER);
        add(btnPnl, BorderLayout.PAGE_END);

        /*Final frame/window settings*/
        pack();
        setSize(590, 413);
        setResizable(false);
        setVisible(true);

    }// public WelcomeScreen() end (constructor)

    public static void main(String[] args) {
        // System.out.println("\nWelcome to the PlanTrip Application!\n");

        // Creating welcome screen
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                thisUserData = new WelcomeScreen();
            }
        });

    }

    public void createAccData(Account a) {
        accData = new File("Database/User_data\\" + a.getUsername() + "_data.txt");
        ArrayList<Trip> tripList;
        String tripInfo;

        try {
            FileWriter myWriter2 = new FileWriter(accData, false);
            myWriter2.write("User: " + a.getUsername() + "\n");
            myWriter2.write("Password: " + a.getPassword() + "\n");
            myWriter2.write("Theme: " + a.getTheme() + "\n");
            myWriter2.write("Budget: " + String.valueOf(a.getBudget()) + "\n\n");

            for (int i = 1; i <= 7; ++i) {
                myWriter2.write("DAY " + i + ":\n");
                tripList = a.getDayTrips(i);
                for (Trip t : tripList) {
                    tripInfo = "Trip: " + t.getName() + " " + t.getNumOfPpl() + " " + t.getHrs() + " " + t.getMins() +
                            " " + t.isCompleted() + " Bus:" + " " + t.getBus().getType();
                    myWriter2.write(tripInfo + "\n");
                }
                myWriter2.write("\n");
            }

            myWriter2.close();

        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    /*Deletes account data */
    public void deleteAccData(Account a) {
        File f = new File("Database/User_data\\" + a.getUsername() + "_data.txt");
        f.delete();
    }

    public String getAccountsPath() {
        return path;
    }

    /*Loads an account */
    private ArrayList<Account> loadAccount(String aFile) {
        Scanner ascan = null;
        try {
            ascan = new Scanner(new File(aFile));
            while (ascan.hasNext()) {
                String[] nextLine = ascan.nextLine().split(" ");
                String username = nextLine[1];
                String password = nextLine[3];

                Account a = loadData(new Account(username, password));

                accList.add(a);
                for (Trip t : a.getDayTrips(2)) {
                    System.out.print("\n" + t + " Account\n");
                }

            }

            ascan.close();
        } catch (IOException e) {
        }

        return accList;
    }

    private Account loadData(Account a) {
        Scanner ascan = null;
        File f = new File("Database/User_data\\" + a.getUsername() + "_data.txt");
        int currDay = 0;
        try {
            ascan = new Scanner(f);
            while (ascan.hasNext()) {
                // System.out.print(ascan.nextLine());
                String[] nextLine = ascan.nextLine().split(" ");

                for (String x : nextLine) {
                    System.out.print(x + ", ");
                    if (nextLine.length > 1) {
                        // System.out.print("\n" + nextLine[0] + " " + nextLine[1] + " loadData lines");
                    }
                }
                System.out.print("\n");

                if (nextLine[0].equals("Theme:")) {
                    a.setTheme(nextLine[1]);
                } else if (nextLine[0].equals("Budget:")) {
                    a.setBudget(Double.parseDouble(nextLine[1]));
                } else if (nextLine[0].equals("")) {
                    continue;
                } else if ((nextLine[0].startsWith("DAY"))) {
                    currDay = Integer.parseInt(nextLine[1].replace(":", ""));
                } else if (nextLine[0].equals("Trip:")) {
                    // public Trip(String name, Bus bus, int numOfPeople, String hrs, String mins)
                    // Trip: HollandStudents 17 03 30 false Bus: Medium
                    Bus b = new Bus(BusType.valueOf(nextLine[7]));
                    Trip t = new Trip(nextLine[1], b, Integer.parseInt(nextLine[2]),
                            nextLine[3], nextLine[4]);
                    t.setCompletedTrip(Boolean.parseBoolean(nextLine[5]));
                    // System.out.print(a);
                    a.addTripToDay(currDay, t);

                }

            }

            ascan.close();
        } catch (IOException e) {
        }
        return a;
    }

    /*Updates an account */
    public void updateAccounts(String old, String changeTo) {
        File f = new File(path);
        Path path = Paths.get(f.getAbsolutePath());
        Charset charset = StandardCharsets.UTF_8;

        try {
            String content = new String(Files.readAllBytes(path), charset);
            content = content.replace(old, changeTo);
            Files.write(path, content.getBytes(charset));
        } catch (Exception e) {

        }
    }

    /*Sets the user's desired theme */
    public void setWelcomeTheme(String newTheme) {
        // For Welcome screen to display chosen theme from account
        switch (newTheme) {
            case "PINK":
                inptPnl.setBackground(new Color(210, 143, 218));
                userLbl.setForeground(Color.BLACK);
                passLbl.setForeground(Color.BLACK);
                break;
            case "RED":
                inptPnl.setBackground(new Color(142, 49, 80));
                userLbl.setForeground(Color.WHITE);
                passLbl.setForeground(Color.WHITE);
                break;
            case "LIGHTBLUE":
                inptPnl.setBackground(new Color(152, 182, 248));
                userLbl.setForeground(Color.BLACK);
                passLbl.setForeground(Color.BLACK);
                break;
            case "GREEN":
                inptPnl.setBackground(new Color(85, 111, 111));
                userLbl.setForeground(Color.WHITE);
                passLbl.setForeground(Color.WHITE);
                break;
            case "PURPLE":
                inptPnl.setBackground(new Color(104, 54, 137));
                userLbl.setForeground(Color.WHITE);
                passLbl.setForeground(Color.WHITE);
                break;
            case "GRAY":
                inptPnl.setBackground(new Color(145, 143, 156));
                userLbl.setForeground(Color.BLACK);
                passLbl.setForeground(Color.BLACK);
                break;
            case "Default":
                inptPnl.setBackground(new Color(55, 73, 136));
                userLbl.setForeground(Color.WHITE);
                passLbl.setForeground(Color.WHITE);
        }
    }

    // =========================================================//
    // = BUTTON LISTENING FUNCTIONALITIES =//
    // =========================================================//
    /*Login Button*/
    private class LoginBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean valid = false;
            String txtName = username.getText();
            String txtPass = String.valueOf(pass.getPassword());
            // returns true if either username or password was not entered
            boolean inputEmpty = (txtName.length() == 0 || txtPass.length() == 0);

            System.out.println("(Loginbtn) All accounts: " + accList);

            // when either username or password was not entered
            if (inputEmpty) {
                errorMsg.setForeground(new Color(237, 28, 36));
                errorMsg.setText("Please enter Account data.");
            } else {

                for (int a = 0; a < accList.size(); a++) {
                    /*
                     * Since JPasswordfield doesn't allow use of getText() and getPassword() returns
                     * char array,
                     * String.valueOf(pass.getPassword()) is used because it converts that to a
                     * regular String
                     */
                    if (username.getText().equals(accList.get(a).getUsername()) &&
                            String.valueOf(pass.getPassword()).equals(accList.get(a).getPassword())) {
                        // if account info found
                        valid = true;
                        // in case error msg gets stuck on screen
                        errorMsg.setText("");
                        // run trip display screen

                        tds = new TripDisplayScreen(thisUserData, accList.get(a));

                    }
                }

                /*If account info from text fields is not found*/
                if (valid == false) {
                    errorMsg.setForeground(new Color(237, 28, 36));
                    errorMsg.setText("Account not found. Please Sign Up.");
                }
            }
        }

    }

   /*SignUp Button*/
    private class SignUpBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // it creates a new account and adds it to accList
            // if entered account already exists, error message

            String txtName = username.getText();
            String txtPass = String.valueOf(pass.getPassword());
            // returns true if either username or password was not entered
            boolean inputEmpty = (txtName.length() == 0 && txtPass.length() == 0);

            // when either username or password was not entered
            if (inputEmpty) {
                errorMsg.setForeground(new Color(237, 28, 36));
                errorMsg.setText("Please enter Account data.");
            } else if (txtName.length() == 0) {
                errorMsg.setForeground(new Color(237, 28, 36));
                errorMsg.setText("Please enter the username.");
            } else if (txtPass.length() == 0) {
                errorMsg.setForeground(new Color(237, 28, 36));
                errorMsg.setText("Please enter the password.");
            } else if (txtName.length() < 6 && txtPass.length() < 8) {
                errorMsg.setForeground(new Color(237, 28, 36));
                errorMsg.setText("Username invalid: [under 6 chars].    Password invalid: [under 8 chars].");
            } else if (txtName.length() < 6) {
                errorMsg.setForeground(new Color(237, 28, 36));
                errorMsg.setText("Username must be at least 6 characters.");

            } else if (txtPass.length() < 8) {
                errorMsg.setForeground(new Color(237, 28, 36));
                errorMsg.setText("Password must be at least 8 characters.");
            } else {
                try {
                    Account acc = new Account(txtName, txtPass);
                    boolean user_exists = false;
                    boolean pass_exists = false;
                    boolean acc_exists = false;

                    // else {
                    /*Checks if entry was entered previously.*/
                    for (int b = 0; b < accList.size(); b++) {
                        if (txtName.equals(accList.get(b).getUsername()) &&
                                txtPass.equals(accList.get(b).getPassword())) {
                            // if account info found
                            errorMsg.setForeground(new Color(237, 28, 36));
                            errorMsg.setText("Account already exists.");
                            System.out.println("A record already exists.");
                            System.out.println(accList);
                            acc_exists = true;

                        } else if (txtName.equals(accList.get(b).getUsername())) {
                            // if username info found
                            errorMsg.setForeground(new Color(237, 28, 36));
                            errorMsg.setText("Username already exists.");
                            System.out.println("A username already exists");
                            System.out.println(accList);
                            user_exists = true;
                        } else if (txtPass.equals(accList.get(b).getPassword())) {
                            // if password info found
                            errorMsg.setForeground(new Color(237, 28, 36));
                            errorMsg.setText("Password already exists.");
                            System.out.println("A password already exists.");
                            System.out.println(accList);
                            pass_exists = true;
                        }
                        

                    }

                    /*Checks if account, user, or password  already exists*/
                    if (acc_exists == false && user_exists == false && pass_exists == false) {
                        accList.add(acc);
                        errorMsg.setForeground(new Color(230, 177, 0));
                        errorMsg.setText("Account created successfully. Please log in.");
                        System.out.println("An account has been created.");

                        FileWriter myWriter2 = new FileWriter(accounts, true);
                        myWriter2.write("Username: " + txtName + " " + "Password: " + txtPass + "\n");
                        myWriter2.close();

                        createAccData(acc);

                    }

                } catch (IOException ioException) {
                    System.out.println(ioException);
                }
            }
        }

    }

    /*Exit Button*/
    private class ExitBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

} // public class WelcomeScreen() end
