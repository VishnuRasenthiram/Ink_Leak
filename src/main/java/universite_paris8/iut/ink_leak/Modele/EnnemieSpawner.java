package universite_paris8.iut.ink_leak.Modele;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Slime;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EnnemieSpawner {
    public static Slime slime;
  public static void ActiverMob(Entité Mob, Pane mainPane){
        EnnemieIA.moove(Mob, mainPane);
    }
    public static void spawnEnnemie(Pane mainPane){
        slime = new Slime("Slime1", 50, 10, 32, 1);
        Pane Slime =new Pane();

        Slime.setId(slime.getName());

        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);

        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Ennemies/Slime/Idles/slime_idle_r.png").toURI().toString()));
        Slime.getChildren().add(imageview);

        slime.setPosXProperty(Slime.localToScene(Slime.getBoundsInLocal()).getMinX());
        Slime.translateXProperty().bind(slime.posXProperty());
        Slime.translateYProperty().bind(slime.posYProperty());

        mainPane.getChildren().add(Slime);
        slime.setPosYProperty(slime.getPosY() + 50);
        slime.setPosXProperty(slime.getPosX() + 300);
        ActiverMob(slime, mainPane);
    }
}
