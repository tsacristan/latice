package latice.model.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import latice.util.exception.PlateauIndexInvalideException;

class TestCoordonnees {

	    @Test
	    void testCoordonneesStockeesCorrectementLigne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(4, 5);
	        Integer ligne = c.ligne();
	        assertEquals(5, ligne);
	    }
	    @Test
	    void testCoordonneesStockeesCorrectementColonne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(4, 5);
	        Integer colonne = c.colonne();
	        assertEquals(4, colonne);
	    }

	    @Test
	    void testCoordonneesSurCoinSuperieurGaucheLigne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(0, 0);
	        Integer ligne = c.ligne();
	        assertEquals(0, ligne);
	    }
	    
	    @Test
	    void testCoordonneesSurCoinSuperieurGaucheColonne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(0, 0);
	        Integer colonne = c.colonne();
	        assertEquals(0, colonne);
	    }

	    @Test
	    void testCoordonneesSurCentrePlateauLigne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(4, 4);
	        Integer ligne = c.ligne();
	        assertEquals(4, ligne);
	    }
	    
	    @Test
	    void testCoordonneesSurCentrePlateauColonne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(4, 4);
	        Integer colonne = c.colonne();
	        assertEquals(4, colonne);
	    }

	    @Test
	    void testCoordonneesSurCoinInferieurDroitLigne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(8, 8);
	        Integer ligne = c.ligne();
	        assertEquals(8, ligne);
	    }
	    
	    @Test
	    void testCoordonneesSurCoinInferieurDroitColonne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(8, 8);
	        Integer colonne = c.colonne();
	        assertEquals(8, colonne);
	    }

	    @Test
	    void testCoordonneesInverséesLigne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(2, 7);
	        Integer ligne = c.ligne();
	        assertEquals(7, ligne);

	    }
	    
	    @Test
	    void testCoordonneesInverséesColonne() throws PlateauIndexInvalideException {
	        Coordonnees c = new Coordonnees(2, 7);
	        Integer colonne = c.colonne();
	        assertEquals(2, colonne);
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteHauteLigne() throws PlateauIndexInvalideException{ 
	    	Coordonnees c = new Coordonnees(1,7);
	    	Integer ligne = c.verificationCaseAdjacenteHaute().ligne();
	    	assertEquals(8,ligne);
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteHauteColonne() throws PlateauIndexInvalideException{ 
	    	Coordonnees c = new Coordonnees(1,7);
	    	Integer colonne = c.verificationCaseAdjacenteHaute().colonne();
	    	assertEquals(1,colonne);
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteBasLigne() throws PlateauIndexInvalideException{ 
	    	Coordonnees c = new Coordonnees(1,7);
	    	Integer ligne = c.verificationCaseAdjacenteBas().ligne();
	    	assertEquals(6,ligne);
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteBasColonne() throws PlateauIndexInvalideException{ 
	    	Coordonnees c = new Coordonnees(1,7);
	    	Integer colonne = c.verificationCaseAdjacenteBas().colonne();
	    	assertEquals(1,colonne);
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteGaucheLigne() throws PlateauIndexInvalideException{ 
	    	Coordonnees c = new Coordonnees(1,7);
	    	Integer ligne = c.verificationCaseAdjacenteGauche().ligne();
	    	assertEquals(7,ligne);
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteGaucheColonne() throws PlateauIndexInvalideException{ 
	    	Coordonnees c = new Coordonnees(1,7);
	    	Integer colonne = c.verificationCaseAdjacenteGauche().colonne();
	    	assertEquals(0,colonne);
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteDroiteLigne() throws PlateauIndexInvalideException{ 
	    	Coordonnees c = new Coordonnees(1,7);
	    	Integer ligne = c.verificationCaseAdjacenteDroite().ligne();
	    	assertEquals(7,ligne);
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteDroiteColonne() throws PlateauIndexInvalideException{ 
	    	Coordonnees c = new Coordonnees(1,7);
	    	Integer colonne = c.verificationCaseAdjacenteDroite().colonne();
	    	assertEquals(2,colonne);
	    }
}
	    
	   