package universite_paris8.iut.ink_leak.Modele.Entité.Murs;

import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurMurs;

import java.util.ArrayList;

public class MurPoussable extends Mur {

    private static int cpt =0;

    public MurPoussable(GenerateurMurs generateurMurs) {
        super("MurPoussable"+cpt,  1, 1, 32, 32,1,1,generateurMurs.getMap(),null,null,generateurMurs);
        cpt++;
    }

    public ArrayList<Mur> déplacementMur(String déplacementDirection, Joueur joueur) {
        double newX = this.getPosX();
        double newY = this.getPosY();

        if (joueur.getBulle().enContact(this)) {

            switch (déplacementDirection) {
                case "N":
                    newY -= 32;
                    break;
                case "S":
                    newY += 32;
                    break;
                case "E":
                    newX += 32;
                    break;
                case "O":
                    newX -= 32;
                    break;
                default:

                    break;
            }

            changementSurLaMap(newX, newY);
        }
        return super.verifMurCassé();
    }

    private void changementSurLaMap(double newX, double newY) {
        boolean peutAller = this.peutAller(newX, newY, getGenerateurMurs().getMap());
        boolean differentX = (int) (newX+16 )/ 32 != (int) (this.getPosX()+16) / 32;
        boolean differentY = (int) (newY+16) / 32 != (int) (this.getPosY()+16 )/ 32;

        if (peutAller && (differentX || differentY)) {
            super.getGenerateurMurs().getMap().setMap((int) this.getPosX() / 32, (int) this.getPosY() / 32, 0);
            this.setEmplacement((int) newX / 32, (int) newY / 32);
            super.getGenerateurMurs().getMap().setMap((int) newX / 32, (int) newY / 32, 23);
        }
    }

}
