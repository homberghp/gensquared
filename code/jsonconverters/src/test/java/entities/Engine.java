package entities;

/**
 *
 * @author "Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}"
 */
public class Engine {

    final String type;
    final int cilinders;
    final double hp;
    final String id;

    public Engine( String type, int cilinders, double hp, String id ) {
        this.type = type;
        this.cilinders = cilinders;
        this.hp = hp;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public int getCilinders() {
        return cilinders;
    }

    public double getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "Engine{" + "type=" + type + ", cilinders=" + cilinders + ", hp="
                + hp + '}';
    }

    public String getId() {
        return id;
    }

}
