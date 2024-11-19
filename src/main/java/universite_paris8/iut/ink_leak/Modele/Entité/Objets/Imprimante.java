package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Environnement;

public class Imprimante extends Objets {

    private static  int cpt=0;

    public Imprimante(Environnement environnement) {
        super("Imprimante"+cpt,environnement);
        cpt++;
    }

    public void action() {
        if (super.getJoueur().getAttaqueDeBase().enContact(this) && super.getNbFoisRecuperable() > 0) {
            super.setNbFoisRecuperable(super.getNbFoisRecuperable() -1);
            super.getJoueur().gagner_vie(2);
        }
    }
}
