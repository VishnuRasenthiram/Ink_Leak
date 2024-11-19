package universite_paris8.iut.ink_leak.Modele.Generateurs.Ennemis;

import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Ennemi;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Puddle;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Environnement;

import java.util.Random;

public class GenMapLabyrinthe extends GenerateurEnnemis {

    public GenMapLabyrinthe(Environnement env) {
        super(env);
        GenerateurEnnemis.setInstance(this);
    }

    @Override
    public Ennemi creeEnnemis() {
        return new Puddle(super.getEnvironnement());

    }
    public void setEnnemisPos(Entité entity) {

        int random = new Random().nextInt(3);

        switch (random) {
            case 0:
                entity.setPosYProperty(entity.getPosY() + 200);
                entity.setPosXProperty(entity.getPosX() + 100);
                break;
            case 1:
                entity.setPosYProperty(entity.getPosY() + 100);
                entity.setPosXProperty(entity.getPosX() + 520);
                break;

            case 2:
                entity.setPosYProperty(entity.getPosY() + 580);
                entity.setPosXProperty(entity.getPosX() + 100);

        }


    }
}
