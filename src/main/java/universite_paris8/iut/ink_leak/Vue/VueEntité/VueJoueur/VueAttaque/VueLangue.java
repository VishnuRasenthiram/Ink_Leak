package universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Controller.Observable.PouvoirObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Langue;

import java.io.File;

public class VueLangue extends VueAttaque {

    public VueLangue(Pane mainPane, Joueur joueur) {
        super(mainPane, joueur);
    }

    public void afficherAttaqueLangue(Langue langue) {

        String orientation = super.getJoueur().getOrientation();
        Pane attaquePane = new Pane();

        ImageView imageview = new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);

        ImageView imageview2 = new ImageView();
        imageview2.setFitHeight(32);
        imageview2.setFitWidth(32);


        if (orientation.equals("N")) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Tongue/hitbox/up/hitbox_u_1.png").toURI().toString()));
            imageview2.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Tongue/hitbox/up/hitbox_u_2.png").toURI().toString()));
            imageview.setLayoutX(0);  // Positionner l'image à 0 sur l'axe X
            imageview.setLayoutY(0);
            imageview2.setLayoutX(0);  // Positionner l'image à 0 sur l'axe X
            imageview2.setLayoutY(32);
        }
        else if (orientation.equals("S")) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Tongue/hitbox/down/hitbox_d_1.png").toURI().toString()));
            imageview2.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Tongue/hitbox/down/hitbox_d_2.png").toURI().toString()));
            imageview.setLayoutX(0);  // Positionner l'image à 0 sur l'axe X
            imageview.setLayoutY(32);
            imageview2.setLayoutX(0);  // Positionner l'image à 0 sur l'axe X
            imageview2.setLayoutY(0);
        }
        else if (orientation.equals("O")) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Tongue/hitbox/left/hitbox_l_1.png").toURI().toString()));
            imageview2.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Tongue/hitbox/left/hitbox_l_2.png").toURI().toString()));
            imageview.setLayoutX(0);  // Positionner l'image à 0 sur l'axe X
            imageview.setLayoutY(0);
            imageview2.setLayoutX(32);  // Positionner l'image à 0 sur l'axe X
            imageview2.setLayoutY(0);
        }
        else {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Tongue/hitbox/right/hitbox_r_1.png").toURI().toString()));
            imageview2.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Entity/Tongue/hitbox/right/hitbox_r_2.png").toURI().toString()));
            imageview.setLayoutX(32);  // Positionner l'image à 0 sur l'axe X
            imageview.setLayoutY(0);
            imageview2.setLayoutX(0);  // Positionner l'image à 0 sur l'axe X
            imageview2.setLayoutY(0);
        }

        attaquePane.getChildren().add(imageview);
        attaquePane.getChildren().add(imageview2);

        attaquePane.translateXProperty().bind(langue.posXProperty());
        attaquePane.translateYProperty().bind(langue.posYProperty());

        attaquePane.setId("attaque");
        super.getMainPane().getChildren().add(attaquePane);

        langue.getEstEnVieProperty().addListener(new PouvoirObs(this));

    }
}
