package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public abstract class Objet extends Entité {
    private Joueur j;
    private static int nbFoisRecuperable=1;
    public Objet(String idPouvoir, int attaque, Map map, GenerateurEnnemis spawner, Joueur j) {
        super(idPouvoir, 1, attaque, 32, 32, 0, 1, map, spawner);
        this.j = j;


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

    public Joueur getJ() { return this.j; }
}
