package universite_paris8.iut.ink_leak.Modele.Entité.Joueur;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.AttaqueDeBase;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;
import universite_paris8.iut.ink_leak.Vue.VueEntité.VueJoueur.VueAttaque;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Joueur extends Entité {
        private static ScheduledExecutorService executorService;
        private ObservableList<Pouvoirs> listePouvoirs;
        private IntegerProperty pouvoirEnCoursProperty;
        public Joueur(String nom_joueur,Map map, GenerateurEnnemis spawner) {
            super(nom_joueur,  6, 1, 30, 32,1,1000,map,spawner);
            this.listePouvoirs= FXCollections.observableArrayList();
            this.pouvoirEnCoursProperty =new SimpleIntegerProperty(0);


        }

    public ObservableList<Pouvoirs> getListePouvoirs() {
        return listePouvoirs;
    }

    public IntegerProperty getPouvoirEnCoursProperty() {return pouvoirEnCoursProperty;}
    public int getPouvoirEnCours(){
            return pouvoirEnCoursProperty.getValue();
    }

    public void setPouvoirEnCours(int pouvoirEnCours) {
            this.pouvoirEnCoursProperty.setValue(pouvoirEnCours);
    }

    public void setPouvoir(int a){
            if(a>0){
                if(getPouvoirEnCours()+1>getListePouvoirs().size()-1){
                    setPouvoirEnCours(0);
                }
                else {
                    setPouvoirEnCours(getPouvoirEnCours()+1);
                }
            }
            else {
                if(getPouvoirEnCours()-1<0){
                    if(getListePouvoirs().isEmpty()){
                        setPouvoirEnCours(0);
                    }
                    else {
                        setPouvoirEnCours(getListePouvoirs().size()-1);
                    }

                }
                else {
                    setPouvoirEnCours(getPouvoirEnCours()-1);
                }
            }

    }



    @Override
    public void attaque(VueAttaque vA) {

        AttaqueDeBase attaqueDeBase=new AttaqueDeBase(super.getMap(),super.getSpawner(),this);
        vA.afficheAttaque(attaqueDeBase);
        attaqueDeBase.déplacement(getOrientationProperty());
    }

    public void attaqueAvecPouvoir(VueAttaque vA){
        switch (getPouvoirEnCours()){
            case 0:
                Bulle bulle =new Bulle( super.getMap(),super.getSpawner(),this);
                vA.afficheAttaque(bulle);
                listePouvoirs.add(bulle);
                bulle.déplacement(getOrientationProperty());
                break;

        }





    }



    public void déplacement(String déplacementDirection) {
        try {
            int vitesse_joueur = super.getVitesse_entite();



                if (executorService != null) return;

                executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> {
                        double x = super.getPosX();
                        double y = super.getPosY();

                        switch (déplacementDirection) {
                            case "N":
                                if (super.peutAller(x, y - vitesse_joueur, super.getMap())) {
                                    super.setPosYProperty(super.getPosY() - vitesse_joueur);
                                    super.setOrientationProperty("N");
                                }
                                break;

                            case "S":
                                if (super.peutAller(x, y + vitesse_joueur, super.getMap())) {
                                    super.setPosYProperty(super.getPosY() + vitesse_joueur);
                                    super.setOrientationProperty("S");
                                }
                                break;

                            case "O":
                                if (super.peutAller(x - vitesse_joueur, y, super.getMap())) {
                                    super.setPosXProperty(super.getPosX() - vitesse_joueur);
                                    super.setOrientationProperty("O");
                                }
                                break;
                            case "E":
                                if (super.peutAller(x + vitesse_joueur, y, super.getMap())) {
                                    super.setPosXProperty(super.getPosX() + vitesse_joueur);
                                    super.setOrientationProperty("E");
                                }
                                break;
                        }

                    });
                }, 0, 5, TimeUnit.MILLISECONDS); // un delay entre les mouvements
            ;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void stop(){
        if (executorService != null) {
            executorService.shutdownNow();
            executorService = null;
        }
    }



        public void gagner_vie(int nb_vie_gagnee) {
            if (this.getVie() + nb_vie_gagnee > 6) { this.setVie_entite(6); }
            else this.setVie_entite(this.getVie() + nb_vie_gagnee);
        }

    }