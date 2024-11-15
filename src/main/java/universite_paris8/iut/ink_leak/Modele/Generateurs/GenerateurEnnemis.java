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

    public abstract void creeEnnemis();


    public void genererEnnemis(Abomination Boss) {creeEnnemis();}

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

    public Environnement getEnvironnement() { return environnement; }

}
