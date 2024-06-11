package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Imprimante extends Objet {

    private int nbFoisRecuperable;

    public Imprimante(Map map, GenerateurObjets generateurObjets, Joueur joueur) {
        super("Imprimante",  map, generateurObjets, joueur);
        this.nbFoisRecuperable = 1;
    }

    public void action() {
        if (super.getJoueur().getAttaqueDeBase().enContact(this) && nbFoisRecuperable > 0) {
            nbFoisRecuperable--;
            super.getJoueur().gagner_vie(2);
        }
    }
}
