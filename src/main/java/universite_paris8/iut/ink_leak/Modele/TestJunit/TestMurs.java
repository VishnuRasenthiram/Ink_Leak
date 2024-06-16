package universite_paris8.iut.ink_leak.Modele.TestJunit;

import org.junit.Test;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.MurCassable;
import universite_paris8.iut.ink_leak.Modele.Entité.Murs.MurPoussable;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurMurs;
import universite_paris8.iut.ink_leak.Modele.Map;

import static org.junit.Assert.*;

public class TestMurs {

    @Test
    public void testMurs() {
        Map map = new Map();
        GenerateurMurs gm = new GenerateurMurs(map);

        map.setMap(1);
        gm.genererMurs();
        assertFalse(gm.getListeMurs().isEmpty()); // vérifie que des murs ont bien été crées

    }

    @Test
    public void testMursDestru() {
        GenerateurMurs gm = new GenerateurMurs(null);
        MurCassable mur = new MurCassable(gm);

        mur.prendre_degat(1);

        assertTrue(gm.getListeMurs().isEmpty()); // vérifie que le mur est bien détruit et n'est plus dans la liste des murs
    }

    @Test
    public void testMurDeplacement() {
        GenerateurMurs gm = new GenerateurMurs(null);
        MurPoussable mur = new MurPoussable(gm);
        mur.setPosXProperty(10);
        mur.setPosYProperty(10);

        mur.déplacementMur("N", null);
        assertEquals(11, mur.getPosY());
    }
}
