package universite_paris8.iut.ink_leak.Controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.*;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis.VueEnnemis;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueObjets.*;

public class ListeObjetsObs implements ListChangeListener<Objets> {
    @FXML
    private Pane mainPane;


    public ListeObjetsObs(Pane mainPane){ this.mainPane = mainPane; }

    @Override
    public void onChanged(Change<? extends Objets> change) {
        VueObjets vueObjetBulle = new VueObjetBulle(mainPane);
        VueObjets vueObjetsPoing =new VueObjetPoing(mainPane);
        VueTube vueTube=new VueTube(mainPane);
        VueImprimante vueImprimante = new VueImprimante(mainPane);

        while(change.next()){
            for(Objets objetsAjoute: change.getAddedSubList()){
                if(objetsAjoute instanceof ObjetBulle){
                    vueObjetBulle.créeSprite(objetsAjoute);
                } else if (objetsAjoute instanceof ObjetPoing) {
                    vueObjetsPoing.créeSprite(objetsAjoute);
                } else if (objetsAjoute instanceof Tube) {
                    vueTube.créeSprite(objetsAjoute);
                } else if (objetsAjoute instanceof Imprimante) {
                    vueImprimante.créeSprite(objetsAjoute);
                }

            }
            for(Objets objetsEnleve : change.getRemoved()){
                if(objetsEnleve instanceof ObjetBulle){
                    vueObjetBulle.supprimerSpriteObjet(objetsEnleve);
                } else if (objetsEnleve instanceof ObjetPoing) {
                    vueObjetsPoing.supprimerSpriteObjet(objetsEnleve);
                } else if (objetsEnleve instanceof Tube) {
                    vueTube.supprimerSpriteObjet(objetsEnleve);
                } else if (objetsEnleve instanceof Imprimante) {
                    vueImprimante.supprimerSpriteObjet(objetsEnleve);
                }
            }
        }
    }
}
