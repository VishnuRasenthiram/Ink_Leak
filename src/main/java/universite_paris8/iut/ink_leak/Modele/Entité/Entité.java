package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.beans.property.*;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurMurs;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Modele.Position;

import java.util.ArrayList;

public abstract class Entité {

    private double largeur;
    private double longueur;
    private String nom_entite;
    private int attaque_entite;
    private int vitesse_entite;
    private long invincibilite;
    private long dernier_degat;
    private int maxVie;

    private IntegerProperty vie_entiteProperty;
    private StringProperty orientationProperty;

    private Position position;

    private Environnement environnement;


    private final ObjectProperty<MovementState> movementStateProperty;


    public Entité(String nom_entite, int vie_entite, int attaque_entite, double largeur, double longueur, int vitesse_entite, long invincibilite, Environnement environnement) {
        this.nom_entite = nom_entite;
        this.vie_entiteProperty = new SimpleIntegerProperty(vie_entite);
        this.attaque_entite = attaque_entite;
        this.vitesse_entite = vitesse_entite;
        this.largeur = largeur;
        this.longueur=longueur;
        this.position=new Position();
        this.orientationProperty = new SimpleStringProperty("S");
        this.invincibilite = invincibilite;
        this.dernier_degat = 0;

        this.environnement = environnement;

        this.movementStateProperty = new SimpleObjectProperty<>(MovementState.IDLE);
        this.maxVie = vie_entite;

    }
    public Entité(String nom_entite,Environnement environnement) {
        this(nom_entite,1,0,32,32,0,0,environnement);

    }

    public enum MovementState {
        IDLE,
        WALK,
    }

    public boolean peutAller(double x, double y, Map map) {

        if (!estDansMap(x, y, map)) {
            return false;
        } else {

            int[] mur = {2, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23};
            int solDegat = 3;


            for (int i : mur) {
                if (verifCaseSurCoord(i, x, y)==i) {
                    return false;
                }
            }
            if (verifCaseSurCoord(solDegat, x, y)==solDegat) {
                this.prendre_degat(1);
            }
        }

        return true;
    }

    public int verifierInteractionEnFace(double x, double y) {
        int[] liste= {1,2,6,22,24,25,26};

        for (int i : liste) {
            if(verifCaseSurCoord(i, x, y)==i){
                return i;
            }
        }

        return 0;
    }

    private int verifCaseSurCoord(int cases, double x, double y) {
        x = x + 10;
        int coord_Mur_GaucheX = coordEnIndiceGauche_Haut(x);
        x = x - 20;
        int coord_Mur_DroitX = coordEnIndiceDroit_Bas(x);
        y = y + 10;
        int coord_Mur_HautY = coordEnIndiceGauche_Haut(y);
        y = y - 20;
        int coord_Mur_BasY = coordEnIndiceDroit_Bas(y);

        Map map = getMap();
        if(map.getMap(coord_Mur_GaucheX, coord_Mur_HautY) == cases ||
                map.getMap(coord_Mur_DroitX, coord_Mur_HautY) == cases ||
                map.getMap(coord_Mur_GaucheX, coord_Mur_BasY) == cases ||
                map.getMap(coord_Mur_DroitX, coord_Mur_BasY) == cases)
        {
            return cases;
        }
        return 0;

    }

    public int coorDansLeTableauY(double coord) {
        return (int) Math.ceil(coord) / 32;
    }

    public int coorDansLeTableauX(double coord) {
        return (int) Math.ceil(coord) / 32;
    }

    public int coordEnIndiceGauche_Haut(double coord) {
        return (int) Math.ceil(coord) / 32;
    }

    public int coordEnIndiceDroit_Bas(double coord) {
        return (int) Math.ceil(coord + 32) / 32;
    }

    private boolean estDansMap(double x, double y, Map map) {

        return (x > 0 && y > 0)
                &&
                (x < (map.getMap().length * 32) - 32
                        &&
                        y < (map.getMap().length * 32) - 32);
    }

    public boolean enContact(Entité entite2) {
        for (int i = (int) getPosX(); i < (int) getPosX() + getLongueur(); i++) {
            for (int j = (int) getPosY(); j < (int) getPosY() + getLargeur(); j++) {
                if (entite2.contientPixel(i, j)) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean contientPixel(int x, int y) {
        return x >= getPosX() && x < getPosX() + getLongueur() &&
                y >= getPosY() && y < getPosY() + getLargeur();
    }

    public void prendre_degat(int degat) {

        if (this.getVie() - degat < 0) {
            this.setVie_entite(0);

        } else if (System.currentTimeMillis() - dernier_degat > invincibilite) {

            this.setVie_entite(this.getVie() - degat);
            this.dernier_degat = System.currentTimeMillis();
        }
    }

    public abstract void attaque();

    public void déplacement(String déplacementDirection) {

        double x = getPosX();
        double y = getPosY();
        //transformer déplacement en int pour le switch
        int direction = Integer.parseInt(déplacementDirection);
        if (direction == 4) {
            direction = (int) (Math.random() * 9);
        }
        if (direction == 5) {
            attaque();
        }
        switch (direction) {
            case 0:
                if (peutAller(x, y - getVitesse_entite(), getMap())) {
                    setPosYProperty(y - getVitesse_entite());
                    setOrientation("S");

                }
                break;
            case 1:
                if (peutAller(x, y + getVitesse_entite(), getMap())) {
                    setPosYProperty(y + getVitesse_entite());
                    setOrientation("N");

                }
                break;
            case 2:

                if (peutAller(x - getVitesse_entite(), y, getMap())) {
                    setPosXProperty(x - getVitesse_entite());
                    setOrientation("O");

                }
                break;
            case 3:
                if (peutAller(x + getVitesse_entite(), y, getMap())) {
                    setPosXProperty(x + getVitesse_entite());
                    setOrientation("E");
                }
                break;
            default:
                break;

        }
        attaque();
    }


    public abstract void gagner_vie(int nb_vie_gagnee);

    public double getPosX() {
        return position.getPosX();
    }

    public DoubleProperty posXProperty() {
        return position.posXPropertyProperty();
    }

    public String getOrientation() {
        return orientationProperty.getValue();
    }

    public StringProperty getOrientationProperty() {
        return orientationProperty;
    }

    public void setOrientation(String orientation) {
        this.orientationProperty.setValue(orientation);
    }

    public void setPosXProperty(double posX) {
        position.setPosX(posX);
    }

    public double getPosY() {
        return position.getPosY();
    }

    public DoubleProperty posYProperty() {
        return position.posYPropertyProperty();
    }

    public void setPosYProperty(double posY) {
        position.setPosY(posY);
    }

    public void setEmplacement(int x, int y) {
        if (x == 19) {
            setPosXProperty((x * 32) - 2);
        } else if (x == 0) {
            setPosXProperty(2);
        } else {
            setPosXProperty(x * 32);
        }
        if (y == 19) {
            setPosYProperty((y * 32) - 2);
        } else if (y == 0) {
            setPosYProperty(2);
        } else {
            setPosYProperty(y * 32);
        }

    }

    public int getVitesse_entite() {
        return vitesse_entite;
    }

    public String getNom_entite() {
        return nom_entite;
    }


    public IntegerProperty getVie_entiteProperty() {
        return vie_entiteProperty;
    }

    public int getVie() {
        return vie_entiteProperty.getValue();
    }

    public void setVie_entite(int vie_entiteProperty) {
        this.vie_entiteProperty.setValue(vie_entiteProperty);
    }

    public int getAttaque_entite() {
        return attaque_entite;
    }


    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public Map getMap(){
        return environnement.getMap();
    }

    public GenerateurMurs getGenerateurMurs(){
        return environnement.getGenerateurMurs();
    }
    public GenerateurEnnemis getGenerateurEnnemis() { return environnement.getGenerateurEnnemis(); }

    public ObjectProperty<MovementState> getMovementStateProperty() {
        return movementStateProperty;
    }

    public void setMovementState(MovementState movementState) {
        movementStateProperty.set(movementState);
    }

    public int getmaxVie() {
        return maxVie;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }


}
