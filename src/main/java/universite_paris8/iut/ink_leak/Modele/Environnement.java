package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;


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
        }

        if (temps % 5 == 0) {
            generateurObjets.genererObjets();
        }
        if (temps % 100 == 0) {
            generateurEnnemis.genererEnnemis(map,joueur); }
        if (temps % 10 == 0) {
            generateurEnnemis.activerMob(joueur, map);
        }
        if(generateurMurs.getListeMurs()!=null){
            generateurMurs.activerMurs();
        }
        if(generateurObjets.getListeObjets()!=null){
            generateurObjets.activerObjet();
        }




    }
    public void changementDeMap(){
        joueur.setBougable(false);
        TuerToutLesEnnemis();
        generateurMurs.genererMurs();
        generateurObjets.EnleverToutLesObjets();
        generateurObjets.genererObjets();
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> joueur.setBougable(true));
        pause.play();
    }

    private void TuerToutLesEnnemis(){
        generateurEnnemis.TuerToutLesEnnemis();
    }

    public GenerateurEnnemis getGenerateurEnnemis() { return this.generateurEnnemis; }

    public Joueur getJoueur() { return this.joueur; }

}
