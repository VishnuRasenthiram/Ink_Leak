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

    private Bulle bulle;
    private Poing poing;
    private Langue langue;
    private AttaqueDeBase attaqueDeBase;


    public Joueur(String nom_joueur, Map map, GenerateurEnnemis generateurEnnemis,GenerateurMurs generateurMurs ) {
        super(nom_joueur,  6, 1, 30, 32,1,1000,map,generateurEnnemis,null,generateurMurs);
        this.listePouvoirs= FXCollections.observableArrayList();
        this.bougable = true;
        this.oppacitéProperty = new SimpleIntegerProperty(1);
        attaqueDeBase= new AttaqueDeBase(super.getMap(),super.getGenerateurEnnemis(),this);
        bulle =new Bulle( super.getMap(),super.getGenerateurEnnemis(),this);
        poing = new Poing(super.getMap(), super.getGenerateurEnnemis(), this);
        langue = new Langue(super.getMap(), super.getGenerateurEnnemis(), this);
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
            attaqueDeBase.setEstEnVie(true);
        attaqueDeBase.déplacement(getOrientation());
    }

    public void attaqueAvecPouvoir(){
        int indice=getIndicePouvoirEnCours();
        if(listePouvoirs.get(indice) instanceof Bulle){
            bulle.déplacement(getOrientation());
        }
        else if (listePouvoirs.get(indice) instanceof Poing) {
            poing.déplacement(getOrientation());
        } else if (listePouvoirs.get(indice) instanceof  Langue) {
            langue.déplacement(getOrientation());
        }
    }


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
                                            System.out.println((super.verifierInteractionEnFace(x + 16, y) != 0));
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
    public Poing getPoing() { return poing; }
    public Langue getLangue() { return langue; }

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

    public String getOrientation(){
        return super.getOrientation();
    }

}