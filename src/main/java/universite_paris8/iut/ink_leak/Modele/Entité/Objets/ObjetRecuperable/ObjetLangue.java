package universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable;

import universite_paris8.iut.ink_leak.Modele.Environnement;

public class ObjetLangue extends ObjetRecuperable {

    public ObjetLangue(Environnement environnement) {
        super("ObjetLangue", environnement,environnement.getJoueur().getLangue());

    }


}
