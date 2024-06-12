package universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Controller.Observable.VieObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VueJoueur extends VueEntite {
    private Pane interfacePane;
    private Timeline animationTimeline;
    private Timeline torcheTimeline;
    private Timeline punchTimeline;

    private Joueur joueur;
    private Pane Joueur;
    private int punchIndex = 0;
    private int walkIndex = 0;
    private int torcheIndex = 0;

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
        JoueurView.setImage(new Image(orientationToFile(entité.getOrientation(),entité,"Idle").toURI().toString()));
        Joueur.getChildren().add(JoueurView);

        Joueur.translateXProperty().bind(entité.posXProperty());
        Joueur.translateYProperty().bind(entité.posYProperty());

        super.getMainPane().getChildren().add(Joueur);

        ImageView Torche = new ImageView();
        Torche.setFitHeight(24);
        Torche.setFitWidth(24);
        Torche.setId("torche");
        Torche.translateXProperty().bind(Bindings.add(entité.posXProperty(), -4));
        Torche.translateYProperty().bind(Bindings.add(entité.posYProperty(), 4));
        interfacePane.getChildren().add(Torche);

        torcheTimeline = new Timeline();
        torcheTimeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.12), e -> {
            List<Image> images = getTorchesImages();
            Torche.setImage(images.get(1));
            torcheIndex = torcheIndex + 1;
            if (torcheIndex >= images.size()) torcheIndex = 0;
            Torche.setImage(images.get(torcheIndex));
        });

        torcheTimeline.getKeyFrames().add(keyFrame);
        torcheTimeline.play();

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



    public List<Image> getTorchesImages() {

        List<Image> images = new ArrayList<>();

        String basePath = "src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Objects/torcheMain_";

        for (int i = 1; i <= 6; i++) {
            File file = new File(basePath + i + ".png");
            images.add(new Image(file.toURI().toString()));
        }

        return images;
    }
    public List<Image> getWalkImages(String orientation) {

        List<Image> images = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            images.add(new Image(orientationToFileAnimation(orientation,joueur,"Walk",i).toURI().toString()));
        }
        return images;
    }

    public void walkAnimation(Joueur joueur) {
        animationTimeline = new Timeline();
        animationTimeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.15), e -> {
            Pane p = (Pane) super.getMainPane().lookup("#"+joueur.getNom_entite());
            p.getChildren().remove(0);
            String orientation = joueur.getOrientation();
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
        for (int i = 1; i <= 2; i++) {
            images.add(new Image(orientationToFileAnimation(orientation,joueur,"Attack",i).toURI().toString()));
        }


        return images;
    }
    public void punchAnimation() {
        punchTimeline = new Timeline();
        punchTimeline.setCycleCount(2);

        KeyFrame punchFrame = new KeyFrame(Duration.seconds(0.11), e -> {
            String orientation = joueur.getOrientation();
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
            pause.setOnFinished(event -> stopAnimation(joueur));
            pause.play();
        });
        punchTimeline.play();
    }
    public void stopAnimation(Joueur joueur) {
        if (animationTimeline != null) {
            animationTimeline.stop();
        }
            Pane player = (Pane) super.getMainPane().lookup("#"+joueur.getNom_entite());
            player.getChildren().remove(0);
            ImageView imageview = new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(new Image(orientationToFile(joueur.getOrientation(),joueur,"Idle").toURI().toString()));
            player.getChildren().add(imageview);
    }



}
