package latice.model.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import latice.util.exception.PlateauIndexInvalideException;

class TestCoordonnees {

	    @Test
	    void testCoordonneesStockeesCorrectement() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(4, 5);
	        assertEquals(5, c.ligne());
	        assertEquals(4, c.colonne());
	    }

	    @Test
	    void testCoordonneesSurCoinSuperieurGauche() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(0, 0);
	        assertEquals(0, c.colonne());
	        assertEquals(0, c.ligne());
	    }

	    @Test
	    void testCoordonneesSurCentrePlateau() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(4, 4);
	        assertEquals(4, c.colonne());
	        assertEquals(4, c.ligne());
	    }

	    @Test
	    void testCoordonneesSurCoinInferieurDroit() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(8, 8);
	        assertEquals(8, c.colonne());
	        assertEquals(8, c.ligne());
	    }

	    @Test
	    void testCoordonneesInversées() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(2, 7);
	        assertEquals(7, c.ligne());
	        assertEquals(2, c.colonne());
	    }
	}