package latice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import latice.model.Case;
import latice.model.Couleur;
import latice.model.Forme;
import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.model.Plateau;
import latice.model.Tuile;
import latice.model.TypeCase;

class TestLatice {

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
	@Test
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
