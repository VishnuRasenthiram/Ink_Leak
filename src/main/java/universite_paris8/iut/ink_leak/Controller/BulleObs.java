package universite_paris8.iut.ink_leak.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Vue.VueAttaque;

import java.io.File;

public class BulleObs implements ChangeListener<Number> {

    private Entité Bulle;
    private VueAttaque vueAttaque;

    public BulleObs(Entité entité, VueAttaque vA) {

            this.Bulle = entité;
            this.vueAttaque = vA;


    }

    @Override
    public void changed(ObservableValue<? extends Number  > observable, Number old,Number nouv) {

        if (nouv.intValue() == 0){
            vueAttaque.removeAttaque();
        }

    }

}
