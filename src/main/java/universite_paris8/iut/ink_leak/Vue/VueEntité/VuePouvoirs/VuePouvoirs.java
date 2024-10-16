package universite_paris8.iut.ink_leak.Vue.VueEntité.VuePouvoirs;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Langue;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;

import java.io.File;

public class VuePouvoirs  {

    private Pane interfacePane;

    public VuePouvoirs(Pane interfacePane){
        this.interfacePane = interfacePane;
    }

    public void créeSpriteListeAttaque(Joueur joueur) {
        HBox hBox = new HBox();
        hBox.setId("listePouvoir");
        hBox.setTranslateX(480);
        hBox.setTranslateY(-32);

        Pane pouvoir= new Pane();
        pouvoir.setId("griffe");
        ImageView imageView = new ImageView();
        imageView.setId("imageView");
        imageView.setFitHeight(32);
        imageView.setFitWidth(32);
        imageView.setImage((new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Powers/claw_icon.png").toURI().toString())));
        pouvoir.getChildren().add(imageView);
        hBox.getChildren().add(pouvoir);

        interfacePane.getChildren().add(hBox);

    }

    public void ajoutSpritePouvoir(Pouvoirs p){
        Pane pouvoir= new Pane();
        pouvoir.setId(p.getNom_entite());
        ImageView imageView = new ImageView();
        imageView.setId("imageView");
        imageView.setFitHeight(32);
        imageView.setFitWidth(32);

        if(p instanceof Bulle){
            imageView.setImage((new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Powers/bubble_icon.png").toURI().toString())));
        }
        else if(p instanceof Poing) {
            imageView.setImage((new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Powers/fist_icon.png").toURI().toString())));
        } else if (p instanceof Langue) {
            imageView.setImage((new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Powers/tongue_icon.png").toURI().toString())));
        }
        pouvoir.getChildren().add(imageView);
        HBox hbox = (HBox)interfacePane.lookup("#listePouvoir");
        hbox.getChildren().add(pouvoir);

    }

}
