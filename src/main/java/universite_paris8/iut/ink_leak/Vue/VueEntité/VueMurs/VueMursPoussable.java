package universite_paris8.iut.ink_leak.Vue.VueEntité.VueMurs;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;

import java.io.File;

public class VueMursPoussable extends VueEntite {

    public VueMursPoussable(Pane mainPane) {
        super(mainPane);
    }

    @Override
    public void créeSprite(Entité entité) {
        Pane mursPoussables = new Pane();
        mursPoussables.setId(entité.getNom_entite());
        ImageView imageview = new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/wall_1.png").toURI().toString()));
        mursPoussables.getChildren().add(imageview);
        mursPoussables.translateXProperty().bind(entité.posXProperty());
        mursPoussables.translateYProperty().bind(entité.posYProperty());

        super.getMainPane().getChildren().add(mursPoussables);


    }



}
