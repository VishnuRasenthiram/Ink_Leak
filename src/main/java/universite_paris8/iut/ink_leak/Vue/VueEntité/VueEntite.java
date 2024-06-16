package universite_paris8.iut.ink_leak.Vue.VueEntité;

import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;

import java.io.File;

public abstract class VueEntite {

    private Pane mainPane;

    public VueEntite(Pane mainPane){
        this.mainPane=mainPane;
    }


    public File orientationToFile(String orientation, Entité entité, String action){
        String nom = entité.getNom_entite().replaceAll("\\d","");
        switch (orientation){

            case "N":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/"+nom+"/"+action+"/"+nom.toLowerCase()+"_"+action.toLowerCase()+"_up.png");

            case "O":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/"+nom+"/"+action+"/"+nom.toLowerCase()+"_"+action.toLowerCase()+"_left.png");

            case "E":
                return new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/"+nom+"/"+action+"/"+nom.toLowerCase()+"_"+action.toLowerCase()+"_right.png");

            default:
                return  new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/"+nom+"/"+action+"/"+nom.toLowerCase()+"_"+action.toLowerCase()+"_down.png");

        }
    }

    public File orientationToFileAnimation(String orientation, Entité entité, String action,int nFrame){
        String nom = entité.getNom_entite().replaceAll("\\d","");
        if (orientation.equals("N")) orientation = "up";
        else if (orientation.equals("S")) orientation = "down";
        else if (orientation.equals("E")) orientation = "right";
        else orientation = "left";

        String basePath = "src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Entities/"+nom+"/"+action+"/" + orientation + "/";

        if(action.equals("Attack")){
            if (orientation.equals("up")) orientation = "u";
            else if (orientation.equals("down")) orientation = "d";
            else if (orientation.equals("right")) orientation = "r";
            else orientation = "l";
        }

        return new File(basePath + nom+"_"+action.toLowerCase()+"_" + orientation + "_" + nFrame + ".png");
    }

    public abstract void créeSprite(Entité entité);

    public Pane getMainPane() {
        return mainPane;
    }


}
