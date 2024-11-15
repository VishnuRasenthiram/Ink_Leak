package universite_paris8.iut.ink_leak.Modele.Generateurs;

import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Ennemi;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Puddle;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenMap4 extends GenerateurEnnemis {

    public GenMap4(Environnement env) {
        super(env);
    }

    @Override
    public void creeEnnemis() {
        Ennemi nouvelEnnemi = new Puddle(super.getEnvironnement());
        super.getListeEntite().add(nouvelEnnemi);
        super.setEnnemisPos(nouvelEnnemi);
    }
}
