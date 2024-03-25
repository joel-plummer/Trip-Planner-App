package code;

/**
 * This creates a bus needed to carry out a trip on a particular day.
 */

public class Bus {

    /*
     * Attributes that should be here:
     * Enum Type: Small, Medium, Luxurious
     * Int Auto Generated ID (format: B10)
     * Int maxCapacity
     */

    private int id;
    private static int nextId = 0;
    private int max_capacity;

    /** 
     * There is another file for this: BusType.java 
     */
    BusType type; 

    //Empty Bus Constructor
    public Bus(){};

    /**
     * This creates an instance of a bus.
     * @param ID describes an indentification tag associated to a particular bus
     * @param type describes the type of bus
     */
    public Bus(int ID, BusType type) 
    {
        this.type = type;
        this.id = ID;
        setCapacity(type);
    }
    
    /**
     * This sets a bus type and an associated ID.
     * @param type describes the type of bus
     */
    public Bus(BusType type) 
    {
        this.type = type;
        this.id = getNextId();
        setCapacity(type);
    }
    

    /**
     * This returns the type of bus 
     * @return this bus' type
     */
    public BusType getType() 
    {
        return type;
    }

    /**
     * This returns the next ID in sequence
     * @return an incremented ID number 
     */
    private int getNextId() 
    {
        return ++nextId;

    }

    /**
     * This allows the user to preview the consecutive ID
     * @return the consecutive bus ID 
     */
    public int idPreview()
    {
        int preview= nextId+1;
        return preview;
    }

    /**
     * This returns the ID of the bus.
     * @return this bus' ID
     */
    public int getID() 
    {
        return id;
    }

    /**
     * This sets the capacity based on the type of bus
     * @param bus is the type of bus
     *
     * The maximum person capacity for each bus is as follows:
     * 
     * Small - 15 persons
     * Medium - 25 persons
     * Luxurious - 40 persons 
     * 
     */
    public void setCapacity(BusType bus) 
    {
        if (bus.equals(BusType.Small))
            max_capacity = 15;
        else if (bus.equals(BusType.Medium))
            max_capacity = 25;
        else if (bus.equals(BusType.Luxurious))
            max_capacity = 40;
    }
    

    /**
     * This returns the the maximum capacity for the bus
     * @return this bus' max capacity
     */
    public int getCapacity() 
    {
        return max_capacity;
    }


    /**
     * This calculates the cost for a bus 
     * @param bus is the type of bus
     * @param passengers is the amount of passengers necessary for the trip
     * @return this trip's final cost based on the type of bus and number of passengers
     * 
     * Cost for the buses is as follows:
     * 
     * Small = 5000,
     * Medium = 7000,
     * Luxurious = 12000.
     * 
     */
    public double calcBus(BusType bus, int passengers) 
    {
        final double tax = 0.15;
        int bus_cost = 0;
        double final_cost;
        double sales_price;

        if (bus.equals(BusType.Small))
            bus_cost = 5000;
        else if (bus.equals(BusType.Medium))
            bus_cost = 7000;
        else if (bus.equals(BusType.Luxurious))
            bus_cost = 12000;

        sales_price = (bus_cost * passengers) * tax;
        final_cost = (bus_cost * passengers) + sales_price;
        return final_cost;
    }
}