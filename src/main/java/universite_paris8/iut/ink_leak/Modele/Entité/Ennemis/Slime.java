package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;


public class Slime extends Entité {

    public static int cpt=0;
    public final Joueur joueur;

    public Slime(GenerateurEnnemis generateurEnnemis, Map map, Joueur joueur){
        super("Slime"+cpt, 2, 1,32, 32,2,200,map, generateurEnnemis,null,null);
        this.joueur = joueur;
        cpt++;

    }

    @Override
    public void attaque() {
        if(this.enContact(joueur)){
            joueur.prendre_degat(1);
        }
    }

    @Override
    public void déplacement(String déplacementDirection) {

                    double x = super.getPosX();
                    double y = super.getPosY();
                    //transformer déplacement en int pour le switch
                    int direction = Integer.parseInt(déplacementDirection);

                    switch (direction) {
                        case 0:
                            if (super.peutAller(x, y - super.getVitesse_entite(), super.getMap())) {
                                super.setPosYProperty(y - super.getVitesse_entite());
                                super.setOrientationProperty("S");

                            }
                            break;
                        case 1:
                            if (super.peutAller(x, y + super.getVitesse_entite(), super.getMap())) {
                                super.setPosYProperty(y + super.getVitesse_entite());
                                super.setOrientationProperty("N");

                            }
                            break;
                        case 2:

                            if (super.peutAller(x - super.getVitesse_entite(), y, super.getMap())) {
                                super.setPosXProperty(x - super.getVitesse_entite());
                                super.setOrientationProperty("O");

                            }
                            break;
                        case 3:
                            if (super.peutAller(x + super.getVitesse_entite(), y, super.getMap())) {
                                super.setPosXProperty(x + super.getVitesse_entite());
                                super.setOrientationProperty("E");
                            }
                            break;
                        default:
                            break;

                    }
                    attaque();
    }

    @Override
    public void gagner_vie(int nb_vie_gagnee) { System.out.println("aaaa"); }
}


