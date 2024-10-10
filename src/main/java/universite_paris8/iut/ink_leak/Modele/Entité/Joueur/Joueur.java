package universite_paris8.iut.ink_leak.Modele.Entité.Joueur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.*;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class Joueur extends Entité {

    private Timeline timeline;

    private boolean bougable;

    private ObservableList<Pouvoirs> listePouvoirs;
    private IntegerProperty indicePouvoirEnCoursProperty;
    private IntegerProperty oppacitéProperty;


    public Joueur(String nom_joueur, Environnement environnement) {
        super(nom_joueur, 6, 1, 30, 32, 1, 1000, environnement);
        this.listePouvoirs = FXCollections.observableArrayList();
        this.bougable = true;
        this.oppacitéProperty = new SimpleIntegerProperty(1);

        this.indicePouvoirEnCoursProperty = new SimpleIntegerProperty(0);
    }

    //Change l'indice du pouvoir en cours et permet de revenir à 0 si on dépasse le nombre de pouvoir débloqué
    public void setPouvoir(int a) {
        if (a > 0) {
            if (getIndicePouvoirEnCours() + 1 > getListePouvoirs().size() - 1) {
                setIndicePouvoirEnCours(0);
            } else {
                setIndicePouvoirEnCours(getIndicePouvoirEnCours() + 1);
            }
        } else {
            if (getIndicePouvoirEnCours() - 1 < 0) {
                if (getListePouvoirs().isEmpty()) {
                    setIndicePouvoirEnCours(0);
                } else {
                    setIndicePouvoirEnCours(getListePouvoirs().size() - 1);
                }
            } else {
                setIndicePouvoirEnCours(getIndicePouvoirEnCours() - 1);
            }
        }

    }

    @Override
    public void attaque() {

        getAttaqueDeBase().setEstEnVie(true);
        getAttaqueDeBase().déplacement(getOrientation());
    }

    public void attaqueAvecPouvoir() {
        int indice = getIndicePouvoirEnCours();
        if (listePouvoirs.get(indice) instanceof Bulle) {
            getBulle().déplacement(getOrientation());
        } else if (listePouvoirs.get(indice) instanceof Poing) {
            getPoing().déplacement(getOrientation());
        } else if (listePouvoirs.get(indice) instanceof Langue) {
            getLangue().déplacement(getOrientation());
        }
    }


    @Override
    public void déplacement(String direction) {
        double vitesseJoueur = getVitesse_entite() - 0.2;
        setMovementState(Joueur.MovementState.WALK);

        if (timeline != null) return;

        double deltaX = 0, deltaY = 0;
        switch (direction) {
            case "N":
                deltaY = -vitesseJoueur;
                break;
            case "S":
                deltaY = vitesseJoueur;
                break;
            case "O":
                deltaX = -vitesseJoueur;
                break;
            case "E":
                deltaX = vitesseJoueur;
                break;
            default:
                return;
        }

        double finalDeltaX = deltaX;
        double finalDeltaY = deltaY;
        timeline = new Timeline(new KeyFrame(Duration.millis(5), event -> {
            Platform.runLater(() -> {
                if (!getBougable()) return;

                double x = getPosX();
                double y = getPosY();
                double newX = x + finalDeltaX;
                double newY = y + finalDeltaY;

                if (peutAller(newX, newY, getMap())) {
                    handleMovement(newX, newY, finalDeltaX, finalDeltaY);
                }
                setOrientation(direction);
            });
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void handleMovement(double newX, double newY, double deltaX, double deltaY) {
        if (verifierInteractionEnFace(newX, newY) == 1) {
            Timeline interactionTimeline = new Timeline();
            setBougable(false);
            for (int i = 0; i < 2000; i += 2) {
                int dx = i / 2;
                KeyFrame keyFrame = new KeyFrame(Duration.millis(i), e -> {
                    double tempX = getPosX() + dx * Math.signum(deltaX);
                    double tempY = getPosY() + dx * Math.signum(deltaY);

                    if (peutAller(tempX, tempY, getMap())) {
                        if (dx > 32 && verifierInteractionEnFace(tempX, tempY) == 0) {
                            stopDeplacement(interactionTimeline);
                        }
                        setPosXProperty(tempX);
                        setPosYProperty(tempY);
                    } else {
                        stopDeplacement(interactionTimeline);
                    }
                });
                interactionTimeline.getKeyFrames().add(keyFrame);
            }
            interactionTimeline.play();
        } else {
            setPosXProperty(newX);
            setPosYProperty(newY);
        }
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
        if (this.getVie() + nb_vie_gagnee > 6) {
            this.setVie_entite(6);
        } else this.setVie_entite(this.getVie() + nb_vie_gagnee);
    }

    public boolean getBougable() {
        return bougable;

    }

    public Bulle getBulle() {
        return getEnvironnement().getBulle();
    }

    public Poing getPoing() {
        return getEnvironnement().getPoing();
    }

    public Langue getLangue() {
        return getEnvironnement().getLangue();
    }

    public AttaqueDeBase getAttaqueDeBase() {
        return getEnvironnement().getAttaqueDeBase();
    }

    public void setBougable(boolean bougable) {
        this.bougable = bougable;
    }

    public ObservableList<Pouvoirs> getListePouvoirs() {
        return listePouvoirs;
    }

    public IntegerProperty getIndicePouvoirEnCoursProperty() {
        return indicePouvoirEnCoursProperty;
    }

    public int getIndicePouvoirEnCours() {
        return indicePouvoirEnCoursProperty.getValue();
    }

    public void setIndicePouvoirEnCours(int pouvoirEnCours) {
        this.indicePouvoirEnCoursProperty.setValue(pouvoirEnCours);
    }

    public Pouvoirs getPouvoirEnCours() {
        if (listePouvoirs.isEmpty()) {
            return null;
        }
        return listePouvoirs.get(getIndicePouvoirEnCours());
    }

    public void ajoutPouvoir(Pouvoirs p) {
        listePouvoirs.add(p);
    }

    public String getOrientation() {
        return super.getOrientation();
    }

}