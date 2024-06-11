package universite_paris8.iut.ink_leak.Vue.VueEntité.VueObjets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;

import java.io.File;

public class VueTube extends VueObjets{

    public VueTube(Pane mainPane) {
        super(mainPane);
    }

    public void créeSprite(Objets tube) {
        Pane objetPane = new Pane();
        objetPane.setId(tube.getNom_entite());
        ImageView imageview = new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);

        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Objects/ink_tube.png").toURI().toString()));
        objetPane.getChildren().add(imageview);

        objetPane.translateXProperty().bind(tube.posXProperty());
        objetPane.translateYProperty().bind(tube.posYProperty());
        super.getMainPane().getChildren().add(objetPane);
    }
}
