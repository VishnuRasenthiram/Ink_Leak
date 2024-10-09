package universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

public class ObjetPoing extends ObjetRecuperable {

    public ObjetPoing( Environnement environnement) {

        super("ObjetPoing",  environnement,environnement.getPoing());

    }


}
