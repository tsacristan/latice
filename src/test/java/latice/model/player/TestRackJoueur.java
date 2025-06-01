package latice.model.player;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import latice.model.PileDebut;
import latice.model.board.Plateau;
import latice.model.material.Couleur;
import latice.model.material.Forme;
import latice.model.material.Tuile;
import latice.util.PlateauListener;
import latice.util.RackListener;
import latice.util.exception.PiocheInvalideException;

class TestRackJoueur {

	@Test
	void test_toString() throws PiocheInvalideException {
		
        Tuile tuile1 = new Tuile(Couleur.ROUGE,Forme.OISEAU); 
        Tuile tuile2 = new Tuile(Couleur.BLEU_MARINE,Forme.FLEUR); 
        RackJoueur rack = new RackJoueur(Arrays.asList(tuile1, tuile2));
        
        String attendu = "[" + tuile1.toString() + ", " + tuile2.toString() + "]";

		assertEquals(attendu, rack.toString());
		
	}
	
	@Test
    void testToStringAvecRackVide() {
        RackJoueur rack = new RackJoueur();
        assertEquals("[]", rack.toString());
    }

	
	@Test
	void testSiLaPileContientMoinsDeCinqTuile() throws PiocheInvalideException {
		PileJoueur pileJ1 = new PileJoueur();
		Tuile tuile1 = new Tuile(Couleur.ROUGE,Forme.OISEAU); 
        Tuile tuile2 = new Tuile(Couleur.BLEU_MARINE,Forme.FLEUR); 
        Tuile tuile3 = new Tuile(Couleur.BLEU_SARCELLE,Forme.FLEUR); 
		pileJ1.ajouterTuile(tuile1);
		pileJ1.ajouterTuile(tuile2);
		pileJ1.ajouterTuile(tuile3);
		
				
		RackJoueur rackJ1 = new RackJoueur();
		rackJ1.rack().add(tuile1);
		rackJ1.rack().add(tuile2);
		rackJ1.rack().add(tuile3);
		rackJ1.rack().add(tuile1);
		rackJ1.rack().add(tuile3);
		
		rackJ1.remplir(pileJ1);
		
		assertEquals(3,pileJ1.size());
		assertEquals(5,rackJ1.rack().size());
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
        TestListener listener1 = new TestListener();
        TestListener listener2 = new TestListener();

        RackJoueur rackJoueur = new RackJoueur() {
            {
                // Ajoute manuellement les listeners
                ajouterListener(listener1);
                ajouterListener(listener2);
            }

            @Override
            public void declencherListeners() {
                super.declencherListeners();
            }
        };

        rackJoueur.declencherListeners();

        assertTrue(listener1.called, "Listener1 aurait dû être notifié");
        assertTrue(listener2.called, "Listener2 aurait dû être notifié");
    }
}
