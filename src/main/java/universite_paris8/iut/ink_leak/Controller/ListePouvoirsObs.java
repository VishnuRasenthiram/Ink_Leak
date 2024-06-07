package universite_paris8.iut.ink_leak.Controller;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VuePouvoirs.VuePouvoirs;

public class ListePouvoirsObs implements ListChangeListener<Pouvoirs> {


    private VuePouvoirs vP;

    public ListePouvoirsObs(Pane interfacePane, Joueur j){


        vP= new VuePouvoirs(interfacePane);
        vP.créeSpriteListeAttaque(j);
    }

    @Override
    public void onChanged(Change<? extends Pouvoirs> change) {
        while(change.next()){
            for(Pouvoirs pouvoirsAjouté: change.getAddedSubList()){
                vP.ajoutSpritePouvoir(pouvoirsAjouté);
            }

        }
    }
}
