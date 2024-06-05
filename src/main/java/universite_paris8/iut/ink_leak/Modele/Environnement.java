package universite_paris8.iut.ink_leak.Modele;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur;

public class Environnement {

    private Joueur joueur;
    private GenerateurEnnemis liste_entites;
    private Map map;

    public Environnement(Joueur joueur, Map map, GenerateurEnnemis spawner) {
        this.joueur = joueur;
        this.liste_entites = spawner;
        this.map = map;
    }

    public void action(int temps) {

        if (temps % 5 == 0) {
            double x = this.joueur.getPosX();
            double y = this.joueur.getPosY();
            joueur.peutAller(x, y, this.map);
        }
        if (temps % 500 == 0) { liste_entites.genererEnnemis(liste_entites, map); }
        if (temps % 2 == 0) { liste_entites.ActiverMob(); }

    }

    public GenerateurEnnemis getListe_entites() { return this.liste_entites; }

    public Joueur getJoueur() { return this.joueur; }

}
