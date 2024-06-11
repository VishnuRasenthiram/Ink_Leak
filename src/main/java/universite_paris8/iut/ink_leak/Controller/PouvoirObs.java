package universite_paris8.iut.ink_leak.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque.VueAttaque;

public class PouvoirObs implements ChangeListener<Boolean> {

    private VueAttaque vueAttaque;

    public PouvoirObs(VueAttaque vA) {
            this.vueAttaque = vA;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean  > observable, Boolean old,Boolean nouv) {

        if (!nouv){
            vueAttaque.removeAttaque();
        }

    }

}
