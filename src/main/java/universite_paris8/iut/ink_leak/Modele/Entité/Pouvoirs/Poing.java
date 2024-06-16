package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Poing extends Pouvoirs {

    public Poing(Map map, GenerateurEnnemis generateurEnnemis, Joueur joueur) {
        super("poing",2, 64, 64, 1,true, map, generateurEnnemis,joueur);
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

                    }
                })
        );

        timeline.setCycleCount(30);
        timeline.play();

        timeline.setOnFinished(e -> {
            super.attaque();
            setEstEnVie(false);
        });
    }



}
