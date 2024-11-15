package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;

import universite_paris8.iut.ink_leak.Modele.Environnement;

public class Puddle extends Ennemi {

    public Puddle(Environnement environnement){
        super("Puddle"+cpt, 2, 32, environnement, environnement.getJoueur());
    }
}


