package universite_paris8.iut.ink_leak.Vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Slime;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;

import java.io.File;

public class VueAttaque {
    private Pane mainPane;
    private  GenerateurEnnemis spawner;
    public VueAttaque(Pane mainPane, GenerateurEnnemis spawner){
        this.mainPane = mainPane;
        this.spawner = spawner;

    }

    public Entité afficheAttaque(Joueur joueur) {
        Entité toucheEnnemi = null;
        Pane attaquePane=new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/test.png").toURI().toString()));
        Circle circle = new Circle(1,1,1);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.RED);
        if (joueur.getOrientationProperty() == "N") {
            attaquePane.setTranslateY(attaquePane.getTranslateY() - 16);
        }
        else if (joueur.getOrientationProperty() == "S") {
            attaquePane.setTranslateY(attaquePane.getTranslateY() + 16);
        }
        else if (joueur.getOrientationProperty() == "E") {
            attaquePane.setTranslateX(attaquePane.getTranslateX() + 16);
        }
        else {
            attaquePane.setTranslateX(attaquePane.getTranslateX() - 16);
        }



        Timeline bulleOpening = new Timeline(
                new KeyFrame(Duration.millis(20), ev -> {

                   //changer la taille de la bullle
                    circle.setRadius(circle.getRadius() + 1);


                })
        );
        //attaquePane.getChildren().add(imageview);
        //annimation du circle qui grandis et avance tout droit
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(20), ev -> {

                    if (joueur.getOrientationProperty() == "N") {
                        attaquePane.setTranslateY(attaquePane.getTranslateY() - 4);
                    }
                    else if (joueur.getOrientationProperty() == "S") {
                        attaquePane.setTranslateY(attaquePane.getTranslateY() + 4);
                    }
                    else if (joueur.getOrientationProperty() == "E") {
                        attaquePane.setTranslateX(attaquePane.getTranslateX() + 4);
                    }
                    else {
                        attaquePane.setTranslateX(attaquePane.getTranslateX() - 4);
                    }

                })
        );
        timeline.setCycleCount(50);
        bulleOpening.setCycleCount(20);
if (joueur.getAttaque() == false) {
    bulleOpening.play();
    joueur.setAttaque(true);
    attaquePane.getChildren().add(circle);
}
        bulleOpening.setOnFinished(e -> {
            timeline.play();
        });
            timeline.setOnFinished(e -> {
                joueur.setAttaque(false);
                attaquePane.getChildren().remove(circle);
            });

        attaquePane.setId("attaque");
        mainPane.getChildren().add(attaquePane);
        if (joueur.getOrientationProperty() == "N") {
            attaquePane.setTranslateX(joueur.getPosX());
            attaquePane.setTranslateY(joueur.getPosY() - 32);

        }
        else if (joueur.getOrientationProperty() == "S") {
            attaquePane.setTranslateX(joueur.getPosX());
            attaquePane.setTranslateY(joueur.getPosY() + 32);
        }
        else if (joueur.getOrientationProperty() == "E") {
            attaquePane.setTranslateX(joueur.getPosX() + 32);
            attaquePane.setTranslateY(joueur.getPosY());
        }
        else {
            attaquePane.setTranslateX(joueur.getPosX() - 32);
            attaquePane.setTranslateY(joueur.getPosY());
        }

        for(Entité sl:spawner.getListeEntite()){

            Pane slime = (Pane) mainPane.lookup("#"+sl.getNom_entite());
            if(joueur.enContact(attaquePane,slime)) {
                toucheEnnemi=sl;
            }
        }

        Timeline attaqueVisible = new Timeline(
                new KeyFrame(Duration.millis(200), ev -> mainPane.getChildren().remove(attaquePane))
        );
     //   attaqueVisible.play();

        return  toucheEnnemi;
    }
}
