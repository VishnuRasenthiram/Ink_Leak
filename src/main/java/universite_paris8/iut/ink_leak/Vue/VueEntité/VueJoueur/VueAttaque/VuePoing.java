package universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Controller.Observable.PouvoirObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;

import java.io.File;

public class VuePoing extends VueAttaque {

    public VuePoing(Pane mainPane, Joueur joueur) {
        super(mainPane, joueur);
    }

    public void afficherAttaquePoing(Poing poing) {

        String orientation = super.getJoueur().getOrientation();
        Pane attaquePane = new Pane();
        ImageView imageview = new ImageView();
        imageview.setFitHeight(64);
        imageview.setFitWidth(64);

        if (orientation.equals("N")) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Fist/hitbox/hitbox_u.png").toURI().toString()));
        }
        else if (orientation.equals("S")) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Fist/hitbox/hitbox_d.png").toURI().toString()));
        }
        else if (orientation.equals("O")) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Fist/hitbox/hitbox_l.png").toURI().toString()));
        }
        else imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Fist/hitbox/hitbox_r.png").toURI().toString()));

        attaquePane.getChildren().add(imageview);

        attaquePane.translateXProperty().bind(poing.posXProperty().subtract(12));
        attaquePane.translateYProperty().bind(poing.posYProperty().subtract(14));

        attaquePane.setId("attaque");

        super.getMainPane().getChildren().add(attaquePane);

        poing.getEstEnVieProperty().addListener(new PouvoirObs(this));

    }
}
