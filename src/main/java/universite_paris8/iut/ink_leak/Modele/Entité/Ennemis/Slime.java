package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;

import universite_paris8.iut.ink_leak.Modele.Environnement;

public class Slime extends Ennemi {

    public Slime(Environnement environnement){
        super("Slime"+cpt, 2, 32, environnement, environnement.getJoueur());
    }
}


