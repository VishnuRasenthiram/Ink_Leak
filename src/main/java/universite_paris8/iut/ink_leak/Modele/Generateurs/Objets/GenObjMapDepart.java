package universite_paris8.iut.ink_leak.Modele.Generateurs.Objets;

import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Imprimante;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Tube;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenObjMapDepart extends GenerateurObjets {
    private final Tube tube;
    private final Tube tube2;

    private final Imprimante imprimante;
    private final Imprimante imprimante2;

    public GenObjMapDepart(Environnement environnement) {
        super(environnement);
        tube=new Tube(getEnvironnement());
        tube2=new Tube(getEnvironnement());
        imprimante=new Imprimante(getEnvironnement());
        imprimante2=new Imprimante(getEnvironnement());
        GenerateurObjets.setInstance(this);
    }

    @Override
    public void creeObjets(ObservableList<Objets> listeObjets) {

        if(tube.getNbFoisRecuperable() !=0) {
            tube.setEmplacement(19,19);
            listeObjets.add(tube);

        }
        if(imprimante.getNbFoisRecuperable() !=0) {
            imprimante.setEmplacement(3,18);
            listeObjets.add(imprimante);
        }

        if(tube2.getNbFoisRecuperable() !=0) {
            tube2.setEmplacement(19,1);
            listeObjets.add(tube2);

        }
        if(imprimante2.getNbFoisRecuperable() !=0) {
            imprimante2.setEmplacement(3,5);
            listeObjets.add(imprimante2);
        }
    }


}
