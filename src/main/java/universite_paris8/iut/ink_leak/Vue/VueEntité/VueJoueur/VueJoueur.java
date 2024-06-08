package universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Controller.VieObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VueJoueur extends VueEntite {
    private Pane interfacePane;
    private Timeline animationTimeline;
    private Timeline punchTimeline;

    private Joueur joueur;
    private Pane Joueur;
    private int punchIndex = 0;
    private int walkIndex = 0;

    public VueJoueur(Pane mainPane, Pane interfacePane){
        super(mainPane);
        this.interfacePane = interfacePane;
    }

    @Override
    public void créeSprite(Entité entité) {

        joueur = (Joueur) entité;
        Joueur = new Pane();
        Joueur.setId(entité.getNom_entite());
        ImageView JoueurView= new ImageView();
        JoueurView.setFitHeight(32);
        JoueurView.setFitWidth(32);
        JoueurView.setImage(new Image(orientationToFile(entité.getOrientationProperty()).toURI().toString()));
        Joueur.getChildren().add(JoueurView);

        Joueur.translateXProperty().bind(entité.posXProperty());
        Joueur.translateYProperty().bind(entité.posYProperty());

        super.getMainPane().getChildren().add(Joueur);

    }



    public void créeSpriteVie(Joueur joueur) {

        Pane vie = new Pane();
        ImageView imageView = new ImageView();
        imageView.setFitHeight(32);
        imageView.setFitWidth(96);
        imageView.setImage((new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Health/health_6.png").toURI().toString())));
        vie.getChildren().add(imageView);

        interfacePane.getChildren().add(vie);
        vie.setTranslateX(0);
        vie.setTranslateY(-32);

        joueur.getVie_entiteProperty().addListener(new VieObs(imageView, Joueur, joueur));
    }

    public File orientationToFile(String orientation){

        switch (orientation){

            case "N":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_up.png");

            case "O":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_left.png");

            case "E":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_right.png");

            default:
                return  new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_down.png");

        }
    }


    public List<Image> getWalkImages(String orientation) {

        List<Image> images = new ArrayList<>();

        if (orientation.equals("N")) orientation = "up";
        else if (orientation.equals("S")) orientation = "down";
        else if (orientation.equals("E")) orientation = "right";
        else orientation = "left";

        String basePath = "src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Walk/" + orientation + "/";

        for (int i = 1; i <= 4; i++) {
            File file = new File(basePath + "entity_walk_" + orientation + "_" + i + ".png");
            images.add(new Image(file.toURI().toString()));
        }

        return images;
    }
    private String orientation;
    public void walkAnimation(Joueur joueur, Pane p) {
        animationTimeline = new Timeline();
        animationTimeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.15), e -> {
            orientation = joueur.getOrientationProperty();
            List<Image> images = getWalkImages(orientation);
            if (p == null) return;
            p.getChildren().clear();
            ImageView imageview = new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(images.get(1));
            walkIndex = walkIndex + 1;
            if (walkIndex >= images.size()) walkIndex = 0;
            imageview.setImage(images.get(walkIndex));
            p.getChildren().add(imageview);
        });

        animationTimeline.getKeyFrames().add(keyFrame);
        animationTimeline.play();
    }
    public List<Image> getPunchAnimation(String orientation) {

        List<Image> images = new ArrayList<>();


        if (orientation.equals("N")) orientation = "up";
        else if (orientation.equals("S")) orientation = "down";
        else if (orientation.equals("E")) orientation = "right";
        else orientation = "left";

        String basePath = "src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/" + orientation + "/";

        if (orientation.equals("up")) orientation = "u";
        else if (orientation.equals("down")) orientation = "d";
        else if (orientation.equals("right")) orientation = "r";
        else orientation = "l";

        for (int i = 1; i <= 2; i++) {
            File file = new File(basePath + "entity_attack_" + orientation + "_" + i + ".png");
            images.add(new Image(file.toURI().toString()));
            System.out.println(file.toURI().toString());
        }


        return images;
    }
    public void punchAnimation() {
        punchTimeline = new Timeline();
        punchTimeline.setCycleCount(2);

        KeyFrame punchFrame = new KeyFrame(Duration.seconds(0.09), e -> {
            orientation = joueur.getOrientationProperty();
            List<Image> images = getPunchAnimation(orientation);

            if (Joueur == null) return;

            Joueur.getChildren().clear();
            ImageView imageview = new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(images.get(1));
            if (punchIndex >= images.size()) punchIndex = 0;
            imageview.setImage(images.get(punchIndex));
            punchIndex = punchIndex + 1;

            Joueur.getChildren().add(imageview);
        });
        punchTimeline.getKeyFrames().add(punchFrame);
        punchTimeline.setOnFinished(e -> {
            PauseTransition pause = new PauseTransition(Duration.millis(450));
            pause.setOnFinished(event -> stopAnimation(joueur, Joueur));
            pause.play();
        });
        punchTimeline.play();
    }
    public void stopAnimation(Joueur joueur,  Pane p) {
        if (animationTimeline != null) {
            animationTimeline.stop();
        }
            String orientation = joueur.getOrientationProperty();

            if (orientation.equals("N")) orientation = "up";
            else if (orientation.equals("S")) orientation = "down";
            else if (orientation.equals("E")) orientation = "right";
            else orientation = "left";
            p.getChildren().clear();
            String idlePath =  "src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Idle/" + "entity_idle_" + orientation + ".png";
            ImageView imageview = new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(new Image(new File(idlePath).toURI().toString()));
            p.getChildren().add(imageview);
    }



}
