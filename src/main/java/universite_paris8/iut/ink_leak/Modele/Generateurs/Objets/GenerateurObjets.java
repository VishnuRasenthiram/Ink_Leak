package universite_paris8.iut.ink_leak.Modele.Generateurs.Objets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable.ObjetBulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable.ObjetLangue;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable.ObjetPoing;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Generateurs.Ennemis.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.ArrayList;


public abstract class GenerateurObjets {
    private static GenerateurObjets uniqueInstance=null;
    private static final ObservableList<Objets> listeObjets=FXCollections.observableArrayList();
    private final Environnement environnement;

    public GenerateurObjets(Environnement environnement){
        this.environnement = environnement;
    }

    public static GenerateurObjets getInstance() {
        return uniqueInstance;
    }

    public static void setInstance(GenerateurObjets generateur) {
        if (generateur != null) {
            uniqueInstance = generateur;
        }
    }

    public void EnleverToutLesObjets(){
        listeObjets.clear();
    }

    public abstract void creeObjets(ObservableList<Objets> listeObjets);

    public void genererObjets(){
       creeObjets(listeObjets);

    }

    public ObservableList<Objets> getListeObjets() {
        return listeObjets;
    }
    public Environnement getEnvironnement() {
        return environnement;
    }
}
