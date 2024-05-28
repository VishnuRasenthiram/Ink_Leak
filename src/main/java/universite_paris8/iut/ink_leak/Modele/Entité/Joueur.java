package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Joueur extends Entité{
        private static ScheduledExecutorService executorService;
        private int taille_joueur;
        protected long invincibilite;
        protected long dernier_degat;

        public Joueur(String nom_joueur, int vie_joueur, int attaque_joueur, int taille_joueur, int vitesse_joueur) {
            super(nom_joueur, vie_joueur, attaque_joueur, vitesse_joueur, taille_joueur);
            this.taille_joueur = taille_joueur;
            this.invincibilite = 1000;
            this.dernier_degat = -invincibilite;

        }

        public int getTaille_joueur() {
            return taille_joueur;
        }


    @Override
    public void déplacement(Pane mainPane) {
        try {
            Pane circle = (Pane) mainPane.lookup("#LePlayer");
            int vitesse_joueur = super.getVitesse_entite();
            mainPane.setOnKeyPressed(e -> {
                System.out.println(e);
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
                            System.out.println("attaque");
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

    public void prendre_degat(int degat) {
            if (this.vie_entiteProperty.getValue() - degat < 0) { this.vie_entiteProperty.setValue(0); }
            if (System.currentTimeMillis() - dernier_degat >= invincibilite) {
                this.setVie_entiteProperty(this.vie_entiteProperty.getValue() - degat);
                this.dernier_degat = System.currentTimeMillis();
                System.out.println(this.vie_entiteProperty.getValue());
            }
        }

        public void gagner_vie(int nb_vie_gagnee) {
            if (this.getVie_entiteProperty().getValue() + nb_vie_gagnee > 6) { this.vie_entiteProperty.setValue(6); }
            else this.setVie_entiteProperty(vie_entiteProperty.getValue() + nb_vie_gagnee);
        }

    }