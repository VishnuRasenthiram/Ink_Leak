package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public abstract class Pouvoirs extends Entité {
    private Joueur joueur;
    public Pouvoirs(String nom_entite, int attaque_entite, double largeur,double longueur, int vitesse_entite, Map map, GenerateurEnnemis spawner, Joueur joueur) {
        super(nom_entite, 0, attaque_entite, largeur,longueur, vitesse_entite, 0, map, spawner);
        this.joueur = joueur;
    }

    @Override
    public void attaque() {

    }

    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public abstract void déplacement(String déplacementDirection);

    @Override
    public void gagner_vie(int nb_vie_gagnee) {

    }
    public void setPosition(){
        if (joueur.getOrientationProperty() == "N") {
            super.setPosXProperty(joueur.getPosX());
            super.setPosYProperty(joueur.getPosY() - 32);
        }
        else if (joueur.getOrientationProperty() == "S") {
            super.setPosXProperty(joueur.getPosX());
            super.setPosYProperty(joueur.getPosY() + 32);
        }
        else if (joueur.getOrientationProperty() == "E") {
            super.setPosXProperty(joueur.getPosX() + 32);
            super.setPosYProperty(joueur.getPosY());
        }
        else {
            super.setPosXProperty(joueur.getPosX() - 32);
            super.setPosYProperty(joueur.getPosY());
        }
    }
}
