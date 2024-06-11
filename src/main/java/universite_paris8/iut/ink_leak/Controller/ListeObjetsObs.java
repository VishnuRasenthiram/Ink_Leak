package universite_paris8.iut.ink_leak.Controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetBulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetLangue;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetPoing;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis.VueEnnemis;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueObjets.VueObjetBulle;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueObjets.VueObjetLangue;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueObjets.VueObjetPoing;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueObjets.VueObjets;

public class ListeObjetsObs implements ListChangeListener<Objets> {
    @FXML
    private Pane mainPane;


    public ListeObjetsObs(Pane mainPane){
        this.mainPane = mainPane;

    }

    @Override
    public void onChanged(Change<? extends Objets> change) {
        VueObjets vueObjetBulle = new VueObjetBulle(mainPane);
        VueObjets vueObjetsPoing =new VueObjetPoing(mainPane);
        VueObjets vueObjetsLangue= new VueObjetLangue(mainPane);

        while(change.next()){
            for(Objets objetsAjoute: change.getAddedSubList()){
                if(objetsAjoute instanceof ObjetBulle){
                    vueObjetBulle.créeSprite(objetsAjoute);
                } else if (objetsAjoute instanceof ObjetPoing) {
                    vueObjetsPoing.créeSprite(objetsAjoute);
                } else if (objetsAjoute instanceof ObjetLangue) {
                    vueObjetsLangue.créeSprite(objetsAjoute);
                }

            }
            for(Objets objetsEnleve : change.getRemoved()){
                if(objetsEnleve instanceof ObjetBulle){
                    vueObjetBulle.supprimerSpriteObjet(objetsEnleve);
                } else if (objetsEnleve instanceof ObjetPoing) {
                    vueObjetsPoing.supprimerSpriteObjet(objetsEnleve);
                }else if (objetsEnleve instanceof ObjetLangue) {
                    vueObjetsLangue.supprimerSpriteObjet(objetsEnleve);
                }
            }
        }
    }
}
