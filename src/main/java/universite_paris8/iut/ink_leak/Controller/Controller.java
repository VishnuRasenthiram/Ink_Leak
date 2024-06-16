package universite_paris8.iut.ink_leak.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Timeline gameLoop;

    private int tempsDeRechargeK;
    private int tempsDeRechargeJ;
    private int temps;
    private long tempsEcoulé;

    private Map map;
    private Environnement env;
    private Joueur joueur;

    private VueTexte vT;
    private Joueur joueur;
    private GenerateurEnnemis generateurEnnemis;
    private GenerateurObjets generateurObjets;
    private VueJoueur ink;
    private VueMap vueMap;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tempsDeRechargeJ =0;
        this.tempsDeRechargeK =0;
        this.map= new Map();

        vueMap = new VueMap(tuileMap, interfacePane, mainBorderPane);
        ink = new VueJoueur(mainPane, interfacePane);

        vueMap.initMap(map, joueur);
        gameLoop();
        gameLoop.play();

        generateurEnnemis = new GenerateurEnnemis();
        generateurMurs = new GenerateurMurs(map);
        this.joueur = new Joueur("Entity", map, generateurEnnemis,generateurMurs);
        joueur.setEmplacement(8, 10);

        joueur.getOrientationProperty().addListener(new OrientationObs(mainPane, ink, joueur));
        generateurObjets = new GenerateurObjets(map, joueur);


        env = new Environnement(joueur, map, generateurEnnemis, generateurObjets,generateurMurs);

        joueur.getMovementStateProperty().addListener((obs, old, nouv) -> {

            if (nouv != Joueur.MovementState.WALK){
                ink.stopAnimation(joueur);
            } else {
                ink.walkAnimation(joueur);
            }
        });

        ListChangeListener<Entité> listenerEnnemis = new ListeEnnemieObs(mainPane, joueur, map);
        generateurEnnemis.getListeEntite().addListener(listenerEnnemis);

        vT = new VueTexte(env, mainPane);
        vT.ajouterTexte("Vous avez récupéré un pouvoir ! Appuyez sur K pour l'utiliser !", 400, 200, 120, 215, 1, 1);
        vT.ajouterTexte("Vous avez trouvé un objet magique !", 400, 200, 120, 265, 1, 2);
        vT.ajouterTexte("Les lumière on été cassé dans cette salle... attention des monstre ont peut-être tendu des pièges...", 400, 200, 120, 315, 1, 3);

        ListChangeListener<Pouvoirs> airpods = new ListePouvoirsObs(interfacePane, joueur);
        joueur.getListePouvoirs().addListener(airpods);

        PouvoirEnCoursObs pv = new PouvoirEnCoursObs(joueur, interfacePane);
        joueur.getIndicePouvoirEnCoursProperty().addListener(pv);

        ListChangeListener<Objets> Buds = new ListeObjetsObs(mainPane);
        generateurObjets.getListeObjets().addListener(Buds);

        ListChangeListener<Mur> oreille= new ListeMursObs(mainPane);
        generateurMurs.getListeMurs().addListener(oreille);
        ink.créeSprite(joueur);
        ink.créeSpriteVie(joueur);
        Musique musique = new Musique();
        musique.jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/Main_theme_(pseudomorph_0z).wav", 1.0f, -1);

        dialogueController = new DialogueController(dialogueLabel, optionButton1, optionButton2, optionButton3);
        dialogueController.initDialogueTree();
        dialogueController.setInitialDialogueNode();

    }

    @FXML
    private void handleOption1() {
        dialogueController.handleOptionSelection(0);
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
                    if (dialogueController.onTargetDialogueReached() == false){
                        PauseTransition pause = new PauseTransition(Duration.millis(1000));
                        pause.setOnFinished(event -> {
                            QuizBox.setVisible(false);
                        });
                        pause.play();
                    }
                    if (interaction == 22 || interaction == 6 ||interaction == 24 || interaction == 25 || interaction == 26 ) {


                        env.changementDeMap(interaction);
                        vueMap.supprimerAffichageMap();
                        vueMap.initMap(map, joueur);
                    }


                    if (temps == 1000000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    }


                    temps++;


                })
        );
        gameLoop.getKeyFrames().add(kf);


    }



}