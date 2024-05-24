package universite_paris8.iut.ink_leak.Modele;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Slime;
import universite_paris8.iut.ink_leak.Vue.VueEnnemis;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GenerateurEnnemis {

    private ArrayList<Slime> slimes ; // Liste pour stocker tous les slimes
    public GenerateurEnnemis(){
        slimes=new ArrayList<Slime>();

    }

    public void ActiverMob(Pane mainPane){
      for (Entité mob : slimes) {
          mob.déplacement(mainPane);
      }
    }

    public void genererEnnemis(Pane mainPane, Timeline gameLoop){
        VueEnnemis vueEnnemis = new VueEnnemis(mainPane);

        Slime slime = new Slime(50, 10, 32, 1,gameLoop); // Créer un nouveau slime
        slimes.add(slime); // Ajouter le slime à la liste
        vueEnnemis.créeSprite(slime);
        slime.setPosYProperty(slime.getPosY() + 50);
        slime.setPosXProperty(slime.getPosX() + 300);




    }

}
