package universite_paris8.iut.ink_leak.Modele.TestJunit;

import org.junit.Test;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetPoing;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;
import static org.junit.Assert.*;

public class TestMap {

    @Test
    public void testChangementMap() {
        Map map = new Map();

        map.setMap(1);
        assertEquals(1, map.getNumMap()); // vérifie si la map est bien devenue la map 1

        map.setMap(-1);
        assertEquals(0, map.getNumMap()); // vérifie bien si la map est devenue la map par défaut

    }

    @Test
    public void testObjetMap() {
        Map map = new Map();
        GenerateurObjets go = new GenerateurObjets(map, null);
        int cpt = 0;

        map.setMap(2);
        go.genererObjets();

        for (Objets o : go.getListeObjets()) {
            if (o instanceof ObjetPoing) {
                cpt++;
            }
        }
        assertEquals(1, cpt); // vérifie si une instance d'ObjetPoing est bien dans la liste d'objets quand on est dans la map 2

        go.getListeObjets().clear();
        cpt = 0;

        map.setMap(3);
        go.genererObjets();

        for (Objets o : go.getListeObjets()) {
            if (o instanceof ObjetPoing) {
                cpt++;
            }
        }
        assertEquals(0, cpt); // vérifie qu'il n'y a pas d'instance d'ObjetPoing quand on est dans une autre map que la 2



    }

}
