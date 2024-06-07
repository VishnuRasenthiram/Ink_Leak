package universite_paris8.iut.ink_leak.Controller;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Slime;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;

public class VieEnnemisObs implements ChangeListener<Number> {

    public Entité entité;

    public Pane ennemisView;
    public Joueur joueur;
    public VieEnnemisObs(Pane ennemis, Entité entité, Joueur joueur) {

        this.entité = entité;
        this.ennemisView = ennemis;
        this.joueur = joueur;


    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number old, Number nouv) {
        if ((int) nouv < (int) old) {
             if(entité instanceof Slime) {
                String direction = joueur.getOrientationProperty().toString();
                System.out.println(direction);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(ennemisView.translateXProperty(), 0.3)),
                        new KeyFrame(Duration.seconds(0.15), new KeyValue(ennemisView.opacityProperty(), 0.5)),
                        new KeyFrame(Duration.seconds(0.24), new KeyValue(ennemisView.opacityProperty(), 0.3)),
                        new KeyFrame(Duration.seconds(0.33), new KeyValue(ennemisView.opacityProperty(), 0.5)),
                        new KeyFrame(Duration.seconds(0.42), new KeyValue(ennemisView.opacityProperty(), 0.3)),
                        new KeyFrame(Duration.seconds(0.51), new KeyValue(ennemisView.opacityProperty(), 0.5)),
                        new KeyFrame(Duration.seconds(0.58), new KeyValue(ennemisView.opacityProperty(), 1))
                );
                timeline.setCycleCount(2);
                timeline.play();
            }
        }


    }

}
