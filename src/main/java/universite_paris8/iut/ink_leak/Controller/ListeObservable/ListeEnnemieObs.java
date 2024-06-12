package universite_paris8.iut.ink_leak.Controller.ListeObservable;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Controller.Observable.OrientationObs;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEnnemis.VueEnnemis;

public class ListeEnnemieObs implements ListChangeListener<Entité> {
    @FXML
    private Pane mainPane;
    private Joueur joueur;
    private Map map;

    public ListeEnnemieObs(Pane mainPane, Joueur j, Map map){
        this.mainPane = mainPane;
        this.joueur = j;
        this.map = map;

    }

    @Override
    public void onChanged(Change<? extends Entité> change) {
        VueEnnemis vueEnnemis = new VueEnnemis(mainPane,joueur,map);

        while(change.next()){
            for(Entité ennemisAjouté: change.getAddedSubList()){
                vueEnnemis.créeSprite(ennemisAjouté);
                ennemisAjouté.getOrientationProperty().addListener(new OrientationObs(mainPane,vueEnnemis,ennemisAjouté));
            }
            for(Entité ennemisEnlevé : change.getRemoved()){
                for(int i= this.mainPane.getChildren().size()-1; i >= 0; i--){
                    if(mainPane.getChildren().get(i).getId().equals(ennemisEnlevé.getNom_entite())){
                        Node nod=this.mainPane.getChildren().get(i);
                        this.mainPane.getChildren().remove(nod);
                    } else if(mainPane.getChildren().get(i).getId().equals(ennemisEnlevé.getNom_entite()+"vie")){
                        Node nod=this.mainPane.getChildren().get(i);
                        this.mainPane.getChildren().remove(nod);
                    }

                }
            }
        }
    }
}
