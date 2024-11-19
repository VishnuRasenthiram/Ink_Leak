package universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable;

import universite_paris8.iut.ink_leak.Modele.Environnement;

public class ObjetBulle extends ObjetRecuperable {

    public ObjetBulle(Environnement environnement) {
        super("ObjetBulle", environnement, environnement.getJoueur().getBulle());

    }


}
