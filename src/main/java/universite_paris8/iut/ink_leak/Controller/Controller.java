package universite_paris8.iut.ink_leak.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Vue.Musique;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque.VueAttaque;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueJoueur;
import universite_paris8.iut.ink_leak.Vue.VueMap;
import universite_paris8.iut.ink_leak.Vue.VueTexte;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private boolean tempsDeRechargeK;
    private boolean tempsDeRechargeJ;
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

    private VueMap vueMap;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tempsDeRechargeJ =true;
        this.tempsDeRechargeK =true;
        this.map= new Map();
        spawner= new GenerateurEnnemis();
        vueMap= new VueMap(tuileMap);
        VueJoueur ink= new VueJoueur(mainPane, interfacePane);

        vueMap.initMap(map);
        gameLoop();
        gameLoop.play();


        this.joueur = new Joueur("LePlayer",map,spawner);
        joueur.setEmplacement(30,200);


        env = new Environnement(joueur, map, spawner,vueMap);

        joueur.orientationProperty().addListener((obs,old,nouv)->{

            Pane p = (Pane) mainPane.lookup("#"+joueur.getNom_entite());
            p.getChildren().remove(0);
            ImageView imageview= new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(new Image(ink.orientationToFile(nouv).toURI().toString()));
            p.getChildren().add(imageview);

        });
        joueur.getMovementStateProperty().addListener((obs, old, nouv) -> {

            Pane p = (Pane) mainPane.lookup("#" + joueur.getNom_entite());
            if (nouv != Joueur.MovementState.WALK){
                ink.stopWalkAnimation(joueur, p);
            }
            else{
                ink.walkAnimation(joueur, p);
            }

        });
        ListChangeListener<Entité> listenerEnnemis=new ListeEnnemieObs(mainPane);
        spawner.getListeEntite().addListener(listenerEnnemis);

        ListChangeListener<Entité> ecouteur=new ListeEnnemieObs(mainPane);
        env = new Environnement(joueur, map, spawner,vueMap);
        vT = new VueTexte(env, txt, mainPane);
        mainPane.getChildren().get(mainPane.getChildren().indexOf(txt)).toFront();
        spawner.getListeEntite().addListener(ecouteur);


        ListChangeListener<Pouvoirs> airpods=new ListePouvoirsObs(interfacePane,joueur);
        joueur.getListePouvoirs().addListener(airpods);

        PouvoirEnCoursObs pv= new PouvoirEnCoursObs(joueur,interfacePane);
        joueur.getIndicePouvoirEnCoursProperty().addListener(pv);

        ink.créeSprite(joueur);
        ink.créeSpriteVie(joueur);
        Musique musique = new Musique();
        musique.jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/Main_theme_(Snarfnpoots).wav",1.0f, -1);
    }

    @FXML
    public void action() {
        VueAttaque vA= new VueAttaque(mainPane);
        mainPane.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.Z){
                joueur.déplacement("N");
            } else if (e.getCode() == KeyCode.S) {
                joueur.déplacement("S");

            } else if (e.getCode() == KeyCode.Q) {
                joueur.déplacement("O");

            } else if (e.getCode() == KeyCode.D) {
                joueur.déplacement("E");
            }
            if(e.getCode()==KeyCode.J){
                if(tempsDeRechargeJ){

                    tempsDeRechargeJ =false;
                    vA.afficheAttaque(joueur.getAttaqueDeBase());
                    joueur.attaque();
                    new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/attaque.wav", 0.7f, 0);

                }
            }
            if(e.getCode()==KeyCode.K){
                if(tempsDeRechargeK){
                    tempsDeRechargeK =false;
                    Pouvoirs pouvoirEnCours=joueur.getPouvoirEnCours();
                    if(pouvoirEnCours!=null){
                        vA.afficheAttaque(pouvoirEnCours);
                        joueur.attaqueAvecPouvoir();
                    }

                }
            }
            if(e.getCode()==KeyCode.A){
                joueur.setPouvoir(-1);
            }
            if(e.getCode()==KeyCode.E){
                joueur.setPouvoir(1);
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
                    vT.afficherTexte();
                    if (temps == 10000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    }

                    if (temps % 30 == 0) {
                        tempsDeRechargeK = true;
                    }
                    if (temps % 10 == 0) {
                        tempsDeRechargeJ = true;
                    }
                    temps++;


                })
        );
        gameLoop.getKeyFrames().add(kf);


    }


}