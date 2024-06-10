package universite_paris8.iut.ink_leak.Modele;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Slime;

import java.util.ArrayList;
import java.util.Random;


public class GenerateurEnnemis {

    private ObservableList<Entité> listeEntite ; // Liste pour stocker tous les slimes
    public GenerateurEnnemis(){
        listeEntite= FXCollections.observableArrayList();

    }

    public void activerMob(){
        ArrayList<Entité> listeMort= new ArrayList<>();
        if(!listeEntite.isEmpty()) {
            for (Entité mob : listeEntite) {
                mob.déplacement("E");
                if(mob.getVie()==0){
                    listeMort.add(mob);
                }
            }
            for (Entité mobMort: listeMort) {
                listeEntite.remove(mobMort);
            }
        }
    }
    public void TuerToutLesEnnemis(){
        listeEntite.clear();
    }
    public void genererEnnemis( GenerateurEnnemis liste_entites, Map map){
         // Ajouter le slime à la liste
        switch (map.getNumMap()){

            case 1:

                break;
            case 2:
                break;
            case 3:

                break;
            default:
                Slime slime = new Slime(liste_entites,map); // Créer un nouveau slime
                listeEntite.add(slime);
                setEnnemisPos(slime);
                break;
        }

    }

    private void setEnnemisPos(Entité enti){

        int random = new Random().nextInt(3);
        switch (random){
            case 0:
                enti.setPosYProperty(enti.getPosY() + 200);
                enti.setPosXProperty(enti.getPosX() + 100);
                break;
            case 1:
                enti.setPosYProperty(enti.getPosY() + 100);
                enti.setPosXProperty(enti.getPosX() + 520);
                break;

            case 2:
                enti.setPosYProperty(enti.getPosY() + 580);
                enti.setPosXProperty(enti.getPosX() + 100);

        }


    }

    public ObservableList<Entité> getListeEntite(){
        return listeEntite;
    }

}
