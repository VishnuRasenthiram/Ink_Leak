package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Vue.VueMap;

public class Environnement {

    private Joueur joueur;
    private GenerateurEnnemis liste_entites;
    private Map map;
    private VueMap vMap;

    public Environnement(Joueur joueur, Map map, GenerateurEnnemis spawner, VueMap vMap) {
        this.joueur = joueur;
        this.liste_entites = spawner;
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
                map.setMap(interaction == 3 ? map.getNumMap() + 1 : (map.getNumMap() > 3 ? 1 : map.getNumMap() - 1));
                TuerToutLesEnnemis();
                vMap.initMap(map);

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> joueur.setBougable(true));
                pause.play();
            }

        }
        if (temps % 100 == 0) { liste_entites.genererEnnemis(liste_entites, map); }
        liste_entites.ActiverMob();

    }
    private void TuerToutLesEnnemis(){
        liste_entites.TuerToutLesEnnemis();
    }

    public GenerateurEnnemis getListe_entites() { return this.liste_entites; }

    public Joueur getJoueur() { return this.joueur; }

}
