package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Tube extends Objets {

    public Tube(Map map, GenerateurObjets listeObjets, Joueur j) {
        super("Tube",  map, listeObjets, j);

    }

    public void action() {
        if(super.getJoueur().enContact(this) && super.getNbFoisRecuperable()>0){
            super.setNbFoisRecuperable(super.getNbFoisRecuperable()-1);
            super.getJoueur().gagner_vie(1);
        }
    }
}
