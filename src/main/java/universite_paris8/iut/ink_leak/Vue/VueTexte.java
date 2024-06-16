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
        return Math.round(env.getJoueur().getPosX())/32 == x && Math.round(env.getJoueur().getPosY())/32 == y;
    }

    public void afficherTexte() {
        this.txt.setVisible(false);
        this.txt.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-font-size: 16; -fx-text-fill: white; -fx-text-alignment: center");
        this.txt.setPrefWidth(400);
        this.txt.setPrefHeight(200);
        this.txt.setTranslateX(120);
        this.txt.setTranslateY(215);
        this.txt.setWrapText(true);

        switch (env.getJoueur().getMap().getNumMap()){
            case 0:
                afficherTexteImprimante();
                if (verifierPositionJoueur(3, 5)) {
                    this.txt.setVisible(true);
                }
                break;
            case 2:
                afficherTextePoing();
                if (verifierPositionJoueur(1, 0)) {
                    this.txt.setVisible(true);
                }
                break;
            case 3:
                afficherTexteBulle();
                if (verifierPositionJoueur(8, 8)) {
                    this.txt.setVisible(true);
                }
                break;
        }


    }

    private void afficherTextePoing(){
        this.txt.setText("Avec ces gros Poings de Liane, on ne pourrait pas détruire ces murs qui bloquent la sortie?!");
    }

    private void afficherTexteBulle(){
        this.txt.setText("Ces bulles devraient permettre de déplacer des caisses en bois !");

    }

    private void afficherTexteImprimante(){
        this.txt.setText("S'il te manque des points de vie, n'hésite pas à taper ces imprimantes pour en récupérer ! Tu peux aussi en récupérer en passant sur des fioles, mais tu ne gagneras pas grand-chose !");

    }


}
