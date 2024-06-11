package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Vue.Musique;
import universite_paris8.iut.ink_leak.Vue.VueMap;

import java.util.List;

import static universite_paris8.iut.ink_leak.Modele.Dijkstra.dijkstra;

public class Environnement {

    private Joueur joueur;
    private GenerateurEnnemis listeEntite;
    private GenerateurObjets listeObjets;
    private Map map;
    private VueMap vMap;

    public Environnement(Joueur joueur, Map map, GenerateurEnnemis listeEntite,GenerateurObjets listeObjets, VueMap vMap) {
        this.joueur = joueur;
        this.listeEntite = listeEntite;
        this.listeObjets = listeObjets;
        this.map = map;
        this.vMap = vMap;
        // Replace these with your desired starting and target positions
        int startX = 0;
        int startY = 0;
        int targetX = 8;
        int targetY = 4;

        List<Direction> path = dijkstra(map.getMap(), startX, startY, targetX, targetY);
        // Process the path (optional)
        if (path != null) {
            for (Direction direction : path) {
                System.out.println(direction);
            }
        } else {
            System.out.println("No path found");
        }
    }



    public void action(int temps) {

        if (temps % 5 == 0) {
            double x = this.joueur.getPosX();
            double y = this.joueur.getPosY();
            joueur.peutAller(x, y, this.map);
            int interaction = joueur.verifierInteractionEnFace(x, y);

            listeObjets.genererObjets();

            if (interaction == 3 || interaction == 4) {


                joueur.setBougable(false);
                vMap.supprimerAffichageMap();
                map.setMap(interaction == 3 ? map.getNumMap() + 1 : (map.getNumMap() > 4 ? 1 : map.getNumMap() - 1));
                TuerToutLesEnnemis();
                listeObjets.EnleverToutLesObjets();
                vMap.initMap(map, joueur);
                listeObjets.genererObjets();
                PauseTransition pause = new PauseTransition(Duration.millis(500));
                pause.setOnFinished(event -> joueur.setBougable(true));
                pause.play();
            }

        }
        if (temps % 100 == 0) { listeEntite.genererEnnemis(listeEntite, map); }
        listeEntite.activerMob();
        listeObjets.activerObjet();


    }
    private void TuerToutLesEnnemis(){
        listeEntite.TuerToutLesEnnemis();
    }

    public GenerateurEnnemis getListeEntite() { return this.listeEntite; }

    public Joueur getJoueur() { return this.joueur; }

}
