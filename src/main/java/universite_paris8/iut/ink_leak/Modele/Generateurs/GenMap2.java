package universite_paris8.iut.ink_leak.Modele.Generateurs;

import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Ennemi;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.HeadLess;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenMap2 extends GenerateurEnnemis{

    public GenMap2(Environnement env) {
        super(env);
    }

    @Override
    public void creeEnnemis() {
        Ennemi nouvelEnnemi = new HeadLess(super.getEnvironnement());
        System.out.println(nouvelEnnemi);
        super.getListeEntite().add(nouvelEnnemi);
        super.setEnnemisPos(nouvelEnnemi);
        System.out.println(super.getListeEntite());
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
    }
}
