package latice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import latice.model.player.PileJoueur;
import latice.util.exception.PiocheInvalideException;

class TestPileDebut {
	
	private static final int TAILLE_PILE_DEBUT = 72;
	
	@Test
	void test_taile_pile_debut_egale_a_72() {
		PileDebut pile = new PileDebut();
		pile.remplir();
		
		assertEquals(TAILLE_PILE_DEBUT, pile.size());
	}
	
	@Test
	void test_si_la_pile_est_melangee() {
		PileDebut pile = new PileDebut();
		PileDebut pile2 = new PileDebut();
		pile.remplir();
		pile2.remplir();
		pile2.melanger();
		
		assertNotEquals(pile, pile2);
	}

	@Test
	void test_distribution_taille_des_piles_joueurs_egales() throws PiocheInvalideException {
		PileDebut pile = new PileDebut();
		pile.remplir();
		
		
		PileJoueur pile1 = new PileJoueur();
		PileJoueur pile2 = new PileJoueur();
		pile.distribuer(new PileJoueur[]{pile1, pile2});
		
		assertEquals(pile1.size(), pile2.size());
	}
	
	
	
}
