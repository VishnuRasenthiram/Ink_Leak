package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;

import universite_paris8.iut.ink_leak.Modele.Environnement;

import java.util.List;

import static universite_paris8.iut.ink_leak.Modele.AEtoile.chercherChemin;

public class HeadLess extends Ennemi {

    public HeadLess(Environnement environnement) {
        super("Headless" + cpt, 2, 32, environnement, environnement.getJoueur());
    }

    public void déplacement(String direction) {
        int startX = coorDansLeTableauX(getPosX());
        int startY = coorDansLeTableauY(getPosY());
        int targetX = joueur.coorDansLeTableauX(joueur.getPosX() + 16);
        int targetY = joueur.coorDansLeTableauY(joueur.getPosY() + 16);
        if (getOrientation().equals("O")) startX = startX + 1;

        List<Integer> path = chercherChemin(getEnvironnement().getMap().getMap(), startX, startY, targetX, targetY);

        if (path != null && !path.isEmpty()) {

            if (path.get(0) == 1) {

                super.déplacement("0");
            } else if (path.get(0) == 2) {
                super.déplacement("1");
            } else if (path.get(0) == 3) {
                super.déplacement("2");
            } else if (path.get(0) == 4) {
                super.déplacement("3");
            }
        }
    }
}


