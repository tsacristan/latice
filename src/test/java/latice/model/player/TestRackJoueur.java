package latice.model.player;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import latice.model.PileDebut;
import latice.model.material.Couleur;
import latice.model.material.Forme;
import latice.model.material.Tuile;
import latice.util.RackListener;
import latice.util.exception.PiocheInvalideException;

class TestRackJoueur {
	
	private static final Tuile TUILE_ROUGE_OISEAU = new Tuile(Couleur.ROUGE,Forme.OISEAU); 
	private static final Tuile TUILE_BLEU_MARINE_FLEUR = new Tuile(Couleur.BLEU_MARINE,Forme.FLEUR); 
	private static final Tuile TUILE_BLEU_SARCELLE_FLEUR = new Tuile(Couleur.BLEU_SARCELLE,Forme.FLEUR); 
	
	private static final PileJoueur PILEJ1 = new PileJoueur();
	private static final PileJoueur PILEJ2 = new PileJoueur();
	private static final PileDebut PILE_PRINCIPALE = new PileDebut();
	
	private static final RackJoueur RACKJ1 = new RackJoueur();
	
	private static final TestListener LISTENER1 = new TestListener();
	private static final TestListener LISTENER2 = new TestListener();
	

	@Test
	void test_toString() {
		
        RackJoueur rack = new RackJoueur(Arrays.asList(TUILE_ROUGE_OISEAU, TUILE_BLEU_MARINE_FLEUR));
        
        String attendu = "[" + TUILE_ROUGE_OISEAU.toString() + ", " + TUILE_BLEU_MARINE_FLEUR.toString() + "]";

		assertEquals(attendu, rack.toString());
		
	}
	
	@Test
    void testToStringAvecRackVide() {
        assertEquals("[]", RACKJ1.toString());
    }

	
	@Test
	void testSiLaPileContientMoinsDeCinqTuile() throws PiocheInvalideException {

		PILEJ1.clear();

		PILEJ1.ajouterTuile(TUILE_ROUGE_OISEAU);
		PILEJ1.ajouterTuile(TUILE_BLEU_MARINE_FLEUR);
		PILEJ1.ajouterTuile(TUILE_BLEU_SARCELLE_FLEUR);

		RACKJ1.rack().add(TUILE_ROUGE_OISEAU);
		RACKJ1.rack().add(TUILE_BLEU_MARINE_FLEUR);
		RACKJ1.rack().add(TUILE_BLEU_SARCELLE_FLEUR);
		RACKJ1.rack().add(TUILE_ROUGE_OISEAU);
		RACKJ1.rack().add(TUILE_BLEU_SARCELLE_FLEUR);
		
		RACKJ1.piocher(PILEJ1);
		assertEquals(3,PILEJ1.size());
		assertEquals(5,RACKJ1.rack().size());
	}
	
	static class TestListener implements RackListener {
        boolean called = false;

        @Override
        public void rackEstMisAJour() {
            called = true;
        }
    }

    @Test
    void testDeclencherListenersManuellement() {
        RackJoueur rackJoueur = new RackJoueur() {
            {
                // Ajoute manuellement les listeners
                ajouterListener(LISTENER1);
                ajouterListener(LISTENER2);
            }

            @Override
            public void declencherListeners() {
                super.declencherListeners();
            }
        };

        rackJoueur.declencherListeners();

        assertTrue(LISTENER1.called, "Listener1 aurait dû être notifié");
        assertTrue(LISTENER1.called, "Listener2 aurait dû être notifié");
    }
    
    @Test
    void tester_remplir_pile_pleine() {
    	PILE_PRINCIPALE.remplir();
    	PILE_PRINCIPALE.distribuer(new PileJoueur[]{PILEJ1, PILEJ2});
    	
    	RackJoueur rackJoueur = new RackJoueur();
    	rackJoueur.rack().add(TUILE_ROUGE_OISEAU);
    	rackJoueur.rack().add(TUILE_BLEU_MARINE_FLEUR);
    	rackJoueur.rack().add(TUILE_BLEU_SARCELLE_FLEUR);
    	rackJoueur.remplir(PILEJ1);
    	
    	assertEquals(RackJoueur.TAILLE_MAX_RACK, rackJoueur.rack().size());
    }
}
