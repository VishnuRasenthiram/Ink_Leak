package universite_paris8.iut.ink_leak.Vue;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class VueTexte {

    public Environnement env;
    private Label txt;
    private Pane mainPane;

    public VueTexte(Environnement env, Label txt, Pane mainPane) {
        this.env = env;
        this.txt = txt;
        this.mainPane = mainPane;
        this.mainPane.getChildren().add(txt);
    }

    public boolean verifierPositionJoueur(int x, int y) {
        return env.getJoueur().getPosX() == x && env.getJoueur().getPosY() == y;
    }

    public void afficherTexte() {

        this.txt.setVisible(false);
        this.txt.setText("Vous avez récupéré un pouvoir ! Appuyez sur K pour l'utiliser !");
        this.txt.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-font-size: 16; -fx-text-fill: white; -fx-text-alignment: center");
        this.txt.setPrefWidth(400);
        this.txt.setPrefHeight(200);
        this.txt.setTranslateX(120);
        this.txt.setTranslateY(215);
        this.txt.setWrapText(true);

        if (verifierPositionJoueur(1, 1)) { this.txt.setVisible(true); }

    }
}
