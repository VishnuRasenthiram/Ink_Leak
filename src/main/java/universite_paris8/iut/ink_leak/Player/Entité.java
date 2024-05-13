package universite_paris8.iut.ink_leak.Player;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.transform.Shear;

public class Entité {
    protected String name;
    protected int health;
    protected int strength;
    protected int characterSpeed;
    protected DoubleProperty posXProperty;
    protected DoubleProperty posYProperty;

    public Entité(String name, int health, int strength, int characterSpeed) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.characterSpeed = characterSpeed;
        posXProperty = new SimpleDoubleProperty();
        posYProperty = new SimpleDoubleProperty();
    }

    public double getPosX() {
        return posXProperty.getValue();
    }

    public DoubleProperty posXProperty() {
        return posXProperty;
    }

    public void setPosXProperty(double posXProperty) {
        this.posXProperty.setValue(posXProperty);
    }

    public double getPosY() {
        return posYProperty.getValue();
    }

    public DoubleProperty posYProperty() {
        return posYProperty;
    }

    public void setPosYProperty(double posYProperty) {
        this.posYProperty.set(posYProperty);
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
