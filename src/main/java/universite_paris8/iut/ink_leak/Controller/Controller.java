package universite_paris8.iut.ink_leak.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListeEnnemieObs;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListeObjetsObs;
import universite_paris8.iut.ink_leak.Controller.ListeObservable.ListePouvoirsObs;
import universite_paris8.iut.ink_leak.Controller.Observable.OrientationObs;
import universite_paris8.iut.ink_leak.Controller.Observable.PouvoirEnCoursObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private boolean tempsDeRechargeK;
    private boolean tempsDeRechargeJ;
    private Timeline gameLoop;
    private int temps;
    private Map map;
    private Environnement env;
    private VueTexte vT;
    private Joueur joueur;
    private GenerateurEnnemis generateurEnnemis;
    private GenerateurObjets generateurObjets;
    private VueJoueur ink;
    private VueMap vueMap;

    @FXML
    private Label dialogueLabel;
    @FXML
    private Button optionButton1;
    @FXML
    private Button optionButton2;
    @FXML
    private Button optionButton3;
    @FXML
    private TilePane tuileMap;
    @FXML
    public BorderPane mainBorderPane;
    @FXML
    private Pane mainPane;
    @FXML
    private Pane interfacePane;

    private SimpleObjectProperty<DialogueNode> currentDialogueNode = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tempsDeRechargeJ = true;
        this.tempsDeRechargeK = true;
        this.map = new Map();

        vueMap = new VueMap(tuileMap, interfacePane, mainBorderPane);
        ink = new VueJoueur(mainPane, interfacePane);

        vueMap.initMap(map, joueur);
        gameLoop();
        gameLoop.play();

        generateurEnnemis = new GenerateurEnnemis();

        this.joueur = new Joueur("Entity", map, generateurEnnemis);
        joueur.setEmplacement(12, 10);

        joueur.getOrientationProperty().addListener(new OrientationObs(mainPane, ink, joueur));
        generateurObjets = new GenerateurObjets(map, joueur);

        env = new Environnement(joueur, map, generateurEnnemis, generateurObjets, vueMap);

        joueur.getMovementStateProperty().addListener((obs, old, nouv) -> {
            if (nouv != Joueur.MovementState.WALK) {
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

        ink.créeSprite(joueur);
        ink.créeSpriteVie(joueur);
        Musique musique = new Musique();
        musique.jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/Main_theme_(pseudomorph_0z).wav", 1.0f, -1);

        // Initialisation de l'arbre de dialogue
        initDialogueTree();
        currentDialogueNode.addListener((obs, oldNode, newNode) -> updateDialogueUI(newNode));
        currentDialogueNode.set(rootNode); // Définir le nœud de dialogue initial
    }

    private DialogueNode rootNode;

    private void initDialogueTree() {
        rootNode = new DialogueNode("Bienvenue dans le jeu. Que voulez-vous faire ?");
        DialogueNode option1 = new DialogueNode("Explorer la forêt");
        DialogueNode option2 = new DialogueNode("Visiter le village");
        DialogueNode option3 = new DialogueNode("Quitter le jeu");

        rootNode.addResponse(option1);
        rootNode.addResponse(option2);
        rootNode.addResponse(option3);

        option1.addResponse(new DialogueNode("Vous trouvez un trésor caché !"));
        option1.addResponse(new DialogueNode("Un loup apparaît !"));

        option2.addResponse(new DialogueNode("Vous rencontrez un marchand."));
        option2.addResponse(new DialogueNode("Vous trouvez une auberge."));
    }

    @FXML
    private void handleOption1() {
        handleOptionSelection(0);
    }

    @FXML
    private void handleOption2() {
        handleOptionSelection(1);
    }

    @FXML
    private void handleOption3() {
        handleOptionSelection(2);
    }

    private void handleOptionSelection(int optionIndex) {
        DialogueNode currentNode = currentDialogueNode.get();
        List<DialogueNode> responses = currentNode.getResponses();
        if (optionIndex >= 0 && optionIndex < responses.size()) {
            currentDialogueNode.set(responses.get(optionIndex));
        }
    }

    private void updateDialogueUI(DialogueNode node) {
        dialogueLabel.setText(node.getMessage());
        List<DialogueNode> responses = node.getResponses();

        optionButton1.setVisible(false);
        optionButton2.setVisible(false);
        optionButton3.setVisible(false);

        if (responses.size() > 0) {
            optionButton1.setText(responses.get(0).getMessage());
            optionButton1.setVisible(true);
        }
        if (responses.size() > 1) {
            optionButton2.setText(responses.get(1).getMessage());
            optionButton2.setVisible(true);
        }
        if (responses.size() > 2) {
            optionButton3.setText(responses.get(2).getMessage());
            optionButton3.setVisible(true);
        }
    }


class DialogueNode {
    private String message;
    private List<DialogueNode> responses;

    public DialogueNode(String message) {
        this.message = message;
        this.responses = new ArrayList<>();
    }

    public void addResponse(DialogueNode response) {
        responses.add(response);
    }

    public String getMessage() {
        return message;
    }

    public List<DialogueNode> getResponses() {
        return responses;
    }
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
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);



        KeyFrame kf = new KeyFrame(
                Duration.millis(17),
                (ev -> {
                    env.action(temps);
                    vT.afficherTexte();



                    if (temps == 1000000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    }

                    if (temps % 50 == 0) {
                        tempsDeRechargeK = true;
                    }
                    if (temps % 90 == 0) {
                        tempsDeRechargeJ = true;
                    }
                    temps++;


                })
        );
        gameLoop.getKeyFrames().add(kf);


    }



}