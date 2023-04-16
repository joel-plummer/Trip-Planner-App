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
     * @param name defines the name of a trip.
     * @param completed defines whether a trip is completed.
     * @param time defines the time in which the trip should be carried out.
     * @param numOfPeople states the number of persons on a given trip.
     * @param ID generates a unique ID for the trip.
     * @param nextID generates a consecutive ID.
     */

    private String name;
    private boolean completed;
    private String[] time = new String[2]; // example [12, 30]
    private int numOfPeople;   
    private int ID;
    private static int nextID = 0;
    private Bus bus;

    /*Generates a consecutive ID */
    private int getNextID() {
        return ++nextID;
    }
    

    /*Gives a preview of the next ID number */
    public int idPreview(){
        int preview= nextID+1;
        return preview;
    }

    public Trip(){}

    public Trip(int ID, String name, Bus bus, int numOfPeople, String hrs, String mins){
        this.ID = ID;
        this.name = name.replaceAll("\\s", "");
        time[0] = hrs;
        time[1] = mins;
        this.bus = bus;
        this.numOfPeople = numOfPeople;
        completed = false;
    }
    
    public Trip(String name, Bus bus, int numOfPeople, String hrs, String mins) {
        this.ID = getNextID();
        this.name = name.replaceAll("\\s", "");
        time[0] = hrs;
        time[1] = mins;
        this.bus = bus;
        this.numOfPeople = numOfPeople;
        completed = false;
    }

    /*Gets the Trip ID */
    public int getID() {
        return ID;
    }

    /*Gets the Trip name */
    public String getName() {
        return name;
    }

    /*Gets the number of persons for the trip */
    public int getNumOfPpl() {
        return numOfPeople;
    }

    /*Gets the Bus */
    public Bus getBus() {
        return bus;
    }

    /*Gets the hours component of the time */
    public String getHrs() {
        return time[0];
    }

    /*Gets the minutes component of the time */
    public String getMins() {
        return time[1];
    }

    /*Finds out whether a trip is completed */
    public boolean isCompleted() {
        return completed;
    }

    /*Sets the trip name */
    public void setName(String name) {
        this.name = name;
    }

    /*Sets the number of persons on a trip */
    public void setNumOfPpl(int newNum) {
        numOfPeople = newNum;
    }

    /*Sets the trip time */
    public void setTime(String newHrs, String newMins) {
        time[0] = newHrs;
        time[1] = newMins;
    }

    /*Sets a completed trip */
    public void setCompletedTrip(boolean c) {
        completed = c;
    }

    /*Method to compare IDs */
    public int compareTo(Trip other) {
        return this.getID() - other.getID();
    }

    public String toString() {
        return ("[#T" + getID() + ", " + getName() + ", " + getNumOfPpl() + ", " + getHrs() + ":" + getMins() + "]");
    }

}
