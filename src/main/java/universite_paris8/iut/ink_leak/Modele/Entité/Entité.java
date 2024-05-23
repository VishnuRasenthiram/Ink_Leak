package universite_paris8.iut.ink_leak.Modele.Entité;
import javafx.beans.property.*;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Entité {
    protected int taille_entite;

    protected String nom_entite;
    protected IntegerProperty vie_entiteProperty;
    private StringProperty orientationProperty;
    protected int attaque_entite;
    protected int vitesse_entite;
    protected DoubleProperty posXProperty;
    protected DoubleProperty posYProperty;


    public Entité(String name, int health, int strength, int characterSpeed) {
        this.nom_entite = name;
        this.vie_entiteProperty = new SimpleIntegerProperty(health);
        this.attaque_entite = strength;
        this.vitesse_entite = characterSpeed;
        this.taille_entite = taille_entite;
        posXProperty = new SimpleDoubleProperty();
        posYProperty = new SimpleDoubleProperty();
        this.orientationProperty = new SimpleStringProperty("S");

    }

    public double getPosX() {
        return posXProperty.getValue();
    }

    public DoubleProperty posXProperty() {
        return posXProperty;
    }

    public String getOrientationProperty() {
        return orientationProperty.get();
    }

    public StringProperty orientationProperty() {
        return orientationProperty;
    }

    public void setOrientationProperty(String orientationProperty) {
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

    public void setEmplacement(double x , double y ){
        setPosXProperty(x);
        setPosYProperty(y);
    }

    public int getVitesse_entite() {
        return vitesse_entite;
    }

    public String getNom_entite() {
        return nom_entite;
    }

    public void setNom_entite(String nom_entite) {
        this.nom_entite = nom_entite;
    }

    public IntegerProperty getVie_entiteProperty() {
        return vie_entiteProperty;
    }

    public void setVie_entiteProperty(int vie_entiteProperty) {
        this.vie_entiteProperty.setValue(vie_entiteProperty);
    }

    public int getAttaque_entite() {
        return attaque_entite;
    }

    public void setAttaque_entite(int attaque_entite) {
        this.attaque_entite = attaque_entite;
    }

    public void setVitesse_entite(int speed) { this.vitesse_entite = speed; }

    public int getTaille_joueur() {
        return taille_entite;
    }

    public boolean peutAller(double x, double y, Pane PlayerPane) {
        double radius = getTaille_joueur();
        TilePane tuileMap = (TilePane) PlayerPane.lookup("#tuileMap");

        if (estDansMap(x, y, PlayerPane)) {
            return false;
        }
        for (Node tuile : tuileMap.getChildren()) {

            Bounds boundsInParent = tuile.localToParent(tuile.getBoundsInLocal());
            double xb = boundsInParent.getMinX();
            double yb = boundsInParent.getMinY();
            double width = boundsInParent.getWidth();
            double height = boundsInParent.getHeight();

            boolean entite_sur_case = x + radius >= xb && x - radius <= xb + width - getTaille_joueur() && y + radius >= yb && y - radius <= yb + height - getTaille_joueur();

            if (tuile.getId() == "rouge") {

                if (entite_sur_case) {
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
                if (entite_sur_case) {
                    this.prendre_degat(1);
                }
            }
        }

        return true;
    }
    private boolean estDansMap(double x, double y, Pane PlayerPane) {
        TilePane tuileMap = (TilePane) PlayerPane.lookup("#tuileMap");
        Bounds boundsInParent = tuileMap.localToParent(tuileMap.getBoundsInLocal());

        double radius = getTaille_joueur();

        double xb = boundsInParent.getMinX();
        double yb = boundsInParent.getMinY();
        double width = boundsInParent.getWidth();
        double height = boundsInParent.getHeight();

        if (x - radius >= xb - getTaille_joueur() && x + radius <= xb + width && y - radius >= yb - getTaille_joueur() && y + radius <= yb + height) {

            return false;
        }
        return true;
    }

    public abstract void prendre_degat(int degat);

    public abstract void gagner_vie(int nb_vie_gagnee);
}
