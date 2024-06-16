package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.beans.property.*;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurMurs;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

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

    private DoubleProperty posXProperty;
    private DoubleProperty posYProperty;

    private Map map;

    private GenerateurEnnemis generateurEnnemis;
    private GenerateurObjets generateurObjets;
    private GenerateurMurs generateurMurs;

    private final ObjectProperty<MovementState> movementStateProperty;

    public Entité(String nom_entite, int vie_entite, int attaque_entite, double largeur, double longueur, int vitesse_entite, long invincibilite, Map map, GenerateurEnnemis generateurEnnemis, GenerateurObjets generateurObjets, GenerateurMurs generateurMurs) {
        this.nom_entite = nom_entite;
        this.vie_entiteProperty = new SimpleIntegerProperty(vie_entite);
        this.attaque_entite = attaque_entite;
        this.vitesse_entite = vitesse_entite;
        this.largeur = largeur;
        this.longueur=longueur;
        this.posXProperty = new SimpleDoubleProperty();
        this.posYProperty = new SimpleDoubleProperty();
        this.orientationProperty = new SimpleStringProperty("S");
        this.invincibilite =invincibilite;
        this.dernier_degat = 0;
        this.map = map;
        this.generateurEnnemis = generateurEnnemis;
        this.generateurObjets = generateurObjets;
        this.generateurMurs = generateurMurs;
        this.movementStateProperty = new SimpleObjectProperty<>(MovementState.IDLE);
        this.maxVie = vie_entite;

    }

    public Entité(String nom_entite, Map map, GenerateurObjets generateurObjets) {
        this(nom_entite,1,0,32,32,0,0,map,null,generateurObjets,null);

    }
    public enum MovementState {
        IDLE,
        WALK,
    }

    public boolean peutAller(double x, double y, Map map) {

        if (!estDansMap(x, y,  map)) {
            return false;
        }
        else {

            int[] mur={2,4,5,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23};
            int solDegat=3;


            for(int i : mur){
                if(verifCaseSurCoord(i,x,y)){
                    return false;
                }
            }
            if(verifCaseSurCoord(solDegat,x,y)){
                this.prendre_degat(1);
            }
        }

        return true;
    }
    public int verifierInteractionEnFace(double x, double y) {
        if(verifCaseSurCoord(1,x,y)){
            return 1;
        }else if(verifCaseSurCoord(2,x,y)) {
            return 2;
        }else if(verifCaseSurCoord(6,x,y)){
            return 6;
        }else if(verifCaseSurCoord(22,x,y)){
            return 22;
        } else if(verifCaseSurCoord(24,x,y)){
            return 24;
        }else if(verifCaseSurCoord(25,x,y)){
            return 25;
        }else if(verifCaseSurCoord(26,x,y)){
            return 26;
        } else if (verifCaseSurCoord(23,x,y)) {
            return 23;
        }

        return 0;
    }
    private boolean verifCaseSurCoord(int cases, double x, double y) {
        x = x +10;
        int coord_Mur_GaucheX =coordEnIndiceGauche_Haut(x);
        x = x -20;
        int coord_Mur_DroitX =coordEnIndiceDroit_Bas(x);
        y = y +10;
        int coord_Mur_HautY =coordEnIndiceGauche_Haut(y);
        y = y -20;
        int coord_Mur_BasY =coordEnIndiceDroit_Bas(y);


        return map.getMap(coord_Mur_GaucheX,coord_Mur_HautY)==cases ||
                map.getMap(coord_Mur_DroitX,coord_Mur_HautY)==cases ||
                map.getMap(coord_Mur_GaucheX,coord_Mur_BasY)==cases ||
                map.getMap(coord_Mur_DroitX,coord_Mur_BasY)==cases;
    }
    public int coorDansLeTableauY(double coord){
        return (int)Math.ceil(coord)/32;
    }
    public int coorDansLeTableauX(double coord){
        return (int)Math.ceil(coord)/32;
    }
    public int coordEnIndiceGauche_Haut(double coord){
        return (int)Math.ceil(coord)/32;
    }
    public int coordEnIndiceDroit_Bas(double coord){
        return (int)Math.ceil(coord+32)/32;
    }
    private boolean estDansMap(double x, double y, Map map) {

        return (x>0 && y>0)
                &&
                (x<(map.getMap().length*32)-32
                        &&
                        y<(map.getMap().length*32)-32);
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

    public void prendre_degat(int degat){

        if (this.getVie() - degat < 0) {
            this.setVie_entite(0);

        }
        else if (System.currentTimeMillis() - dernier_degat > invincibilite) {

            this.setVie_entite(this.getVie() - degat);
            this.dernier_degat = System.currentTimeMillis();
        }
    }

    public abstract void attaque();
    public void déplacement(String déplacementDirection){

            double x = getPosX();
            double y = getPosY();
            //transformer déplacement en int pour le switch
            int direction = Integer.parseInt(déplacementDirection);
            if (direction == 4){
                direction = (int) (Math.random() * 9);
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
        return posXProperty.getValue();
    }

    public DoubleProperty posXProperty() {
        return posXProperty;
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

    public void setEmplacement(int x , int y ){
        if(x==19){
            setPosXProperty((x*32)-2);
        } else if (x==0) {
            setPosXProperty((x*32)+2);
        }
        else {
            setPosXProperty(x*32);
        }
        if(y==19){
            setPosYProperty((y*32)-2);
        } else if (y==0) {
            setPosYProperty((y*32)+2);
        }
        else {
            setPosYProperty(y*32);
        }

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

    public double getLongueur() {
        return longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public GenerateurEnnemis getGenerateurEnnemis(){
        return generateurEnnemis;
    }

    public Map getMap(){
        return map;
    }

    public GenerateurObjets getGenerateurObjets() {
        return generateurObjets;
    }

    public GenerateurMurs getGenerateurMurs() {
        return generateurMurs;
    }

    public long getDernier_degat() {
        return dernier_degat;
    }

    public void setDernier_degat(long dernier_degat) {
        this.dernier_degat = dernier_degat;
    }

    public long getInvincibilite() {
        return invincibilite;
    }

    public void setInvincibilite(long invincibilite) {
        this.invincibilite = invincibilite;
    }
    public ObjectProperty<MovementState> getMovementStateProperty() {
        return movementStateProperty;
    }
    public void setMovementState(MovementState movementState) {
        movementStateProperty.set(movementState);
    }
    public int getmaxVie() {
        return maxVie;
    }

}
