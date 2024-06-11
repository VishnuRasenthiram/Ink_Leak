package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.Random;


public class Slime extends Entité {
    public static int cpt=0;


    public Slime(GenerateurEnnemis generateurEnnemis, Map map){

        super("S"+cpt, 2, 1,32, 32,1,600,map, generateurEnnemis,null);
        cpt++;

    }



    @Override
    public void attaque() {

    }

    @Override
    public void déplacement(String déplacementDirection) {

                    int random = new Random().nextInt(7);
                    double x = super.getPosX();
                    double y = super.getPosY();

                    switch (random) {
                        case 0:
                            if (super.peutAller(x, y - super.getVitesse_entite(), super.getMap())) {
                                super.setPosYProperty(y - super.getVitesse_entite());

                            }
                            break;
                        case 1:
                            if (super.peutAller(x, y + super.getVitesse_entite(), super.getMap())) {
                                super.setPosYProperty(y + super.getVitesse_entite());

                            }
                            break;
                        case 2:

                            if (super.peutAller(x - super.getVitesse_entite(), y, super.getMap())) {
                                super.setPosXProperty(x - super.getVitesse_entite());

                            }
                            break;
                        case 3:
                            if (super.peutAller(x + super.getVitesse_entite(), y, super.getMap())) {
                                super.setPosXProperty(x + super.getVitesse_entite());
                            }
                            break;
                        default:
                            break;

                    }

    }



    @Override
    public void gagner_vie(int nb_vie_gagnee) {
        System.out.println("aaaa");
    }
}


