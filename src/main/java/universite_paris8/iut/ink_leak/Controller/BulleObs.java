package universite_paris8.iut.ink_leak.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque.VueAttaque;

public class BulleObs implements ChangeListener<Number> {

    private VueAttaque vueAttaque;

    public BulleObs( VueAttaque vA) {
            this.vueAttaque = vA;
    }

    @Override
    public void changed(ObservableValue<? extends Number  > observable, Number old,Number nouv) {

        if (nouv.intValue() == 0){
            vueAttaque.removeAttaque();
        }

    }

}
