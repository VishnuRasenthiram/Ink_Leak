package universite_paris8.iut.ink_leak.Controller;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import universite_paris8.iut.ink_leak.Modele.Map;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.ink_leak.Player.Character;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class MapController implements Initializable {
    private Map env;
    @FXML
    private Label welcomeText;


    @FXML
    private TilePane tuileMap;
    public static Character character;
    @FXML
    public BorderPane toutenhaut;
    @FXML
    private Pane PlayerID;
    private int PlayerSpeed;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavsaFX Application!");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.env= new Map();


        for (int i = 0; i < env.getMap().length; i++) {
            for (int j = 0; j < env.getMap()[i].length; j++) {
                creerTuile(env.getMap(i,j),i,j);
            }
        }
        character = new Character("LePlayer", 100, 50, 30, 7);

        Pane PlayerID = (Pane) toutenhaut.lookup("#PlayerID");

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
            pane.getChildren().add(r);
        }
        else if (tuile==2){
            pane= new Pane();
            Rectangle r =new Rectangle(32,32);
            r.setFill(Color.BLUE);
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
    private static ScheduledExecutorService executorService;
    public static int getCharacterSpeed() {
        return character.getCharacterSpeed();
    }
    @FXML
    protected void moove() {

        Circle circle = (Circle) PlayerID.lookup("#LePlayer");
        PlayerSpeed = getCharacterSpeed();

        PlayerID.setOnKeyPressed(e -> {
            if (executorService != null) return;
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
            if (e.getCode() == KeyCode.UP) {
                circle.setTranslateY(circle.getTranslateY() - PlayerSpeed);
            } else if (e.getCode() == KeyCode.DOWN) {
                circle.setTranslateY(circle.getTranslateY() + PlayerSpeed);
            } else if (e.getCode() == KeyCode.LEFT) {
                circle.setTranslateX(circle.getTranslateX() - PlayerSpeed);
            } else if (e.getCode() == KeyCode.RIGHT) {
                circle.setTranslateX(circle.getTranslateX() + PlayerSpeed);

            }
                }
            }, 0, 5, TimeUnit.MILLISECONDS);
        });
    }
    @FXML
    protected void stop() {
        if (executorService != null) {
            executorService.shutdownNow();
            executorService = null;
        }
    }

    private boolean peutAller(double x, double y) {

        Circle circle = (Circle) PlayerID.lookup("#LePlayer");
        double xa = circle.localToScene(circle.getBoundsInLocal()).getMinX();
        double ya = circle.localToScene(circle.getBoundsInLocal()).getMinY();
        System.out.println("Coordonnées absolues du cercle : (" + xa + ", " + ya + ")");



        return true;
    }
}