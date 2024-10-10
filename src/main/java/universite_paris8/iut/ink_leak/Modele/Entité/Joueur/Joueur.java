package universite_paris8.iut.ink_leak.Modele.Entité.Joueur;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.*;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurMurs;
import universite_paris8.iut.ink_leak.Modele.Map;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class Joueur extends Entité {

    private Timeline timeline;

    private boolean bougable;

    private ObservableList<Pouvoirs> listePouvoirs;
    private IntegerProperty indicePouvoirEnCoursProperty;
    private IntegerProperty oppacitéProperty;





    public Joueur(String nom_joueur, Environnement environnement) {
        super(nom_joueur,  6, 1, 30, 32,1,1000,environnement);
        this.listePouvoirs= FXCollections.observableArrayList();
        this.bougable = true;
        this.oppacitéProperty = new SimpleIntegerProperty(1);

        this.indicePouvoirEnCoursProperty = new SimpleIntegerProperty(0);
    }

    //Change l'indice du pouvoir en cours et permet de revenir à 0 si on dépasse le nombre de pouvoir débloqué
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

        getAttaqueDeBase().setEstEnVie(true);
        getAttaqueDeBase().déplacement(getOrientation());
    }

    public void attaqueAvecPouvoir(){
        int indice=getIndicePouvoirEnCours();
        if(listePouvoirs.get(indice) instanceof Bulle){
            getBulle().déplacement(getOrientation());
        }
        else if (listePouvoirs.get(indice) instanceof Poing) {
            getPoing().déplacement(getOrientation());
        } else if (listePouvoirs.get(indice) instanceof  Langue) {
            getLangue().déplacement(getOrientation());
        }
    }


    //Déplacement du personnage avec un timeline pour avoir de la fluidité dans les mouvements
    @Override
    public void déplacement(String direction) {

            double vitesseJoueur = super.getVitesse_entite()-0.2;
            setMovementState(Joueur.MovementState.WALK);

            if (timeline != null) return;

            timeline = new Timeline(new KeyFrame(Duration.millis(5), event -> {
                Platform.runLater(() -> {
                    if (!getBougable()) return;

                    double x = super.getPosX();
                    double y = super.getPosY();
                    switch (direction) {
                        case "N":
                            if (super.peutAller(x, y - vitesseJoueur, super.getMap())) {
                                if (super.verifierInteractionEnFace(x,y - vitesseJoueur) == 1) {
                                    Timeline timeline = new Timeline();
                                    setBougable(false);
                                    for (int i = 0; i < 2000; i += 2) {
                                        int dx = i/2;
                                        KeyFrame keyFrame = new KeyFrame(Duration.millis(i), e -> {
                                            if (super.peutAller(x, y - dx, super.getMap())){
                                                if (dx > 32){
                                                    if (super.verifierInteractionEnFace(x, y-dx) == 0) {
                                                        stopDeplacement(timeline);
                                                    }
                                                }
                                                super.setPosYProperty(y - dx);
                                            } else {
                                                stopDeplacement(timeline);
                                            }

                                        });
                                        timeline.getKeyFrames().add(keyFrame);
                                    }

                                    timeline.play();
                                } else{
                                    super.setPosYProperty(y - vitesseJoueur);

                                }
                            }
                            break;
                        case "S":
                            if (super.peutAller(x, y + vitesseJoueur, super.getMap())) {
                                if (super.verifierInteractionEnFace(x,y + vitesseJoueur) == 1) {
                                    Timeline timeline = new Timeline();
                                    setBougable(false);

                                    for (int i = 0; i < 2000; i += 2) {
                                        int dx = i/2;
                                        KeyFrame keyFrame = new KeyFrame(Duration.millis(i), e -> {
                                            if (super.peutAller(x, y + dx, super.getMap())){
                                                if (dx > 32){
                                                    if (super.verifierInteractionEnFace(x, y + dx) == 0) {
                                                        stopDeplacement(timeline);
                                                    }
                                                }
                                                super.setPosYProperty(y + dx);
                                            }else {
                                                stopDeplacement(timeline);

                                            }
                                        });

                                        timeline.getKeyFrames().add(keyFrame);
                                    }

                                    timeline.play();
                                } else{
                                    super.setPosYProperty(y + vitesseJoueur);

                                }
                            }
                            break;
                        case "O":
                            if (super.peutAller(x - vitesseJoueur, y, super.getMap())) {
                                if (super.verifierInteractionEnFace(x - vitesseJoueur, y) == 1) {
                                    Timeline timeline = new Timeline();
                                    setBougable(false);

                                    for (int i = 0; i < 2000; i += 2) {
                                        int dx = i/2;

                                        KeyFrame keyFrame = new KeyFrame(Duration.millis(i), e -> {
                                            if (super.peutAller(x - dx, y, super.getMap())){
                                                if (dx > 32){
                                                    if (super.verifierInteractionEnFace(x-dx, y) == 0) {
                                                        stopDeplacement(timeline);
                                                        stopDeplacement(timeline);
                                                    }
                                                }
                                                super.setPosXProperty(x - dx);

                                            }else {
                                                stopDeplacement(timeline);

                                            }
                                        });

                                        timeline.getKeyFrames().add(keyFrame);
                                    }

                                    timeline.play();
                                } else{
                                    super.setPosXProperty(x - vitesseJoueur);

                                }
                            }
                            break;
                        case "E":
                            if (super.peutAller(x + vitesseJoueur, y, super.getMap())) {
                                if (super.verifierInteractionEnFace(x + vitesseJoueur, y) == 1) {
                                    Timeline timeline = new Timeline();
                                    setBougable(false);

                                    for (int i = 0; i < 2000; i += 2) {
                                        int dx = i/2;

                                        KeyFrame keyFrame = new KeyFrame(Duration.millis(i), e -> {

                                           if (super.peutAller(x + dx, y, super.getMap())){
                                               if (dx > 32){
                                                   if (super.verifierInteractionEnFace(x+dx, y) == 0) {
                                                       stopDeplacement(timeline);
                                                   }
                                               }
                                               super.setPosXProperty(x + dx);
                                            }else {
                                                stopDeplacement(timeline);
                                            }
                                        });

                                        timeline.getKeyFrames().add(keyFrame);
                                    }

                                    timeline.play();
                                } else{
                                    super.setPosXProperty(x + vitesseJoueur);

                                }
                            }
                            break;


                    }
                    super.setOrientation(direction);
                });
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();


    }
    public void stopDeplacement(Timeline tl) {
        tl.stop();
        setBougable(true);
    }
    public void stop() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
            setMovementState(Joueur.MovementState.IDLE);
        }
    }


    //gains de pv et limitation à 6
    public void gagner_vie(int nb_vie_gagnee) {
        if (this.getVie() + nb_vie_gagnee > 6) { this.setVie_entite(6); }
        else this.setVie_entite(this.getVie() + nb_vie_gagnee);
    }

    public boolean getBougable(){
        return bougable;

    }
    public Bulle getBulle() {
        return getEnvironnement().getBulle();
    }
    public Poing getPoing() { return getEnvironnement().getPoing(); }
    public Langue getLangue() { return getEnvironnement().getLangue(); }

    public AttaqueDeBase getAttaqueDeBase() {
        return getEnvironnement().getAttaqueDeBase();
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

    public String getOrientation(){
        return super.getOrientation();
    }

}