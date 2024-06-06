package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public  class Objets extends Entité {

    public Objets(String nom_entite, int vie_entite, int attaque_entite, double hauteur, double longueur,int vitesse_entite, long invincibilite, Map map, GenerateurEnnemis spawner) {
        super(nom_entite, vie_entite, attaque_entite, hauteur,longueur, vitesse_entite, invincibilite, map, spawner);
    }
    @Override
    public void attaque() {

    }

    @Override
    public void déplacement(String déplacementDirection) {

    }

    @Override
    public void gagner_vie(int nb_vie_gagnee) {

    }
}
