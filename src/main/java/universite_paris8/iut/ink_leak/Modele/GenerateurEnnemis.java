package universite_paris8.iut.ink_leak.Modele;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Slime;

import java.util.ArrayList;


public class GenerateurEnnemis {

    private ObservableList<Entité> listeEntite ; // Liste pour stocker tous les slimes
    public GenerateurEnnemis(){
        listeEntite= FXCollections.observableArrayList();

    }

    public void ActiverMob(){
      for (Entité mob : listeEntite) {
         mob.déplacement("E");
      }
        ArrayList<Entité> listeMort= new ArrayList<>();
        if(!listeEntite.isEmpty()) {
            for (Entité mob : listeEntite) {
                mob.déplacement(0);
                if(mob.getVie()==0){
                    listeMort.add(mob);
                }
            }

            for (Entité mobMort: listeMort) {
                listeEntite.remove(mobMort);
            }
        }
    }

    public void genererEnnemis( GenerateurEnnemis spawner, Map map){
        Slime slime = new Slime(spawner,map); // Créer un nouveau slime
        listeEntite.add(slime); // Ajouter le slime à la liste
        slime.setPosYProperty(slime.getPosY() + 50);
        slime.setPosXProperty(slime.getPosX() + 300);

    }

    public ObservableList<Entité> getListeEntite(){
        return listeEntite;
    }

}
