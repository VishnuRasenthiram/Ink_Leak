package universite_paris8.iut.ink_leak.Controller;

import javafx.fxml.FXML;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.scene.Node;

import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static universite_paris8.iut.ink_leak.Controller.MapController.character;

public class PlayerMoove {
    private int PlayerSpeed;
    @FXML
    private TilePane tuileMap;

    @FXML
    private Pane PlayerID;
    private static ScheduledExecutorService executorService;
    public static int getCharacterSpeed() {
        return character.getSpeed();
    }
    private static int getCharacterSize() {
        return character.getSize();
    }

    public void move() {

        Circle circle = (Circle) PlayerID.lookup("#LePlayer");
        PlayerSpeed = getCharacterSpeed();

        PlayerID.setOnKeyPressed(e -> {

            if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(new Runnable() { // c'est une boucle qui s'active a l'appui d'une touche et ne s'arrete que si on relache la touche
                    double x = circle.localToScene(circle.getBoundsInLocal()).getMinX();
                    double y = circle.localToScene(circle.getBoundsInLocal()).getMinY();
                    @Override
                    public void run() {
                        System.out.println("x : " + x + " y : " + y);
                        x = circle.localToScene(circle.getBoundsInLocal()).getMinX();
                        y = circle.localToScene(circle.getBoundsInLocal()).getMinY();
                        if (caseBleue(x, y)) {
                            character.HealthProperty().setValue(character.HealthProperty().get() - 1);
                        }
                        if (e.getCode() == KeyCode.UP) {
                            if(peutAller(x,y - PlayerSpeed))
                                circle.setTranslateY(circle.getTranslateY() - PlayerSpeed);
                        } else if (e.getCode() == KeyCode.DOWN) {
                            if(peutAller(x,y + PlayerSpeed))
                                circle.setTranslateY(circle.getTranslateY() + PlayerSpeed);
                        } else if (e.getCode() == KeyCode.LEFT) {
                            if(peutAller(x - PlayerSpeed,y))
                                circle.setTranslateX(circle.getTranslateX() - PlayerSpeed);
                        } else if (e.getCode() == KeyCode.RIGHT) {
                            if(peutAller(x + PlayerSpeed,y))
                                circle.setTranslateX(circle.getTranslateX() + PlayerSpeed);

                        }
                    }
                }, 0, 5, TimeUnit.MILLISECONDS); // un delay entre les mouvements
        });
    }
    @FXML
    protected void stop() {
        if (executorService != null) {
            executorService.shutdownNow();
            executorService = null;
        }
    }

    private boolean caseBleue(double x, double y) {
        double radius = getCharacterSize();
        Circle circle = (Circle) PlayerID.lookup("#LePlayer");
        for (Node tuile : tuileMap.getChildren()) {
            if (Objects.equals(tuile.getId(), "bleu")) {
                double xb = tuile.localToScene(tuile.getBoundsInLocal()).getMinX();
                double yb = tuile.localToScene(tuile.getBoundsInLocal()).getMinY();
                double width = tuile.localToScene(tuile.getBoundsInLocal()).getWidth();
                double height = tuile.localToScene(tuile.getBoundsInLocal()).getHeight();

                xb -= width/1.6;
                yb -= width/1.6;

                if (x + radius >= xb  && x - radius <= xb + width  && y + radius >= yb && y - radius <= yb + height) {
                    System.out.println("Dégâts");
                    return false;
                }

            }
        }

        return true;
    }
    private boolean peutAller(double x, double y) {
        double radius = getCharacterSize();
        Circle circle = (Circle) PlayerID.lookup("#LePlayer");
        for (Node tuile : tuileMap.getChildren()) {
            if (Objects.equals(tuile.getId(), "rouge")) {
                double xb = tuile.localToScene(tuile.getBoundsInLocal()).getMinX();
                double yb = tuile.localToScene(tuile.getBoundsInLocal()).getMinY();
                double width = tuile.localToScene(tuile.getBoundsInLocal()).getWidth();
                double height = tuile.localToScene(tuile.getBoundsInLocal()).getHeight();

                xb -= width/1.6;
                yb -= width/1.6;

                if (x + radius >= xb  && x - radius <= xb + width  && y + radius >= yb && y - radius <= yb + height) {
                    System.out.println("Collision");
                    return false;
                }

            }
        }

        return true;
    }
}
