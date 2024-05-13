package universite_paris8.iut.ink_leak.Player;

public class Entité {
    protected String name;
    protected int health;
    protected int strength;
    protected int speed;

    public Entité(String name, int health, int strength, int speed) {
        this.name = name;
        this.health = health;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
