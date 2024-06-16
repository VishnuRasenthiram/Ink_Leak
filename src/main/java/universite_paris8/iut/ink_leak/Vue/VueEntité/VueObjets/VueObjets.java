package universite_paris8.iut.ink_leak.Vue.VueEntité.VueObjets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;

import java.io.File;

public abstract class VueObjets {

    private Pane mainPane;

    public VueObjets(Pane mainPane) {
        this.mainPane = mainPane;
    }

    public void supprimerSpriteObjet(Objets objet){
        Pane objetPane = (Pane) mainPane.lookup("#"+objet.getNom_entite());
        mainPane.getChildren().remove(objetPane);
    }

    public abstract void créeSprite(Objets objet);

    public Pane getMainPane() {
        return mainPane;
    }
}
