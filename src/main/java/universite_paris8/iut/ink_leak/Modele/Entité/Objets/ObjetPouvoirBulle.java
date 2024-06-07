package universite_paris8.iut.ink_leak.Modele.Entité.Objets;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public  class ObjetPouvoirBulle extends Entité {
    private Joueur j;
    private static int nbFoisRecuperable=1;
    public ObjetPouvoirBulle( Map map, GenerateurEnnemis spawner, Joueur j) {
        super("ObjetBulle", 1, 0, 32,32, 0, 1, map, spawner);
        this.j = j;


    }
    @Override
    public void attaque() {

    }

    @Override
    public void déplacement(String déplacementDirection) {
        if(j.enContact(this) && nbFoisRecuperable>0){
            nbFoisRecuperable--;
            j.ajoutPouvoir(j.getBulle());
            this.setEmplacement(0,0);
        }
    }

    @Override
    public void gagner_vie(int nb_vie_gagnee) {

    }

    public void setEmplacement(int x,int y){
        super.setEmplacement(x*32,y*32);
    }
}
