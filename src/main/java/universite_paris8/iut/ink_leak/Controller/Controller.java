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
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.GenerateurObjets;
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
    private GenerateurEnnemis generateurEnnemis;
    private GenerateurObjets generateurObjets;
    private VueJoueur ink;

    private VueMap vueMap;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tempsDeRechargeJ =true;
        this.tempsDeRechargeK =true;
        this.map= new Map();
        generateurEnnemis = new GenerateurEnnemis();

        vueMap= new VueMap(tuileMap, interfacePane, mainBorderPane);
        ink= new VueJoueur(mainPane, interfacePane);

        vueMap.initMap(map, joueur);
        gameLoop();
        gameLoop.play();


        this.joueur = new Joueur("LePlayer",map, generateurEnnemis);
        joueur.setEmplacement(30,200);


        generateurObjets = new GenerateurObjets(map, joueur);


        env = new Environnement(joueur, map, generateurEnnemis, generateurObjets,vueMap);

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
                ink.stopAnimation(joueur, p);
            }
            else{
                ink.walkAnimation(joueur, p);
            }

        });
        ListChangeListener<Entité> listenerEnnemis=new ListeEnnemieObs(mainPane,joueur);
        generateurEnnemis.getListeEntite().addListener(listenerEnnemis);
        vT = new VueTexte(env, txt, mainPane);
        mainPane.getChildren().get(mainPane.getChildren().indexOf(txt)).toFront();



        ListChangeListener<Pouvoirs> airpods=new ListePouvoirsObs(interfacePane,joueur);
        joueur.getListePouvoirs().addListener(airpods);

        PouvoirEnCoursObs pv= new PouvoirEnCoursObs(joueur,interfacePane);
        joueur.getIndicePouvoirEnCoursProperty().addListener(pv);

        ListChangeListener<Objets> Buds= new ListeObjetsObs(mainPane);
        generateurObjets.getListeObjets().addListener(Buds);

        ink.créeSprite(joueur);
        ink.créeSpriteVie(joueur);
        Musique musique = new Musique();
        musique.jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/Main_theme_(Snarfnpoots).wav",1.0f, -1);
    }
    private String currentDirection = null;

    @FXML
    public void action() {
        VueAttaque vA = new VueAttaque(mainPane, joueur);

        mainPane.setOnKeyPressed(e -> {
            String newDirection = null;
            if (e.getCode() == KeyCode.Z) {
                newDirection = "N";
            } else if (e.getCode() == KeyCode.S) {
                newDirection = "S";
            } else if (e.getCode() == KeyCode.Q) {
                newDirection = "O";
            } else if (e.getCode() == KeyCode.D) {
                newDirection = "E";
            }

            if (newDirection != null) {
                if (!newDirection.equals(currentDirection)) {
                    if (currentDirection != null) {
                        joueur.stop();
                    }
                    currentDirection = newDirection;
                    joueur.déplacement(newDirection);
                }
            }

            if (e.getCode() == KeyCode.J) {
                if (tempsDeRechargeJ) {
                    tempsDeRechargeJ = false;
                    vA.afficheAttaque(joueur.getAttaqueDeBase());
                    ink.punchAnimation();
                    joueur.attaque();
                    new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/attaque.wav", 0.7f, 0);
                }
            } else if (e.getCode() == KeyCode.K) {
                if (tempsDeRechargeK) {
                    tempsDeRechargeK = false;
                    Pouvoirs pouvoirEnCours = joueur.getPouvoirEnCours();
                    if (pouvoirEnCours != null) {
                        vA.afficheAttaque(pouvoirEnCours);
                        joueur.attaqueAvecPouvoir();
                    }
                }
            } else if (e.getCode() == KeyCode.A) {
                joueur.setPouvoir(-1);
            } else if (e.getCode() == KeyCode.E) {
                joueur.setPouvoir(1);
            }

        });

        mainPane.setOnKeyReleased(e -> {
            if (currentDirection == null) {
                return;
            }
            if ((e.getCode() == KeyCode.Z && currentDirection.equals("N")) ||
                    (e.getCode() == KeyCode.S && currentDirection.equals("S")) ||
                    (e.getCode() == KeyCode.Q && currentDirection.equals("O")) ||
                    (e.getCode() == KeyCode.D && currentDirection.equals("E"))) {
                joueur.stop();
                currentDirection = null;
            }
        });
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