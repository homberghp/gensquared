package entities;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class Door {
    String doorName;
    String color;
    String id;

    public Door( String doorName, String color , String id ) {
        this.doorName = doorName;
        this.color = color;
    }

    public String getDoorName() {
        return doorName;
    }

    public String getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Door{" + "doorName=" + doorName + ", color=" + color + '}';
    }
    
}
