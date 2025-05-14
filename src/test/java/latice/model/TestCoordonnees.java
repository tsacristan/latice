package latice.model;

import org.junit.jupiter.api.Test;

import latice.util.PlateauIndexInvalideException;

import static org.junit.jupiter.api.Assertions.*;

public class TestCoordonnees {

	    @Test
	    public void testCoordonneesStockeesCorrectement() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(4, 5);
	        assertEquals(5, c.ligne());
	        assertEquals(4, c.colonne());
	    }

	    @Test
	    public void testCoordonneesSurCoinSuperieurGauche() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(0, 0);
	        assertEquals(0, c.colonne());
	        assertEquals(0, c.ligne());
	    }

	    @Test
	    public void testCoordonneesSurCentrePlateau() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(4, 4);
	        assertEquals(4, c.colonne());
	        assertEquals(4, c.ligne());
	    }

	    @Test
	    public void testCoordonneesSurCoinInferieurDroit() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(8, 8);
	        assertEquals(8, c.colonne());
	        assertEquals(8, c.ligne());
	    }

	    @Test
	    public void testCoordonneesInversées() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(2, 7);
	        assertEquals(7, c.ligne());
	        assertEquals(2, c.colonne());
	    }
	}