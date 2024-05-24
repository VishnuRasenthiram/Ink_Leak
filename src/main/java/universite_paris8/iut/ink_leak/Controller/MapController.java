package universite_paris8.iut.ink_leak.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.EnnemieSpawner;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Map;
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
    private static ScheduledExecutorService executorService;
    private Timeline gameLoop;
    private int temps;
    private Map map;
    @FXML
    private TilePane tuileMap;
    public Joueur joueur;
    @FXML
    public BorderPane mainBorderPane;
    @FXML
    private Pane mainPane;
    @FXML
    private Pane interfacePane;
    private int vitesse_joueur;
    @FXML
    private Pane flacons;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.map= new Map();
        VueMap vueMap= new VueMap(tuileMap);
        VueJoueur ink= new VueJoueur(mainPane, interfacePane);

        vueMap.initMap(map);
        gameLoop();
        gameLoop.play();

        this.joueur = new Joueur("LePlayer", 6, 1, 32, 1);
        ink.créeSpriteJoueur(joueur);
        joueur.setEmplacement(30,200);
        joueur.orientationProperty().addListener((obs,old,nouv)->{

            Pane p = (Pane) mainPane.lookup("#"+joueur.getNom_entite());
            p.getChildren().remove(0);
            ImageView imageview= new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(new Image(ink.orientationToFile(nouv).toURI().toString()));
            p.getChildren().add(imageview);

        });

        ink.créeSpriteVie(joueur);

    }



    @FXML
    public void action() {
        joueur.déplacement(mainPane);
    }
    @FXML
    protected void stop() {
        joueur.stop(mainPane);
    }
/*
    private void attaquer() {
        Pane Attaque=new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/test.png").toURI().toString()));
        Attaque.getChildren().add(imageview);
        mainPane.getChildren().add(Attaque);
        if (joueur.getOrientationProperty() == "N") {
            Attaque.setTranslateX(joueur.getPosX());
            Attaque.setTranslateY(joueur.getPosY() - 32);
        }
        else if (joueur.getOrientationProperty() == "S") {
            Attaque.setTranslateX(joueur.getPosX());
            Attaque.setTranslateY(joueur.getPosY() + 32);
        }
        else if (joueur.getOrientationProperty() == "E") {
            Attaque.setTranslateX(joueur.getPosX() + 32);
            Attaque.setTranslateY(joueur.getPosY());
        }
        else {
            Attaque.setTranslateX(joueur.getPosX() - 32);
            Attaque.setTranslateY(joueur.getPosY());
        }
        Timeline attaqueVisible = new Timeline(
                new KeyFrame(Duration.millis(200), ev -> mainPane.getChildren().remove(Attaque))
        );
        attaqueVisible.play();
    }
*/
    private void gameLoop() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final int[] is = {0};

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.01),

                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev -> {
                    if (temps % 5 == 0) {
                        double x = joueur.getPosX();
                        double y = joueur.getPosY();
                        joueur.peutAller(x, y, mainPane);

                        if (temps == 10000) {
                            System.out.println("fini");
                            gameLoop.stop();
                        } else if (temps % 500 == 0) {
                            EnnemieSpawner.spawnEnnemie(mainPane, is[0]);

                        } else if (temps % 2 == 0) {
                            EnnemieSpawner.ActiverMob(mainPane);


                        }

                    }
                    temps++;
                    is[0]++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }



}