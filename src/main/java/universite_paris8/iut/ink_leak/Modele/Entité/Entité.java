package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Entité {
    protected String name;
    protected IntegerProperty health;
    protected int strength;
    protected int characterSpeed;
    protected DoubleProperty posXProperty;
    protected DoubleProperty posYProperty;

    public Entité(String name, int health, int strength, int characterSpeed) {
        this.name = name;
        this.health = new SimpleIntegerProperty(health);
        this.strength = strength;
        this.characterSpeed = characterSpeed;
        posXProperty = new SimpleDoubleProperty();
        posYProperty = new SimpleDoubleProperty();
        this.orientationProperty = new SimpleIntegerProperty(0);
    }

    public double getPosX() {
        return posXProperty.getValue();
    }

    public DoubleProperty posXProperty() {
        return posXProperty;
    }

    public int getOrientationProperty() {
        return orientationProperty.get();
    }

    public IntegerProperty orientationPropertyProperty() {
        return orientationProperty;
    }

    public void setOrientationProperty(int orientationProperty) {
        this.orientationProperty.set(orientationProperty);
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

    public final IntegerProperty getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health.setValue(health);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getSize() {
        return size;
    }
    public boolean peutAller(double x, double y, Pane PlayerPane) {
        double radius = getSize();
        TilePane tuileMap = (TilePane) PlayerPane.lookup("#tuileMap");

        if (EstDansMap(x, y, PlayerPane)) {
            return false;
        }
        for (Node tuile : tuileMap.getChildren()) {
            if (tuile.getId() == "rouge") {
                Bounds boundsInParent = tuile.localToParent(tuile.getBoundsInLocal());
                double xb = boundsInParent.getMinX();
                double yb = boundsInParent.getMinY();
                double width = boundsInParent.getWidth();
                double height = boundsInParent.getHeight();

                if (x + radius >= xb && x - radius <= xb + width - getSize() && y + radius >= yb && y - radius <= yb + height - getSize()) {
                    // Crée un rectangle transparent avec une bordure rouge
                    Rectangle collisionRect = new Rectangle(xb, yb, width, height);
                    collisionRect.setFill(Color.TRANSPARENT);
                    collisionRect.setStroke(Color.BLUE);
                    collisionRect.setStrokeWidth(2);

                    // Ajoute le rectangle à la scène
                    PlayerPane.getChildren().add(collisionRect);



                    return false;
                }
            }
        }

        return true;
    }
    private boolean EstDansMap(double x, double y, Pane PlayerPane) {
        TilePane tuileMap = (TilePane) PlayerPane.lookup("#tuileMap");
        Bounds boundsInParent = tuileMap.localToParent(tuileMap.getBoundsInLocal());

        double radius = getSize();

        double xb = boundsInParent.getMinX();
        double yb = boundsInParent.getMinY();
        double width = boundsInParent.getWidth();
        double height = boundsInParent.getHeight();

        if (x - radius >= xb - getSize() && x + radius <= xb + width && y - radius >= yb - getSize() && y + radius <= yb + height) {

            return false;
        }
        return true;
    }

    public void setCharacterSpeed(int speed) { this.characterSpeed = speed; }
}
