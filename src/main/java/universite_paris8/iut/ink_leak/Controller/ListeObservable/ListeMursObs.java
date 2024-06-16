package universite_paris8.iut.ink_leak.Controller.ListeObservable;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.Mur;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.MurCassable;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.MurPoussable;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueMurs.VueMursCassable;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueMurs.VueMursPoussable;

public class ListeMursObs implements ListChangeListener<Mur> {
    @FXML
    private Pane mainPane;

    public ListeMursObs(Pane mainPane){
        this.mainPane = mainPane;
    }
    @Override
    public void onChanged(Change<? extends Mur> change) {
        VueMursCassable vueMursCassable = new VueMursCassable(mainPane);
        VueMursPoussable vueMursPoussable= new VueMursPoussable(mainPane);
        while(change.next()){
            for(Mur murAjouté: change.getAddedSubList()){
                if(murAjouté instanceof MurCassable){
                    vueMursCassable.créeSprite(murAjouté);
                } else if (murAjouté instanceof MurPoussable) {
                    vueMursPoussable.créeSprite(murAjouté);
                }

            }
            for(Mur murCassé : change.getRemoved()){
                for(int i= this.mainPane.getChildren().size()-1; i >= 0; i--){
                    if(mainPane.getChildren().get(i).getId().equals(murCassé.getNom_entite())){
                        Node spriteMur=this.mainPane.getChildren().get(i);
                        this.mainPane.getChildren().remove(spriteMur);
                    }


                }
            }
        }
    }
}
