package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Slime;
import universite_paris8.iut.ink_leak.Vue.VueEnnemis;
import java.util.ArrayList;
import static java.lang.Thread.sleep;

public class GenerateurEnnemis {

    private ObservableList<Entité> listeEntite ; // Liste pour stocker tous les slimes
    public GenerateurEnnemis(){
        listeEntite= FXCollections.observableArrayList();

    }

    public void ActiverMob(){
      for (Entité mob : listeEntite) {
         mob.déplacement(0);
      }
    }

    public void genererEnnemis( Timeline gameLoop, GenerateurEnnemis spawner, Map map){
        Slime slime = new Slime(5, 10, 32, 1,gameLoop,map,spawner); // Créer un nouveau slime
        listeEntite.add(slime); // Ajouter le slime à la liste
        slime.setPosYProperty(slime.getPosY() + 50);
        slime.setPosXProperty(slime.getPosX() + 300);

    }

    public ObservableList<Entité> getListeEntite(){
        return listeEntite;
    }

}
