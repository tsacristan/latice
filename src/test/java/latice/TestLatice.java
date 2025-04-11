package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import latice.model.PileDebut;
import latice.model.PileJoueur;

class TestLatice {

	@Test
	void TestTaillePileDebutEgaleA72() {
		PileDebut pile = new PileDebut();
		pile.remplir();
		
		assertEquals(72,pile.size());
	}
	
	@Test
	void TestSiLaPileEstMelanger() {
		PileDebut pile = new PileDebut();
		PileDebut pile2 = new PileDebut();
		pile.remplir();
		pile2.remplir();
		pile2.melanger();
		
		assertNotEquals(pile,pile2);
		
	}
	
	@Test
	void TestTailleDesPilesJoueursEgales() {
		PileDebut pile = new PileDebut();
		pile.remplir();
		
		
		PileJoueur pile1 = new PileJoueur();
		PileJoueur pile2 = new PileJoueur();
		pile.distribuer(new PileJoueur[]{pile1, pile2});
		
		assertEquals(pile1.size(),pile2.size());
		
		
	}

}
