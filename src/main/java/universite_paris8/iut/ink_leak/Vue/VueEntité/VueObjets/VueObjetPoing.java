package universite_paris8.iut.ink_leak.Vue.VueEntité.VueObjets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;

import java.io.File;

public class VueObjetPoing extends VueObjets{



    public VueObjetPoing(Pane mainPane) {
        super(mainPane);
    }


    public void créeSprite(Objets poing) {
        Pane objetPane =new Pane();
        objetPane.setId(poing.getNom_entite());
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);

        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/objects/buckets/green_bucket.png").toURI().toString()));
        objetPane.getChildren().add(imageview);

        objetPane.translateXProperty().bind(poing.posXProperty());
        objetPane.translateYProperty().bind(poing.posYProperty());
        super.getMainPane().getChildren().add(objetPane);

    }
}
