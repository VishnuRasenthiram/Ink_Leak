package universite_paris8.iut.ink_leak.Vue.VueEntité.VueMurs;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;

import java.io.File;

public class VueMursCassable extends VueEntite {

    public VueMursCassable(Pane mainPane) {
        super(mainPane);
    }
    @Override
    public void créeSprite(Entité entité) {
        Pane mursCassables = new Pane();
        mursCassables.setId(entité.getNom_entite());

        ImageView imageview = new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/wall_2.png").toURI().toString()));
        mursCassables.getChildren().add(imageview);
        mursCassables.translateXProperty().bind(entité.posXProperty());
        mursCassables.translateYProperty().bind(entité.posYProperty());

        super.getMainPane().getChildren().add(mursCassables);


    }



}
