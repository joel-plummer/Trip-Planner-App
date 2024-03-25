package code;

import java.util.*;

public class Account 
{
    /**
      * This represents a user account and associated user details.
      */

    /* 
        Attributes that should be here:
        String Username
        String Password
        Double Budget
        Account/App Settings (maintaining chosen theme for example)
        Trip objects
    */    

    private String username;
    private String password;
    private double budget;
    private double remaining;
    private String theme;
    private ArrayList<Trip> day1Trips, day2Trips, day3Trips, day4Trips, day5Trips, day6Trips, day7Trips;
    
    
    /**
     * This sets up a username, password, intial budget, theme and trips per day for a user account.
     * @param username describes an instance of a username associated to a particular account. 
     * @param password describes an instance of a password associated to a particular account.
     */
    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
        budget = 0.0;
        remaining = 0.0;
        theme = "Default";

        day1Trips = new ArrayList<Trip>();
        day2Trips = new ArrayList<Trip>();
        day3Trips = new ArrayList<Trip>();
        day4Trips = new ArrayList<Trip>();
        day5Trips = new ArrayList<Trip>();
        day6Trips = new ArrayList<Trip>();
        day7Trips = new ArrayList<Trip>();

    }

    /**
     * This returns the username of the account.
     * @return this account's username
     */    
    public String getUsername()
    {
        return username;
    }

    /**
     * This returns the password of the account.
     * @return this account's password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * This returns the budget associated with the account.
     * @return this account's budget
     */
    public double getBudget()
    {
        return budget;
    }

    /**
     * This gets the preferred theme of the account's user.
     * @return the user's preferred theme 
     */
    public String getTheme()
    {
        return theme;
    }

    /**
     * This returns the remaining budget after any deductions or readditions.
     * @return this user's remaining budget
     */
    public double getRemaining()
    {
        return remaining;
    }


    /**
     * This returns the total number of buses registered for trips across 7 days.
     * @return this user's total buses across trips
     */
    public int getTotBuses()
    {
        int total = day1Trips.size() + day2Trips.size() + day3Trips.size() + day4Trips.size() +
        day5Trips.size() + day6Trips.size() + day7Trips.size();
        
        return total;
    }


    /**
     * This returns the total number of persons registered forntrips across 7 days. 
     * @return this user's total number of persons across trips
     */
    public int getTotPpl()
    {
        int result = calcTotPpl(day1Trips) + calcTotPpl(day2Trips) + calcTotPpl(day3Trips) + 
        calcTotPpl(day4Trips) + calcTotPpl(day5Trips) + calcTotPpl(day6Trips) + calcTotPpl(day7Trips);

        return result;
    }

    /**
     * This calculates the total number of persons on trips for a particualr day
     * @param dayTrips is the list of trips for a particular day
     * @return this user's total number of persons on trips for a particular day
     */
    public int calcTotPpl(ArrayList<Trip> dayTrips)
    {
        int total = 0; 

        for (int i = 0; i < dayTrips.size(); i++){
            total += dayTrips.get(i).getNumOfPpl();
        }

        return total;
    }

    
    /**
     * This finds the trips for a specific day (1-7) based on the requested day.
     * @param day describes the day of the trip
     * @return this user's trip for a particular day 
     */
    public ArrayList<Trip> getDayTrips(int day)
    {
        ArrayList<Trip> dayTrips = new ArrayList<Trip>();

        switch(day){
            case 1:
                dayTrips = day1Trips;
                break;

            case 2:
                dayTrips = day2Trips;
                break;

            case 3:
                dayTrips = day3Trips;
                break;

            case 4:
                dayTrips = day4Trips;
                break;

            case 5:
                dayTrips = day5Trips;
                break;

            case 6:
                dayTrips = day6Trips;
                break;

            case 7:
                dayTrips = day7Trips;
        }
        
        return dayTrips;
    }
    


    /**
     * This adds a trip to a particular day.
     * @param dayNum is the day specified the trip must be added to
     * @param t is the trip to be added to the list of the sepcified day
     */
    public void addTripToDay(int dayNum, Trip t)
    {
        getDayTrips(dayNum).add(t);
    }
    
    /**
     * This removes a trip from a particualr day.
     * @param dayNum is the day the specified trip must be removed from 
     * @param t is the trip to be removed from the list of the specified day 
     */
    public void removeTripfromDay(int dayNum, Trip t)
    {
        getDayTrips(dayNum).remove(t);
    }

    /**
     * This sets the username for an account.
     * @param newUser is an entered username from the user that can set or replace an existing username
     */
    public void setUsername(String newUser)
    {
        username = newUser;
    }

    /**
     * This sets the password for an account.
     * @param newPass is an entered password from the user that can set or replace an existing password
     */
    public void setPassword(String newPass)
    {
        password = newPass;
    }

    /**
     * This sets the budget for an acocunt.
     * @param newBudget is an entered numerical value from the user that sets the budget to be used for the account
     */
    public void setBudget(double newBudget)
    {
        budget = newBudget;
    }

    /**
     * This sets the user's preferred theme for the account.
     * @param newTheme is an entered theme value that changes the colour of display panels to the user's preference
     */
    public void setTheme(String newTheme)
    {
        theme = newTheme;
    }

    /**
     * This formats how an account is displayed.
     */
    public String toString()
    {
        return(getUsername()+"\t"+getPassword()+"\t"+getBudget()+"\t"+getTheme());
    }
    
}

