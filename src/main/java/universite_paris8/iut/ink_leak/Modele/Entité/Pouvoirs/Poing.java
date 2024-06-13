package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Poing extends Pouvoirs {

    private IntegerProperty estENVIEProperty;
    private static int cpt=0;
    public Poing(Map map, GenerateurEnnemis spawner, Joueur j) {

        super("poing"+cpt,2, 64, 64, 1, map, spawner,j);
        this.estENVIEProperty = new SimpleIntegerProperty(1);
        cpt++;

    }

    public IntegerProperty getEstENVIEProperty() {
        return estENVIEProperty;
    }

    @Override
    public void déplacement(String déplacementDirection) {
        super.setPosition(48);
        estENVIEProperty.set(1);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(30), ev -> {
                    getJoueur().setBougable(false);
                    for(Entité sl:super.getlisteEntite().getListeEntite()){

                        if(this.enContact(sl)) {
                            sl.prendre_degat(super.getAttaque_entite());
                        }
                    }
                })
        );

        timeline.setCycleCount(30);
        timeline.play();
        timeline.setOnFinished(e -> {
            getJoueur().setBougable(true);
            estENVIEProperty.setValue(0);
        });
    }
}
