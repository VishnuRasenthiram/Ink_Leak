package universite_paris8.iut.ink_leak.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Vue.Musique;

import java.io.File;

public class VieObs implements ChangeListener<Number> {

    public Entité joueur;
    public ImageView imageView;

    public VieObs(ImageView image, Entité entité) {

        this.joueur = entité;
        this.imageView = image;

    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number old, Number nouv) {

        imageView.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Health/health_"+joueur.getVie_entiteProperty().getValue()+".png").toURI().toString()));
        new Musique().jouer("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_MUSIC/degat.wav", 0);

    }

}
