package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.beans.property.*;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public abstract class Entité {
    private int taille_entite;
    private String nom_entite;
    private IntegerProperty vie_entiteProperty;
    private StringProperty orientationProperty;
    private int attaque_entite;
    private int vitesse_entite;
    private long invincibilite;
    private long dernier_degat;
    private DoubleProperty posXProperty;
    private DoubleProperty posYProperty;
    private Map map;
    private GenerateurEnnemis spawner;

    public Entité(String nom_entite, int vie_entite, int attaque_entite,int taille_entite, int vitesse_entite, long invincibilite,Map map,GenerateurEnnemis spawner) {
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
        this.map = map;
        this.spawner = spawner;


    }


    public boolean peutAller(double x, double y, Map map) {

        if (!estDansMap(x, y,  map)) {
            return false;
        }
        else {

            int mur=1;
            int solDegat=2;
            if(verifCaseSurCoord(mur,x,y)){
                return false;
            }

            if(verifCaseSurCoord(solDegat,x,y)){
                this.prendre_degat(1);
            }
        }

        return true;
    }
    private boolean verifCaseSurCoord(int cases, double x, double y) {
        int coord_Mur_GaucheX =coordEnIndiceGauche_Haut(x);
        int coord_Mur_DroitX =coordEnIndiceDroit_Bas(x);
        int coord_Mur_HautY =coordEnIndiceGauche_Haut(y);
        int coord_Mur_BasY =coordEnIndiceDroit_Bas(y);

        return map.getMap(coord_Mur_GaucheX,coord_Mur_HautY)==cases ||
                map.getMap(coord_Mur_DroitX,coord_Mur_HautY)==cases ||
                map.getMap(coord_Mur_GaucheX,coord_Mur_BasY)==cases ||
                map.getMap(coord_Mur_DroitX,coord_Mur_BasY)==cases;
    }

    private int coordEnIndiceGauche_Haut(double coord){
        return (int)Math.floor(coord)/32;
    }
    private int coordEnIndiceDroit_Bas(double coord){
        return (int)Math.floor(coord+32)/32;
    }
    private boolean estDansMap(double x, double y, Map map) {

        return (x>0 && y>0)
                &&
               (x<(map.getMap().length*32)-32
                &&
                y<(map.getMap().length*32)-32);
    }


    public abstract void attaque(Entité entitéAttaqué);
    public abstract void déplacement(int déplacementDirection);

    public boolean enContact(double coordAttaqueX,double coordAttaqueY, Entité entité2) {
        int coord_Sprite_GaucheX_Ent1 =coordEnIndiceGauche_Haut(coordAttaqueX);
        int coord_Sprite_DroitX_Ent1 =coordEnIndiceDroit_Bas(coordAttaqueX);
        int coord_Sprite_HautY_Ent1 =coordEnIndiceGauche_Haut(coordAttaqueY);
        int coord_Sprite_BasY_Ent1 =coordEnIndiceDroit_Bas(coordAttaqueY);

        int coord_Sprite_GaucheX_Ent2 =coordEnIndiceGauche_Haut(entité2.getPosX());
        int coord_Sprite_DroitX_Ent2 =coordEnIndiceDroit_Bas(entité2.getPosX());
        int coord_Sprite_HautY_Ent2 =coordEnIndiceGauche_Haut(entité2.getPosY());
        int coord_Sprite_BasY_Ent2 =coordEnIndiceDroit_Bas(entité2.getPosY());

        if(coord_Sprite_GaucheX_Ent1==coord_Sprite_GaucheX_Ent2 || coord_Sprite_DroitX_Ent1==coord_Sprite_DroitX_Ent2 || coord_Sprite_BasY_Ent1==coord_Sprite_BasY_Ent2 || coord_Sprite_HautY_Ent1==coord_Sprite_HautY_Ent2){
            return true;

        }

        return false;
    }

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

    public Map getMap(){
        return map;
    }
}
