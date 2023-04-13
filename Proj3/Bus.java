public class Bus {
    /* 
        Attributes that should be here:
        Enum Type: Small, Medium, Luxurious
        Int Auto Generated ID (format: B10)
        Int maxCapacity
    */

    BusType type; //There is another file for this class

    public Bus(BusType type) {
        this.type = type;
    }

    public BusType getType(){
        return type;
    }


}

