package universite_paris8.iut.ink_leak.Controller;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import universite_paris8.iut.ink_leak.Modele.Map;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.ink_leak.Player.Character;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledExecutorService;

public class MapController extends PlayerMoove implements Initializable {
    private Map env;
    public static Character character;

    @FXML
    private TilePane tuileMap;
    @FXML
    public BorderPane mainPane;
    @FXML
    public ProgressBar vie;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.env= new Map();


        for (int i = 0; i < env.getMap().length; i++) {
            for (int j = 0; j < env.getMap()[i].length; j++) {
                creerTuile(env.getMap(i,j),i,j);
            }
        }
        character = new Character("LePlayer", 100, 50, 20, 3);
        vie.progressProperty().bind(character.HealthProperty());

        Pane PlayerID = (Pane) mainPane.lookup("#PlayerID");

        Circle circle = new Circle();
        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setRadius(character.getSize());
        circle.setId(character.getName());

        PlayerID.getChildren().add(circle);

    }
    private void creerTuile(int tuile,int x,int y) {
        //System.out.println("ajouter sprite");
        Pane pane;

        if(tuile==1){
            pane= new Pane();
            Rectangle r =new Rectangle(32,32);
            r.setFill(Color.RED);
            pane.setId("rouge");
            pane.getChildren().add(r);
        }
        else if (tuile==2){
            pane= new Pane();
            Rectangle r =new Rectangle(32,32);
            r.setFill(Color.BLUE);
            pane.setId("bleu");
            pane.getChildren().add(r);
        }
        else{
            pane= new Pane();
            Rectangle r =new Rectangle(32,32);
            r.setFill(Color.GRAY);
            pane.getChildren().add(r);
        }

        tuileMap.getChildren().add(pane);
        // ils ont le meme identifiant
    }
    @FXML
    private void Movement(){ move(); }
    @FXML
    private void StopMove(){ stop(); }
}