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
    public VueJoueur(Pane mainPane){
        this.mainPane = mainPane;

    }

    public void créeSpriteJoueur(Joueur joueur){
        Pane Joueur=new Pane();
        Joueur.setId(joueur.getNom_entite());
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idles/entity_idle_down.png").toURI().toString()));
        Joueur.getChildren().add(imageview);
        joueur.setPosXProperty(Joueur.localToScene(Joueur.getBoundsInLocal()).getMinX());
        Joueur.translateXProperty().bind(joueur.posXProperty());
        Joueur.translateYProperty().bind(joueur.posYProperty());

        mainPane.getChildren().add(Joueur);
        joueur.setPosYProperty(joueur.getPosY() + 50);
        joueur.setPosXProperty(joueur.getPosX() + 300);
    }

    public void créeSpriteVie(int nbVie) {

        Pane vie = new Pane();
        ImageView imageView = new ImageView();
        imageView.setFitHeight(32);
        imageView.setFitWidth(96);
        imageView.setImage((new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Health/health_"+nbVie+".png").toURI().toString())));
        vie.getChildren().add(imageView);
        vie.setId("vie");

        mainPane.getChildren().add(vie);
        vie.setTranslateX(0);
        vie.setTranslateY(0);



    }

}
