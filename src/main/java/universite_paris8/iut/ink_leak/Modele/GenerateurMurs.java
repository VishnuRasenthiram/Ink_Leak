package universite_paris8.iut.ink_leak.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.MurCassable.MurCassable;

import java.util.ArrayList;


public class GenerateurMurs {
    private ObservableList<MurCassable> listeMurs; // Liste pour stocker tous les objets
    private Map map;
    private Joueur joueur;

    public GenerateurMurs(Map map, Joueur joueur){
        listeMurs = FXCollections.observableArrayList();
        this.map=map;
        this.joueur=joueur;

    }
    public void activerMurs(){
        ArrayList<MurCassable> listeEnleve= new ArrayList<>();
        if(!listeMurs.isEmpty()) {
            for (MurCassable mur : listeMurs) {
                mur.déplacement("S");
                if(mur.getVie()==0){
                    listeEnleve.add(mur);
                }
            }
            for (MurCassable murCassé: listeEnleve) {
                listeMurs.remove(murCassé);
            }
        }
    }
    public void EnleverToutLesObjets(){
        listeMurs.clear();
    }

    public void genererMurs(){
        for(int x=0 ; x<map.getLargeur();x++){
            for(int y=0 ; y<map.getHauteur();y++){
                if(map.getMap(x,y)==23){
                    MurCassable murCassable= new MurCassable(this,joueur);
                    murCassable.setEmplacement(x,y);
                    listeMurs.add(murCassable);
                }
            }
        }

    }



    public ObservableList<MurCassable> getListeMurs() {
        return listeMurs;
    }

    public Map getMap() {
        return map;
    }
}
