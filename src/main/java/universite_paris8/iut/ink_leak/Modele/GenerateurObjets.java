package universite_paris8.iut.ink_leak.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetBulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetPoing;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;

import java.util.ArrayList;


public class GenerateurObjets {
    private ObservableList<Objets> listeObjets ; // Liste pour stocker tous les objets
    private Map map;

    private ObjetBulle objetBulle;
    private ObjetPoing objetPoing;
    public GenerateurObjets(Map map, Joueur joueur){
        listeObjets= FXCollections.observableArrayList();
        this.map=map;
        objetBulle= new ObjetBulle(map,this,joueur);
        objetPoing= new ObjetPoing(map,this,joueur);

    }
    public void activerObjet(){
        ArrayList<Objets> listeEnleve= new ArrayList<>();
        if(!listeObjets.isEmpty()) {
            for (Objets objet : listeObjets) {
                objet.action();
                if(objet.getNbFoisRecuperable()==0){
                    listeEnleve.add(objet);
                }
            }
            for (Objets objets: listeEnleve) {
                listeObjets.remove(objets);
            }
        }
    }
    public void EnleverToutLesObjets(){
        listeObjets.clear();
    }
    public void genererObjets(){
        switch (map.getNumMap()){

            case 1:
                if(objetBulle.getNbFoisRecuperable() !=0){
                    setObjetsPos(objetBulle);
                    listeObjets.add(objetBulle);
                }
                break;
            case 2:
                if(objetPoing.getNbFoisRecuperable() !=0){
                    setObjetsPos(objetPoing);
                    listeObjets.add(objetPoing);
                }
                break;
            case 3:

                break;
            default:

                break;
        }

    }

    private void setObjetsPos(Objets objet){


        switch (map.getNumMap()){
            case 0:
                objet.setEmplacement(15,15);
                break;
            case 1:
                objet.setEmplacement(16,15);
                break;

            case 2:
                objet.setEmplacement(17,15);

        }


    }

    public ObservableList<Objets> getListeObjets() {
        return listeObjets;
    }
}
