package universite_paris8.iut.ink_leak.Vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Controller.BulleObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Slime;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.io.File;

public class VueAttaque {
    private Pane mainPane;
    private  GenerateurEnnemis spawner;

    private Pane attaquePane;
    private double i=0;
    public VueAttaque(Pane mainPane, GenerateurEnnemis spawner){
        this.mainPane = mainPane;
        this.spawner = spawner;

    }

    public Entité afficheAttaque(Joueur joueur, Bulle bulle) {
        Entité ennemiTouché = null;
        attaquePane=new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/test.png").toURI().toString()));


        Circle circle = new Circle(16,16,1);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.RED);
        attaquePane.getChildren().add(circle);
        System.out.println("bulleOpening");
        Timeline bulleOpening = new Timeline(
                new KeyFrame(Duration.millis(20), ev -> {
                    circle.setRadius(circle.getRadius() + 1);
                })
        );
        bulleOpening.setCycleCount(20);
        attaquePane.translateXProperty().bind(bulle.posXProperty());
        attaquePane.translateYProperty().bind(bulle.posYProperty());
        bulleOpening.play();
        bulleOpening.setOnFinished(e -> {

        });

        attaquePane.setId("attaque");
        mainPane.getChildren().add(attaquePane);


        for(Entité sl:spawner.getListeEntite()){


            if(joueur.enContact(attaquePane.getTranslateX(),attaquePane.getTranslateY(),sl)) {
                ennemiTouché=sl;
            }
        }


        bulle.getEstENVIEProperty().addListener(new BulleObs(bulle,this));

        return  ennemiTouché;
    }
    public void removeAttaque(){
        mainPane.getChildren().remove(attaquePane);

    }
}