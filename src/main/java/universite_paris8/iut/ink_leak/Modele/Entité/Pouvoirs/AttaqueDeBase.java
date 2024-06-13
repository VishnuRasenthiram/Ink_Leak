package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class AttaqueDeBase extends Pouvoirs{

    public AttaqueDeBase(Map map,GenerateurEnnemis liste_entites, Joueur j) {
        super("attBase",  1, 32, 32, 1, map, liste_entites,j);

    }

    @Override
    public void déplacement(String déplacementDirection) {
        super.setPosition(32);
        double x = super.getPosX();
        double y = super.getPosY();
        int vitesse_joueur = super.getVitesse_entite();
        switch (déplacementDirection) { // 0=Z 1=S 2=Q 3=D
            case "N":
                if (super.peutAller(x, y - vitesse_joueur, super.getMap())) {
                    super.setPosYProperty(super.getPosY() - vitesse_joueur);
                }
                break;

            case "S":
                if (super.peutAller(x, y + vitesse_joueur, super.getMap())) {
                    super.setPosYProperty(super.getPosY() + vitesse_joueur);
                }
                break;

            case "O":
                if (super.peutAller(x - vitesse_joueur, y, super.getMap())) {
                    super.setPosXProperty(super.getPosX() - vitesse_joueur);
                }
                break;
            case  "E":
                if (super.peutAller(x + vitesse_joueur, y, super.getMap())) {
                    super.setPosXProperty(super.getPosX() + vitesse_joueur);
                }
                break;
        }
        for(Entité sl:super.getlisteEntite().getListeEntite()){

            if(this.enContact(sl)) {
                sl.prendre_degat(super.getAttaque_entite());
            }
        }
    }
}
