package universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

public class AttaqueDeBase extends Pouvoirs{

    public AttaqueDeBase(Environnement environnement) {
        super("attBase",  1, 32, 32, 1, true,environnement);
    }

    @Override
    public void déplacement(String déplacementDirection) {
        super.setEstEnVie(true);
        super.setPosition();
        double x = super.getPosX();
        double y = super.getPosY();
        int vitesse_joueur = super.getVitesse_entite();
        super.setOrientation(déplacementDirection);
        switch (déplacementDirection) {
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
        for(Entité sl:super.getGenerateurEnnemis().getListeEntite()){

            if(this.enContact(sl)) {
                sl.prendre_degat(super.getAttaque_entite());
            }
        }
        super.attaque();
        this.setEstEnVie(false);
    }

}
