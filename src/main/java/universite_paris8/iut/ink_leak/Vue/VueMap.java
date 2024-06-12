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
        if(tuile==0){
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/background/floors/floor.png").toURI().toString()));
            pane.setId("sol");
        } else if (tuile==1) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/background/corridors/corridor_upward.png").toURI().toString()));
            pane.setId("rouge");
        } else if (tuile==2) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/background/floors/water.png").toURI().toString()));
            pane.setId("bleue");
        } else if (tuile==3) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/background/walls/wall_exit.png").toURI().toString()));
            pane.setId("porte");
        }else if (tuile==4) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/background/walls/wall_exit.png").toURI().toString()));
            pane.setId("porte");
        }else if (tuile==6) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/abc.png").toURI().toString()));
            pane.setId("torche");
        }else if (tuile==10) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/background/floors/ice.png").toURI().toString()));
            pane.setId("sol_glace");
        }else if (tuile==9) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/background/floors/rocky_ice.png").toURI().toString()));
            pane.setId("bloc_glace");
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
