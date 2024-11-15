package universite_paris8.iut.ink_leak.Modele.Generateurs;

import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Ennemi;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Faker;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenMap3 extends GenerateurEnnemis {

    public GenMap3(Environnement env) {
        super(env);
    }

    @Override
    public void creeEnnemis() {
        Ennemi nouvelEnnemi = new Faker(super.getEnvironnement());
        super.getListeEntite().add(nouvelEnnemi);
        super.setEnnemisPos(nouvelEnnemi);
    }
}
