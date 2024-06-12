package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

public abstract class Objets extends Entité {
    private Joueur joueur;
    private IntegerProperty nbFoisRecuperable;
    public Objets(String idPouvoir, Map map, GenerateurObjets generateurObjets, Joueur joueur) {
        super(idPouvoir, map, generateurObjets);
        this.joueur = joueur;
        nbFoisRecuperable=new SimpleIntegerProperty(1);


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

    public int getNbFoisRecuperable() {
        return nbFoisRecuperable.getValue();
    }

    public IntegerProperty nbFoisRecuperableProperty() {
        return nbFoisRecuperable;
    }

    public void setNbFoisRecuperable(int nbFoisRecuperable) {
        this.nbFoisRecuperable.setValue(nbFoisRecuperable);
    }
}
