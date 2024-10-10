package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class Abomination extends Ennemi {

    private Timeline animationTimeline;
    private int iteration = 1;

    private IntegerProperty phaseProperty;

    public Abomination(Environnement environnement) {

        super("Abomination" + cpt, 30, 256, environnement, environnement.getJoueur());
        this.phaseProperty = new SimpleIntegerProperty(1);
    }

    @Override
    public void attaque() {
        animationTimeline = new Timeline();
        animationTimeline.setCycleCount(100);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.4), e -> {
            this.setLongueur(256 + iteration);
            this.setLargeur(256 + iteration);
            if (iteration == 50) iteration--;
            else iteration++;
        });
        animationTimeline.getKeyFrames().add(keyFrame);
        animationTimeline.play();
    }

    public void attaque_proche() {
        setPhaseProperty(1);
    }

    public void attaque_loin() {
        setPhaseProperty(2);
    }


    @Override
    public void gagner_vie(int nb_vie_gagnee) {

    }

    public void déplacement(String direction) {

    }

    public void setPhaseProperty(int phase) {
        this.phaseProperty.set(phase);
    }

    public IntegerProperty getPhaseProperty() {
        return phaseProperty;
    }


}


