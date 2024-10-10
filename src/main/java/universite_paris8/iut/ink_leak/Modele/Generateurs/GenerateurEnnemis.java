package universite_paris8.iut.ink_leak.Modele.Generateurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static universite_paris8.iut.ink_leak.Modele.AEtoile.chercherChemin;


public class GenerateurEnnemis {

    private ObservableList<Entité> listeEntite;
    Abomination Abomination = null;
    private Environnement environnement;

    public GenerateurEnnemis(Environnement environnement) {
        listeEntite = FXCollections.observableArrayList();
        this.environnement = environnement;
    }

    public void activerMob(Joueur joueur, Map map) {
        ArrayList<Entité> listeMort = new ArrayList<>();
        if (!listeEntite.isEmpty()) {
            for (Entité mob : listeEntite) {
                if (mob instanceof HeadLess) {
                    int startX = mob.coorDansLeTableauX(mob.getPosX());
                    int startY = mob.coorDansLeTableauY(mob.getPosY());
                    int targetX = joueur.coorDansLeTableauX(joueur.getPosX() + 16);
                    int targetY = joueur.coorDansLeTableauY(joueur.getPosY() + 16);
                    if (mob.getOrientation().equals("O")) startX = startX + 1;

                    List<Integer> path = chercherChemin(map.getMap(), startX, startY, targetX, targetY);

                    if (path != null && !path.isEmpty()) {

                        if (path.get(0) == 1) {

                            mob.déplacement("0");
                        } else if (path.get(0) == 2) {
                            mob.déplacement("1");
                        } else if (path.get(0) == 3) {
                            mob.déplacement("2");
                        } else if (path.get(0) == 4) {
                            mob.déplacement("3");
                        }
                    }

                } else if (mob instanceof Abomination) {
                    int startX = mob.coorDansLeTableauX(mob.getPosX() + 128);
                    int startY = mob.coorDansLeTableauY(mob.getPosY() + 150);
                    int targetX = joueur.coorDansLeTableauX(joueur.getPosX() + 16);
                    int targetY = joueur.coorDansLeTableauY(joueur.getPosY() + 16);

                    List<Integer> path = chercherChemin(map.getMap(), startX, startY, targetX, targetY);


                    if (path != null && !path.isEmpty()) {
                        if (path.size() > 6) {
                            ((Abomination) mob).attaque_proche();
                        } else {
                            ((Abomination) mob).attaque_loin();
                        }

                    }
                } else if (mob instanceof Slime) {
                    mob.déplacement("4");
                } else {
                    mob.déplacement("5");
                }
                if (mob.getVie() == 0) {
                    listeMort.add(mob);
                }


            }
        }
        for (Entité ennemisMort : listeMort) {
            listeEntite.remove(ennemisMort);
        }

    }

    public void TuerToutLesEnnemis() {
        listeEntite.clear();
    }

    public void creeEnnemis(String nomEnnemi, Environnement environnement) {
        Entité nouvelEnnemi = null;

        switch (nomEnnemi) {
            case "Slime":
                nouvelEnnemi = new Slime(environnement);
                break;
            case "HeadLess":
                nouvelEnnemi = new HeadLess(environnement);
                break;
            case "Faker":
                nouvelEnnemi = new Faker(environnement);
                break;
            case "Puddle":
                nouvelEnnemi = new Puddle(environnement);
                break;
            default:
                System.err.println("Type d'ennemi inconnu : " + nomEnnemi);
                return;
        }

        if (nouvelEnnemi != null) {
            listeEntite.add(nouvelEnnemi);
            setEnnemisPos(nouvelEnnemi);
        }
    }


    public void genererEnnemis(Abomination Boss) {
        if (Abomination == null && Boss != null) {
            Abomination = Boss;
        }
        switch (environnement.getMap().getNumMap()) {
            case 2:
                creeEnnemis("HeadLess", environnement);
                break;
            case 3:
                creeEnnemis("Faker", environnement);
                break;
            case 4:
                creeEnnemis("Puddle", environnement);
                break;
            case 5:
                listeEntite.add(Abomination);
                Abomination.setPosXProperty(198);
                Abomination.setPosYProperty(0);
                break;
            default:
                creeEnnemis("Slime", environnement);
                break;
        }
    }

    private void setEnnemisPos(Entité enti) {

        int random = new Random().nextInt(3);
        switch (random) {
            case 0:
                enti.setPosYProperty(enti.getPosY() + 200);
                enti.setPosXProperty(enti.getPosX() + 100);
                break;
            case 1:
                enti.setPosYProperty(enti.getPosY() + 100);
                enti.setPosXProperty(enti.getPosX() + 520);
                break;

            case 2:
                enti.setPosYProperty(enti.getPosY() + 580);
                enti.setPosXProperty(enti.getPosX() + 100);

        }


    }

    public ObservableList<Entité> getListeEntite() {
        return listeEntite;
    }

}
