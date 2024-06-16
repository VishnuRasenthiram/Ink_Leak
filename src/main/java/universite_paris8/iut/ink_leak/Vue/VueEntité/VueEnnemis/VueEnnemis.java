package universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.ink_leak.Controller.Observable.VieEnnemisObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;

import java.io.File;

public class VueEnnemis extends VueEntite {

    private Joueur joueur;
    private Map map;

    public VueEnnemis(Pane mainPane, Joueur joueur, Map map) {
        super(mainPane);
        this.joueur = joueur;
        this.map = map;
    }

    @Override
    public void créeSprite(Entité entité) {
        Pane ennemis = new Pane();
        ennemis.setId(entité.getNom_entite());

        ImageView imageview = new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(orientationToFile(entité.getOrientation(),entité,"Idle").toURI().toString()));
        ennemis.getChildren().add(imageview);

        ennemis.translateXProperty().bind(entité.posXProperty());
        ennemis.translateYProperty().bind(entité.posYProperty());

        // Rectangle pour représenter les points de vie (PV)
        Pane Rectangle = new Pane();
        Rectangle.setPrefWidth(28);
        Rectangle.setPrefHeight(6);
        Rectangle.setId(entité.getNom_entite() + "vie");
        Rectangle.setStyle("-fx-background-color: #edc2c2; -fx-border-color: rgba(0,0,0,0.68); -fx-border-radius: 50; -fx-background-radius: 50;");
        Rectangle vieRect = new Rectangle();
        vieRect.setWidth(28);  // Largeur du rectangle (correspondant à la taille de l'image)
        vieRect.setHeight(6); // Hauteur du rectangle (à ajuster selon vos besoins)
        vieRect.setId(entité.getNom_entite() + "vie");
        vieRect.setStyle("-fx-fill: #990e0e; -fx-stroke: rgba(0,0,0,0.56); -fx-stroke-width: 1; -fx-arc-height: 50; -fx-arc-width: 10;");

        vieRect.widthProperty().bind(entité.getVie_entiteProperty().multiply(32).divide(2));
        Rectangle.translateXProperty().bind(entité.posXProperty());
        Rectangle.translateYProperty().bind(entité.posYProperty());
        Rectangle.getChildren().add(vieRect);
        super.getMainPane().getChildren().add(ennemis);
        super.getMainPane().getChildren().add(Rectangle);


        entité.getVie_entiteProperty().addListener(new VieEnnemisObs(ennemis, entité, joueur,map));
    }

}
