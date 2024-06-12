package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Vue.VueMap;

public class Environnement {

    private Joueur joueur;
    private GenerateurEnnemis generateurEnnemis;
    private GenerateurObjets generateurObjets;
    private Map map;
    private VueMap vMap;

    public Environnement(Joueur joueur, Map map, GenerateurEnnemis generateurEnnemis, GenerateurObjets generateurObjets, VueMap vMap) {
        this.joueur = joueur;
        this.generateurEnnemis = generateurEnnemis;
        this.generateurObjets = generateurObjets;
        this.map = map;
        this.vMap = vMap;
    }

    public void action(int temps) {

        if (temps % 5 == 0) {
            double x = this.joueur.getPosX();
            double y = this.joueur.getPosY();
            joueur.peutAller(x, y, this.map);
            int interaction = joueur.verifierInteractionEnFace(x, y);


            if (interaction == 3 || interaction == 4) {
                joueur.setBougable(false);
                vMap.supprimerAffichageMap();
                map.setMap(interaction == 3 ? map.getNumMap() + 1 : (map.getNumMap() > 4 ? 1 : map.getNumMap() - 1));
                TuerToutLesEnnemis();
                generateurObjets.EnleverToutLesObjets();
                vMap.initMap(map, joueur);
                generateurObjets.genererObjets();
                PauseTransition pause = new PauseTransition(Duration.millis(500));
                pause.setOnFinished(event -> joueur.setBougable(true));
                pause.play();
            }
            generateurObjets.genererObjets();


        }
        if (temps % 100 == 0) { generateurEnnemis.genererEnnemis(map,joueur); }
        if(temps %5==0){
            generateurEnnemis.activerMob();
        }
        generateurObjets.activerObjet();

    }
    private void TuerToutLesEnnemis(){
        generateurEnnemis.TuerToutLesEnnemis();
    }

    public GenerateurEnnemis getGenerateurEnnemis() { return this.generateurEnnemis; }

    public Joueur getJoueur() { return this.joueur; }

}
