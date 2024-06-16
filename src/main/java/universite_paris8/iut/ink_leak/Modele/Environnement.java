package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurMurs;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurObjets;


public class Environnement {

    private Joueur joueur;

    private GenerateurEnnemis generateurEnnemis;
    private GenerateurObjets generateurObjets;
    private GenerateurMurs generateurMurs;

    private Map map;

    public Environnement(Joueur joueur, Map map, GenerateurEnnemis generateurEnnemis, GenerateurObjets generateurObjets, GenerateurMurs generateurMurs) {
        this.joueur = joueur;
        this.generateurEnnemis = generateurEnnemis;
        this.generateurObjets = generateurObjets;
        this.generateurMurs = generateurMurs;
        this.map = map;
    }

    public void action(int temps) {
        if(temps ==1){
            generateurMurs.genererMurs();
            generateurObjets.genererObjets();
        }

        if (temps % 100 == 0) {
            generateurEnnemis.genererEnnemis(map,joueur, null); }
        if (temps % 2 == 0) {
            generateurEnnemis.activerMob(joueur, map);
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
                return 1;
            case 22:
                joueur.setEmplacement(0,19);
                return 2;
            case 24:
                joueur.setEmplacement(19,0);
                return 3;
            case 25:
                joueur.setEmplacement(19,19);
                return 4;
            case 26:
                joueur.setEmplacement(10,19);
                return 5;
            default:
                joueur.setEmplacement(8,10);
                return 0;
        }
    }

    private void TuerToutLesEnnemis(){
        generateurEnnemis.TuerToutLesEnnemis();
    }

    public GenerateurEnnemis getGenerateurEnnemis() { return this.generateurEnnemis; }

    public Joueur getJoueur() { return this.joueur; }

}
