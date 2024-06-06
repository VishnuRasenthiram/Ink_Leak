package universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;

import java.io.File;

public class VueEnnemis extends VueEntite {


    public VueEnnemis(Pane mainPane) {
        super(mainPane);
    }

    @Override
    public void créeSprite(Entité entité) {
        Pane ennemis =new Pane();
        ennemis.setId(entité.getNom_entite());
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Ennemies/Slime/Idles/slime_idle_r.png").toURI().toString()));
        ennemis.getChildren().add(imageview);

        
        ennemis.translateXProperty().bind(entité.posXProperty());
        ennemis.translateYProperty().bind(entité.posYProperty());
        super.getMainPane().getChildren().add(ennemis);

    }
}
