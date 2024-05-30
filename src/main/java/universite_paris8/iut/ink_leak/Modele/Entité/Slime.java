package universite_paris8.iut.ink_leak.Modele.Entité;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Slime extends Entité{
    public static int cpt=0;
    private static ScheduledExecutorService executorService;

    public Slime(  GenerateurEnnemis spawner,Map map){

        super("S"+cpt, 5, 1,1, 1,200,map,spawner);
        cpt++;

    }



    @Override
    public void attaque(Entité entitéAttaqué) {

    }

    @Override
    public void déplacement(int déplacementDirection) {
        try {
            if (executorService != null) return;

            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(() -> {
                Platform.runLater(() -> {
                    int random = new Random().nextInt(4);
                    double x = super.getPosX();
                    double y = super.getPosY();


                    if (random == 0) {
                                    if(super.peutAller(x,y - 1,super.getMap())) {
                                        super.setPosYProperty(y - 1);
                                    }
                    }
                    else if (random == 1) {
                                    if(super.peutAller(x,y + 1,super.getMap())) {
                                        super.setPosYProperty(y + 1);
                                    }
                    }
                    else if (random == 2) {

                                    if(super.peutAller(x - 1,y,super.getMap())) {
                                        super.setPosXProperty(x - 1);
                                    }
                    }
                    else {
                                    if(super.peutAller(x + 1,y,super.getMap())) {
                                        super.setPosXProperty(x + 1);
                                    }
                    }

                });
            }, 0, 100, TimeUnit.MILLISECONDS); // un delay entre les mouvements
            ;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }



    @Override
    public void gagner_vie(int nb_vie_gagnee) {
        System.out.println("aaaa");
    }
}


