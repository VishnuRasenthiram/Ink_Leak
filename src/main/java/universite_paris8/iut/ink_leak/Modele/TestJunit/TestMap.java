package universite_paris8.iut.ink_leak.Modele.TestJunit;

import org.junit.Test;
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

}
