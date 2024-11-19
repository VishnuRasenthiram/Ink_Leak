package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.Activeur.ActiveurMob;
import universite_paris8.iut.ink_leak.Modele.Generateurs.Activeur.ActiveurObjets;
import universite_paris8.iut.ink_leak.Modele.Generateurs.Ennemis.*;
import universite_paris8.iut.ink_leak.Modele.Generateurs.Murs.GenerateurMurs;
import universite_paris8.iut.ink_leak.Modele.Generateurs.Objets.*;


public class Environnement {
    private static Environnement uniqueInstance=null;
    private Joueur joueur;

    private GenerateurEnnemis generateurEnnemis;
    private GenerateurObjets generateurObjets;
    private GenerateurMurs generateurMurs;
    private ActiveurMob activeurMob;
    private ActiveurObjets activeurObjet;
    private Map map;



    private Environnement(Map map) {
        this.joueur =  Joueur.getInstance("Entity",this);
        joueur.setEmplacement(8, 10);
        this.map = map;



        this.generateurEnnemis = new GenMapDepart(this);
        this.generateurObjets = new GenObjMapDepart(this);
        this.generateurMurs =new GenerateurMurs(this);
        this.activeurMob = new ActiveurMob(this);
        this.activeurObjet = new ActiveurObjets(this);
    }

    public static Environnement getInstance(Map map) {
        if(uniqueInstance==null) {
            uniqueInstance= new Environnement(map);
        }
        return uniqueInstance;
    }

    public void action(int temps) {
        if(temps ==1){
            generateurMurs.genererMurs();
            generateurObjets.genererObjets();
        }

        if (temps % 200 == 0) {
            generateurEnnemis.genererEnnemis(); }
        if (temps % 2 == 0) {
           activeurMob.activerMob();
        }

        if(generateurObjets.getListeObjets()!=null){
            activeurObjet.activerObjets();
        }

    }
    public void changementDeMap(int interaction){
        tuerToutLesEnnemis();
        enleverTousLesObjets();
        map.setMap(porteToMap(interaction));

        joueur.setBougable(false);

        generateurMurs.EnleverToutLesMurs();
        generateurMurs.genererMurs();
        generateurObjets.genererObjets();
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> joueur.setBougable(true));
        pause.play();
    }

    public int porteToMap(int interaction){
        switch (interaction){
            case 6:
                joueur.setEmplacement(9,11);

                return 1;
            case 22:
                joueur.setEmplacement(0,19);
                generateurEnnemis = new GenMapEau(this);
                generateurObjets= new GenObjMapEau(this);
                return 2;
            case 24:
                joueur.setEmplacement(19,0);
                generateurEnnemis = new GenMapGlace(this);
                generateurObjets = new GenObjMapGlace(this);
                return 3;
            case 25:
                joueur.setEmplacement(19,19);
                generateurEnnemis = new GenMapLabyrinthe(this);
                generateurObjets = new GenObjMapLabyrinthe(this);
                return 4;
            case 26:
                joueur.setEmplacement(10,19);
                generateurEnnemis = new GenBossMap(this);
                return 5;
            default:
                joueur.setEmplacement(8,10);
                return 0;
        }
    }

    private void tuerToutLesEnnemis(){
        generateurEnnemis.TuerToutLesEnnemis();
    }
    private void enleverTousLesObjets(){
        generateurObjets.EnleverToutLesObjets();
    }
    public Joueur getJoueur() { return this.joueur; }

    public GenerateurEnnemis getGenerateurEnnemis() { return this.generateurEnnemis; }

    public GenerateurObjets getGenerateurObjets() {
        return generateurObjets;
    }
    public GenerateurMurs getGenerateurMurs() {
        return generateurMurs;
    }



    public Map getMap() {
        return map;
    }
}
