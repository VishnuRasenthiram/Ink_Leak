package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;


public class Puddle extends Entité {
    public static int cpt=0;
    public final Joueur joueur;

    public Puddle(GenerateurEnnemis generateurEnnemis, Map map, Joueur joueur){

        super("Puddle"+cpt, 2, 1,32, 32,2,200,map, generateurEnnemis,null, null);
        this.joueur = joueur;
        cpt++;
    }

    @Override
    public void attaque() {
        if(this.enContact(joueur)){
            joueur.prendre_degat(1);
        }
    }



    @Override
    public void gagner_vie(int nb_vie_gagnee) {
        System.out.println("aaaa");
    }
}


