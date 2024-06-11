package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class Langue extends Pouvoirs{
    public Langue(Map map, GenerateurEnnemis generateurEnnemis, Joueur joueur) {
        super("Langue", 3, 32, 32, 1, true, map, generateurEnnemis, joueur);
    }

    @Override
    public void déplacement(String déplacementDirection) {

    }
}
