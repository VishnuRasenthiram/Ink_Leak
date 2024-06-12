package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Bulle extends Pouvoirs{

        private int portée;

        private IntegerProperty estENVIEProperty;
        private static int cpt=0;
        public Bulle(Map map,GenerateurEnnemis liste_entites, Joueur j) {

            super("bulle"+cpt, 1, 32, 32, 3, map, liste_entites,j);
            this.portée = 12;
            this.estENVIEProperty = new SimpleIntegerProperty(1);
            cpt++;

        }

        public int getPortée() {
            return portée;
        }

        public void setPortée(int portée) {
            this.portée = portée;
        }




    public IntegerProperty getEstENVIEProperty() {
        return estENVIEProperty;
    }

    @Override
    public void déplacement(String déplacementDirection) {
        super.setPosition();
        estENVIEProperty.set(1);
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
            estENVIEProperty.setValue(0);
        });
    }




}
