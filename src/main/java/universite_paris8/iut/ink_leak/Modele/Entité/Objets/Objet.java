package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

public abstract class Objet extends Entité {
    private Joueur joueur;
    public Objet(String idObjet, Map map, GenerateurObjets generateurObjets, Joueur joueur) {
        super(idObjet, map, generateurObjets);
        this.joueur = joueur;


    }
    @Override
    public void attaque() {}

    public abstract void action();

    @Override
    public void gagner_vie(int nb_vie_gagnee) {

    }

    @Override
    public void déplacement(String déplacementDirection) {

    }

    public void setEmplacement(int x, int y){
        super.setEmplacement(x*32,y*32);
    }

    public Joueur getJoueur() { return this.joueur; }
}
