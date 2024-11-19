package universite_paris8.iut.ink_leak.Modele.Generateurs.Objets;

import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable.ObjetBulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenObjMapGlace extends GenerateurObjets {
    private final ObjetBulle objetBulle;

    public GenObjMapGlace(Environnement environnement) {
        super(environnement);
        objetBulle =new ObjetBulle(getEnvironnement());
        GenerateurObjets.setInstance(this);
    }

    @Override
    public void creeObjets(ObservableList<Objets> listeObjets) {

        if(objetBulle.getNbFoisRecuperable() !=0){
            objetBulle.setEmplacement(10, 8);
            listeObjets.add(objetBulle);
        }
    }
}
