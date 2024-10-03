package universite_paris8.iut.ink_leak.Modele.Generateurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.*;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable.ObjetBulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable.ObjetLangue;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable.ObjetPoing;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.ArrayList;


public class GenerateurObjets {

    private ObservableList<Objets> listeObjets;

    private Map map;

    private ObjetBulle objetBulle;
    private ObjetPoing objetPoing;
    private ObjetLangue objetLangue;

    private Tube tube,tube2;

    private Imprimante imprimante,imprimante2;

    public GenerateurObjets(Environnement environnement){
        listeObjets= FXCollections.observableArrayList();
        this.map=environnement.getMap();
        objetBulle= new ObjetBulle(environnement);
        objetPoing= new ObjetPoing(environnement);
        objetLangue= new ObjetLangue(environnement);
        tube= new Tube(environnement);
        tube2= new Tube(environnement);
        imprimante = new Imprimante(environnement);
        imprimante2 = new Imprimante(environnement);
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


            case 2:
                if(objetPoing.getNbFoisRecuperable() !=0){
                    objetPoing.setEmplacement(1, 0);
                    listeObjets.add(objetPoing);
                }
                break;
            case 3:
                if(objetBulle.getNbFoisRecuperable() !=0){
                    objetBulle.setEmplacement(10, 8);
                    listeObjets.add(objetBulle);
                }
                break;
            case 4:
                if(objetLangue.getNbFoisRecuperable() !=0){
                    objetLangue.setEmplacement(18,1);
                    listeObjets.add(objetLangue);
                }
                break;
            case 0:

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

                break;
        }

    }

    public ObservableList<Objets> getListeObjets() {
        return listeObjets;
    }
}
