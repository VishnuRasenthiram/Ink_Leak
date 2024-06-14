package universite_paris8.iut.ink_leak.Vue;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Map;
import java.io.File;

public class VueMap {
    @FXML
    private TilePane tuileMap;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Pane interfacePane;
    public VueMap(TilePane tuileMap, Pane InterfacePane, BorderPane mainBorderPane){

        this.tuileMap = tuileMap;
        this.mainBorderPane = mainBorderPane;
        this.interfacePane = InterfacePane;
    }

    public void initMap(Map map, Joueur entité){
        if (map.getNumMap() == 2){
            ImageView ObscuritéCache = new ImageView();
            ObscuritéCache.setFitHeight(1800);
            ObscuritéCache.setFitWidth(3000);
            ObscuritéCache.setId("cacheNoir");
            ObscuritéCache.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/darkness.png").toURI().toString()));
            ObscuritéCache.translateXProperty().bind(Bindings.add(entité.posXProperty(), -1504));
            ObscuritéCache.translateYProperty().bind(Bindings.add(entité.posYProperty(), -898));
            interfacePane.getChildren().add(ObscuritéCache);
        } else {
            interfacePane.getChildren().removeIf(node -> node.getId() != null && node.getId().equals("cacheNoir"));
        }
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
        if (tuile == 0) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/floor.png").toURI().toString()));
            pane.setId("sol");
        } else if (tuile == 1) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/ice.png").toURI().toString()));
            pane.setId("ice");
        } else if (tuile == 2) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/rocky_ice.png").toURI().toString()));
            pane.setId("rockIce");
        } else if (tuile == 3) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/water.png").toURI().toString()));
            pane.setId("water");
        } else if (tuile == 4) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/wall_1.png").toURI().toString()));
            pane.setId("wall1");
        } else if (tuile == 5) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/wall_2.png").toURI().toString()));
            pane.setId("wall2");
        } else if (tuile == 6) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/wall_exit.png").toURI().toString()));
            pane.setId("exit1");
        } else if (tuile == 7) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/edges/edge_bottom.png").toURI().toString()));
            pane.setId("edgeBottom");
        } else if (tuile == 8) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/edges/edge_left.png").toURI().toString()));
            pane.setId("edgeLeft");
        } else if (tuile == 9) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/edges/edge_right.png").toURI().toString()));
            pane.setId("edgeRight");
        } else if (tuile == 10) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/edges/edge_top.png").toURI().toString()));
            pane.setId("edgeTop");
        } else if (tuile == 11) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/corridors/corridor_sideway.png").toURI().toString()));
            pane.setId("corridorSideway");
        } else if (tuile == 12) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/corridors/corridor_upward.png").toURI().toString()));
            pane.setId("corridorUpward");
        } else if (tuile == 13) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/corners/corner_bottom_l.png").toURI().toString()));
            pane.setId("cornerBottomLeft");
        } else if (tuile == 14) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/corners/corner_bottom_r.png").toURI().toString()));
            pane.setId("cornerBottomRight");
        } else if (tuile == 15) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/corners/corner_top_l.png").toURI().toString()));
            pane.setId("cornerTopLeft");
        } else if (tuile == 16) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/corners/corner_top_r.png").toURI().toString()));
            pane.setId("cornerTopRight");
        } else if (tuile == 17) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/black.png").toURI().toString()));
            pane.setId("black");
        } else if (tuile == 18) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/bottomWall.png").toURI().toString()));
            pane.setId("bottomWall");
        } else if (tuile == 19) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/topWall.png").toURI().toString()));
            pane.setId("topWall");
        } else if (tuile == 20) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/rightWall.png").toURI().toString()));
            pane.setId("rightWall");
        } else if (tuile == 21) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/leftWall.png").toURI().toString()));
            pane.setId("leftWall");
        } else if (tuile == 22) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/walls/wall_exit.png").toURI().toString()));
            pane.setId("exit2");
        }

        pane.getChildren().add(imageview);

        tuileMap.getChildren().add(pane);

    }

    public void supprimerAffichageMap(){
        fonduAuNoir(mainBorderPane);
        tuileMap.getChildren().clear();
        new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/changement_salle.wav", 1.0f, 0);
    }

    public void fonduAuNoir(BorderPane mainBorderPane) {

        Rectangle rectangleNoir = new Rectangle(mainBorderPane.getWidth(), mainBorderPane.getHeight());
        rectangleNoir.setFill(Color.BLACK);

        mainBorderPane.getChildren().add(rectangleNoir);

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
