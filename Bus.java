
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

    BusType type; // There is another file for this

    public Bus(){};

    public Bus(BusType type) {
        this.type = type;
        this.id = getNextId();
        if (type.equals(BusType.Small))
            max_capacity = 15;
        if (type.equals(BusType.Medium))
            max_capacity = 25;
        if (type.equals(BusType.Luxurious))
            max_capacity = 40;
    }

    public BusType getType() {
        return type;
    }

    private int getNextId() {
        return ++nextId;

    }

    public int getID() {
        return id;
    }

    // Set the maximum capacity of the bus
    public void setCapacity(BusType bus) {
        if (bus.equals(BusType.Small))
            max_capacity = 15;
        else if (bus.equals(BusType.Medium))
            max_capacity = 25;
        else if (bus.equals(BusType.Luxurious))
            max_capacity = 40;
    }
    /*
     * Cost for the buses
     * Small = 5000
     * Medium = 7000
     * Luxurious = 12000
     */

    public int getCapacity() {
        return max_capacity;
    }

    // Calculates the cost of a bus trip
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

        //sales_price = (bus_cost * passengers) * tax;
        //final_cost = (bus_cost * passengers) + sales_price;
        return bus_cost;
    }
}
