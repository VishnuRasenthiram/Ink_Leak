package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;


public class HeadLess extends Ennemi {

    public HeadLess(GenerateurEnnemis generateurEnnemis, Map map, Joueur joueur){

        super("Headless"+cpt, 2, generateurEnnemis, map, joueur);
    }
}


