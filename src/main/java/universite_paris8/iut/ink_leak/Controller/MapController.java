package universite_paris8.iut.ink_leak.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Slime;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur;
import universite_paris8.iut.ink_leak.Vue.VueJoueur;
import universite_paris8.iut.ink_leak.Vue.VueMap;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledExecutorService;

public class MapController implements Initializable {

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
    private GenerateurEnnemis spawner;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.map= new Map();
        spawner= new GenerateurEnnemis();
        VueMap vueMap= new VueMap(tuileMap);
        VueJoueur ink= new VueJoueur(mainPane, interfacePane);

        vueMap.initMap(map);
        gameLoop();
        gameLoop.play();

        this.joueur = new Joueur("LePlayer", 6, 1, 32, 1,spawner);
        ink.créeSprite(joueur);
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
        ListChangeListener<Entité> ecouteur=new ListeEnnemieObs(mainPane);
        spawner.getListeEntite().addListener(ecouteur);
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

    private void gameLoop() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);



        KeyFrame kf = new KeyFrame(
                Duration.millis(60),
                (ev -> {
                    if (temps % 5 == 0) {
                        double x = joueur.getPosX();
                        double y = joueur.getPosY();
                        joueur.peutAller(x, y, mainPane);

                        if (temps == 10000) {
                            System.out.println("fini");
                            gameLoop.stop();
                        } else if (temps % 500 == 0) {
                            spawner.genererEnnemis(gameLoop,spawner);

                        } else if (temps % 2 == 0) {
                            spawner.ActiverMob(mainPane);


                        }

                    }
                    temps++;

                })
        );
        gameLoop.getKeyFrames().add(kf);
    }



}