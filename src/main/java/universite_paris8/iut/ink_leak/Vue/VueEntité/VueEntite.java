package universite_paris8.iut.ink_leak.Vue.VueEntité;

import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;

public abstract class VueEntite {

    private Pane mainPane;
    private Pane mainborderPane;

    public VueEntite(Pane mainPane, Pane mainborderPane){

        this.mainPane=mainPane;
        this.mainborderPane=mainborderPane;
    }

    public abstract void créeSprite(Entité entité);

    public Pane getMainPane() {
        return mainPane;
    }
    public Pane getMainBorderPane(){
        return mainborderPane;
    }
}
