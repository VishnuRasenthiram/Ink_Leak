package universite_paris8.iut.ink_leak.Modele;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetPouvoirBulle;
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
                vMap.supprimerAffichageMap();
                map.setMap(interaction == 3 ? map.getNumMap() + 1 : (map.getNumMap() > 3 ? 1 : map.getNumMap() - 1));
                TuerToutLesEnnemis();
                vMap.initMap(map);

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
