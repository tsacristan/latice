package latice.model.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.model.material.Case;
import latice.model.material.Couleur;
import latice.model.material.Forme;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.util.exception.PlateauIndexInvalideException;

class TestCoordonnees {
		
	private Coordonnees centre;
	private Coordonnees hautGauche;
	private Coordonnees basDroite;
	private Coordonnees c1_7;
	private Coordonnees c1_8;
	private Coordonnees c4_5;
	private Coordonnees c2_7;
	private Coordonnees c8_5;
	private Coordonnees c6_0;
	private Coordonnees c0_5;
	private Coordonnees c2_5;
	private Coordonnees c2_5_bis;
	
	
	
	@BeforeEach
	void setUp() throws PlateauIndexInvalideException {
	    centre = new Coordonnees(4, 4);
	    hautGauche = new Coordonnees(0, 0);
	    basDroite = new Coordonnees(8, 8);
	    c1_7 = new Coordonnees(1, 7);
	    c1_8 = new Coordonnees(1, 8);
	    c4_5 = new Coordonnees(4, 5);
	    c2_7 = new Coordonnees(2, 7);
	    c8_5 = new Coordonnees(8,5);
	    c6_0 = new Coordonnees(6, 0);
	    c0_5 = new Coordonnees(0, 5);
	    c2_5 =  new Coordonnees(2,5);
	    c2_5_bis =  new Coordonnees(2,5);
	}

			
	    @Test
	    void testCoordonneesStockeesCorrectementLigne() throws PlateauIndexInvalideException {
	        assertEquals(5, c4_5.ligne());
	    }
	    @Test
	    void testCoordonneesStockeesCorrectementColonne() throws PlateauIndexInvalideException {
	        assertEquals(4, c4_5.colonne());
	    }

	    @Test
	    void testCoordonneesSurCoinSuperieurGaucheLigne() throws PlateauIndexInvalideException {
	        assertEquals(0,hautGauche.ligne());
	    }
	    
	    @Test
	    void testCoordonneesSurCoinSuperieurGaucheColonne() throws PlateauIndexInvalideException {
	        assertEquals(0, hautGauche.colonne());
	    }

	    @Test
	    void testCoordonneesSurCentrePlateauLigne() throws PlateauIndexInvalideException {
	        assertEquals(4, centre.ligne());
	    }
	    
	    @Test
	    void testCoordonneesSurCentrePlateauColonne() throws PlateauIndexInvalideException {
	        assertEquals(4, centre.colonne());
	    }

	    @Test
	    void testCoordonneesSurCoinInferieurDroitLigne() throws PlateauIndexInvalideException {
	        assertEquals(8, basDroite.ligne());
	    }
	    
	    @Test
	    void testCoordonneesSurCoinInferieurDroitColonne() throws PlateauIndexInvalideException {
	        assertEquals(8, basDroite.colonne());
	    }

	    @Test
	    void testCoordonneesInverséesLigne() throws PlateauIndexInvalideException {
	        assertEquals(7, c2_7.ligne());

	    }
	    
	    @Test
	    void testCoordonneesInverséesColonne() throws PlateauIndexInvalideException {
	        assertEquals(2, c2_7.colonne());
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteHauteLigne() throws PlateauIndexInvalideException{ 
	    	assertEquals(8,c1_7.verificationCaseAdjacenteHaute().ligne());
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteHauteColonne() throws PlateauIndexInvalideException{ 
	    	assertEquals(1,c1_7.verificationCaseAdjacenteHaute().colonne());
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteBasLigne() throws PlateauIndexInvalideException{ 
	    	assertEquals(6,c1_7.verificationCaseAdjacenteBas().ligne());
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteBasColonne() throws PlateauIndexInvalideException{ 
	    	assertEquals(1,c1_7.verificationCaseAdjacenteBas().colonne());
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteGaucheLigne() throws PlateauIndexInvalideException{ 
	    	assertEquals(7,c1_7.verificationCaseAdjacenteGauche().ligne());
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteGaucheColonne() throws PlateauIndexInvalideException{ 
	    	assertEquals(0,c1_7.verificationCaseAdjacenteGauche().colonne());
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteDroiteLigne() throws PlateauIndexInvalideException{ 
	    	assertEquals(7,c1_7.verificationCaseAdjacenteDroite().ligne());
	    }
	    
	    @Test
	    void testverificationCaseAdjacenteDroiteColonne() throws PlateauIndexInvalideException{ 
	    	assertEquals(2,c1_7.verificationCaseAdjacenteDroite().colonne());
	    }
	
	   @Test
	    void testVerificationCaseAdjacenteHautePlateauInvalide() throws PlateauIndexInvalideException {    	
	    	assertEquals(null,c1_8.verificationCaseAdjacenteHaute());
	   }
	    
	    @Test
	    void testVerificationCaseAdjacenteDroitePlateauInvalide() throws PlateauIndexInvalideException {	    	
	    	assertEquals(null,c8_5.verificationCaseAdjacenteDroite());
	    }
	    
	    @Test
	    void testVerificationCaseAdjacenteBasPlateauInvalide() throws PlateauIndexInvalideException {	    	
	    	assertEquals(null,c6_0.verificationCaseAdjacenteBas());
	    }
	    
	    @Test
	    void testVerificationCaseAdjacenteGauchePlateauInvalide() throws PlateauIndexInvalideException {
	    	assertEquals(null,c0_5.verificationCaseAdjacenteGauche());
	    }
	    
	    @Test 
	    void testPasEgale() throws PlateauIndexInvalideException {	    	
	    	assertNotEquals(null,c2_5.verificationCaseAdjacenteGauche());
	    }
	    
	    
	    
	    @Test
	    void testEqualsGetClass() throws PlateauIndexInvalideException {
	    	class Coordonnees2 {
		        private int ligne;
		        private int colonne;

		        public Coordonnees2(int ligne, int colonne) {
		            this.ligne = ligne;
		            this.colonne = colonne;
		        }
		    }
	    	Coordonnees2 c2 = new Coordonnees2(2,5);
	    	assertNotEquals(c2_5,c2);
	    }
	    
	    @Test 
	    void testHashode() throws PlateauIndexInvalideException {	    	
	    	int hashCode1 = c2_5.hashCode();
	    	int hashCode2 = c2_5_bis.hashCode();
	    	
	    	assertEquals(hashCode1,hashCode2);
	    }
}
	    
	   