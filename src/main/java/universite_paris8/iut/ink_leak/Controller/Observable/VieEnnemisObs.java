package universite_paris8.iut.ink_leak.Controller.Observable;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Main;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Slime;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Vue.Musique;
import universite_paris8.iut.ink_leak.Modele.Map;

public class VieEnnemisObs implements ChangeListener<Number> {

    public Entité entité;


    public Pane ennemisView;
    public Joueur joueur;
    private Map map;
    public VieEnnemisObs(Pane ennemis, Entité entité, Joueur joueur, Map map) {

        this.entité = entité;
        this.ennemisView = ennemis;
        this.joueur = joueur;
        this.map = map;


    }
    private ColorAdjust colorAdjust;

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number old, Number nouv) {
        colorAdjust = new ColorAdjust();

        if ((int) nouv < (int) old) {
            if (entité instanceof Slime) {
                String direction = joueur.getOrientation().toString();

                String direction = joueur.getOrientationProperty().toString();

                colorAdjust.setBrightness(5);
                ennemisView.setEffect(colorAdjust);

                Timeline timeline = new Timeline();

                switch (direction) {
                    case "N":

                        for (int i = 0; i < 10; i++) {
                            double timeFraction = (double)i / 9;
                            double translation = (1 - Math.pow(1 - timeFraction, 2)) * 30;
                            if(entité.peutAller(entité.getPosX(),entité.getPosY() - translation,map)) {
                                KeyFrame keyFrame = new KeyFrame(Duration.seconds(timeFraction * 0.4),
                                        new KeyValue(entité.posYProperty(), entité.getPosY() - translation),
                                        new KeyValue(colorAdjust.brightnessProperty(), i >= 5 ? 0 : colorAdjust.getBrightness())
                                );
                                timeline.getKeyFrames().add(keyFrame);
                            }
                        }
                        break;

                    case "O":
                        for (int i = 0; i < 10; i++) {
                            double timeFraction = (double)i / 9;
                            double translation = (1 - Math.pow(1 - timeFraction, 2)) * 30;
                            if(entité.peutAller(entité.getPosX()- translation,entité.getPosY() ,map)) {
                                KeyFrame keyFrame = new KeyFrame(Duration.seconds(timeFraction * 0.4),
                                        new KeyValue(entité.posXProperty(), entité.getPosX() - translation),
                                        new KeyValue(colorAdjust.brightnessProperty(), i >= 5 ? 0 : colorAdjust.getBrightness())
                                );
                                timeline.getKeyFrames().add(keyFrame);
                            }
                        }
                        break;
                    case "E":
                        for (int i = 0; i < 10; i++) {
                            double timeFraction = (double)i / 9;
                            double translation = (1 - Math.pow(1 - timeFraction, 2)) * 30;
                            if(entité.peutAller(entité.getPosX()+ translation,entité.getPosY() ,map)) {
                                KeyFrame keyFrame = new KeyFrame(Duration.seconds(timeFraction * 0.4),
                                        new KeyValue(entité.posXProperty(), entité.getPosX() + translation),
                                        new KeyValue(colorAdjust.brightnessProperty(), i >= 5 ? 0 : colorAdjust.getBrightness())
                                );
                                timeline.getKeyFrames().add(keyFrame);
                            }
                        }
                        break;
                    case "S":
                        for (int i = 0; i < 10; i++) {
                            double timeFraction = (double)i / 9;
                            double translation = (1 - Math.pow(1 - timeFraction, 2)) * 30;
                            if(entité.peutAller(entité.getPosX(),entité.getPosY() + translation,map)) {


                                KeyFrame keyFrame = new KeyFrame(Duration.seconds(timeFraction * 0.4),
                                        new KeyValue(entité.posYProperty(), entité.getPosY() + translation),
                                        new KeyValue(colorAdjust.brightnessProperty(), i >= 5 ? 0 : colorAdjust.getBrightness())
                                );
                                timeline.getKeyFrames().add(keyFrame);
                            }

                        }
                }

                timeline.setCycleCount(1);
                timeline.play();
                timeline.setOnFinished(event -> colorAdjust.setBrightness(0));

            }
        }
    }


}
