package universite_paris8.iut.ink_leak.Modele.Generateurs.Objets;

import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable.ObjetPoing;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenObjMapEau extends GenerateurObjets  {
    private final ObjetPoing objetPoing;

    public GenObjMapEau(Environnement environnement) {
        super(environnement);
        objetPoing=new ObjetPoing(getEnvironnement());
        GenerateurObjets.setInstance(this);
    }

    @Override
    public void creeObjets(ObservableList<Objets> listeObjets) {

        if(objetPoing.getNbFoisRecuperable() !=0){
            objetPoing.setEmplacement(1, 0);
            listeObjets.add(objetPoing);
        }
    }
}
