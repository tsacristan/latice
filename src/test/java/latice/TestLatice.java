package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import latice.model.Case;
import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.model.Plateau;
import latice.model.TypeCase;

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

	@Test
	void TestTaillePlateau() {
		Plateau plateau = new Plateau();
		assertEquals(81,plateau.tailleDuTableau());
	}
	
	@Test
	void TestCaseLuneDuPlateau() {
		Plateau plateau = new Plateau();
		TypeCase lune = TypeCase.CASE_LUNE;
		assertTrue((plateau.grille()[4][4].typeCase() == lune));
	}
	
	void TestDesCasesSoleilsDuPlateau() {
		Plateau plateau = new Plateau();
		TypeCase soleil = TypeCase.CASE_SOLEIL;
		assertTrue((plateau.grille()[0][0].typeCase() == soleil));
		assertTrue((plateau.grille()[1][1].typeCase() == soleil));
		assertTrue((plateau.grille()[2][2].typeCase() == soleil));
		assertTrue((plateau.grille()[0][4].typeCase() == soleil));
		assertTrue((plateau.grille()[0][8].typeCase() == soleil));
		assertTrue((plateau.grille()[1][7].typeCase() == soleil));
		assertTrue((plateau.grille()[2][6].typeCase() == soleil));
		assertTrue((plateau.grille()[4][0].typeCase() == soleil));
		assertTrue((plateau.grille()[8][0].typeCase() == soleil));
		assertTrue((plateau.grille()[7][1].typeCase() == soleil));
		assertTrue((plateau.grille()[6][2].typeCase() == soleil));
		assertTrue((plateau.grille()[8][4].typeCase() == soleil));
		assertTrue((plateau.grille()[8][8].typeCase() == soleil));
		assertTrue((plateau.grille()[7][7].typeCase() == soleil));
		assertTrue((plateau.grille()[6][6].typeCase() == soleil));
		assertTrue((plateau.grille()[4][8].typeCase() == soleil));
	}
}
