package universite_paris8.iut.ink_leak.Modele.Generateurs.Ennemis;

import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Abomination;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Ennemi;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenBossMap extends GenerateurEnnemis {

    public GenBossMap(Environnement env) {
        super(env);

    }

    @Override
    public Ennemi creeEnnemis() {
        setBossDejaGenere(true);
        Abomination abomination=new Abomination(super.getEnvironnement());
        super.setAbomination(abomination);
        return abomination;

    }
    public void setEnnemisPos(Entité entity) {

        entity.setPosYProperty(entity.getPosY() );
        entity.setPosXProperty(entity.getPosX()+200 );

    }


}
