package universite_paris8.iut.ink_leak.Modele;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;

import java.util.ArrayList;

public class ActiveurMob {
        private static GenerateurEnnemis generateurEnnemis;
        public ActiveurMob(GenerateurEnnemis generateurEnnemis) {
            this.generateurEnnemis = generateurEnnemis;
        }

    public void activerMob() {
        ArrayList<Entité> listeMort = new ArrayList<>();
            for (Entité mob : generateurEnnemis.getListeEntite()) {
                mob.déplacement("5");
                if (mob.getVie() == 0) {
                    listeMort.add(mob);
                }
            }
            generateurEnnemis.getListeEntite().removeAll(listeMort);
    }
}
