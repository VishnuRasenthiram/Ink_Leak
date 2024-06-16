package universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Controller.Observable.VieEnnemisObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Abomination;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;

import java.io.File;
import java.sql.SQLOutput;
import java.util.List;

public class VueEnnemis extends VueEntite {

    private Joueur joueur;
    private Map map;
    private Timeline animationTimeline;
    private  int iteration = 1;

    public VueEnnemis(Pane mainPane, Joueur joueur, Map map) {
        super(mainPane);
        this.joueur = joueur;
        this.map = map;
    }

    @Override
    public void créeSprite(Entité entité) {
        Pane ennemis = new Pane();
        ennemis.setId(entité.getNom_entite());
        System.out.println(entité.getNom_entite());
        ImageView imageview = new ImageView();
        imageview.setFitWidth(entité.getLargeur());
        imageview.setFitWidth(entité.getLongueur());

        if (entité instanceof Abomination){
            animationTimeline = new Timeline();
            animationTimeline.setCycleCount(Timeline.INDEFINITE);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.4), e -> {
                imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/Abomination/Idle/abomination_idle_" + iteration + ".png").toURI().toString()));
                if (iteration == 4) iteration = 1;
                else iteration++;
            });
            animationTimeline.getKeyFrames().add(keyFrame);
            animationTimeline.play();
        } else{
            imageview.setImage(new Image(orientationToFile(entité.getOrientation(),entité,"Idle").toURI().toString()));

        }
        System.out.println(imageview.getFitWidth());
        ennemis.getChildren().add(imageview);

        ennemis.translateXProperty().bind(entité.posXProperty());
        ennemis.translateYProperty().bind(entité.posYProperty());

        // Rectangle pour représenter les points de vie (PV)
        Pane Rectangle = new Pane();
        Rectangle.setPrefWidth(entité.getLongueur()-4);
        Rectangle.setPrefHeight(entité.getLargeur()/5);

        Rectangle.setId(entité.getNom_entite() + "vie");
        Rectangle.setStyle("-fx-background-color: #edc2c2; -fx-border-color: rgba(0,0,0,0.68); -fx-border-radius: 50; -fx-background-radius: 50;");
        Rectangle vieRect = new Rectangle();

        vieRect.setWidth(entité.getLongueur()-4);  // Largeur du rectangle (correspondant à la taille de l'image)
        vieRect.setHeight(entité.getLargeur()/5); // Hauteur du rectangle (à ajuster selon vos besoins)
        vieRect.setId(entité.getNom_entite() + "vie");
        vieRect.setStyle("-fx-fill: #990e0e; -fx-stroke: rgba(0,0,0,0.56); -fx-stroke-width: 1; -fx-arc-height: 50; -fx-arc-width: 10;");

        vieRect.widthProperty().bind(entité.getVie_entiteProperty().multiply(entité.getLongueur()).divide(entité.getmaxVie()));
        Rectangle.translateXProperty().bind(entité.posXProperty());
        Rectangle.translateYProperty().bind(entité.posYProperty());

        super.getMainPane().getChildren().add(ennemis);
        super.getMainPane().getChildren().add(Rectangle);
        Rectangle.getChildren().add(vieRect);

        entité.getVie_entiteProperty().addListener(new VieEnnemisObs(ennemis, entité, joueur,map));
    }

}
