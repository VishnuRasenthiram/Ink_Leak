package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Langue extends Pouvoirs{

    private int i;

    public Langue(Map map, GenerateurEnnemis generateurEnnemis, Joueur joueur) {
        super("Langue", 1, 64, 32, 1, true, map, generateurEnnemis, joueur);
    }

    @Override
    public void déplacement(String déplacementDirection) {
        super.setPosition();
        setEstEnVie(true);
        super.setOrientation(déplacementDirection);
        i=11;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(20), ev -> {
                    double x = super.getPosX();
                    double y = super.getPosY();
                    int vitesse_joueur = super.getVitesse_entite()+i;
                    i--;
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
            super.attaque();
            setEstEnVie(false);
        });
    }
}
