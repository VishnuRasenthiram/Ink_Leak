package universite_paris8.iut.ink_leak.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
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
    public Joueur joueur;
    @FXML
    public BorderPane mainBorderPane;
    @FXML
    private Pane mainPane;
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

        joueur = new Joueur("LePlayer", 100, 50, 32, 2);

        System.out.println("x:"+ joueur.getPosX() +"y:"+ joueur.getPosY());


        Pane Joueur=new Pane();
        Joueur.setId(joueur.getName());
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idles/entity_idle_down.png").toURI().toString()));
        Joueur.getChildren().add(imageview);
        joueur.setPosXProperty(Joueur.localToScene(Joueur.getBoundsInLocal()).getMinX());
        Joueur.translateXProperty().bind(joueur.posXProperty());
        Joueur.translateYProperty().bind(joueur.posYProperty());


        mainPane.getChildren().add(Joueur);


    }

    private void creerTuile(int tuile,int x,int y){

        Pane pane= new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        if(tuile==0){
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/floor.png").toURI().toString()));

        } else if (tuile==1) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/corridors/corridor_upward.png").toURI().toString()));
            pane.setId("rouge");
        } else if (tuile==2) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/water.png").toURI().toString()));
        }


        pane.getChildren().add(imageview);


        tuileMap.getChildren().add(pane);



    }
    private static ScheduledExecutorService executorService;
    public  int getCharacterSpeed() {
        return joueur.getCharacterSpeed();
    }
    private int getCharacterSize() {
        return joueur.getSize();
    }

    @FXML
    public void moove() {
        try {
            Pane circle = (Pane) mainPane.lookup("#LePlayer");
            PlayerSpeed = getCharacterSpeed();
            mainPane.setOnKeyPressed(e -> {

                if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> {
                        double x = joueur.getPosX();
                        double y = joueur.getPosY();
                        System.out.println("x:"+ joueur.getPosX() +"y:"+ joueur.getPosY());
                        if (e.getCode() == KeyCode.UP) {
                            if(peutAller(x,y - PlayerSpeed)) {
                                joueur.setPosYProperty(joueur.getPosY() - PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.DOWN) {
                            if(peutAller(x,y + PlayerSpeed)) {
                                joueur.setPosYProperty(joueur.getPosY() + PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.LEFT) {
                            if(peutAller(x - PlayerSpeed,y)) {
                                joueur.setPosXProperty(joueur.getPosX() - PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.RIGHT) {
                            if(peutAller(x + PlayerSpeed,y))
                            {
                                joueur.setPosXProperty(joueur.getPosX() + PlayerSpeed);
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
        Pane circle = (Pane) mainPane.lookup("#LePlayer");
        double radius = getCharacterSize();
        TilePane tuileMap = (TilePane) mainPane.lookup("#tuileMap");

        for (Node tuile : tuileMap.getChildren()) {
            if (tuile.getId()== "rouge") {
                Bounds boundsInParent = tuile.localToParent(tuile.getBoundsInLocal());
                double xb = boundsInParent.getMinX();
                double yb = boundsInParent.getMinY();
                double width = boundsInParent.getWidth();
                double height = boundsInParent.getHeight();

                if (x + radius >= xb && x - radius <= xb + width && y + radius >= yb && y - radius <= yb + height) {
                    // Crée un rectangle transparent avec une bordure rouge
                    Rectangle collisionRect = new Rectangle(xb, yb, width, height);
                    collisionRect.setFill(Color.TRANSPARENT);
                    collisionRect.setStroke(Color.BLUE);
                    collisionRect.setStrokeWidth(2);

                    // Ajoute le rectangle à la scène
                    mainPane.getChildren().add(collisionRect);



                    return false;
                }
            }
        }

        return true;
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
                    if(temps==1){
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



}