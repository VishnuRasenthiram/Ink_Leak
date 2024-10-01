package universite_paris8.iut.ink_leak.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListeEnnemieObs;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListeMursObs;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListeObjetsObs;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListePouvoirsObs;
import universite_paris8.iut.ink_leak.Controller.Observable.OrientationObs;
import universite_paris8.iut.ink_leak.Controller.Observable.PouvoirEnCoursObs;
import universite_paris8.iut.ink_leak.Modele.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Abomination;
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
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis.VueBoss;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque.VueAttaque;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueJoueur;
import universite_paris8.iut.ink_leak.Vue.VueMap;
import universite_paris8.iut.ink_leak.Vue.VueTexte;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private DialogueController dialogueController;

    @FXML
    private Label txt;
    private Label dialogueLabel;
    @FXML
    private Button optionButton1;
    @FXML
    private Button optionButton2;
    @FXML
    private Button optionButton3;
    @FXML
    private VBox QuizBox;
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

    private ListeEnnemieObs listenerEnnemis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jeuFini = false;
        this.tempsDeRechargeJ = 0;
        this.tempsDeRechargeK = 0;
        this.map = new Map();

        vueMap = new VueMap(tuileMap, interfacePane, mainBorderPane);
        ink = new VueJoueur(mainPane, interfacePane);

        vueMap.initMap(map, joueur);
        gameLoop();
        gameLoop.play();

        generateurEnnemis = new GenerateurEnnemis();
        generateurMurs = new GenerateurMurs(map);
        this.joueur = new Joueur("Entity", map, generateurEnnemis, generateurMurs);
        joueur.setEmplacement(8, 10);

        joueur.getOrientationProperty().addListener(new OrientationObs(mainPane, ink, joueur));

        generateurObjets = new GenerateurObjets(map, joueur);

        env = new Environnement(joueur, map, generateurEnnemis, generateurObjets, generateurMurs);

        joueur.getMovementStateProperty().addListener((obs, old, nouv) -> {

            if (nouv != Joueur.MovementState.WALK) {
                ink.stopAnimation(joueur);
            } else {
                ink.walkAnimation(joueur);
            }
        });
        listenerEnnemis = new ListeEnnemieObs(mainPane, joueur, map,jeuFini);

        generateurEnnemis.getListeEntite().addListener(listenerEnnemis);
        Abomination Abomination = new Abomination(generateurEnnemis,map, joueur);
        VueBoss VB = new VueBoss(mainPane, joueur, map);
        Abomination.getPhaseProperty().addListener((obs, old, nouv) -> {
            if (nouv.intValue() == 2) {
                VB.créeAttaque1(Abomination);
            } else {
                VB.créeAttaque2(Abomination);

            }
        });
        generateurEnnemis.genererEnnemis(map,joueur, Abomination);

        vT = new VueTexte(env, mainPane);

        ListChangeListener<Pouvoirs> airpods = new ListePouvoirsObs(interfacePane, joueur);
        joueur.getListePouvoirs().addListener(airpods);

        PouvoirEnCoursObs pv = new PouvoirEnCoursObs(joueur, interfacePane);
        joueur.getIndicePouvoirEnCoursProperty().addListener(pv);

        ListChangeListener<Objets> Buds = new ListeObjetsObs(mainPane);
        generateurObjets.getListeObjets().addListener(Buds);

        ListChangeListener<Mur> oreille = new ListeMursObs(mainPane);
        generateurMurs.getListeMurs().addListener(oreille);
        ink.créeSprite(joueur);
        ink.créeSpriteVie(joueur);
        Musique musique = new Musique();
        musique.jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/Main_theme_(pseudomorph_0z).wav", 1.0f, -1);


        // FONCTIONNEL MAIS PAS IMPLEMENTE
        dialogueController = new DialogueController(dialogueLabel, optionButton1, optionButton2, optionButton3);
        dialogueController.initDialogueTree();
        dialogueController.setInitialDialogueNode();



    }

    @FXML
    private void handleOption1() {
        dialogueController.handleOptionSelection(0);
//        Musique musique = new Musique();
//        musique.jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/Main_theme_(pseudomorph_0z).wav",1.0f, -1);

    }


    @FXML
    private void handleOption2() {
        dialogueController.handleOptionSelection(1);
    }

    @FXML
    private void handleOption3() {
        dialogueController.handleOptionSelection(2);
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

            if (e.getCode() == KeyCode.J) {//attaque de base
                if (tempsDeRechargeJ == 0) {
                    tempsDeRechargeJ = 1;
                    vA.afficheAttaque(joueur.getAttaqueDeBase());
                    ink.punchAnimation();
                    joueur.attaque();
                    new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/attaque.wav", 0.7f, 0);
                }
            } else if (e.getCode() == KeyCode.K) {//attaque avec pouvoirs
                if (tempsDeRechargeK == 0) {
                    tempsDeRechargeK = 3;
                    Pouvoirs pouvoirEnCours = joueur.getPouvoirEnCours();
                    if (pouvoirEnCours != null) {
                        vA.afficheAttaque(pouvoirEnCours);
                        joueur.attaqueAvecPouvoir();
                    }
                    if (pouvoirEnCours instanceof Bulle) {
                        new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/bulle.wav", 1.0f, 0);
                    } else if (pouvoirEnCours instanceof Poing) {
                        new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/poing.wav", 1.0f, 0);
                    }
                }
            } else if (e.getCode() == KeyCode.A) {//changement du pouvoir en cours
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
        tempsEcoulé = 0;
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                Duration.millis(17),
                (ev -> {

                    tempsEcoulé += 17;
                    if (tempsEcoulé >= 1000) {
                        if (tempsDeRechargeJ > 0) {
                            tempsDeRechargeJ--;
                        }
                        if (tempsDeRechargeK > 0) {
                            tempsDeRechargeK--;
                        }
                        tempsEcoulé -= 1000;
                    }
                    vT.afficherTexte();
                    double x = this.joueur.getPosX();
                    double y = this.joueur.getPosY();
                    int interaction = joueur.verifierInteractionEnFace(x, y);
                    if(!listenerEnnemis.getJeuFini()){
                        env.action(temps);
                    }

                    //gestion changement de map

                    /*
                    if (dialogueController.onTargetDialogueReached() == false){
                        PauseTransition pause = new PauseTransition(Duration.millis(1000));
                        pause.setOnFinished(event -> {
                            QuizBox.setVisible(false);
                        });
                        pause.play();
                    }
                    */
                    if (interaction == 22 || interaction == 6 ||interaction == 24 || interaction == 25 || interaction == 26 ) {

                        env.changementDeMap(interaction);
                        vueMap.supprimerAffichageMap();
                        vueMap.initMap(map, joueur);
                    }
                    if (joueur.getVie() == 0) {
                        joueur.setVie_entite(6);
                        if (map.getNumMap() == 0) {
                            env.changementDeMap(0);
                        } else {
                            env.changementDeMap(6);
                        }
                        vueMap.supprimerAffichageMap();
                        vueMap.initMap(map, joueur);
                    }

                    if(listenerEnnemis.getJeuFini()){
                        joueur.setBougable(false);
                        afficherFin();
                    }

                    temps++;


                })
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.setOnFinished(e -> {
            gameLoop.getKeyFrames().remove(kf);
        });


    }

    private void afficherFin() {
        StackPane stackPane = new StackPane();
        stackPane.setId("stackPane");
        stackPane.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView();
        imageView.setStyle("-fx-background-color: transparent;");
        imageView.setId("imageView");
        imageView.setFitHeight(640);
        imageView.setFitWidth(640);
        imageView.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/title_screen.png").toURI().toString()));

        Label message = new Label("Bravo, tu as réussi à battre ce monstre, tu es désormais libre !");
        message.setTranslateY(message.getTranslateY()-50);
        message.setStyle("-fx-text-fill: white; -fx-font-size: 16;");
        message.setAlignment(Pos.CENTER);
        Button boutonQuitter = new Button("Quitter");
        boutonQuitter.setTranslateY(boutonQuitter.getTranslateY()+20);
        boutonQuitter.setOnAction(e -> Platform.exit());

        stackPane.getChildren().addAll(imageView, message,boutonQuitter);

        VBox dialogBox = new VBox();
        dialogBox.setId("diagBox");
        dialogBox.setAlignment(Pos.CENTER);
        dialogBox.setSpacing(10);

        dialogBox.getChildren().add(stackPane);

        mainPane.getChildren().add(dialogBox);
        dialogBox.setTranslateX(0);
        dialogBox.setTranslateY(0);
    }

}





