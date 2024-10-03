package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.Mur;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.ArrayList;

public abstract class Pouvoirs extends Entité {

    private Joueur joueur;
    private BooleanProperty estEnVieProperty;

    public Pouvoirs(String nom_entite, int attaque_entite, double largeur, double longueur, int vitesse_entite, boolean estEnVie, Environnement environnement) {
        super(nom_entite, 0, attaque_entite, largeur,longueur, vitesse_entite, 0,environnement);
        this.joueur=environnement.getJoueur();
        this.estEnVieProperty = new SimpleBooleanProperty(estEnVie);
    }

    //Active les murs dès qu'un pouvoir est utilisé et enleve les murs qui sont cassé par le pouvoir
    @Override
    public void attaque() {
        ArrayList<Mur> mursEnleve= new ArrayList<>();
        for(Mur mur :super.getGenerateurMurs().getListeMurs()){
            if(mur.enContact(this)){
                mursEnleve=mur.déplacementMur(this.getOrientation());
                this.setEstEnVie(false);
            }
        }
        for(Mur mur : mursEnleve){
            joueur.getGenerateurMurs().getListeMurs().remove(mur);
        }
    }

    public void setPosition(){
        if (joueur.getOrientation() == "N") {
            super.setPosXProperty(joueur.getPosX());
            super.setPosYProperty(joueur.getPosY() - 32);
        }
        else if (joueur.getOrientation() == "S") {
            super.setPosXProperty(joueur.getPosX());
            super.setPosYProperty(joueur.getPosY() + 32);
        }
        else if (joueur.getOrientation() == "E") {
            super.setPosXProperty(joueur.getPosX() + 32);
            super.setPosYProperty(joueur.getPosY());
        }
        else {
            super.setPosXProperty(joueur.getPosX() - 32);
            super.setPosYProperty(joueur.getPosY());
        }
    }

    @Override
    public abstract void déplacement(String déplacementDirection);
    @Override
    public void gagner_vie(int nb_vie_gagnee) {}

    public Joueur getJoueur() {
        return joueur;
    }
    public BooleanProperty getEstEnVieProperty() {
        return estEnVieProperty;
    }
    public void setEstEnVie(boolean estEnVie) {
        this.estEnVieProperty.setValue(estEnVie);
    }
    public boolean getEstEnVie() {
        return estEnVieProperty.getValue();
    }
}
