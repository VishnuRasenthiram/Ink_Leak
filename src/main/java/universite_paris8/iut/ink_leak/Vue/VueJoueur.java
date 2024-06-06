package universite_paris8.iut.ink_leak.Vue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Controller.VieObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VueJoueur extends VueEntite{
    private Pane interfacePane;
    private Timeline animationTimeline;
    private Joueur joueur;
    private Pane Joueur;

    public VueJoueur(Pane mainPane, Pane interfacePane){
        super(mainPane);
        this.interfacePane = interfacePane;
    }

    @Override
    public void créeSprite(Entité entité) {

        joueur = (Joueur) entité;
        Joueur = new Pane();
        Joueur.setId(entité.getNom_entite());
        ImageView JoueurView= new ImageView();
        JoueurView.setFitHeight(32);
        JoueurView.setFitWidth(32);
        JoueurView.setImage(new Image(orientationToFile(entité.getOrientationProperty()).toURI().toString()));
        Joueur.getChildren().add(JoueurView);

        Joueur.translateXProperty().bind(entité.posXProperty());
        Joueur.translateYProperty().bind(entité.posYProperty());

        super.getMainPane().getChildren().add(Joueur);
    }



    public void créeSpriteVie(Joueur joueur) {

        Pane vie = new Pane();
        ImageView imageView = new ImageView();
        imageView.setFitHeight(32);
        imageView.setFitWidth(96);
        imageView.setImage((new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/Health/health_6.png").toURI().toString())));
        vie.getChildren().add(imageView);

        interfacePane.getChildren().add(vie);
        vie.setTranslateX(0);
        vie.setTranslateY(0);

        joueur.getVie_entiteProperty().addListener(new VieObs(imageView, Joueur, joueur));
    }

    public File orientationToFile(String orientation){

        switch (orientation){

            case "N":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_up.png");

            case "O":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_left.png");

            case "E":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_right.png");

            default:
                return  new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_down.png");

        }
    }
    //timeline
    //créé une gameloop timeline
    public File créeTimeline(String orientation) {
        File file;
        if (orientation.equals("N"))
            file = new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_up.png");
        else if (orientation.equals("S"))
            file = new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_down.png");
        else if (orientation.equals("E"))
            file = new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_right.png");
        else
            file = new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/idle/entity_idle_left.png");

        return file;
    }
    public List<Image> getWalkImages(String orientation) {

        List<Image> images = new ArrayList<>();

        if (orientation.equals("N")) orientation = "up";
        else if (orientation.equals("S")) orientation = "down";
        else if (orientation.equals("E")) orientation = "right";
        else orientation = "left";

        String basePath = "src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Walk/" + orientation + "/";

        for (int i = 1; i <= 4; i++) {
            File file = new File(basePath + "entity_walk_" + orientation + "_" + i + ".png");
            images.add(new Image(file.toURI().toString()));
        }

        return images;
    }
    private int currentIndex = 0;
    private String orientation;
    public void walkAnimation(Joueur joueur, Pane p) {
        animationTimeline = new Timeline();
        animationTimeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.15), e -> {
            orientation = joueur.getOrientationProperty();
            List<Image> images = getWalkImages(orientation);
            if (p == null) return;
            p.getChildren().clear();
            ImageView imageview = new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(images.get(1));  // On change cette image plus tard dans l'animation
            // Créer une animation d'images
            currentIndex = currentIndex + 1;
            if (currentIndex >= images.size()) currentIndex = 0;
            imageview.setImage(images.get(currentIndex));
            p.getChildren().add(imageview);
        });

        animationTimeline.getKeyFrames().add(keyFrame);
        animationTimeline.play();
    }
    public void stopWalkAnimation(Joueur joueur,  Pane p) {
        if (animationTimeline != null) {
            animationTimeline.stop();
            String orientation = joueur.getOrientationProperty();

            if (orientation.equals("N")) orientation = "up";
            else if (orientation.equals("S")) orientation = "down";
            else if (orientation.equals("E")) orientation = "right";
            else orientation = "left";
            p.getChildren().clear();
            String idlePath =  "src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Characters/Entity/Idle/" + "entity_idle_" + orientation + ".png";
            ImageView imageview = new ImageView();
            imageview.setFitHeight(32);
            imageview.setFitWidth(32);
            imageview.setImage(new Image(new File(idlePath).toURI().toString()));
            p.getChildren().add(imageview);
        }
    }



}
