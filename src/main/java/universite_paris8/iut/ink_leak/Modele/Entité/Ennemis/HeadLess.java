package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;

import universite_paris8.iut.ink_leak.Modele.Environnement;

public class HeadLess extends Ennemi {

    public HeadLess(Environnement environnement){
        super("Headless"+cpt, 2, environnement, environnement.getJoueur());
    }
}


