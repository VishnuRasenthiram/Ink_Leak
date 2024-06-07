package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class ObjetPoing extends Objet {

    private int nbFoisRecuperable;

    public ObjetPoing(Map map, GenerateurEnnemis spawner, Joueur j) {
        super("ObjetPoing", 0, map, spawner, j);
        this.nbFoisRecuperable = 1;
    }

    public void action() {
        if(super.getJ().enContact(this) && nbFoisRecuperable>0){
            nbFoisRecuperable--;
            super.getJ().ajoutPouvoir(super.getJ().getPoing());
        }
    }
}
