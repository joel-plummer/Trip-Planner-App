
public class Bus {
    /*
     * Attributes that should be here:
     * Enum Type: Small, Medium, Luxurious
     * Int Auto Generated ID (format: B10)
     * Int cost
     * Int maxCapacity
     */

    /**This class generates a bus
      * @param id is an identifier for a bus.
      * @param nextId generates a new id for a consecutive bus.
      * @param max capacity defines the maximum number of persons for a particular bus type.
      * @param cost stores the bus' cost.
    */

    private int id;
    private static int nextId = 0;
    private int cost;  
    private int max_capacity;

    BusType type; // There is another file for this: BusType.java

    public Bus(){};

    /*Sets the maximum bus capacity based on the bus size */
    public Bus(BusType type) {
        this.type = type;
        this.id = getNextId();
        setCapacity(type);
        setCost(type);
    }

    /*Gets the type of bus */
    public BusType getType() {
        return type;
    }

    /*Generates a consecutive ID */
    private int getNextId() {
        return ++nextId;

    }

    /*Allows a preview of an ID */
    public int idPreview(){
        int preview= nextId+1;
        return preview;
    }

    /*Gets a bus ID */
    public int getID() {
        return id;
    }

    /*Sets the maximum capacity of the bus*/
    public void setCapacity(BusType bus) {
        if (bus.equals(BusType.Small))
            max_capacity = 15;
        else if (bus.equals(BusType.Medium))
            max_capacity = 25;
        else if (bus.equals(BusType.Luxurious))
            max_capacity = 40;
    }

    /*Sets the cost of the bus*/
    public void setCost(BusType bus) {
        if (bus.equals(BusType.Small))
            cost = 5000;
        else if (bus.equals(BusType.Medium))
            cost = 7000;
        else if (bus.equals(BusType.Luxurious))
            cost = 12000;
    }

    /*
     * Cost for the buses
     * Small = 5000
     * Medium = 7000
     * Luxurious = 12000
     */

    /*Gets the maximun bus capacity */
    public int getCapacity() {
        return max_capacity;
    }

    /*Gets the bus cost */
    public int getCost() {
        return cost;
    }

    /*Calculates the cost of a bus trip*/
    public double calcBus(BusType bus, int passengers) {
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

