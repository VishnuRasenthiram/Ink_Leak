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
import java.sql.Time;
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
        initAnimation();
        gameLoop.play();

        this.joueur = new Joueur("LePlayer", 100, 50, 32, 2);
        ink.créeSpriteJoueur(joueur);
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
                            if(peutAller(x,y - PlayerSpeed)) {
                                joueur.setPosYProperty(joueur.getPosY() - PlayerSpeed);
                                joueur.setDirection('N');
                            }
                        }
                        if (e.getCode() == KeyCode.S) {
                            if(peutAller(x,y + PlayerSpeed)) {
                                joueur.setPosYProperty(joueur.getPosY() + PlayerSpeed);
                                joueur.setDirection('S');
                            }
                        }
                        if (e.getCode() == KeyCode.Q) {
                            if(peutAller(x - PlayerSpeed,y)) {
                                joueur.setPosXProperty(joueur.getPosX() - PlayerSpeed);
                                joueur.setDirection('O');
                            }
                        }
                        if (e.getCode() == KeyCode.D) {
                            if(peutAller(x + PlayerSpeed,y))
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

    private boolean peutAller(double x, double y) {
        ImageView barre = (ImageView) mainBorderPane.lookup("#vie");
        double radius = getCharacterSize();
        TilePane tuileMap = (TilePane) mainPane.lookup("#tuileMap");

        for (Node tuile : tuileMap.getChildren()) {
            Bounds boundsInParent = tuile.localToParent(tuile.getBoundsInLocal());
            double xb = boundsInParent.getMinX();
            double yb = boundsInParent.getMinY();
            double width = boundsInParent.getWidth();
            double height = boundsInParent.getHeight();

            boolean joueur_sur_case = x + radius >= xb && x - radius <= xb + width && y + radius >= yb && y - radius <= yb + height;

            if (tuile.getId() == "rouge") {
                if (joueur_sur_case) {
                    // Crée un rectangle transparent avec une bordure rouge
                    Rectangle collisionRect = new Rectangle(xb, yb, width, height);
                    collisionRect.setFill(Color.TRANSPARENT);
                    collisionRect.setStroke(Color.RED);
                    collisionRect.setStrokeWidth(1);
                    // Ajoute le rectangle à la scène
                    mainPane.getChildren().add(collisionRect);

                    return false;
                }
            }

            if (tuile.getId() == "bleue") {
                if (joueur_sur_case) {
                    joueur.prendre_degat(1);
                    barre.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Health/health_" + joueur.getHealth().getValue() + ".png").toURI().toString()));
                }
            }
        }

        return true;
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
                        peutAller(x, y);

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }



}