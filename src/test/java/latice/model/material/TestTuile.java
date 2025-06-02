package latice.model.material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import latice.model.material.Couleur;
import latice.model.material.Forme;
import latice.model.material.Tuile;

class TestTuile {
	
	private static final Tuile TUILE_BLEU_MARINE_DAUPHIN = new Tuile(Couleur.BLEU_MARINE,Forme.DAUPHIN);
	private static final Tuile TUILE_BLEU_SARCELLE_FLEUR = new Tuile(Couleur.BLEU_SARCELLE,Forme.FLEUR);
	
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

	@Test
	void testEquals() {
		
		assertFalse(TUILE_BLEU_SARCELLE_FLEUR.equals(TUILE_BLEU_MARINE_DAUPHIN));
	}
	@Test
	void testEqualsNull() {
		assertFalse(TUILE_BLEU_SARCELLE_FLEUR.equals(null));
	}
	
	
	
	@Test
	void testEqualsFailsDueToDifferentClass() {
		class FakeTuile extends Tuile {
		    public FakeTuile(Couleur couleur, Forme forme) {
		        super(couleur, forme);
		    }
		}
		Tuile autre = new FakeTuile(Couleur.ROUGE, Forme.OISEAU); 

		boolean result = TUILE_BLEU_MARINE_DAUPHIN.equals(autre);

		assertFalse(result);
    }
	

	
}
