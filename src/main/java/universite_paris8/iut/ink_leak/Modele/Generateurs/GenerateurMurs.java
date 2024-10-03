package universite_paris8.iut.ink_leak.Modele.Generateurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.Mur;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.MurCassable;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.MurPoussable;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Map;


public class GenerateurMurs {

    private ObservableList<Mur> listeMurs; // Liste pour stocker tous les objets
    private Map map;
    private Environnement environnement;

    public GenerateurMurs(Environnement environnement){
        listeMurs = FXCollections.observableArrayList();
        this.map=environnement.getMap();
        this.environnement=environnement;
    }

    public void EnleverToutLesMurs(){
        listeMurs.clear();
    }

    public void genererMurs(){
        for(int x=0 ; x<map.getLargeur();x++){
            for(int y=0 ; y<map.getHauteur();y++){
                if(map.getMap(x,y)==23){
                    MurCassable murCassable= new MurCassable(environnement);
                    murCassable.setEmplacement(x,y);
                    listeMurs.add(murCassable);
                }
                if(map.getMap(x,y)==5){
                    MurPoussable murPoussable= new MurPoussable(environnement);
                    murPoussable.setEmplacement(x,y);
                    listeMurs.add(murPoussable);
                }
            }
        }

    }

    public ObservableList<Mur> getListeMurs() {
        return listeMurs;
    }

    public Map getMap() {
        return environnement.getMap();
    }
}
