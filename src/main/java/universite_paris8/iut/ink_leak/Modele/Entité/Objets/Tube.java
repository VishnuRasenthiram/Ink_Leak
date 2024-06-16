package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Tube extends Objets {

    private static  int cpt=0;

    public Tube(Map map, GenerateurObjets listeObjets, Joueur j) {
        super("Tube"+cpt,  map, listeObjets, j);
        cpt++;
    }

    public void action() {
        if(super.getJoueur().enContact(this) && super.getNbFoisRecuperable()>0){
            super.setNbFoisRecuperable(super.getNbFoisRecuperable()-1);
            super.getJoueur().gagner_vie(1);
        }
    }
}
