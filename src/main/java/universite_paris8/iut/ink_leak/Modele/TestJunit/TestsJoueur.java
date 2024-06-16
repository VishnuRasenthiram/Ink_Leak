package universite_paris8.iut.ink_leak.Modele.TestJunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Bulle;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Langue;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Poing;
import universite_paris8.iut.ink_leak.Modele.Map;

public class TestsJoueur {

    @Test
    public void testPeutAller(){
        Map map = new Map();
        map.setMap(0);
        Joueur j = new Joueur("s",map, null ,null);
        assertTrue(j.peutAller(300,300,map));// test position ou le joueur peutAller
        assertFalse(j.peutAller(1500,300,map));// test position en dehors de la map en X
        assertFalse(j.peutAller(300,1500,map));//test position en dehors de la map en Y
        assertFalse(j.peutAller(0,0,map));//test position ou il y a un mur

    }
    @Test
    public void testGagneEtPerdreVie(){
        Map map = new Map();
        map.setMap(0);
        Joueur j = new Joueur("s",map, null ,null);

        j.gagner_vie(1);
        assertEquals(6,j.getVie());

        j.setVie_entite(0);
        j.gagner_vie(7);
        assertEquals(6,j.getVie());

        j.prendre_degat(1);
        assertEquals(5,j.getVie());

        j.setVie_entite(0);
        j.prendre_degat(1);
        assertEquals(0,j.getVie());

    }
    @Test
    public void testSetPouvoir() {
        Map map = new Map();
        map.setMap(0);
        Joueur j = new Joueur("s",map, null ,null);
        j.ajoutPouvoir(new Bulle(map, null, j));
        j.ajoutPouvoir(new Poing(map, null, j));
        j.ajoutPouvoir(new Langue(map, null, j));

        j.setPouvoir(1);
        assertEquals(1, j.getIndicePouvoirEnCours());

        j.setPouvoir(1);
        assertEquals(2, j.getIndicePouvoirEnCours());

        j.setPouvoir(1);
        assertEquals(0, j.getIndicePouvoirEnCours());

        j.setPouvoir(-1);
        assertEquals(2, j.getIndicePouvoirEnCours());
    }

}
