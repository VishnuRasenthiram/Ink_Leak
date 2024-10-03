package universite_paris8.iut.ink_leak.Modele.Entité.Murs;
import universite_paris8.iut.ink_leak.Modele.Environnement;


import java.util.ArrayList;

public class MurCassable extends Mur{

    private static int cpt =0;

    public MurCassable(Environnement environnement) {
        super("MurCassable"+cpt,  1, 1, 32, 32,1,1,environnement);
        cpt++;
    }
    //Verifie si c'est bien le poing qui l'attaque et si c'est le cas, casse le mur
    public ArrayList<Mur> déplacementMur(String déplacementDirection) {
        if (getEnvironnement().getPoing().enContact(this)) {
            this.prendre_degat(1);
            super.getGenerateurMurs().getMap().setMap((int) this.getPosX() / 32, (int) this.getPosY() / 32, 0);
        }
        return super.verifMurCassé();
    }


}
