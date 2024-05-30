package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.application.Platform;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Vue.VueAttaque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Joueur extends Entité{
        private static ScheduledExecutorService executorService;

        public Joueur(String nom_joueur,Map map, GenerateurEnnemis spawner) {
            super(nom_joueur,  6, 1, 30, 1,1000,map,spawner);

        }



    @Override
    public void attaque(Entité entitéAttaqué) {

        if(entitéAttaqué!=null){
            entitéAttaqué.prendre_degat(1);
        }
    }



    @Override
    public void déplacement(String déplacementDirection) {
        try {
            int vitesse_joueur = super.getVitesse_entite();

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
            ;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void stop(){
        if (executorService != null) {
            executorService.shutdownNow();
            executorService = null;
        }
    }



        public void gagner_vie(int nb_vie_gagnee) {
            if (this.getVie() + nb_vie_gagnee > 6) { this.setVie_entite(6); }
            else this.setVie_entite(this.getVie() + nb_vie_gagnee);
        }

    }