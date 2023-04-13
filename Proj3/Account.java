import java.util.*;

public class Account {
    /* 
        Attributes that should be here:
        String Username
        String Password
        Double Budget
        Account/App Settings (maintaining chosen theme for example)
        Trip objects
    */

    //calcremaining needs to be adjusted
    //we may end up changing this file a bit

    private String username;
    private String password;
    private double budget;
    private double remaining;
    private String theme;
    private ArrayList<Trip> day1Trips, day2Trips, day3Trips, day4Trips, day5Trips, day6Trips, day7Trips;
    

    public Account(String username, String password){
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


    public int getTotBuses(){
        int total = day1Trips.size() + day2Trips.size() + day3Trips.size() + day4Trips.size() +
        day5Trips.size() + day6Trips.size() + day7Trips.size();
        
        return total;
    }


    public int getTotPpl(){
        int result = calcTotPpl(day1Trips) + calcTotPpl(day2Trips) + calcTotPpl(day3Trips) + 
        calcTotPpl(day4Trips) + calcTotPpl(day5Trips) + calcTotPpl(day6Trips) + calcTotPpl(day7Trips);

        return result;
    }

    public int calcTotPpl(ArrayList<Trip> dayTrips){
        int total = 0; 

        for (int i = 0; i < dayTrips.size(); i++){
            total += dayTrips.get(i).getNumOfPpl();
        }

        return total;
    }

    

    public ArrayList<Trip> getDayTrips(int day){
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
