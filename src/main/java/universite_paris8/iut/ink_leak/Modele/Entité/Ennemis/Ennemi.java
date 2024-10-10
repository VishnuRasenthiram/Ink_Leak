package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public abstract class Ennemi extends Entité {

    public static int cpt=0;
    public final Joueur joueur;

    public Ennemi(String nomEntite, int vie, int taille, Environnement env, Joueur joueur){

        super(nomEntite+cpt, vie, 1, taille, taille,2,200,env);
        this.joueur = joueur;
        cpt++;
    }


    @Override
    public void attaque() {
        if(this.enContact(joueur)){
            joueur.prendre_degat(1);
        }
    }



    @Override
    public void gagner_vie(int nb_vie_gagnee) {

    }

}
