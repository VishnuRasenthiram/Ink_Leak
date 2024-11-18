package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class Poing extends Pouvoirs {

    public Poing(Environnement environnement) {
        super("poing",2, 64, 64, 1,true,environnement);
    }

    @Override
    public void déplacement(String déplacementDirection) {
        super.setPosition();
        setEstEnVie(true);
        super.setOrientation(déplacementDirection);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(20), ev -> {
                    if(this.getEstEnVie()) {
                        for (Entité sl : super.getGenerateurEnnemis().getListeEntite()) {
                            if (this.enContact(sl)) {
                                sl.prendre_degat(super.getAttaque_entite());
                            }
                        }
                        PauseTransition pause = new PauseTransition(Duration.millis(200));
                        pause.setOnFinished(event -> setEstEnVie(false));
                        pause.play();



                    }
                })
        );

        timeline.setCycleCount(30);
        timeline.play();

        timeline.setOnFinished(e -> {
            super.action();
            setEstEnVie(false);
        });
    }



}
