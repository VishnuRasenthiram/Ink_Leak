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
            ImageView Torche = new ImageView();
            Torche.setFitHeight(1800);
            Torche.setFitWidth(3000);
            Torche.setId("torche");
            Torche.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/noir.png").toURI().toString()));
            Torche.translateXProperty().bind(Bindings.add(entité.posXProperty(), -1504));
            Torche.translateYProperty().bind(Bindings.add(entité.posYProperty(), -898));
            interfacePane.getChildren().add(Torche);
        } else {
            interfacePane.getChildren().removeIf(node -> node.getId() != null && node.getId().equals("torche"));
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
        }else if (tuile==6) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/abc.png").toURI().toString()));
            pane.setId("torche");
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
