package universite_paris8.iut.ink_leak.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;

public class PouvoirEnCoursObs implements ChangeListener<Number> {

    public Joueur joueur;
    public Pane interfacePane;

    public PouvoirEnCoursObs(Joueur joueur, Pane interfacePane ) {

        this.joueur = joueur;
        this.interfacePane = interfacePane;

    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number old, Number nouv) {
        // Récupere le sprite du prochain pouvoir dans la liste et agrandi sa taille
        Pouvoirs prochainPouvoir=joueur.getListePouvoirs().get((int)nouv);
        HBox listePouvoirs = (HBox) interfacePane.lookup("#listePouvoir");
        Pane pane=(Pane)listePouvoirs.lookup("#"+prochainPouvoir.getNom_entite());
        ImageView imageView = (ImageView) pane.lookup("#imageView");
        imageView.setFitWidth(42);
        imageView.setFitHeight(42);


        // Récupere le sprite du pouvoir qui a été choisis précedement pour lui donner sa taille d'origine
        Pouvoirs pouvoirPrecedent=joueur.getListePouvoirs().get((int)old);
        HBox listePouvoirs2 = (HBox) interfacePane.lookup("#listePouvoir");
        Pane paneO=(Pane)listePouvoirs2.lookup("#"+pouvoirPrecedent.getNom_entite());
        ImageView imageViewO = (ImageView) paneO.lookup("#imageView");
        imageViewO.setFitWidth(32);
        imageViewO.setFitHeight(32);

    }

}
