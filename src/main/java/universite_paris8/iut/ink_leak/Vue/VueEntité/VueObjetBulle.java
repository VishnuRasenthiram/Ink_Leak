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
        Pane bullePane =new Pane();
        bullePane.setId(entité.getNom_entite());
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Objects/Buckets/blue_bucket.png").toURI().toString()));
        bullePane.getChildren().add(imageview);


        bullePane.translateXProperty().bind(entité.posXProperty());
        bullePane.translateYProperty().bind(entité.posYProperty());
        mainPane.getChildren().add(bullePane);

    }
}
