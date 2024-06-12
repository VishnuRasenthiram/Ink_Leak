package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

public class ObjetLangue extends Objets {



    public ObjetLangue(Map map, GenerateurObjets generateurObjets, Joueur joueur) {
        super("ObjetLangue",  map, generateurObjets, joueur);

    }

    public void action() {
        if(super.getJoueur().enContact(this) && super.getNbFoisRecuperable()>0){
            super.setNbFoisRecuperable(super.getNbFoisRecuperable()-1);
            super.getJoueur().ajoutPouvoir(super.getJoueur().getLangue());
        }
    }
}
