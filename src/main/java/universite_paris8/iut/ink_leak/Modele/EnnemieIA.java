package universite_paris8.iut.ink_leak.Modele;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.EnnemieSpawner;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Map;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur;

import static java.lang.Thread.sleep;

public class EnnemieIA {
    private static ScheduledExecutorService executorService;
    public Entité mob;
    public static void IA(){
        //TODO
    }
    public  int getjoueurSpeed(Entité mob) {
        return mob.getCharacterSpeed();
    }
    @FXML
    public static void moove(Entité mob, Pane mainPane) {
        try {

            int Speed = mob.getCharacterSpeed() + 10;
            int random = (int) (Math.random() * 4);
                if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> {
                        double x = mob.getPosX();
                        double y = mob.getPosY();
                        //  System.out.println("x:"+ joueur.getPosX() +" y:"+ joueur.getPosY());

                        if (random == 0) {

                            if(mob.peutAller(x,y - Speed, mainPane)) {

                                mob.setPosYProperty(mob.getPosY() - Speed);
                            }
                        }
                        if (random == 1) {
                            if(mob.peutAller(x,y + Speed, mainPane)) {
                                mob.setPosYProperty(mob.getPosY() + Speed);
                            }
                        }
                        if (random == 2) {
                            if(mob.peutAller(x - Speed,y, mainPane)) {
                                mob.setPosXProperty(mob.getPosX() - Speed);
                            }
                        }
                        if (random == 3) {
                            if(mob.peutAller(x + Speed,y, mainPane))
                            {
                                mob.setPosXProperty(mob.getPosX() + Speed);
                            }
                        }
                    });
                }, 0, 5, TimeUnit.MILLISECONDS); // un delay entre les mouvements

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    protected void stop() {
        if (executorService != null) {
            executorService.shutdownNow();
            executorService = null;
        }
    }
}
