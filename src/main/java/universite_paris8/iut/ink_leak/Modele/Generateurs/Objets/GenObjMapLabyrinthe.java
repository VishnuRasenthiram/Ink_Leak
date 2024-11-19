package universite_paris8.iut.ink_leak.Modele.Generateurs.Objets;

import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable.ObjetLangue;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenObjMapLabyrinthe extends GenerateurObjets{
    private final ObjetLangue objetLangue;
    public GenObjMapLabyrinthe(Environnement environnement) {
        super(environnement);
        objetLangue= new ObjetLangue(getEnvironnement());
        GenerateurObjets.setInstance(this);
    }

    @Override
    public void creeObjets(ObservableList<Objets> listeObjets) {

        if(objetLangue.getNbFoisRecuperable() !=0){
            objetLangue.setEmplacement(18,1);
            listeObjets.add(objetLangue);
        }
    }
}
