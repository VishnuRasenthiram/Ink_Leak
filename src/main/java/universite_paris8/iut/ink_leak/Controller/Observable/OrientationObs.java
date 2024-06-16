package universite_paris8.iut.ink_leak.Controller.Observable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueEntite;

public class OrientationObs implements ChangeListener<String> {

    private Pane mainPane;
    private Entité entité;
    private VueEntite vE;

    public OrientationObs(Pane mainPane, VueEntite vE, Entité entité) {
        this.mainPane = mainPane;
        this.entité=entité;
        this.vE=vE;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String old, String nouv) {
        Pane p = (Pane) mainPane.lookup("#"+entité.getNom_entite());
        p.getChildren().remove(0);
        ImageView imageview= new ImageView();
        imageview.setFitHeight(entité.getLargeur());
        imageview.setFitWidth(entité.getLongueur());
        imageview.setImage(new Image(vE.orientationToFile(nouv,entité,"Idle").toURI().toString()));
        p.getChildren().add(imageview);
    }
}
