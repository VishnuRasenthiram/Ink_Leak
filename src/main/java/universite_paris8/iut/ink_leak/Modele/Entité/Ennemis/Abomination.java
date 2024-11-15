package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Environnement;

import java.util.List;

import static universite_paris8.iut.ink_leak.Modele.AEtoile.chercherChemin;

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

    public void déplacement(String direction) {
        int startX = coorDansLeTableauX(getPosX() + 128);
        int startY = coorDansLeTableauY(getPosY() + 150);
        int targetX = joueur.coorDansLeTableauX(joueur.getPosX() + 16);
        int targetY = joueur.coorDansLeTableauY(joueur.getPosY() + 16);

        List<Integer> path = chercherChemin(getEnvironnement().getMap().getMap(), startX, startY, targetX, targetY);


        if (path != null && !path.isEmpty()) {
            if (path.size() > 6) {
                attaque_proche();
            } else {
                attaque_loin();
            }

        }

    }

    public void attaque_proche() {
        setPhaseProperty(1);
    }

    public void attaque_loin() {
        setPhaseProperty(2);
    }


    public void setPhaseProperty(int phase) {
        this.phaseProperty.set(phase);
    }

    public IntegerProperty getPhaseProperty() {
        return phaseProperty;
    }


}


