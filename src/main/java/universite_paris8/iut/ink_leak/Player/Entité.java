package universite_paris8.iut.ink_leak.Player;

import javafx.scene.transform.Shear;

public class Entité {
    protected String name;
    protected int health;
    protected int strength;
    protected int characterSpeed;

    public Entité(String name, int health, int strength, int characterSpeed) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.characterSpeed = characterSpeed;
    }
    public int getCharacterSpeed() {
        return characterSpeed;
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
