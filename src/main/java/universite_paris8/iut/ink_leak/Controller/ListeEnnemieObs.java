package universite_paris8.iut.ink_leak.Controller;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis.VueEnnemis;

public class ListeEnnemieObs implements ListChangeListener<Entité> {
    @FXML
    private Pane mainPane;
    public ListeEnnemieObs(Pane mainPane){
        this.mainPane = mainPane;
    }

    @Override
    public void onChanged(Change<? extends Entité> change) {
        VueEnnemis vueEnnemis = new VueEnnemis(mainPane);

        while(change.next()){
            for(Entité ennemisAjouté: change.getAddedSubList()){
                vueEnnemis.créeSprite(ennemisAjouté);
            }


            for(Entité ennemisEnlevé : change.getRemoved()){
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
