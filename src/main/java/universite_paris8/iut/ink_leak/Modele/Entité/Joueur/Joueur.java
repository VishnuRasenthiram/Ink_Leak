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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Joueur extends Entité {
        private static ScheduledExecutorService executorService;
        private ObservableList<Pouvoirs> listePouvoirs;
        private IntegerProperty pouvoirEnCoursProperty;
        private boolean bougable;
        private IntegerProperty oppacitéProperty;
        private IntegerProperty indicePouvoirEnCoursProperty;
        private Bulle bulle;
        private AttaqueDeBase attaqueDeBase;

        public Joueur(String nom_joueur,Map map, GenerateurEnnemis spawner) {
            super(nom_joueur,  6, 1, 30, 32,1,1000,map,spawner);
            this.listePouvoirs= FXCollections.observableArrayList();
            this.bougable = true;
            this.oppacitéProperty = new SimpleIntegerProperty(1);
            attaqueDeBase= new AttaqueDeBase(super.getMap(),super.getSpawner(),this);
            bulle =new Bulle( super.getMap(),super.getSpawner(),this);
            this.indicePouvoirEnCoursProperty = new SimpleIntegerProperty(0);
        }

    public void setPouvoir(int a){
            if(a>0){
                if(getIndicePouvoirEnCours()+1>getListePouvoirs().size()-1){
                    setIndicePouvoirEnCours(0);
                }
                else {
                    setIndicePouvoirEnCours(getIndicePouvoirEnCours()+1);
                }
            }
            else {
                if(getIndicePouvoirEnCours()-1<0){
                    if(getListePouvoirs().isEmpty()){
                        setIndicePouvoirEnCours(0);
                    }
                    else {
                        setIndicePouvoirEnCours(getListePouvoirs().size()-1);
                    }
                }
                else {
                    setIndicePouvoirEnCours(getIndicePouvoirEnCours()-1);
                }
            }

    }

    @Override
    public void attaque() {
        attaqueDeBase.déplacement(getOrientationProperty());
    }

    public void attaqueAvecPouvoir(){
        int indice=getIndicePouvoirEnCours();
        if(listePouvoirs.get(indice) instanceof Bulle){
            bulle.déplacement(getOrientationProperty());
        }
    }

    public void déplacement(String déplacementDirection) {
        try {
            int vitesse_joueur = super.getVitesse_entite();


            if (getBougable() == false) {
                stop();
                return;
            }
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
            setMovementState(Joueur.MovementState.WALK);

            ;
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void stop(){
        if (executorService != null) {
            executorService.shutdownNow();
            executorService = null;
            setMovementState(Joueur.MovementState.IDLE);

        }
    }



        public void gagner_vie(int nb_vie_gagnee) {
            if (this.getVie() + nb_vie_gagnee > 6) { this.setVie_entite(6); }
            else this.setVie_entite(this.getVie() + nb_vie_gagnee);
        }

    public IntegerProperty getOppacitéProperty() {
        return oppacitéProperty;
    }
    public void setOppacitéProperty(int oppacité) {
        this.oppacitéProperty.set(oppacité);
    }
    public boolean getBougable(){
        return bougable;

    }
    public Bulle getBulle() {
        return bulle;
    }

    public AttaqueDeBase getAttaqueDeBase() {
        return attaqueDeBase;
    }

    public void setBougable(boolean bougable){
        this.bougable = bougable;
    }
    public ObservableList<Pouvoirs> getListePouvoirs() {
        return listePouvoirs;
    }

    public IntegerProperty getIndicePouvoirEnCoursProperty() {return indicePouvoirEnCoursProperty;}
    public int getIndicePouvoirEnCours(){
        return indicePouvoirEnCoursProperty.getValue();
    }

    public void setIndicePouvoirEnCours(int pouvoirEnCours) {
        this.indicePouvoirEnCoursProperty.setValue(pouvoirEnCours);
    }
    public Pouvoirs getPouvoirEnCours() {
        if(listePouvoirs.isEmpty()){
            return null;
        }
        return listePouvoirs.get(getIndicePouvoirEnCours());
    }
    public void ajoutPouvoir(Pouvoirs p){
        listePouvoirs.add(p);
    }

}