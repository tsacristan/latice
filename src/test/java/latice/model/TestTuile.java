package latice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestTuile {
	
	private static final Tuile TUILE_BLEU_MARINE_DAUPHIN = new Tuile(Couleur.BLEU_MARINE,Forme.DAUPHIN);
	
	@Test
	void test_couleur_et_forme_tuile() {
		assertEquals(Couleur.BLEU_MARINE, TUILE_BLEU_MARINE_DAUPHIN.couleur());
		assertEquals(Forme.DAUPHIN, TUILE_BLEU_MARINE_DAUPHIN.forme());
	}
	
	@Test
	void test_to_string() {
		String resultatToString = "Tuile [couleur=" + Couleur.BLEU_MARINE + ", forme=" + Forme.DAUPHIN + "]";
		
		assertEquals(resultatToString, TUILE_BLEU_MARINE_DAUPHIN.toString());
	}

}
