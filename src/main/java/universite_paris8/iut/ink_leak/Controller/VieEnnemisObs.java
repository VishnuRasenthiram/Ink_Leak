package universite_paris8.iut.ink_leak.Controller;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Slime;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Vue.Musique;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import java.io.File;

public class VieEnnemisObs implements ChangeListener<Number> {

    public Entité entité;
    public ImageView imageView;
    public Pane joueurView;

    public VieEnnemisObs(ImageView image, Pane joueur, Entité entité) {

        this.entité = entité;
        this.imageView = image;
        this.joueurView = joueur;


    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number old, Number nouv) {
        if ((int) nouv < (int) old) {
             if(entité instanceof Slime) {
                String direction = joueurView.getId();
                System.out.println(direction);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(joueurView.translateXProperty(), 0.3)),
                        new KeyFrame(Duration.seconds(0.15), new KeyValue(joueurView.opacityProperty(), 0.5)),
                        new KeyFrame(Duration.seconds(0.24), new KeyValue(joueurView.opacityProperty(), 0.3)),
                        new KeyFrame(Duration.seconds(0.33), new KeyValue(joueurView.opacityProperty(), 0.5)),
                        new KeyFrame(Duration.seconds(0.42), new KeyValue(joueurView.opacityProperty(), 0.3)),
                        new KeyFrame(Duration.seconds(0.51), new KeyValue(joueurView.opacityProperty(), 0.5)),
                        new KeyFrame(Duration.seconds(0.58), new KeyValue(joueurView.opacityProperty(), 1))
                );
                timeline.setCycleCount(2);
                timeline.play();
            }
        }


    }

}
