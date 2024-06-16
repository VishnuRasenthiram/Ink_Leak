package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Langue;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.io.File;


public class Abomination extends Entité {
    public static int cpt=0;
    public final Joueur joueur;

    private Timeline animationTimeline;
    private int iteration = 1;

    private Bulle bulle;
    private Poing poing;
    private Langue langue;
    private IntegerProperty phaseProperty;
    public Abomination(GenerateurEnnemis generateurEnnemis, Map map, Joueur joueur){

        super("Abomination"+cpt, 30, 1,256, 256,2,200,map, generateurEnnemis,null, null);
        this.joueur = joueur;
        this.phaseProperty = new SimpleIntegerProperty(1);
        bulle =new Bulle( super.getMap(),super.getGenerateurEnnemis(),joueur);
        poing = new Poing(super.getMap(), super.getGenerateurEnnemis(), joueur);
        langue = new Langue(super.getMap(), super.getGenerateurEnnemis(), joueur);
        cpt++;
    }

    @Override
    public void attaque() {
        animationTimeline = new Timeline();
        animationTimeline.setCycleCount(100);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.4), e -> {
            this.setLongueur(256+iteration);
            this.setLargeur(256+iteration);
            if (iteration == 50 ) iteration --;
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
        return;
    }

    public void setPhaseProperty(int phase) {
        this.phaseProperty.set(phase);
    }
    public IntegerProperty getPhaseProperty() {
        return phaseProperty;
    }


}


