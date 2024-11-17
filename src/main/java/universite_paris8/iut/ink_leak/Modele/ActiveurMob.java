package universite_paris8.iut.ink_leak.Modele;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;

import java.util.ArrayList;

public class ActiveurMob {
        private Environnement environnement;
        public ActiveurMob(Environnement environnement) {
            this.environnement = environnement;
        }

    public void activerMob() {
        ArrayList<Entité> listeMort = new ArrayList<>();
            for (Entité mob : environnement.getGenerateurEnnemis().getListeEntite()) {
                mob.déplacement("5");
                if (mob.getVie() == 0) {
                    listeMort.add(mob);
                }
            }
        environnement.getGenerateurEnnemis().getListeEntite().removeAll(listeMort);
    }
}
