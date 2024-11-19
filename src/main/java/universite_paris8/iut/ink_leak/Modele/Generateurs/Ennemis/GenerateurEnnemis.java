package universite_paris8.iut.ink_leak.Modele.Generateurs.Ennemis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Environnement;


public abstract class GenerateurEnnemis {
    private static GenerateurEnnemis uniqueInstance=null;
    private static ObservableList<Entité> listeEntite=FXCollections.observableArrayList();
    private final Environnement environnement;
    private Boolean bossDejaGenere =false;

    private Abomination abomination=null;

    public GenerateurEnnemis(Environnement environnement) {
        this.environnement = environnement;
    }

    public static GenerateurEnnemis getInstance() {
        return uniqueInstance;
    }
    public static void setInstance(GenerateurEnnemis generateur) {
        if (generateur != null) {
            uniqueInstance = generateur;
        }
    }

    public void TuerToutLesEnnemis() {
        listeEntite.clear();
    }

    public abstract Ennemi creeEnnemis();
    public abstract void setEnnemisPos(Entité entity);

    public void genererEnnemis() {
        if(!bossDejaGenere){
            Ennemi ennemi= creeEnnemis();
            setEnnemisPos(ennemi);
            listeEntite.add(ennemi);
        }

    }

    public ObservableList<Entité> getListeEntite() {
        return listeEntite;
    }

    public Environnement getEnvironnement() { return environnement; }
    public void setBossDejaGenere(Boolean bossDejaGenere) {
        this.bossDejaGenere = bossDejaGenere;
    }

    public void setAbomination(Abomination abomination) {
        this.abomination = abomination;
    }

    public Abomination getAbomination() {
        return abomination;
    }

}
