package universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.AttaqueDeBase;

import java.io.File;

public class VueAttaqueDeBase extends VueAttaque {


    public VueAttaqueDeBase(Pane mainPane) {
        super(mainPane);
    }

    public void afficheAttaqueDeBase(AttaqueDeBase attaqueDeBase){

        Pane attaquePane=new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/hitbox/hitbox_3.png").toURI().toString()));
        attaquePane.getChildren().add(imageview);
        attaquePane.setId("attaque");

        attaquePane.translateXProperty().bind(attaqueDeBase.posXProperty());
        attaquePane.translateYProperty().bind(attaqueDeBase.posYProperty());

        super.getMainPane().getChildren().add(attaquePane);
        Timeline attaqueVisible = new Timeline(
                new KeyFrame(Duration.millis(200), ev -> super.getMainPane().getChildren().remove(attaquePane))
        );
        attaqueVisible.play();

    }
}
