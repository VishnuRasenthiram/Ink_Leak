package universite_paris8.iut.ink_leak.Vue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Controller.VieObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur;
import java.io.File;

public class VueJoueur {
    @FXML
    private Pane mainPane;
    @FXML
    private Pane interfacePane;
    public VueJoueur(Pane mainPane, Pane interfacePane){
        this.mainPane = mainPane;
        this.interfacePane = interfacePane;

    }

    public void créeSpriteJoueur(Joueur joueur){
        Pane Joueur=new Pane();
        Joueur.setId(joueur.getNom_entite());
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(orientationToFile(joueur.getOrientationProperty()).toURI().toString()));
        Joueur.getChildren().add(imageview);
        joueur.setPosXProperty(Joueur.localToScene(Joueur.getBoundsInLocal()).getMinX());
        Joueur.translateXProperty().bind(joueur.posXProperty());
        Joueur.translateYProperty().bind(joueur.posYProperty());

        mainPane.getChildren().add(Joueur);
        Joueur.toFront();

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
        vie.setTranslateY(0);

        joueur.getVie_entiteProperty().addListener(new VieObs(imageView, joueur));

    }

    public File orientationToFile(String orientation){

        switch (orientation){

            case "N":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idles/entity_idle_up.png");

            case "O":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idles/entity_idle_left.png");

            case "E":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idles/entity_idle_right.png");

            default:
                return  new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idles/entity_idle_down.png");

        }
    }

}
