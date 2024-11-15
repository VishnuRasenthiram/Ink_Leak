package universite_paris8.iut.ink_leak.Modele.Generateurs;

import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Abomination;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Ennemi;
import universite_paris8.iut.ink_leak.Modele.Environnement;

public class GenBossMap extends GenerateurEnnemis {

    public GenBossMap(Environnement env) {
        super(env);
    }

    @Override
    public void creeEnnemis(String nomEnnemi, Environnement environnement) {
        Ennemi nouvelEnnemi = new Abomination(environnement);
        super.getListeEntite().add(nouvelEnnemi);
        super.setEnnemisPos(nouvelEnnemi);
    }
}
