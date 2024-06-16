package universite_paris8.iut.ink_leak.Modele.Generateurs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.HeadLess;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Puddle;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Faker;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Abomination;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Slime;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static universite_paris8.iut.ink_leak.Modele.AEtoile.chercherChemin;


public class GenerateurEnnemis {

    private ObservableList<Entité> listeEntite;
    Abomination Abomination = null;

    public GenerateurEnnemis(){
        listeEntite= FXCollections.observableArrayList();
    }

    public void activerMob(Joueur joueur, Map map){
        ArrayList<Entité> listeMort= new ArrayList<>();
        if(!listeEntite.isEmpty()) {
            for (Entité mob : listeEntite) {
                if (mob instanceof HeadLess) {
                int startX = mob.coorDansLeTableauX(mob.getPosX());
                int startY = mob.coorDansLeTableauY(mob.getPosY());
                int targetX = joueur.coorDansLeTableauX(joueur.getPosX()+16);
                int targetY = joueur.coorDansLeTableauY(joueur.getPosY()+16);
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
                    int startX = mob.coorDansLeTableauX(mob.getPosX()+128);
                    int startY = mob.coorDansLeTableauY(mob.getPosY()+150);
                    int targetX = joueur.coorDansLeTableauX(joueur.getPosX()+16);
                    int targetY = joueur.coorDansLeTableauY(joueur.getPosY()+16);

                    List<Integer> path = chercherChemin(map.getMap(), startX, startY, targetX, targetY);

                    if (path != null && !path.isEmpty()) {
                        if (path.size() > 8){
                            ((Abomination) mob).attaque_proche();
                        } else {
                            ((Abomination) mob).attaque_loin();
                        }

                    }
                } else if(mob instanceof Slime){
                    mob.déplacement("4");
                } else {
                    mob.déplacement("5");
                }
                if (mob.getVie() == 0) {
                    listeMort.add(mob);
                }


            }
        }
        for(Entité ennemisMort: listeMort){
            listeEntite.remove(ennemisMort);
        }

    }
    public void TuerToutLesEnnemis(){
        listeEntite.clear();
    }
    public void genererEnnemis( Map map, Joueur joueur, Abomination Boss){
        if (Abomination == null && Boss != null){
            Abomination = Boss;

        }
        switch (map.getNumMap()){
            case 1:

                break;
            case 2:
                HeadLess HeadLess = new HeadLess(this,map, joueur); // Créer un nouveau slime
                listeEntite.add(HeadLess);
                setEnnemisPos(HeadLess);
                break;
            case 3:
                Faker Faker = new Faker(this,map, joueur); // Créer un nouveau slime
                listeEntite.add(Faker);
                setEnnemisPos(Faker);
                break;
            case 4:
                Puddle Puddle = new Puddle(this,map, joueur); // Créer un nouveau slime
                listeEntite.add(Puddle);
                setEnnemisPos(Puddle);
                break;
            case 5:
                listeEntite.add(Abomination);
                Abomination.setPosXProperty(198);
                Abomination.setPosYProperty(0);
                break;

            default:
                Slime slime = new Slime(this,map, joueur); // Créer un nouveau slime
                listeEntite.add(slime);
                setEnnemisPos(slime);

                break;
        }

    }

    private void setEnnemisPos(Entité enti){

        int random = new Random().nextInt(3);
        switch (random){
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

    public ObservableList<Entité> getListeEntite(){
        return listeEntite;
    }

}
