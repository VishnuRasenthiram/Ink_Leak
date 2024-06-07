package universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Controller.BulleObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;

import java.io.File;

public class VueBulle extends VueAttaque{
    private Joueur joueur;
    public VueBulle(Pane mainPane, Joueur joueur) {
        super(mainPane, joueur);
    }
    public void afficheAttaqueBulle( Bulle bulle) {

        Pane attaquePane=new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Bubble/hitbox/hitbox_1.png").toURI().toString()));

        attaquePane.getChildren().add(imageview);

        attaquePane.translateXProperty().bind(bulle.posXProperty());
        attaquePane.translateYProperty().bind(bulle.posYProperty());

        attaquePane.setId("attaque");
        super.getMainPane().getChildren().add(attaquePane);


        bulle.getEstENVIEProperty().addListener(new BulleObs(this));


    }
}
