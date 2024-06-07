package universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.AttaqueDeBase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VueAttaqueDeBase extends VueAttaque {

    private Timeline animationTimeline;
    private Joueur joueur;

    public VueAttaqueDeBase(Pane mainPane, Joueur joueur) {
        super(mainPane, joueur);
    }

    public void afficheAttaqueDeBase(AttaqueDeBase attaqueDeBase){

        Pane attaquePane=new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/hitbox/hitbox_5.png").toURI().toString()));
        attaquePane.getChildren().add(imageview);
        attaquePane.setId("attaque");

        attaquePane.translateXProperty().bind(attaqueDeBase.posXProperty());
        attaquePane.translateYProperty().bind(attaqueDeBase.posYProperty());

        super.getMainPane().getChildren().add(attaquePane);
        Timeline attaqueVisible = new Timeline(
                new KeyFrame(Duration.millis(200), ev -> super.getMainPane().getChildren().remove(attaquePane))
        );
        attaqueVisible.play();
        griffeAnimation(attaquePane);

    }
    public List<Image> getGriffeAnimation() {

        List<Image> images = new ArrayList<>();
        String basePath = "src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/hitbox/";

        for (int i = 6; i >= 1; i--) {
            File file = new File(basePath + "hitbox_" + i + ".png");
            images.add(new Image(file.toURI().toString()));
        }

        return images;
    }
    private int currentIndex = 0;
    private String orientation;
    public void griffeAnimation( Pane p) {
        animationTimeline = new Timeline();
        animationTimeline.setCycleCount(5);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.05), e -> {
            orientation = super.getJoueur().getOrientationProperty();
            List<Image> images = getGriffeAnimation();
            if (p == null) return;
            p.getChildren().clear();
            ImageView imageview = new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(images.get(1));
            currentIndex = currentIndex + 1;
            imageview.setImage(images.get(currentIndex));
            if (orientation.equals("O")) {
                imageview.setScaleX(-1);
            }
            p.getChildren().add(imageview);
        });

        animationTimeline.getKeyFrames().add(keyFrame);
        animationTimeline.play();
    }
}
