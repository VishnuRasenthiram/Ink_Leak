package universite_paris8.iut.ink_leak.Vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Controller.BulleObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.AttaqueDeBase;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VueAttaque {

    private Pane mainPane;

    private Pane attaquePane;

    public VueAttaque(Pane mainPane){
        this.mainPane = mainPane;


    }



    public void afficheAttaque( Pouvoirs pouvoirs) {
        if(pouvoirs instanceof Bulle){
             afficheAttaqueBulle((Bulle) pouvoirs);
        } else if (pouvoirs instanceof AttaqueDeBase) {
            afficheAttaqueDeBase((AttaqueDeBase) pouvoirs);
        } else {
            System.out.println(" To do");
        }

    }
    public void afficheAttaqueDeBase(AttaqueDeBase attaqueDeBase){

        Pane attaquePane=new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/hitbox/hitbox_3.png").toURI().toString()));
        attaquePane.getChildren().add(imageview);
        attaquePane.setId("attaque");

        attaquePane.translateXProperty().bind(attaqueDeBase.posXProperty());
        attaquePane.translateYProperty().bind(attaqueDeBase.posYProperty());

        mainPane.getChildren().add(attaquePane);
        Timeline attaqueVisible = new Timeline(
                new KeyFrame(Duration.millis(200), ev -> mainPane.getChildren().remove(attaquePane))
        );
        attaqueVisible.play();

    }

    private void afficheAttaqueBulle( Bulle bulle) {

        attaquePane=new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Attack/test.png").toURI().toString()));


        Circle circle = new Circle(16,16,16);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.RED);
        attaquePane.getChildren().add(circle);




        attaquePane.translateXProperty().bind(bulle.posXProperty());
        attaquePane.translateYProperty().bind(bulle.posYProperty());



        attaquePane.setId("attaque");
        mainPane.getChildren().add(attaquePane);





        bulle.getEstENVIEProperty().addListener(new BulleObs(this));


    }




    public void removeAttaque() {
        ArrayList<Node> nodesToRemove = new ArrayList<>();
        for (Node node : mainPane.getChildren()) {
            if(node.getId()!=null){
                if (node.getId().equals("attaque")) {
                    nodesToRemove.add(node);
                }
            }

        }
        mainPane.getChildren().removeAll(nodesToRemove);
    }
}