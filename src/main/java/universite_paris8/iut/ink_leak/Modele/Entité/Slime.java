package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Slime extends Entité{


    int temps;
    public Slime(String nom_joueur, int vie_joueur, int attaque_joueur, int taille_joueur, int vitesse_joueur){
        super(nom_joueur, vie_joueur, attaque_joueur, vitesse_joueur, taille_joueur);
    }

    @Override
    public void déplacement(Pane mainPane) {
        Timeline gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        int Speed = super.getTaille_entite();
        int random = (int) (Math.random() * 4);
        double x = super.getPosX();
        double y = super.getPosY();

        if (random == 0) {


            KeyFrame kf = new KeyFrame(
                    // on définit le FPS (nbre de frame par seconde)
                    Duration.seconds(0.01),
                    // on définit ce qui se passe à chaque frame
                    // c'est un eventHandler d'ou le lambda
                    (ev ->{
                        if(super.peutAller(x,super.getPosY() - 1, mainPane)) {
                            if (temps == Speed) {

                                gameLoop.stop();
                            }
                            super.setPosYProperty(super.getPosY() - 1);
                            temps++;
                        }
                    })
            );
            gameLoop.getKeyFrames().add(kf);
            gameLoop.play();


        }
        if (random == 1) {

            KeyFrame kf = new KeyFrame(
                    // on définit le FPS (nbre de frame par seconde)
                    Duration.seconds(0.01),
                    // on définit ce qui se passe à chaque frame
                    // c'est un eventHandler d'ou le lambda
                    (ev ->{
                        if(super.peutAller(x,super.getPosY() + 1, mainPane)) {
                            if(temps== Speed){
                                gameLoop.stop();
                            }
                            super.setPosYProperty(super.getPosY() + 1);
                            temps++;
                        }
                    })
            );
            gameLoop.getKeyFrames().add(kf);
            gameLoop.play();

        }
        if (random == 2) {

            KeyFrame kf = new KeyFrame(
                    // on définit le FPS (nbre de frame par seconde)
                    Duration.seconds(0.01),
                    // on définit ce qui se passe à chaque frame
                    // c'est un eventHandler d'ou le lambda
                    (ev ->{

                        if(super.peutAller(super.getPosX() - 1,y, mainPane)) {
                            if (temps == Speed) {
                                gameLoop.stop();
                            }
                            super.setPosXProperty(super.getPosX() - 1);

                            temps++;
                        }
                    })
            );
            gameLoop.getKeyFrames().add(kf);
            gameLoop.play();


        }
        if (random == 3) {

            KeyFrame kf = new KeyFrame(
                    // on définit le FPS (nbre de frame par seconde)
                    Duration.seconds(0.01),
                    // on définit ce qui se passe à chaque frame
                    // c'est un eventHandler d'ou le lambda
                    (ev ->{
                        if(super.peutAller(super.getPosX() + 1,y, mainPane)) {
                            if (temps == Speed) {
                                gameLoop.stop();

                            }
                            super.setPosXProperty(super.getPosX() + 1);


                            temps++;
                        }
                    })
            );
            gameLoop.getKeyFrames().add(kf);
            gameLoop.play();
        }





    }

    @Override
    public void prendre_degat(int degat) { System.out.println("bbbb"); }

    @Override
    public void gagner_vie(int nb_vie_gagnee) {
        System.out.println("aaaa");
    }
}


