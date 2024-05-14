package universite_paris8.iut.ink_leak.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Map;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.ink_leak.Player.Character;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import universite_paris8.iut.ink_leak.Player.Entité;
public class MapController implements Initializable {
    private Timeline gameLoop;
    private int temps;
    @FXML
    private Circle leCercle;
    private Map env;

    @FXML
    private TilePane tuileMap;
    public  Character character;
    @FXML
    public BorderPane toutenhaut;
    @FXML
    private Pane PlayerPane;
    private int PlayerSpeed;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.env= new Map();
        initAnimation();
        gameLoop.play();

        for (int i = 0; i < env.getMap().length; i++) {
            for (int j = 0; j < env.getMap()[i].length; j++) {
                creerTuile(env.getMap(i,j),i,j);
            }
        }
        Circle circle = new Circle();
        character = new Character("LePlayer", 100, 50, 20, 2);

        System.out.println("x:"+character.getPosX() +"y:"+character.getPosY());
        circle.setCenterX(0);
        circle.setCenterY(0);
        //circle.setTranslateX(300);
       // circle.setTranslateY(300);
        circle.setRadius(character.getSize());
        circle.setId(character.getName());

        character.setPosXProperty(circle.localToScene(circle.getBoundsInLocal()).getMinX());
        circle.translateXProperty().bind(character.posXProperty());
        circle.translateYProperty().bind(character.posYProperty());
        character.setPosYProperty(character.getPosY() + 100);
        character.setPosXProperty(character.getPosX() + 300);
        PlayerPane.getChildren().add(circle);


    }

    private void creerTuile(int tuile,int x,int y) {

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



    }
    private static ScheduledExecutorService executorService;
    public  int getCharacterSpeed() {
        return character.getCharacterSpeed();
    }
    private int getCharacterSize() {
        return character.getSize();
    }

    @FXML
    public void moove() {
        try {
            PlayerSpeed = getCharacterSpeed();

            PlayerPane.setOnKeyPressed(e -> {
                if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> {
                        double x = character.getPosX();
                        double y = character.getPosY();

                        System.out.println("x:"+character.getPosX() +"y:"+character.getPosY());
                        if (e.getCode() == KeyCode.UP) {
                            if(character.peutAller(x,y - PlayerSpeed, PlayerPane)) {
                                character.setPosYProperty(character.getPosY() - PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.DOWN) {
                            if(character.peutAller(x,y + PlayerSpeed, PlayerPane)) {
                                character.setPosYProperty(character.getPosY() + PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.LEFT) {
                            if(character.peutAller(x - PlayerSpeed,y, PlayerPane)) {
                                character.setPosXProperty(character.getPosX() - PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.RIGHT) {
                            if(character.peutAller(x + PlayerSpeed,y, PlayerPane))
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
                    if(temps==1000){
                 //       System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%5==0){
                 //       System.out.println("un tour");
                        leCercle.setLayoutX(leCercle.getLayoutX()+5);
                        leCercle.setLayoutY(leCercle.getLayoutY()+5);

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}