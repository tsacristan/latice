package latice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCoordonnees {

	    @Test
	    public void testCoordonneesStockeesCorrectement() {
	        Coordonnees c = new Coordonnees(4, 5);
	        assertEquals(5, c.colonne());
	        assertEquals(4, c.ligne());
	    }

	    @Test
	    public void testCoordonneesSurCoinSuperieurGauche() {
	        Coordonnees c = new Coordonnees(0, 0);
	        assertEquals(0, c.colonne());
	        assertEquals(0, c.ligne());
	    }

	    @Test
	    public void testCoordonneesSurCentrePlateau() {
	        Coordonnees c = new Coordonnees(4, 4);
	        assertEquals(4, c.colonne());
	        assertEquals(4, c.ligne());
	    }

	    @Test
	    public void testCoordonneesSurCoinInferieurDroit() {
	        Coordonnees c = new Coordonnees(8, 8);
	        assertEquals(8, c.colonne());
	        assertEquals(8, c.ligne());
	    }

	    @Test
	    public void testCoordonneesInversées() {
	        Coordonnees c = new Coordonnees(2, 7);
	        assertEquals(7, c.colonne());
	        assertEquals(2, c.ligne());
	    }
	}