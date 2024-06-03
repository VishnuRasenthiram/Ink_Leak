package universite_paris8.iut.ink_leak.Modele.Entité.Ennemis;


import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

public class Slime extends Entité {
    public static int cpt=0;
    private static ScheduledExecutorService executorService;

    public Slime(  GenerateurEnnemis spawner,Map map){

        super("S"+cpt, 5, 1,1, 1,600,map,spawner);
        cpt++;

    }



    @Override
    public void attaque(Entité entitéAttaqué) {

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


