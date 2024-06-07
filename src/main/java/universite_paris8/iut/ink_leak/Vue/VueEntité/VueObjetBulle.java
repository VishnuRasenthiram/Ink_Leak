package universite_paris8.iut.ink_leak.Vue.VueEntité;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;

import java.io.File;

public class VueObjetBulle {

    private Pane mainPane;
    public VueObjetBulle(Pane mainPane) {
        this.mainPane=mainPane;
    }

    public void créeSprite(Entité entité) {
        Pane pouvoirPane =new Pane();
        pouvoirPane.setId(entité.getNom_entite());
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        if (entité.getNom_entite() == "ObjetBulle") {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Objects/Buckets/blue_bucket.png").toURI().toString()));
            pouvoirPane.getChildren().add(imageview);
        }
        else if (entité.getNom_entite() == "ObjetPoing") {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Objects/Buckets/green_bucket.png").toURI().toString()));
            pouvoirPane.getChildren().add(imageview);
        }


        pouvoirPane.translateXProperty().bind(entité.posXProperty());
        pouvoirPane.translateYProperty().bind(entité.posYProperty());
        mainPane.getChildren().add(pouvoirPane);

    }
}
