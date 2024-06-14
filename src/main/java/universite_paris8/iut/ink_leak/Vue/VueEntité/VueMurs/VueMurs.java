package universite_paris8.iut.ink_leak.Vue.VueEntité.VueMurs;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.ink_leak.Controller.Observable.VieEnnemisObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;

import java.io.File;

public class VueMurs extends VueEntite {



    public VueMurs(Pane mainPane) {
        super(mainPane);


    }

    @Override
    public void créeSprite(Entité entité) {
        Pane ennemis = new Pane();
        ennemis.setId(entité.getNom_entite());

        ImageView imageview = new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/wall_2.png").toURI().toString()));
        ennemis.getChildren().add(imageview);
        ennemis.translateXProperty().bind(entité.posXProperty());
        ennemis.translateYProperty().bind(entité.posYProperty());

        // Rectangle pour représenter les points de vie (PV)

        super.getMainPane().getChildren().add(ennemis);


    }



}
