package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Slime;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class EnnemieSpawner {
    public static List<Slime> slimes = new ArrayList<>(); // Liste pour stocker tous les slimes


    public static void ActiverMob(Pane mainPane){
      for (Entité mob : slimes) {
          mob.déplacement(mainPane);
      }
    }

    public static void spawnEnnemie(Pane mainPane, int name, Timeline gameLoop){
        String a = String.valueOf(name);

        Slime slime = new Slime(a, 50, 10, 32, 1,gameLoop); // Créer un nouveau slime
        slimes.add(slime); // Ajouter le slime à la liste

        Pane Slime =new Pane();

        Slime.setId(slime.getNom_entite());

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




    }

}
