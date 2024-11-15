package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;

import universite_paris8.iut.ink_leak.Modele.Environnement;

public class Faker extends Ennemi {

    public Faker(Environnement environnement){
        super("Faker"+cpt, 2, 32, environnement, environnement.getJoueur());
    }
}


