public class Account {
    /* Attributes that should be here:
    String Username
    String Password
    Double Budget
    Account/App Settings (maintaining chosen theme for example)
    Trip obj
    */

    private String username;
    private String password;
    private double budget;
    private double remaining;
    private String theme;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
        budget = 0.0;
        remaining = 0.0;
        theme = "Default";
    }


    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public double getBudget(){
        return budget;
    }

    public String getTheme(){
        return theme;
    }

    public double getRemaining(){
        return remaining;
    }


    public void setUsername(String newUser){
        username = newUser;
    }

    public void setPassword(String newPass){
        password = newPass;
    }

    public void setBudget(double newBudget){
        budget = newBudget;
    }

    public void setTheme(String newTheme){
        theme = newTheme;
    }

    public void calcRemaining(double cost){
        remaining = budget - cost;
    }


    public String toString()
    {
        return(getUsername()+"\t"+getPassword()+"\t"+getBudget());
    }
    
}
