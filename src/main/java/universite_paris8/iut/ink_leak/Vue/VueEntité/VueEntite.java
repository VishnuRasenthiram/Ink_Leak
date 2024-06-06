package universite_paris8.iut.ink_leak.Vue.VueEntité;

import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;

public abstract class VueEntite {

    private Pane mainPane;

    public VueEntite(Pane mainPane){
        this.mainPane=mainPane;
    }

    public abstract void créeSprite(Entité entité);

    public Pane getMainPane(){
        return mainPane;
    }
}
