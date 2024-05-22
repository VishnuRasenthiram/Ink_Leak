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
import universite_paris8.iut.ink_leak.Vue.VueJoueur;
import universite_paris8.iut.ink_leak.Vue.VueMap;


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
    private Map map;
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

        this.map= new Map();
        VueMap vueMap= new VueMap(tuileMap);
        VueJoueur ink= new VueJoueur(mainPane);

        vueMap.initMap(map);
        initAnimation();
        gameLoop.play();

        this.joueur = new Joueur("LePlayer", 100, 50, 32, 2);
        ink.créeSpriteJoueur(joueur);
        System.out.println("x:"+ joueur.getPosX() +"y:"+ joueur.getPosY());




    }


    private static ScheduledExecutorService executorService;
    public  int getjoueurSpeed() {
        return joueur.getCharacterSpeed();
    }
    private int getjoueurSize() {
        return joueur.getSize();
    }

    @FXML
    public void moove() {
        try {
            Pane circle = (Pane) mainPane.lookup("#LePlayer");
            PlayerSpeed = getjoueurSpeed();
            mainPane.setOnKeyPressed(e -> {

                if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> {
                        double x = joueur.getPosX();
                        double y = joueur.getPosY();
                        if (e.getCode() == KeyCode.UP) {
                            //System.out.println("x:"+ joueur.getPosX() +"y:"+ joueur.getPosY());

                            if(joueur.peutAller(x,y - PlayerSpeed, mainPane)) {

                                joueur.setPosYProperty(joueur.getPosY() - PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.DOWN) {
                            if(joueur.peutAller(x,y + PlayerSpeed, mainPane)) {
                                joueur.setPosYProperty(joueur.getPosY() + PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.LEFT) {
                            if(joueur.peutAller(x - PlayerSpeed,y, mainPane)) {
                                joueur.setPosXProperty(joueur.getPosX() - PlayerSpeed);
                            }
                        }
                        if (e.getCode() == KeyCode.RIGHT) {
                            if(joueur.peutAller(x + PlayerSpeed,y, mainPane))
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

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.006),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==100){
                        System.out.println("fini");
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