package universite_paris8.iut.ink_leak.Modele;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Slime;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class GenerateurEnnemis {
    private ObservableList<Entité> listeEntite ; // Liste pour stocker tous les slimes
    public GenerateurEnnemis(){
        listeEntite= FXCollections.observableArrayList();

    }

    public void activerMob(Joueur joueur, Map map){
        ArrayList<Entité> listeMort= new ArrayList<>();
        if(!listeEntite.isEmpty()) {
            for (Entité mob : listeEntite) {
               // mob.déplacement("E");
                // Replace these with your desired starting and target positions
                int startX = mob.coorDansLeTableauX(mob.getPosX());
                int startY = mob.coorDansLeTableauY(mob.getPosY());
                int targetX = joueur.coorDansLeTableauX(joueur.getPosX());
                int targetY = joueur.coorDansLeTableauY(joueur.getPosY());

                List<Integer> path = Dijkstra.dijkstra(map.getMap(), startX, startY, targetX, targetY);

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
                } else {
                    //System.out.println("No path found");
                }
                if(mob.getVie()==0){
                    listeMort.add(mob);
                }
            }
            for (Entité mobMort: listeMort) {
                listeEntite.remove(mobMort);
            }
        }
    }
    public void TuerToutLesEnnemis(){
        listeEntite.clear();
    }
    public void genererEnnemis( Map map, Joueur joueur){
         // Ajouter le slime à la liste
        switch (map.getNumMap()){

            case 1:

                break;
            case 2:
                break;
            case 3:

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
