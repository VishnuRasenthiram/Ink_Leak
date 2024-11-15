package universite_paris8.iut.ink_leak.Modele.Generateurs;

import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Ennemi;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Slime;
import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenMap1 extends GenerateurEnnemis {

    public GenMap1(Environnement env) {
        super(env);
    }

    @Override
    public void creeEnnemis(String nomEnnemi, Environnement environnement) {
        Ennemi nouvelEnnemi = new Slime(environnement);
        super.getListeEntite().add(nouvelEnnemi);
        super.setEnnemisPos(nouvelEnnemi);
    }
}
