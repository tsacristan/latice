package latice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import latice.model.player.PileJoueur;
import latice.util.exception.PiocheInvalideException;

class TestPileDebut {
	
	private static final int TAILLE_PILE_DEBUT = 72;
	private static final PileDebut PILE = new PileDebut();
	private static final PileDebut PILE2 = new PileDebut();
	private static final PileJoueur PILEJ1 = new PileJoueur();
	private static final PileJoueur PILEJ2 = new PileJoueur();
	
	@Test
	void test_taile_pile_debut_egale_a_72() {
		assertEquals(TAILLE_PILE_DEBUT, PILE.size());
	}
	
	@Test
	void test_si_la_pile_est_melangee() {
		PILE.remplir();
		PILE2.remplir();
		PILE2.melanger();
		
		assertNotEquals(PILE, PILE2);
	}

	@Test
	void test_distribution_taille_des_piles_joueurs_egales() throws PiocheInvalideException {
		
		PILE.distribuer(new PileJoueur[]{PILEJ1, PILEJ2});
		
		assertEquals(PILEJ1.size(), PILEJ2.size());
	}
	
	
	
}
