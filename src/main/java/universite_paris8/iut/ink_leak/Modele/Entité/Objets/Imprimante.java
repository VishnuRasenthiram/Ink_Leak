package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Imprimante extends Objet {

    private int nbFoisRecuperable;

    public Imprimante(Map map, GenerateurEnnemis spawner, Joueur j) {
        super("Imprimante", 0, map, spawner, j);
        this.nbFoisRecuperable = 1;
    }

    public void action() {
        if (super.getJ().getAttaqueDeBase().enContact(this) && nbFoisRecuperable > 0) {
            nbFoisRecuperable--;
            super.getJ().gagner_vie(2);
        }
    }
}
