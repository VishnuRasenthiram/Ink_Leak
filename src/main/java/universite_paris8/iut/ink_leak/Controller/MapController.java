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
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
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
    private Timeline attaqueVisible;
    private int temps;
    @FXML
    private Circle leCercle;
    private Map map;
    @FXML
    private Label welcomeText;
    public Entité entité;
    @FXML
    private TilePane tuileMap;
    public Joueur joueur;
    @FXML
    public BorderPane mainBorderPane;
    @FXML
    private Pane mainPane;
    private int PlayerSpeed;
    @FXML
    private Pane flacons;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.map= new Map();
        VueMap vueMap= new VueMap(tuileMap);
        VueJoueur ink= new VueJoueur(mainPane);

        vueMap.initMap(map);
        gameLoop();
        gameLoop.play();

        this.joueur = new Joueur("LePlayer", 100, 50, 32, 1);
        ink.créeSpriteJoueur(joueur);
        System.out.println("x:"+ joueur.getPosX() +"y:"+ joueur.getPosY());


    }

    private static ScheduledExecutorService executorService;
    public  int getCharacterSpeed() {
        return joueur.getCharacterSpeed();
    }
    private int getCharacterSize() {
        return joueur.getSize();
    }

    @FXML
    public void action() {
        try {
            Pane circle = (Pane) mainPane.lookup("#LePlayer");
            PlayerSpeed = getCharacterSpeed();
            mainPane.setOnKeyPressed(e -> {
                System.out.println(e);
                if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> {
                        double x = joueur.getPosX();
                        double y = joueur.getPosY();
                        System.out.println("x:"+ joueur.getPosX() +"y:"+ joueur.getPosY());
                        if(e.getCode() == KeyCode.SHIFT) {
                            PlayerSpeed = 2;
                        }
                        if (e.getCode() == KeyCode.Z) {
                            if(joueur.peutAller(x,y - PlayerSpeed, mainPane)) {
                                joueur.setPosYProperty(joueur.getPosY() - PlayerSpeed);
                                joueur.setDirection('N');
                            }
                        }
                        if (e.getCode() == KeyCode.S) {
                            if(joueur.peutAller(x,y + PlayerSpeed, mainPane)) {
                                joueur.setPosYProperty(joueur.getPosY() + PlayerSpeed);
                                joueur.setDirection('S');
                            }
                        }
                        if (e.getCode() == KeyCode.Q) {
                            if(joueur.peutAller(x - PlayerSpeed,y, mainPane)) {
                                joueur.setPosXProperty(joueur.getPosX() - PlayerSpeed);
                                joueur.setDirection('O');
                            }
                        }
                        if (e.getCode() == KeyCode.D) {
                            if(joueur.peutAller(x + PlayerSpeed,y, mainPane))
                            {
                                joueur.setPosXProperty(joueur.getPosX() + PlayerSpeed);
                                joueur.setDirection('E');
                            }
                        }
                        if (e.getCode() == KeyCode.J) {
                            attaquer();
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

    private void attaquer() {
        attaqueVisible = new Timeline();
        long temps = System.currentTimeMillis();
        KeyFrame kf = new KeyFrame(Duration.seconds(0.0001), (ev -> {
            Pane Attaque=new Pane();
            ImageView imageview= new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/test.png").toURI().toString()));
            Attaque.getChildren().add(imageview);
            mainPane.getChildren().add(Attaque);
            if (joueur.getDirection() == 'N') {
                Attaque.setTranslateX(joueur.getPosX());
                Attaque.setTranslateY(joueur.getPosY() - 32);
            }
            else if (joueur.getDirection() == 'S') {
                Attaque.setTranslateX(joueur.getPosX());
                Attaque.setTranslateY(joueur.getPosY() + 32);
            }
            else if (joueur.getDirection() == 'E') {
                Attaque.setTranslateX(joueur.getPosX() + 32);
                Attaque.setTranslateY(joueur.getPosY());
            }
            else {
                Attaque.setTranslateX(joueur.getPosX() - 32);
                Attaque.setTranslateY(joueur.getPosY());
            }
            if (temps >= System.currentTimeMillis() + 1000) { mainPane.getChildren().remove(Attaque); }
        }));
        attaqueVisible.getKeyFrames().add(kf);
        attaqueVisible.play();

    }

    private void gameLoop() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.0017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if (temps%5==0){
                        double x = joueur.getPosX();
                        double y = joueur.getPosY();
                        joueur.peutAller(x, y, mainPane);

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }



}