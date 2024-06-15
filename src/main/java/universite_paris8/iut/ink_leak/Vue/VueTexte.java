package universite_paris8.iut.ink_leak.Vue;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Environnement;

import java.util.ArrayList;
import java.util.List;

public class VueTexte {

    public Environnement env;
    private List<TextWithCoords> textes;
    private Pane mainPane;

    public VueTexte(Environnement env, Pane mainPane) {
        this.env = env;
        this.textes = new ArrayList<>();
        this.mainPane = mainPane;
    }

    private static class TextWithCoords {
        Label text;
        int posX;
        int posY;

        TextWithCoords(Label text, int posX, int posY) {
            this.text = text;
            this.posX = posX;
            this.posY = posY;
        }
    }

    public void ajouterTexte(String message, double width, double height, double x, double y, int posX, int posY) {
        Label txt = new Label(message);
        txt.setVisible(false);
        txt.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-font-size: 16; -fx-text-fill: white; -fx-text-alignment: center");
        txt.setPrefWidth(width);
        txt.setPrefHeight(height);
        txt.setTranslateX(x);
        txt.setTranslateY(y);
        txt.setWrapText(true);
        txt.setId("texte");

        TextWithCoords textWithCoords = new TextWithCoords(txt, posX, posY);
        this.textes.add(textWithCoords);
        this.mainPane.getChildren().add(txt);
    }

    public void afficherTexte() {
        for (TextWithCoords textWithCoords : textes) {
            textWithCoords.text.setVisible(false);
            if (verifierPositionJoueur(textWithCoords.posX, textWithCoords.posY)) {
                textWithCoords.text.setVisible(true);

            }
        }
    }

    public boolean verifierPositionJoueur(int x, int y) {
        return env.getJoueur().coordEnIndiceGauche_Haut(env.getJoueur().getPosX()) == x && env.getJoueur().coordEnIndiceGauche_Haut(env.getJoueur().getPosY()) == y;
    }
}
