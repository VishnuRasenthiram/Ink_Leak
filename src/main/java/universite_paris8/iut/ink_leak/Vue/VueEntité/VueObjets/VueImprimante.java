package universite_paris8.iut.ink_leak.Vue.VueEntité.VueObjets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;

import java.io.File;

public class VueImprimante extends VueObjets {

    public VueImprimante(Pane mainPane) {
        super(mainPane);
    }

    public void créeSprite(Objets imprimante) {
        Pane objetPane = new Pane();
        objetPane.setId(imprimante.getNom_entite());
        ImageView imageview = new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);

        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/objects/printer.png").toURI().toString()));
        objetPane.getChildren().add(imageview);

        objetPane.translateXProperty().bind(imprimante.posXProperty());
        objetPane.translateYProperty().bind(imprimante.posYProperty());
        super.getMainPane().getChildren().add(objetPane);
    }
}
