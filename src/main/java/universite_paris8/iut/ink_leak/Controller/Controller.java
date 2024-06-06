package universite_paris8.iut.ink_leak.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.AttaqueDeBase;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Vue.VueAttaque;
import universite_paris8.iut.ink_leak.Vue.VueJoueur;
import universite_paris8.iut.ink_leak.Vue.VueMap;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private boolean tempsDeRechargeK;
    private boolean tempsDeRechargeJ;
    private Timeline gameLoop;
    private int temps;
    private Map map;
    public Environnement env;
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
        env = new Environnement(joueur, map, spawner);
        spawner.getListeEntite().addListener(ecouteur);
        ink.créeSpriteVie(joueur);

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
                    AttaqueDeBase attaqueDeBase=new AttaqueDeBase(map,spawner,joueur);
                    vA.afficheAttaque(attaqueDeBase);
                    attaqueDeBase.déplacement(joueur.getOrientationProperty());

                }
            }

            if(e.getCode()==KeyCode.K){
                if(tempsDeRechargeK){
                    tempsDeRechargeK =false;
                    Bulle bulle = new Bulle( map, spawner, joueur);
                    vA.afficheAttaque(bulle);
                    bulle.déplacement(joueur.getOrientationProperty());
                }
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
                    if (temps % 5 == 0) {
                        double x = joueur.getPosX();
                        double y = joueur.getPosY();
                        joueur.peutAller(x, y, map);

                        int interaction = joueur.verifierInteractionEnFace(x, y, map);

                        if (interaction == 3 || interaction == 4) {
                            if (interaction == 3 && map.getNumMap() < 0) {
                                return;
                            }
                            joueur.setBougable(false);
                            vueMap.supprimerAffichageMap();
                            map.setMap(interaction == 3 ? map.getNumMap() + 1 : (map.getNumMap() > 3 ? 1 : map.getNumMap() - 1));
                            vueMap.initMap(map);
                            env.TuerToutLesEnnemis();
                            updatePlayerPosition(joueur);
                        }

                        if (temps == 10000) {
                            System.out.println("fini");
                            gameLoop.stop();
                        }
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
    private void updatePlayerPosition(Joueur joueur) {
        switch (joueur.getOrientationProperty()) {
            case "N":
                joueur.setPosYProperty(joueur.getPosY() + 32);
                joueur.setOrientationProperty("N");
                break;
            case "S":
                joueur.setPosYProperty(joueur.getPosY() - 32);
                joueur.setOrientationProperty("S");
                break;
            case "O":
                joueur.setPosXProperty(joueur.getPosX() + 32);
                joueur.setOrientationProperty("O");
                break;
            case "E":
                joueur.setPosXProperty(joueur.getPosX() - 32);
                joueur.setOrientationProperty("E");
                break;
        }
        joueur.setBougable(true);
    }


}