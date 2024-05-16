package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Shear;

public class Entité {
    protected int size;

    protected String name;
    protected int health;
    protected int strength;
    protected int characterSpeed;
    protected DoubleProperty posXProperty;
    protected DoubleProperty posYProperty;

    public Entité(String name, int health, int strength, int characterSpeed, int size) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.characterSpeed = characterSpeed;
        this.size = size;
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
    public int getSize() {
        return size;
    }

    public boolean peutAller(double x, double y, Pane PlayerPane) {
        double radius = getSize();
        TilePane tuileMap = (TilePane) PlayerPane.lookup("#tuileMap");
        System.out.println("xerererer");

        if (EstDansMap(x, y, PlayerPane)) {
            return false;
        }
        System.out.println("xsfsfsffs");
        for (Node tuile : tuileMap.getChildren()) {
            if (tuile.getId() == "rouge") {
                Bounds boundsInParent = tuile.localToParent(tuile.getBoundsInLocal());
                double xb = boundsInParent.getMinX();
                double yb = boundsInParent.getMinY();
                double width = boundsInParent.getWidth();
                double height = boundsInParent.getHeight();

                if (x + radius >= xb && x - radius <= xb + width && y + radius >= yb && y - radius <= yb + height) {
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

        if (x - radius >= xb && x + radius <= xb + width && y - radius >= yb && y + radius <= yb + height) {
            return false;
        }
        return true;
    }
}
