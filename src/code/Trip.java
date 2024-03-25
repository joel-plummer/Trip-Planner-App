package code;

public class Trip implements Comparable<Trip> {
    /*
     * Attributes that should be here:
     * String Name
     * Int Auto Generated ID (format: T100)
     * Time (array to store hours, and mins as int)
     * int No. of People
     * Boolean Completed
     * Bus obj
     */

    /**
     * This class creates a Trip.
     */

    private String name;
    private boolean completed;
    private String[] time = new String[2]; // example [12, 30]
    private int numOfPeople;   
    private int ID;
    private static int nextID = 0;
    private Bus bus;

    /**
     * This generates an incremented trip ID
     * @return
     */
    private int getNextID() {
        return ++nextID;
    }
    
    /**
     * This generates a preview of the consecutive trip ID. 
     * @return the next possible trip ID in sequence. 
     */
    public int idPreview(){
        int preview= nextID+1;
        return preview;
    }

    //Empty trip constructor
    public Trip(){}

    /**
     * Constructor 1
     * This creates an instance of a Trip with an ID.
     * @param ID describes an indentification tag associated to a particular trip
     * @param name defines the name of the Trip
     * @param bus describes the bus used for the Trip
     * @param numOfPeople describes the number of persons for the Trip
     * @param hrs defines the time's hour for the Trip
     * @param mins defines the time's minutes for the Trip
     */
    public Trip(int ID, String name, Bus bus, int numOfPeople, String hrs, String mins){
        this.ID = ID;
        this.name = name.replaceAll("\\s", "");
        time[0] = hrs;
        time[1] = mins;
        this.bus = bus;
        this.numOfPeople = numOfPeople;
        completed = false;
    }
 
    /**
     * Constructor 2
     * This creates an instance of a Trip.
     * @param name defines the name of the Trip
     * @param bus describes the bus used for the Trip
     * @param numOfPeople describes the number of persons for the Trip
     * @param hrs defines the time's hour for the Trip
     * @param mins defines the time's minutes for the Trip
     */
    public Trip(String name, Bus bus, int numOfPeople, String hrs, String mins) {
        this.ID = getNextID();
        this.name = name.replaceAll("\\s", "");
        time[0] = hrs;
        time[1] = mins;
        this.bus = bus;
        this.numOfPeople = numOfPeople;
        completed = false;
    }

    /**
     * This returns the Trip ID
     * @return the Trip's ID 
     */
    public int getID() {
        return ID;
    }


    /**
     * This returns the Trip name
     * @return the Trip's name
     */
    public String getName() {
        return name;
    }


    /**
     * This returns the number of persons for a trip
     * @return the number of persons for a trip
     */
    public int getNumOfPpl() {
        return numOfPeople;
    }


    /**
     * This returns the related bus object for a trip.
     * @return the bus object
     */
    public Bus getBus() {
        return bus;
    }

    /**
     * This returns a Trip time's hour
     * @return time's hour 
     */
    public String getHrs() {
        return time[0];
    }
    /**
     * This returns a Trip time's minutes
     * @return time's minutes
     */
    public String getMins() {
        return time[1];
    }

    /**
     * This verifies if a trip is completed.
     * @return true/false if a trip is completed.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * This sets the Trip name.
     * @param name is the new name of a trip
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This sets the number of persons on a trip. 
     * @param newNum is the new number of persons on a trip
     */
    public void setNumOfPpl(int newNum) {
        numOfPeople = newNum;
    }

    /**
     * This sets a new time for the Trip.
     * @param newHrs is the new time's hours
     * @param newMins is the new time's minutes
     */
    public void setTime(String newHrs, String newMins) {
        time[0] = newHrs;
        time[1] = newMins;
    }

    /**
     * This sets a trip to 'completed'
     */
    public void setCompletedTrip(boolean c) {
        completed = c;
    }

    /**
     * This method allows default sorting of trips by ID
     */
    public int compareTo(Trip other) {
        return this.getID() - other.getID();
    }

    /**
     * Formatting the display of a trip
     */    
    public String toString() {
        return ("[#T" + getID() + ", " + getName() + ", " + getNumOfPpl() + ", " + getHrs() + ":" + getMins() + "]");
    }

}