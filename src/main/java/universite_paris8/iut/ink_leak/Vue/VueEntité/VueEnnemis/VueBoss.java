package universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VueBoss extends VueEntite {

    private Joueur joueur;
    private Map map;
    private Timeline animationTimeline;
    private Timeline projectileTimeline;
    private List<Circle> projectiles = new ArrayList<>();
    private List<Circle> circles = new ArrayList<>();
    private Random random = new Random();

    private int iteration = 1;

    public VueBoss(Pane mainPane, Joueur joueur, Map map) {
        super(mainPane);
        this.joueur = joueur;
        this.map = map;
    }

    @Override
    public void créeSprite(Entité entité) {
    }

    private void stopAttaque1() {
        if (animationTimeline != null) {
            animationTimeline.stop();
            animationTimeline = null;
            for (Circle circle : circles) {
                super.getMainPane().getChildren().remove(circle);
            }
            circles.clear();
        }
    }

    private void stopAttaque2() {
        if (projectileTimeline != null) {
            projectileTimeline.stop();
            projectileTimeline = null;
            for (Circle projectile : projectiles) {
                super.getMainPane().getChildren().remove(projectile);
            }
            projectiles.clear();
        }
    }

    public void créeAttaque1(Entité entité) {
        stopAttaque2();

        double centerX = entité.getPosX()+entité.getLongueur()/2;
        double centerY = entité.getPosY()+entité.getLargeur()/1.5;
        double radius = 140;
        int numCircles = 4;

        for (int i = 0; i < numCircles; i++) {
            Circle circle = new Circle(10, Color.BLUE);
            circles.add(circle);
            super.getMainPane().getChildren().add(circle);
        }

        animationTimeline = new Timeline(new KeyFrame(Duration.millis(190), event -> {
            for (int i = 0; i < numCircles; i++) {
                double angle = 2 * Math.PI * i / numCircles + iteration * 0.1;
                double x = centerX + radius * Math.cos(angle);
                double y = centerY + radius * Math.sin(angle);
                circles.get(i).setCenterX(x);
                circles.get(i).setCenterY(y);
            }
            iteration++;
        }));

        animationTimeline.setCycleCount(Timeline.INDEFINITE);
        animationTimeline.play();
    }

    public void créeAttaque2(Entité entité) {
        stopAttaque1(); // Arrêter et supprimer l'attaque 1

        projectileTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            double startX = entité.getPosX()+entité.getLongueur()/2;
            double startY = entité.getPosY()+entité.getLargeur()/2;

            Circle projectile = new Circle(5, Color.RED);
            projectile.setCenterX(startX);
            projectile.setCenterY(startY);
            super.getMainPane().getChildren().add(projectile);
            projectiles.add(projectile);

            // Générer une direction aléatoire
            double angle = 2 * Math.PI * random.nextDouble();
            double speed = 2; // Vitesse du projectile
            double dx = speed * Math.cos(angle);
            double dy = speed * Math.sin(angle);

            // Définir l'animation du projectile
            Timeline moveTimeline = new Timeline(new KeyFrame(Duration.millis(20), moveEvent -> {
                projectile.setCenterX(projectile.getCenterX() + dx);
                projectile.setCenterY(projectile.getCenterY() + dy);
            }));
            moveTimeline.setCycleCount(Timeline.INDEFINITE);
            moveTimeline.play();
        }));

        projectileTimeline.setCycleCount(Timeline.INDEFINITE);
        projectileTimeline.play();
    }
}
