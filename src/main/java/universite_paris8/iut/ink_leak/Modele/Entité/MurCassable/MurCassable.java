package universite_paris8.iut.ink_leak.Modele.Entité.MurCassable;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;
import universite_paris8.iut.ink_leak.Modele.GenerateurMurs;
import universite_paris8.iut.ink_leak.Modele.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

public class MurCassable extends Entité {
    private Joueur joueur;
    private static int cpt =0;
    public MurCassable(GenerateurMurs generateurMurs, Joueur joueur) {
        super("Mur"+cpt,  1, 1, 32, 32,1,1,null,null,null,generateurMurs);
        this.joueur = joueur;
        cpt++;
    }

    @Override
    public void attaque() {

    }

    @Override

    public void déplacement(String déplacementDirection) {
            if(joueur.getPoing().enContact(this)){
                this.prendre_degat(1);
                super.getGenerateurMurs().getMap().setMap((int)this.getPosX()/32,(int)this.getPosY()/32,0);
            }


    }

    @Override
    public void gagner_vie(int nb_vie_gagnee) {

    }
}
