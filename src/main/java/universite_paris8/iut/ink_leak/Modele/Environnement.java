package universite_paris8.iut.ink_leak.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;

public class Environnement {

    private GenerateurEnnemis liste_entites;

    public Environnement() { this.liste_entites = new GenerateurEnnemis(); }

    public void ajouterEntite(Entité nv_entite) {
        liste_entites.getListeEntite().add(nv_entite);
    }

    public void activerEntites() { liste_entites.ActiverMob(); }

    public void genererEnnemi() { System.out.println("ennemi généré woooo~~~"); }

    public final ObservableList<Entité> getListe_entites() { return liste_entites.getListeEntite(); }

}
