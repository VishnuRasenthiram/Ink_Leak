package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;


public class Puddle extends Ennemi {

    public Puddle(GenerateurEnnemis generateurEnnemis, Map map, Joueur joueur){

        super("Puddle"+cpt, 2, generateurEnnemis, map, joueur);
    }
}


