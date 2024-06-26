package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

public abstract class Objets extends Entité {

    private Joueur joueur;
    private IntegerProperty nbFoisRecuperable;

    public Objets(String idPouvoir, Map map, GenerateurObjets generateurObjets, Joueur joueur) {
        super(idPouvoir, map, generateurObjets);
        this.joueur = joueur;
        nbFoisRecuperable=new SimpleIntegerProperty(1);


    }
    //Fait la vérification si le joueur est en contact et si oui ajouté le pouvoir dans la liste ou donne des pv
    public abstract void action();

    @Override
    public void attaque() {}
    @Override
    public void gagner_vie(int nb_vie_gagnee) {}
    @Override
    public void déplacement(String déplacementDirection) {}

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
