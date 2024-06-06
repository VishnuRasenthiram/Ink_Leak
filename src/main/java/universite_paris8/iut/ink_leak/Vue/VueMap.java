package universite_paris8.iut.ink_leak.Vue;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.awt.*;
import java.io.File;

public class VueMap {
    @FXML
    private TilePane tuileMap;
    @FXML
    private BorderPane mainBorderPane;
    public VueMap(TilePane tuileMap, BorderPane mainBorderPane){
        this.tuileMap = tuileMap;
        this.mainBorderPane = mainBorderPane;
    }

    public void initMap(Map map){
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap()[i].length; j++) {
                creerTuile(map.getMap(j,i));
            }
        }
    }


    private void creerTuile(int tuile){

        Pane pane= new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        if(tuile==0){
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/floor.png").toURI().toString()));
            pane.setId("sol");
        } else if (tuile==1) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/corridors/corridor_upward.png").toURI().toString()));
            pane.setId("rouge");
        } else if (tuile==2) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/water.png").toURI().toString()));
            pane.setId("bleue");
        } else if (tuile==3) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/wall_exit.png").toURI().toString()));
            pane.setId("porte");
        }else if (tuile==4) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/wall_exit.png").toURI().toString()));
            pane.setId("porte");
        }

        pane.getChildren().add(imageview);

        tuileMap.getChildren().add(pane);

    }

    public void supprimerAffichageMap(){
        fonduAuNoir(mainBorderPane);
        tuileMap.getChildren().clear();
        new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/changement_salle.wav", 1.0f, 0);
    }

    public void fonduAuNoir(BorderPane mainPane) {

        ImageView rectangleNoir = new ImageView();
        rectangleNoir.setFitWidth(1080);
        rectangleNoir.setFitHeight(720);
        rectangleNoir.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/black_screen.png").toURI().toString()));
        mainPane.getChildren().add(rectangleNoir);

        Timeline fondu = new Timeline(
                new KeyFrame(Duration.seconds(0.0), new KeyValue(rectangleNoir.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.1), new KeyValue(rectangleNoir.opacityProperty(), 0.9)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(rectangleNoir.opacityProperty(), 0.8)),
                new KeyFrame(Duration.seconds(0.3), new KeyValue(rectangleNoir.opacityProperty(), 0.7)),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(rectangleNoir.opacityProperty(), 0.6)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rectangleNoir.opacityProperty(), 0.5)),
                new KeyFrame(Duration.seconds(0.6), new KeyValue(rectangleNoir.opacityProperty(), 0.4)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(rectangleNoir.opacityProperty(), 0.3)),
                new KeyFrame(Duration.seconds(0.8), new KeyValue(rectangleNoir.opacityProperty(), 0.2)),
                new KeyFrame(Duration.seconds(0.9), new KeyValue(rectangleNoir.opacityProperty(), 0.1)),
                new KeyFrame(Duration.seconds(1.0), new KeyValue(rectangleNoir.opacityProperty(), 0.0))
        );
        fondu.setCycleCount(1);
        fondu.play();

    }

}
