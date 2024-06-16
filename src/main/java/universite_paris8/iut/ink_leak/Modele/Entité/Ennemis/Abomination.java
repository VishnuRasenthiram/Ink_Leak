package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Langue;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;


public class Abomination extends Entité {
    public static int cpt=0;
    public final Joueur joueur;
    private Bulle bulle;
    private Poing poing;
    private Langue langue;
    public Abomination(GenerateurEnnemis generateurEnnemis, Map map, Joueur joueur){

        super("Abomination"+cpt, 30, 1,256, 256,2,200,map, generateurEnnemis,null, null);
        this.joueur = joueur;
        bulle =new Bulle( super.getMap(),super.getGenerateurEnnemis(),joueur);
        poing = new Poing(super.getMap(), super.getGenerateurEnnemis(), joueur);
        langue = new Langue(super.getMap(), super.getGenerateurEnnemis(), joueur);
        cpt++;
    }

    @Override
    public void attaque() {
        langue.déplacement("S");
    }



    @Override
    public void gagner_vie(int nb_vie_gagnee) {
        System.out.println("aaaa");
    }

    public void déplacement(String direction) {
        return;
    }
}


