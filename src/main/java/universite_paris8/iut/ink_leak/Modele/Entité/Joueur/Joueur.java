package universite_paris8.iut.ink_leak.Modele.Entité.Joueur;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Joueur extends Entité {
        private static ScheduledExecutorService executorService;
        private ObservableList<Pouvoirs> listePouvoirs;
        private IntegerProperty pouvoirEnCoursProperty;
        private boolean bougable;
        private IntegerProperty oppacitéProperty;

        public Joueur(String nom_joueur,Map map, GenerateurEnnemis spawner) {
            super(nom_joueur,  6, 1, 30, 1,1000,map,spawner);
            this.listePouvoirs= FXCollections.observableArrayList();
            this.pouvoirEnCoursProperty = new SimpleIntegerProperty(0);
            this.bougable = true;
            this.oppacitéProperty = new SimpleIntegerProperty(1);
        }
        public IntegerProperty getOppacitéProperty() {
            return oppacitéProperty;
        }
        public void setOppacitéProperty(int oppacité) {
            this.oppacitéProperty.set(oppacité);
        }
        public boolean getBougable(){
            return bougable;
        }
        public void setBougable(boolean bougable){
            this.bougable = bougable;
        }
    public ObservableList<Pouvoirs> getListePouvoirs() {
        return listePouvoirs;
    }

    public IntegerProperty getPouvoirEnCoursProperty() {return pouvoirEnCoursProperty;}

    public Pouvoirs getPouvoirEnCours(){
            return this.listePouvoirs.get(pouvoirEnCoursProperty.getValue());
    }

    @Override
    public void attaque(Entité entitéAttaqué) {

        if(entitéAttaqué!=null){
            entitéAttaqué.prendre_degat(1);
        }
    }

    public void attaqueAvecPouvoir(Entité entitéAttaqué, Pouvoirs pouvoir){

    }



    public void déplacement(String déplacementDirection) {
        try {
            int vitesse_joueur = super.getVitesse_entite();


            if (getBougable() == false) {
                stop();
                return;
            }
            if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> {
                        double x = super.getPosX();
                        double y = super.getPosY();

                        switch (déplacementDirection) { // 0=Z 1=S 2=Q 3=D
                            case "N":
                                if (super.peutAller(x, y - vitesse_joueur, super.getMap())) {
                                    super.setPosYProperty(super.getPosY() - vitesse_joueur);
                                    super.setOrientationProperty("N");
                                }
                                break;

                            case "S":
                                if (super.peutAller(x, y + vitesse_joueur, super.getMap())) {
                                    super.setPosYProperty(super.getPosY() + vitesse_joueur);
                                    super.setOrientationProperty("S");
                                }
                                break;

                            case "O":
                                if (super.peutAller(x - vitesse_joueur, y, super.getMap())) {
                                    super.setPosXProperty(super.getPosX() - vitesse_joueur);
                                    super.setOrientationProperty("O");
                                }
                                break;
                            case "E":
                                if (super.peutAller(x + vitesse_joueur, y, super.getMap())) {
                                    super.setPosXProperty(super.getPosX() + vitesse_joueur);
                                    super.setOrientationProperty("E");
                                }
                                break;
                        }

                    });
                }, 0, 5, TimeUnit.MILLISECONDS); // un delay entre les mouvements
            setMovementState(Joueur.MovementState.WALK);

            ;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void stop(){
        if (executorService != null) {
            executorService.shutdownNow();
            executorService = null;
            setMovementState(Joueur.MovementState.IDLE);

        }
    }



        public void gagner_vie(int nb_vie_gagnee) {
            if (this.getVie() + nb_vie_gagnee > 6) { this.setVie_entite(6); }
            else this.setVie_entite(this.getVie() + nb_vie_gagnee);
        }

    }