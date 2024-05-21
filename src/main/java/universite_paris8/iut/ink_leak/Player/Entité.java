package universite_paris8.iut.ink_leak.Player;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Entité {
    protected String name;
    protected IntegerProperty health;
    protected int strength;
    protected int speed;

    public Entité(String name, int health, int strength, int speed) {
        this.name = name;
        this.health = new SimpleIntegerProperty(health);
        this.strength = strength;
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public final IntegerProperty HealthProperty() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
