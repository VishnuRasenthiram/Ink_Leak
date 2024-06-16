package universite_paris8.iut.ink_leak.Modele.Generateurs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Dijkstra;
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

                    List<Integer> path = Dijkstra.dijkstraAstar(map.getMap(), startX, startY, targetX, targetY);

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

                } else if (!(mob instanceof Abomination)) {
                    mob.déplacement("4");
                } else {
                    mob.attaque();
                }
                if (mob.getVie() == 0) {
                    listeMort.add(mob);
                }
                for (Entité mobMort : listeMort) {
                    listeEntite.remove(mobMort);
                }

            }
        }
    }
    public void TuerToutLesEnnemis(){
        listeEntite.clear();
    }
    public void genererEnnemis( Map map, Joueur joueur){

        switch (map.getNumMap()){
            case 1:
                Slime slime = new Slime(this,map, joueur); // Créer un nouveau slime
                listeEntite.add(slime);
                setEnnemisPos(slime);
                break;
            case 2:
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
                if (Abomination != null){
                    break;
                } else{
                    Abomination = new Abomination(this,map, joueur); // Créer un nouveau slime
                    listeEntite.add(Abomination);
                    Abomination.setPosXProperty(200);
                    Abomination.setPosYProperty(0);
                }

                break;
            default:
                if (Abomination != null){
                    break;
                } else{
                    Abomination = new Abomination(this,map, joueur); // Créer un nouveau slime
                    listeEntite.add(Abomination);
                    Abomination.setPosXProperty(200);
                    Abomination.setPosYProperty(0);
                }
                HeadLess HeadLess = new HeadLess(this,map, joueur); // Créer un nouveau slime
                listeEntite.add(HeadLess);
                setEnnemisPos(HeadLess);
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
