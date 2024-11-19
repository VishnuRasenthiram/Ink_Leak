package universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable;

import universite_paris8.iut.ink_leak.Modele.Environnement;

public class ObjetPoing extends ObjetRecuperable {

    public ObjetPoing( Environnement environnement) {

        super("ObjetPoing",  environnement,environnement.getJoueur().getPoing());

    }


}
