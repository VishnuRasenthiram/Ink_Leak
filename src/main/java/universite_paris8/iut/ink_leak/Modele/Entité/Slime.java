package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;

import java.util.Random;

public class Slime extends Entité{
    private Timeline gameLoop;
    public static int cpt=0;

    int temps;
    public Slime(int vie_joueur, int attaque_joueur, int taille_joueur, int vitesse_joueur, Timeline gameLoop, GenerateurEnnemis spawner){

        super("S"+cpt, vie_joueur, attaque_joueur,taille_joueur, vitesse_joueur,200,spawner, false);
        cpt++;
        this.gameLoop=gameLoop;
    }

    @Override
    public void attaque(Pane mainPane) {
    }

    @Override
    public void déplacement(Pane mainPane) {
        gameLoop.getKeyFrames().removeAll();
        KeyFrame kf;
        int random = new Random().nextInt(4);
        double x = super.getPosX();
        double y = super.getPosY();
        double ms=0.01;
        System.out.println(random);
        if (random == 0) {
             kf = new KeyFrame(
                    Duration.millis(ms),
                    (ev ->{
                        if(super.peutAller(x,super.getPosY() - 1, mainPane)) {
                            super.setPosYProperty(super.getPosY() - 1);
                        }
                    })
            );
        }
        else if (random == 1) {

             kf = new KeyFrame(
                    Duration.millis(ms),
                    (ev ->{
                        if(super.peutAller(x,super.getPosY() + 1, mainPane)) {
                            super.setPosYProperty(super.getPosY() + 1);
                        }
                    })
            );
        }
        else if (random == 2) {

             kf = new KeyFrame(
                    Duration.millis(ms),
                    (ev ->{
                        if(super.peutAller(super.getPosX() - 1,y, mainPane)) {
                            super.setPosXProperty(super.getPosX() - 1);
                        }
                    })
            );
        }
        else {

             kf = new KeyFrame(
                    Duration.millis(ms),
                    (ev ->{
                        if(super.peutAller(super.getPosX() + 1,y, mainPane)) {
                            super.setPosXProperty(super.getPosX() + 1);
                        }
                    })
            );
        }

        gameLoop.getKeyFrames().add(kf);

    }



    @Override
    public void gagner_vie(int nb_vie_gagnee) {
        System.out.println("aaaa");
    }
}


