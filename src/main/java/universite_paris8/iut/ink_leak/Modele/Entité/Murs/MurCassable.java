package universite_paris8.iut.ink_leak.Modele.Entité.Murs;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurMurs;

import java.util.ArrayList;

public class MurCassable extends Mur{

    private static int cpt =0;

    public MurCassable(GenerateurMurs generateurMurs) {
        super("MurCassable"+cpt,  1, 1, 32, 32,1,1,generateurMurs.getMap(),null,null,generateurMurs);
        cpt++;
    }

    public ArrayList<Mur> déplacementMur(String déplacementDirection, Joueur joueur) {
        if (joueur.getPoing().enContact(this)) {
            this.prendre_degat(1);
            super.getGenerateurMurs().getMap().setMap((int) this.getPosX() / 32, (int) this.getPosY() / 32, 0);
        }
        return super.verifMurCassé();
    }

}
