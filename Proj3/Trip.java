

public class Trip {
    /* 
        Attributes that should be here:
        String Name
        Int Auto Generated ID (format: T100)
        Time (array to store hours, and mins as int)
        Boolean Completed
        Int Day
        Bus obj
    */

    //some attributes are missing
    //Some getters and setters are missing

    private String name;
    private boolean completed;
    private int [] time; //example [12, 30]
    private String day; //example "DAY 1"

    public Trip(String name, String day, int [] time) {
        this.name = name;
        this.day = day;
        this.time = time;
        completed = false;

    }


    public String getName() {
        return name;
    }

    public int[] getTime() {
        return time;
    }

    public boolean isCompleted() {
        return completed;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int[] time) {
        this.time = time;
    }

    public void setCompletedTrip() {
        completed = true;
    }


}
