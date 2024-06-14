package universite_paris8.iut.ink_leak.Controller.ListeObservable;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Controller.Observable.OrientationObs;
import universite_paris8.iut.ink_leak.Modele.Entité.MurCassable.MurCassable;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis.VueEnnemis;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueMurs.VueMurs;

public class ListeMursObs implements ListChangeListener<MurCassable> {
    @FXML
    private Pane mainPane;


    public ListeMursObs(Pane mainPane){
        this.mainPane = mainPane;


    }

    @Override
    public void onChanged(Change<? extends MurCassable> change) {
        VueMurs vueEnnemis = new VueMurs(mainPane);

        while(change.next()){
            for(MurCassable ennemisAjouté: change.getAddedSubList()){
                vueEnnemis.créeSprite(ennemisAjouté);
                ennemisAjouté.getOrientationProperty().addListener(new OrientationObs(mainPane,vueEnnemis,ennemisAjouté));
            }
            for(MurCassable ennemisEnlevé : change.getRemoved()){
                for(int i= this.mainPane.getChildren().size()-1; i >= 0; i--){
                    if(mainPane.getChildren().get(i).getId().equals(ennemisEnlevé.getNom_entite())){
                        Node nod=this.mainPane.getChildren().get(i);
                        this.mainPane.getChildren().remove(nod);
                    }


                }
            }
        }
    }
}
