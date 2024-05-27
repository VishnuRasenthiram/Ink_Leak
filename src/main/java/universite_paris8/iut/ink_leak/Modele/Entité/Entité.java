package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.beans.property.*;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.ink_leak.Controller.VieObs;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;

public abstract class Entité {
    protected int taille_entite;

    protected String nom_entite;
    protected IntegerProperty vie_entiteProperty;
    private StringProperty orientationProperty;
    protected int attaque_entite;
    protected int vitesse_entite;
    protected long invincibilite;
    protected long dernier_degat;
    protected DoubleProperty posXProperty;
    protected DoubleProperty posYProperty;
    private GenerateurEnnemis spawner;

    public Entité(String nom_entite, int vie_entite, int attaque_entite,int taille_entite, int vitesse_entite, long invincibilite,GenerateurEnnemis spawner) {
        this.nom_entite = nom_entite;
        this.vie_entiteProperty = new SimpleIntegerProperty(vie_entite);
        this.attaque_entite = attaque_entite;
        this.vitesse_entite = vitesse_entite;
        this.taille_entite = taille_entite;
        this.posXProperty = new SimpleDoubleProperty();
        this.posYProperty = new SimpleDoubleProperty();
        this.orientationProperty = new SimpleStringProperty("S");
        this.invincibilite =invincibilite;
        this.dernier_degat = 0;
        this.spawner = spawner;


    }


    public boolean peutAller(double x, double y, Pane mainPane) {
        double rayon = this.taille_entite;
        TilePane tuileMap = (TilePane) mainPane.lookup("#tuileMap");

        if (estDansMap(x, y, mainPane)) {
            return false;
        }
        for (Node tuile : tuileMap.getChildren()) {
            Bounds délimitationDansParent = tuile.localToParent(tuile.getBoundsInLocal());
            double xToutaGauche = délimitationDansParent.getMinX();
            double yToutenBas = délimitationDansParent.getMinY();
            double largeur = délimitationDansParent.getWidth();
            double hauteur = délimitationDansParent.getHeight();

            boolean entite_sur_case = x + rayon >= xToutaGauche && x - rayon <= xToutaGauche + largeur - this.taille_entite && y + rayon >= yToutenBas && y - rayon <= yToutenBas + hauteur - this.taille_entite;

            if (tuile.getId().equals("rouge")) {

                if (entite_sur_case) {

                    return false;
                }
            }

            if (tuile.getId().equals("bleue")) {

                if (entite_sur_case) {
                    this.prendre_degat(1);
                }
            }


        }

        return true;
    }
    private boolean estDansMap(double x, double y, Pane mainPane) {
        TilePane tuileMap = (TilePane) mainPane.lookup("#tuileMap");
        Bounds délimitationDansParent = tuileMap.localToParent(tuileMap.getBoundsInLocal());

        double rayon = this.taille_entite;

        double xToutaGauche = délimitationDansParent.getMinX();
        double yToutenBas = délimitationDansParent.getMinY();
        double largeur = délimitationDansParent.getWidth();
        double hauteur = délimitationDansParent.getHeight();

        if (x - rayon >= xToutaGauche - this.taille_entite && x + rayon <= xToutaGauche + largeur && y - rayon >= yToutenBas - this.taille_entite && y + rayon <= yToutenBas + hauteur) {

            return false;
        }
        return true;
    }


    public abstract void attaque(Pane mainPane);
    public abstract void déplacement(Pane mainPane);

    public boolean enContact(Pane pane1, Pane pane2) { return pane1.getBoundsInParent().intersects(pane2.getBoundsInParent()); }

    public void prendre_degat(int degat){

        if (this.getVie() - degat < 0) {

            this.setVie_entite(0);
            this.spawner.getListeEntite().remove(this);

        }
        else if (System.currentTimeMillis() - dernier_degat > invincibilite) {

            this.setVie_entite(this.getVie() - degat);
            this.dernier_degat = System.currentTimeMillis();
        }
    }

    public abstract void gagner_vie(int nb_vie_gagnee);

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
    public int getVie(){
        return vie_entiteProperty.getValue();
    }

    public void setVie_entite(int vie_entiteProperty) {
        this.vie_entiteProperty.setValue(vie_entiteProperty);
    }

    public int getAttaque_entite() {
        return attaque_entite;
    }

    public void setAttaque_entite(int attaque_entite) {
        this.attaque_entite = attaque_entite;
    }

    public void setVitesse_entite(int speed) { this.vitesse_entite = speed; }

    public int getTaille_entite() {
        return taille_entite;
    }

    public GenerateurEnnemis getSpawner(){
        return spawner;
    }
}
