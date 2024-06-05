package universite_paris8.iut.ink_leak.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur;
import universite_paris8.iut.ink_leak.Vue.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Timeline gameLoop;
    private int temps;
    private Map map;
    private Environnement env;
    @FXML
    private Label txt;
    private VueTexte vT;
    @FXML
    private TilePane tuileMap;
    private Joueur joueur;
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

        this.joueur = new Joueur("LePlayer",map,spawner);
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
        env = new Environnement(joueur, map, spawner);
        vT = new VueTexte(env, txt, mainPane);
        mainPane.getChildren().get(mainPane.getChildren().indexOf(txt)).toFront();
        spawner.getListeEntite().addListener(ecouteur);
        ink.créeSpriteVie(joueur);
        Musique musique = new Musique();
        musique.jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/Main_theme_(Snarfnpoots).wav", -1);

    }

    @FXML
    public void action() {
        VueAttaque vA= new VueAttaque(mainPane,spawner);
        mainPane.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.Z){
                joueur.déplacement(0);
            } else if (e.getCode() == KeyCode.S) {
                joueur.déplacement(1);

            } else if (e.getCode() == KeyCode.Q) {
                joueur.déplacement(2);

            } else if (e.getCode() == KeyCode.D) {
                joueur.déplacement(3);

            }

            if(e.getCode()==KeyCode.J){

                joueur.attaque(vA.afficheAttaque(joueur));
            }
        });
    }
    @FXML
    protected void stop() {
        joueur.stop();
    }

    private void gameLoop() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.millis(60),
                (ev -> {
                    env.action(temps);
                    temps++;
                    vT.afficherTexte();

                })
        );
        gameLoop.getKeyFrames().add(kf);
    }



}