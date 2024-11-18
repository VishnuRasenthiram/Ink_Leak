package universite_paris8.iut.ink_leak.Modele.Generateurs;

import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Abomination;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Ennemi;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Environnement;

import java.util.Random;

public class GenBossMap extends GenerateurEnnemis {

    public GenBossMap(Environnement env) {
        super(env);

    }

    @Override
    public Ennemi creeEnnemis() {
        setBossDejaGenere(true);
        return new Abomination(super.getEnvironnement());

    }
    public void setEnnemisPos(Entité entity) {

        entity.setPosYProperty(entity.getPosY() );
        entity.setPosXProperty(entity.getPosX()+200 );

    }


}
