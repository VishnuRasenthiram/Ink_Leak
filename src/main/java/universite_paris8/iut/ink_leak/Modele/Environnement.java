package universite_paris8.iut.ink_leak.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;

public class Environnement {

    private ObservableList<Entité> liste_entites;
    private Map map;


    public Environnement(Map map) {
        liste_entites = FXCollections.observableArrayList();
        this.map = map;
    }

    public void ajouterEntite(Entité nv_entite) { liste_entites.add(nv_entite); }

    public void activerEntites(KeyEvent e) {
        for (Entité ennemi : liste_entites) { System.out.println("deplacement"); }
    }

    public void genererEnnemi() { System.out.println("ennemi généré woooo~~~"); }

    public final ObservableList<Entité> getListe_entites() { return liste_entites; }

}
