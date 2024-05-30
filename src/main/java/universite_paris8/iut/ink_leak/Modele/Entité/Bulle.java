package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Bulle extends Entité {

        private int portée;
        private int dégât;
        private Joueur joueur;

        private IntegerProperty estENVIEProperty;

        public Bulle(Map map,GenerateurEnnemis spawner, Joueur j) {
            super("bulle",0, 0, 6, 2, 0, map, spawner);
            this.portée = portée;
            this.joueur = j;
            setPosition();
            this.estENVIEProperty = new SimpleIntegerProperty(1);

        }
        private void setPosition(){
            if (joueur.getOrientationProperty() == "N") {
                super.setPosXProperty(joueur.getPosX());
                super.setPosYProperty(joueur.getPosY() - 32);
            }
            else if (joueur.getOrientationProperty() == "S") {
                super.setPosXProperty(joueur.getPosX());
                super.setPosYProperty(joueur.getPosY() + 32);
            }
            else if (joueur.getOrientationProperty() == "E") {
                super.setPosXProperty(joueur.getPosX() + 32);
                super.setPosYProperty(joueur.getPosY());
            }
            else {
                super.setPosXProperty(joueur.getPosX() - 32);
                super.setPosYProperty(joueur.getPosY());
            }
        }
        public int getPortée() {
            return portée;
        }

        public void setPortée(int portée) {
            this.portée = portée;
        }

        public int getDégât() {
            return dégât;
        }

        public void setDégât(int dégât) {
            this.dégât = dégât;
        }


    @Override
    public void attaque(Entité entitéAttaqué) {

    }

    public IntegerProperty getEstENVIEProperty() {
        return estENVIEProperty;
    }

    @Override
    public void déplacement(String déplacementDirection) {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(20), ev -> {
                    double x = super.getPosX();
                    double y = super.getPosY();
                    int vitesse_joueur = super.getVitesse_entite();
                    switch (déplacementDirection) { // 0=Z 1=S 2=Q 3=D
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
                })
        );
        //atta
        timeline.setCycleCount(30);
        timeline.play();
        timeline.setOnFinished(e -> {
            estENVIEProperty.setValue(0);
        });
    }

    @Override
    public void gagner_vie(int nb_vie_gagnee) {

    }

    // autres méthodes spécifiques à VueAttaque
}
