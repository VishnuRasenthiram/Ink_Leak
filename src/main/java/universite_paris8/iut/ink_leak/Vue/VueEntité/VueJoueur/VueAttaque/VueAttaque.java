package universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.AttaqueDeBase;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;

import java.util.ArrayList;


public class VueAttaque {

    private Pane mainPane;
    private Joueur joueur;

    public VueAttaque(Pane mainPane, Joueur joueur){
        this.mainPane = mainPane;
        this.joueur = joueur;

    }
    public Pane getMainPane() {
        return mainPane;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void afficheAttaque(Pouvoirs pouvoirs) {
        if(pouvoirs instanceof AttaqueDeBase){
            VueAttaqueDeBase vAB=new VueAttaqueDeBase(mainPane, joueur);
            vAB.afficheAttaqueDeBase((AttaqueDeBase) pouvoirs);
        } else if (pouvoirs instanceof Bulle ) {
            VueBulle vB= new VueBulle(mainPane, joueur);
            vB.afficheAttaqueBulle((Bulle) pouvoirs);
        } else if (pouvoirs instanceof Poing){
            VuePoing vP = new VuePoing(mainPane, joueur);
            vP.afficherAttaquePoing((Poing) pouvoirs);
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