package universite_paris8.iut.ink_leak.Modele.TestJunit;

import org.junit.Test;
import universite_paris8.iut.ink_leak.Modele.Entité.Ennemis.Slime;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Map;
import static org.junit.Assert.*;

public class TestEnnemis {

    @Test
    public void testGenererEnnemi() {
        Map map = new Map();
        map.setMap(0);
        GenerateurEnnemis ge = new GenerateurEnnemis();

        ge.genererEnnemis(map , null);
        assertFalse(ge.getListeEntite().isEmpty()); // vérifie qu'un ennemi a bien été généré dans la map par défaut

    }

    @Test
    public void testEnnemiVie() {
        Slime s = new Slime(null, null, null);

        s.prendre_degat(1);
        assertEquals(1, s.getVie()); // vérifie qu'un slime peut bien perdre de la vie
    }

    @Test
    public void testEnnemiMort() {
        GenerateurEnnemis ge = new GenerateurEnnemis();
        Slime s = new Slime(ge, null, null);

        s.prendre_degat(2);
        assertTrue(ge.getListeEntite().isEmpty()); // vérifie que le slime est bien mort et n'est plus dans la liste d'ennemis

    }

}
