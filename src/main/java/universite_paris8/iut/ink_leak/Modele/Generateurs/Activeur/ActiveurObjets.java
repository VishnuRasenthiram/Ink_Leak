package universite_paris8.iut.ink_leak.Modele.Generateurs.Activeur;

import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Environnement;

import java.util.ArrayList;

public class ActiveurObjets {
    private Environnement environnement;

    public ActiveurObjets(Environnement environnement) {
        this.environnement = environnement;
    }

    public void activerObjets() {
        ArrayList<Objets> listeEnleve = new ArrayList<>();
        if (!getListeObjets().isEmpty()) {
            for (Objets objet : getListeObjets()) {
                objet.action();
                if (objet.getNbFoisRecuperable() == 0) {
                    listeEnleve.add(objet);
                }
            }
            for (Objets objets : listeEnleve) {
                getListeObjets().remove(objets);
            }
        }
    }

    private ObservableList<Objets> getListeObjets() {
        return environnement.getGenerateurObjets().getListeObjets();
    }

}