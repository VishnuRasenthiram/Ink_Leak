package universite_paris8.iut.ink_leak.Modele.Generateurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.ArrayList;
import java.util.Random;


public abstract class GenerateurEnnemis {

    private ObservableList<Entité> listeEntite;
    Abomination Abomination = null;
    private Environnement environnement;

    public GenerateurEnnemis(Environnement environnement) {
        listeEntite = FXCollections.observableArrayList();
        this.environnement = environnement;
    }


    public void TuerToutLesEnnemis() {
        listeEntite.clear();
    }

    public abstract void creeEnnemis(String nomEnnemi, Environnement environnement);


    public void genererEnnemis(Abomination Boss) {
        if (Abomination == null && Boss != null) {
            Abomination = Boss;
        }
        switch (environnement.getMap().getNumMap()) {
            case 2:
                creeEnnemis("HeadLess", environnement);
                break;
            case 3:
                creeEnnemis("Faker", environnement);
                break;
            case 4:
                creeEnnemis("Puddle", environnement);
                break;
            case 5:
                listeEntite.add(Abomination);
                Abomination.setPosXProperty(198);
                Abomination.setPosYProperty(0);
                break;
            default:
                creeEnnemis("Slime", environnement);
                break;
        }
    }

    public void setEnnemisPos(Entité enti) {

        int random = new Random().nextInt(3);
        switch (random) {
            case 0:
                enti.setPosYProperty(enti.getPosY() + 200);
                enti.setPosXProperty(enti.getPosX() + 100);
                break;
            case 1:
                enti.setPosYProperty(enti.getPosY() + 100);
                enti.setPosXProperty(enti.getPosX() + 520);
                break;

            case 2:
                enti.setPosYProperty(enti.getPosY() + 580);
                enti.setPosXProperty(enti.getPosX() + 100);

        }


    }

    public ObservableList<Entité> getListeEntite() {
        return listeEntite;
    }

}
