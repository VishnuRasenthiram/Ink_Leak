package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.AttaqueDeBase;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Langue;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;
import universite_paris8.iut.ink_leak.Modele.Generateurs.*;


public class Environnement {
    private static Environnement uniqueInstance=null;
    private Joueur joueur;

    private GenerateurEnnemis generateurEnnemis;
    private GenerateurObjets generateurObjets;
    private GenerateurMurs generateurMurs;
    private ActiveurMob ActiveurMob;
    private Map map;

    private Bulle bulle;
    private Poing poing;
    private Langue langue;
    private AttaqueDeBase attaqueDeBase;

    private Environnement(Map map) {
        this.joueur =  Joueur.getInstance("Entity",this);
        joueur.setEmplacement(8, 10);
        this.map = map;

        this.attaqueDeBase= new AttaqueDeBase(this);
        this.bulle =new Bulle( this);
        this.poing = new Poing(this);
        this.langue = new Langue(this);

        this.generateurEnnemis = new GenMap1(this);
        this.generateurObjets = new GenerateurObjets(this);
        this.generateurMurs =new GenerateurMurs(this);
        this.ActiveurMob = new ActiveurMob(this.generateurEnnemis);
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
            generateurEnnemis.genererEnnemis(null); }
        if (temps % 2 == 0) {
           ActiveurMob.activerMob();
        }

        if(generateurObjets.getListeObjets()!=null){
            generateurObjets.activerObjet();
        }

    }
    public void changementDeMap(int interaction){
        map.setMap(porteToMap(interaction));

        joueur.setBougable(false);
        TuerToutLesEnnemis();
        generateurMurs.EnleverToutLesMurs();
        generateurMurs.genererMurs();
        generateurObjets.EnleverToutLesObjets();
        generateurObjets.genererObjets();
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> joueur.setBougable(true));
        pause.play();
    }

    public int porteToMap(int interaction){
        switch (interaction){
            case 6:
                joueur.setEmplacement(9,11);
                System.out.println(generateurEnnemis);
                generateurEnnemis = new GenMap2(this);
                System.out.println(generateurEnnemis);
                return 1;
            case 22:
                joueur.setEmplacement(0,19);
                generateurEnnemis = new GenMap2(this);
                return 2;
            case 24:
                joueur.setEmplacement(19,0);
                generateurEnnemis = new GenMap3(this);
                return 3;
            case 25:
                joueur.setEmplacement(19,19);
                generateurEnnemis = new GenMap4(this);
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

    private void TuerToutLesEnnemis(){
        generateurEnnemis.TuerToutLesEnnemis();
    }
    public Joueur getJoueur() { return this.joueur; }

    public GenerateurEnnemis getGenerateurEnnemis() { return this.generateurEnnemis; }

    public GenerateurObjets getGenerateurObjets() {
        return generateurObjets;
    }
    public GenerateurMurs getGenerateurMurs() {
        return generateurMurs;
    }

    public Bulle getBulle() {
        return bulle;
    }

    public Poing getPoing() {
        return poing;
    }

    public Langue getLangue() {
        return langue;
    }

    public AttaqueDeBase getAttaqueDeBase() {
        return attaqueDeBase;
    }

    public Map getMap() {
        return map;
    }
}
