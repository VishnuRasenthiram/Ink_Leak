package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class ObjetBulle extends Objet {

    private int nbFoisRecuperable;

    public ObjetBulle(Map map, GenerateurEnnemis spawner, Joueur j) {
        super("ObjetBulle", 0, map, spawner, j);
        this.nbFoisRecuperable = 1;
    }

    public void action() {
        if(super.getJ().enContact(this) && nbFoisRecuperable>0){
            nbFoisRecuperable--;
            super.getJ().ajoutPouvoir(super.getJ().getBulle());
        }
    }

}
