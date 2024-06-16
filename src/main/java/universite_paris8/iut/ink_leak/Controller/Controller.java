package universite_paris8.iut.ink_leak.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListeEnnemieObs;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListeMursObs;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListeObjetsObs;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListePouvoirsObs;
import universite_paris8.iut.ink_leak.Controller.Observable.OrientationObs;
import universite_paris8.iut.ink_leak.Controller.Observable.PouvoirEnCoursObs;
import universite_paris8.iut.ink_leak.Modele.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.Mur;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurMurs;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurObjets;
import universite_paris8.iut.ink_leak.Vue.Musique;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque.VueAttaque;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueJoueur;
import universite_paris8.iut.ink_leak.Vue.VueMap;
import universite_paris8.iut.ink_leak.Vue.VueTexte;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Timeline gameLoop;

    private int tempsDeRechargeK;
    private int tempsDeRechargeJ;
    private int temps;
    private long tempsEcoulé;
    private boolean jeuFini;

    private Map map;
    private Environnement env;
    private Joueur joueur;

    @FXML
    private Label txt;
    @FXML
    private TilePane tuileMap;
    @FXML
    public BorderPane mainBorderPane;
    @FXML
    private Pane mainPane;
    @FXML
    private Pane interfacePane;

    private GenerateurEnnemis generateurEnnemis;
    private GenerateurObjets generateurObjets;
    private GenerateurMurs generateurMurs;

    private VueJoueur ink;
    private VueMap vueMap;
    private VueTexte vT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lancementJeu();
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
                if (tempsDeRechargeJ==0) {
                    tempsDeRechargeJ = 1;
                    vA.afficheAttaque(joueur.getAttaqueDeBase());
                    ink.punchAnimation();
                    joueur.attaque();
                    new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/attaque.wav", 0.7f, 0);
                }
            } else if (e.getCode() == KeyCode.K) {
                if (tempsDeRechargeK==0) {
                    tempsDeRechargeK = 3;
                    Pouvoirs pouvoirEnCours = joueur.getPouvoirEnCours();
                    if (pouvoirEnCours != null) {
                        vA.afficheAttaque(pouvoirEnCours);
                        joueur.attaqueAvecPouvoir();
                    }
                    if (pouvoirEnCours instanceof Bulle) {
                        new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/bulle.wav", 1.0f, 0);
                    }
                    else if (pouvoirEnCours instanceof Poing) {
                        new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/poing.wav", 1.0f, 0);
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
        tempsEcoulé=0;
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        System.out.println("time line ");
        KeyFrame kf = new KeyFrame(
                Duration.millis(17),
                (ev -> {

                    tempsEcoulé+=17;
                    if(tempsEcoulé>=1000){
                        if(tempsDeRechargeJ>0){
                            tempsDeRechargeJ--;
                        }
                        if(tempsDeRechargeK>0){
                            tempsDeRechargeK--;
                        }
                        tempsEcoulé-=1000;
                    }
                    vT.afficherTexte();
                    double x = this.joueur.getPosX();
                    double y = this.joueur.getPosY();
                    int interaction = joueur.verifierInteractionEnFace(x, y);
                    env.action(temps);
                    if (interaction == 22 || interaction == 6 ||interaction == 24 || interaction == 25 || interaction == 26 ) {

                        env.changementDeMap(interaction);
                        vueMap.supprimerAffichageMap();
                        vueMap.initMap(map, joueur);
                    }
                    if (joueur.getVie() == 0 && !jeuFini)  {
                        jeuFini=true;

                        gameLoop.stop();

                        afficherFin();
                    }

                    if (temps == 10000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    }


                    temps++;


                })
        );
        gameLoop.getKeyFrames().add(kf);


    }

    public void lancementJeu(){
        jeuFini=false;
        this.tempsDeRechargeJ =0;
        this.tempsDeRechargeK =0;
        this.map= new Map();

        vueMap= new VueMap(tuileMap, interfacePane, mainBorderPane);
        ink= new VueJoueur(mainPane, interfacePane);

        vueMap.initMap(map, joueur);
        gameLoop();
        gameLoop.play();

        generateurEnnemis = new GenerateurEnnemis();
        generateurMurs = new GenerateurMurs(map);
        this.joueur = new Joueur("Entity",map, generateurEnnemis,generateurMurs);
        joueur.setEmplacement(8,10);

        joueur.getOrientationProperty().addListener(new OrientationObs(mainPane,ink,joueur));

        generateurObjets = new GenerateurObjets(map, joueur);

        env = new Environnement(joueur, map, generateurEnnemis, generateurObjets,generateurMurs);

        joueur.getMovementStateProperty().addListener((obs, old, nouv) -> {

            if (nouv != Joueur.MovementState.WALK){
                ink.stopAnimation(joueur);
            }
            else{
                ink.walkAnimation(joueur);
            }

        });
        ListChangeListener<Entité> listenerEnnemis=new ListeEnnemieObs(mainPane,joueur,map);
        generateurEnnemis.getListeEntite().addListener(listenerEnnemis);
        vT = new VueTexte(env, txt, mainPane);
        mainPane.getChildren().get(mainPane.getChildren().indexOf(txt)).toFront();

        ListChangeListener<Pouvoirs> airpods=new ListePouvoirsObs(interfacePane,joueur);
        joueur.getListePouvoirs().addListener(airpods);

        PouvoirEnCoursObs pv= new PouvoirEnCoursObs(joueur,interfacePane);
        joueur.getIndicePouvoirEnCoursProperty().addListener(pv);

        ListChangeListener<Objets> Buds= new ListeObjetsObs(mainPane);
        generateurObjets.getListeObjets().addListener(Buds);

        ListChangeListener<Mur> oreille= new ListeMursObs(mainPane);
        generateurMurs.getListeMurs().addListener(oreille);
        ink.créeSprite(joueur);
        ink.créeSpriteVie(joueur);
//        Musique musique = new Musique();
//        musique.jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/Main_theme_(pseudomorph_0z).wav",1.0f, -1);

    }




    public void reinitialiser() {
        jeuFini = false;

        // Supprimer l'écran de fin de partie
        VBox dialogBox = (VBox) mainPane.lookup("#diagBox");
        if (dialogBox != null) {
            mainPane.getChildren().remove(dialogBox);
        }

        // Réinitialiser l'état du joueur
        joueur.setVie_entite(6);
        joueur.setEmplacement(8, 10);
        joueur.getListePouvoirs().clear();
        joueur.setBougable(true);

        // Réinitialiser les générateurs
        generateurObjets.getListeObjets().clear();
        generateurMurs.getListeMurs().clear();
        generateurEnnemis.getListeEntite().clear();

        // Réinitialiser la carte et la vue de la carte
        vueMap.supprimerAffichageMap();
        map.setMap(0);
        vueMap.initMap(map, joueur);

        // Réinitialiser le temps de recharge et les compteurs de temps
        this.tempsDeRechargeJ = 0;
        this.tempsDeRechargeK = 0;
        this.temps = 0;
        this.tempsEcoulé = 0;

        // Redémarrer la boucle de jeu
        if (gameLoop != null && gameLoop.getStatus() == Timeline.Status.RUNNING) {
            gameLoop.stop();
        }
        gameLoop();
        gameLoop.play();

        // Assurez-vous que le bouton "Recommencer" est cliquable
        boutonRecommencer.setDisable(false);
    }


    private Button boutonRecommencer ;
    private Button boutonQuitter;


    private void afficherFin() {
        boutonRecommencer= new Button("Recommencer");
        boutonQuitter= new Button("Quitter");
        VBox dialogBox = new VBox();
        dialogBox.setId("diagBox");
        dialogBox.setAlignment(Pos.CENTER);
        dialogBox.setSpacing(10);
        dialogBox.setStyle("-fx-background-color: black; -fx-border-color: white;");

        Label message = new Label("GAME OVER");
        message.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        boutonRecommencer.setDisable(false);
        boutonRecommencer.setOnAction(e -> {
            mainPane.getChildren().remove(dialogBox);
            reinitialiser();
        });

        boutonQuitter.setOnAction(e -> Platform.exit());

        dialogBox.getChildren().addAll(message, boutonRecommencer, boutonQuitter);
        mainPane.getChildren().add(dialogBox);
        dialogBox.setTranslateX((mainPane.getWidth() - dialogBox.getWidth()) / 2);
        dialogBox.setTranslateY((mainPane.getHeight() - dialogBox.getHeight()) / 2);
    }



}