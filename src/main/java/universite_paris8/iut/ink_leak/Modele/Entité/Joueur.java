package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Vue.VueAttaque;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Joueur extends Entité{
        private static ScheduledExecutorService executorService;
        private int taille_joueur;


        public Joueur(String nom_joueur, int vie_joueur, int attaque_joueur, int taille_joueur, int vitesse_joueur, GenerateurEnnemis spawner) {
            super(nom_joueur, vie_joueur, attaque_joueur,taille_joueur, vitesse_joueur,1000,spawner, false);
            this.taille_joueur = taille_joueur;

            this.dernier_degat = -invincibilite;


        }

    @Override
    public void attaque(Pane mainPane) {

    }

    public int getTaille_joueur() {
            return taille_joueur;
        }


    @Override
    public void déplacement(Pane mainPane) {
        try {

            int vitesse_joueur = super.getVitesse_entite();
            VueAttaque vA= new VueAttaque(mainPane,super.getSpawner());
            mainPane.setOnKeyPressed(e -> {

                if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> {
                        double x = super.getPosX();
                        double y = super.getPosY();


                        if (e.getCode() == KeyCode.Z) {
                            if(super.peutAller(x,y - vitesse_joueur, mainPane)) {
                                super.setPosYProperty(super.getPosY() - vitesse_joueur);
                                super.setOrientationProperty("N");
                            }
                        }
                        if (e.getCode() == KeyCode.S) {
                            if(super.peutAller(x,y + vitesse_joueur, mainPane)) {
                                super.setPosYProperty(super.getPosY() + vitesse_joueur);
                                super.setOrientationProperty("S");
                            }
                        }
                        if (e.getCode() == KeyCode.Q) {
                            if(super.peutAller(x - vitesse_joueur,y, mainPane)) {
                                super.setPosXProperty(super.getPosX() - vitesse_joueur);
                                super.setOrientationProperty("O");
                            }
                        }
                        if (e.getCode() == KeyCode.D) {
                            if(super.peutAller(x + vitesse_joueur,y, mainPane))
                            {
                                super.setPosXProperty(super.getPosX() + vitesse_joueur);
                                super.setOrientationProperty("E");
                            }
                        }
                        if (e.getCode() == KeyCode.J) {
                            if(vA.afficheAttaque(this)!= null){
                                vA.afficheAttaque(this).prendre_degat(1);
                            };
                        }
                    });
                }, 0, 5, TimeUnit.MILLISECONDS); // un delay entre les mouvements
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stop(Pane mainPane){
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