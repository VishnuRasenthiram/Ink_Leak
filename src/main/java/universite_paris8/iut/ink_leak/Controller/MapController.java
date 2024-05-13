package universite_paris8.iut.ink_leak.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
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

    private Timeline gameLoop;

    private int temps;


    @FXML
    private Circle leCercle;

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
        initAnimation();
        // demarre l'animation
        gameLoop.play();
        this.env= new Map();


        for (int i = 0; i < env.getMap().length; i++) {
            for (int j = 0; j < env.getMap()[i].length; j++) {
                creerTuile(env.getMap(i,j),i,j);
            }

        }
        Circle circle2 = new Circle(3);
        character = new Character("LePlayer", 100, 50, 20, 2);



        System.out.println("x:"+character.getPosX() +"y:"+character.getPosY());



        circle2.setId(character.getName());
        circle2.translateXProperty().bindBidirectional(character.posXProperty());
        circle2.translateYProperty().bindBidirectional(character.posYProperty());
        PlayerID.getChildren().add(circle2);
        Circle circle = (Circle) PlayerID.lookup("#LePlayer");
        System.out.println((circle.localToScene(circle.getBoundsInLocal()).getMinX()));




    }
    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==100){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%5==0){
                        System.out.println("un tour");
                        leCercle.setLayoutX(leCercle.getLayoutX()+5);
                        leCercle.setLayoutY(leCercle.getLayoutY()+5);

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
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
    private static int getCharacterSize() {
        return character.getSize();
    }

    @FXML
    public void moove() {
        try {
            Circle circle = (Circle) PlayerID.lookup("#LePlayer");
            PlayerSpeed = getCharacterSpeed();

            PlayerID.setOnKeyPressed(e -> {

                if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> {
                        double x = character.getPosX();
                        double y = character.getPosY();


                        if (e.getCode() == KeyCode.UP) {
                            if(peutAller(x,y - PlayerSpeed)) {
                                character.setPosYProperty(character.getPosY() - PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.DOWN) {
                            if(peutAller(x,y + PlayerSpeed)) {
                                character.setPosYProperty(character.getPosY() + PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.LEFT) {
                            if(peutAller(x - PlayerSpeed,y)) {
                                character.setPosXProperty(character.getPosX() - PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.RIGHT) {
                            if(peutAller(x + PlayerSpeed,y))
                            {
                                character.setPosXProperty(character.getPosX() + PlayerSpeed);
                            }

                        }
                    });
                }, 0, 5, TimeUnit.MILLISECONDS); // un delay entre les mouvements
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    protected void stop() {
        if (executorService != null) {
            executorService.shutdownNow();
            executorService = null;
        }
    }

    private boolean peutAller(double x, double y) {
        // System.out.println("x : " + x + " y : " + y + "LE ROND");
        Circle circle = (Circle) PlayerID.lookup("#LePlayer");
        double radius = getCharacterSize();

        for (Node tuile : tuileMap.getChildren()) {
            if (tuile.getId() != null) {
                double xb = tuile.localToScene(tuile.getBoundsInLocal()).getMinX();
                double yb = tuile.localToScene(tuile.getBoundsInLocal()).getMinY();
                double width = tuile.localToScene(tuile.getBoundsInLocal()).getWidth();
                double height = tuile.localToScene(tuile.getBoundsInLocal()).getHeight();

                xb -= width/1.6; // va savoir pourquoi 1.6, ça devrait etre 2 mais ça marche mieux avec 1.7
                yb -= width/1.6;

                if (x + radius >= xb  && x - radius <= xb + width  && y + radius >= yb && y - radius <= yb + height) {
                    System.out.println("Collision");
                    return false;
                }
            }
        }

        return true;
    }
}