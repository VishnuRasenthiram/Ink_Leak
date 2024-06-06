package universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Controller.BulleObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.AttaqueDeBase;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import java.io.File;
import java.util.ArrayList;


public class VueAttaque {

    private Pane mainPane;

    public VueAttaque(Pane mainPane){
        this.mainPane = mainPane;


    }
    public Pane getMainPane() {
        return mainPane;
    }

    public void afficheAttaque(Pouvoirs pouvoirs) {
        if(pouvoirs instanceof AttaqueDeBase){
            VueAttaqueDeBase ab=new VueAttaqueDeBase(mainPane);
            ab.afficheAttaqueDeBase((AttaqueDeBase) pouvoirs);
        } else if (pouvoirs instanceof Bulle ) {
            VueBulle vB= new VueBulle(mainPane);
            vB.afficheAttaqueBulle((Bulle) pouvoirs);
        } else {
            System.out.println(" To do");
        }

    }


    public void removeAttaque() {
        ArrayList<Node> nodesToRemove = new ArrayList<>();
        for (Node node : mainPane.getChildren()) {
            if(node.getId()!=null){
                if (node.getId().equals("attaque")) {
                    nodesToRemove.add(node);
                }
            }

        }
        mainPane.getChildren().removeAll(nodesToRemove);
    }
}