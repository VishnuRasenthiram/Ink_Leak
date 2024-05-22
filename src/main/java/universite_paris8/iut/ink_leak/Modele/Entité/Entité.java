package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Shear;

import java.io.File;

public abstract class Entité {
    protected int size;

    protected String name;
    protected IntegerProperty health;
    private IntegerProperty orientationProperty;
    protected int strength;
    protected int characterSpeed;
    protected DoubleProperty posXProperty;
    protected DoubleProperty posYProperty;
    protected char direction;

    public Entité(String name, int health, int strength, int characterSpeed) {
        this.name = name;
        this.health = new SimpleIntegerProperty(health);
        this.strength = strength;
        this.characterSpeed = characterSpeed;
        this.size = size;
        posXProperty = new SimpleDoubleProperty();
        posYProperty = new SimpleDoubleProperty();
        this.orientationProperty = new SimpleIntegerProperty(0);
        this.direction = 'S';
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

    public IntegerProperty getHealth() {
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

    public void setCharacterSpeed(int speed) { this.characterSpeed = speed; }
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

            Bounds boundsInParent = tuile.localToParent(tuile.getBoundsInLocal());
            double xb = boundsInParent.getMinX();
            double yb = boundsInParent.getMinY();
            double width = boundsInParent.getWidth();
            double height = boundsInParent.getHeight();

            boolean joueur_sur_case = x + radius >= xb && x - radius <= xb + width - getSize() && y + radius >= yb && y - radius <= yb + height - getSize();

            if (tuile.getId() == "rouge") {

                if (joueur_sur_case) {
                    // Crée un rectangle transparent avec une bordure rouge
                    Rectangle collisionRect = new Rectangle(xb, yb, width, height);
                    collisionRect.setFill(Color.TRANSPARENT);
                    collisionRect.setStroke(Color.RED);
                    collisionRect.setStrokeWidth(1);

                    // Ajoute le rectangle à la scène
                    PlayerPane.getChildren().add(collisionRect);



                    return false;
                }
            }

            if (tuile.getId() == "bleue") {
                if (joueur_sur_case) {
                    this.prendre_degat(1);
                    //barre.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Health/health_" + joueur.getHealth().getValue() + ".png").toURI().toString()));
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

    public abstract void prendre_degat(int degat);
}
