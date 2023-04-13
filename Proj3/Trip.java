public class Trip implements Comparable<Trip> {
    /* 
        Attributes that should be here:
        String Name
        Int Auto Generated ID (format: T100)
        Time (array to store hours, and mins as int)
        int No. of People
        Boolean Completed
        Int Day
        Bus obj
    */

    private String name;
    private boolean completed;
    private String [] time = new String[2]; //example [12, 30]
    private int numOfPeople; //example "DAY 1"
    private int ID;
    private static int nextID = 100;
    private Bus bus;


    private int getNextID() {
        return ++nextID; 
    }

    public Trip(String name, Bus bus, int numOfPeople, String hrs, String mins) {
        this.ID = getNextID();
        this.name = name;
        time[0] = hrs;
        time[1] = mins;
        this.bus = bus;
        this.numOfPeople = numOfPeople;
        completed = false;

    }


    public int getID() {
        return ID;
    }
    
    public String getName() {
        return name;
    }

    public int getNumOfPpl() {
        return numOfPeople;
    }

    public Bus getBus() {
        return bus;
    }

    public String getHrs() {
        return time[0];
    }

    public String getMins() {
        return time[1];
    }

    public boolean isCompleted() {
        return completed;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setNumOfPpl(int newNum) {
        numOfPeople = newNum;
    }

    public void setTime(String newHrs, String newMins) {
        time[0] = newHrs;
        time[1] = newMins;
    }

    public void setCompletedTrip() {
        completed = true;
    }



    public int compareTo(Trip other)
    {
        return this.getID() - other.getID();   
    }

    public String toString()
    {
        return("[#T" + getID() + ", " + getName() + ", " + getNumOfPpl() + ", " + getHrs() + ":" + getMins() + "]");
    }
    
}

