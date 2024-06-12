package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Poing extends Pouvoirs {

    public Poing(Map map, GenerateurEnnemis generateurEnnemis, Joueur joueur) {

        super("poing",2, 64, 64, 1,true, map, generateurEnnemis,joueur);

    }



    @Override
    public void déplacement(String déplacementDirection) {
        super.setPosition();
        setEstEnVie(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(20), ev -> {
                    double x = super.getPosX();
                    double y = super.getPosY();
                    int vitesse_joueur = super.getVitesse_entite();
                    switch (déplacementDirection) {
                        case "N":
                            if (super.peutAller(x, y - vitesse_joueur, super.getMap())) {
                                super.setPosYProperty(super.getPosY() - vitesse_joueur);
                            }
                            break;

                        case "S":
                            if (super.peutAller(x, y + vitesse_joueur, super.getMap())) {
                                super.setPosYProperty(super.getPosY() + vitesse_joueur);
                            }
                            break;

                        case "O":
                            if (super.peutAller(x - vitesse_joueur, y, super.getMap())) {
                                super.setPosXProperty(super.getPosX() - vitesse_joueur);
                            }
                            break;
                        case  "E":
                            if (super.peutAller(x + vitesse_joueur, y, super.getMap())) {
                                super.setPosXProperty(super.getPosX() + vitesse_joueur);
                            }
                            break;
                    }
                    for(Entité sl:super.getGenerateurEnnemis().getListeEntite()){

                        if(this.enContact(sl)) {
                            sl.prendre_degat(super.getAttaque_entite());
                        }
                    }
                })
        );

        timeline.setCycleCount(30);
        timeline.play();
        timeline.setOnFinished(e -> {
            setEstEnVie(false);
        });
    }



}
