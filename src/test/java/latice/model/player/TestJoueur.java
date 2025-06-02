package latice.model.player;

import latice.model.material.Couleur;
import latice.model.material.Forme;
import latice.model.material.Tuile;
import latice.util.exception.PiocheInvalideException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJoueur {

    private static Joueur joueurALEX;
    private static Joueur joueurBOB;
    private static Joueur joueurDEFAUT;
    
    private static RackJoueur rack1 = new RackJoueur();
    private static final PileJoueur pile1 = new PileJoueur();
    
    private static final Integer score = 12;
    

    @BeforeEach
    void setUp() {
        PileJoueur pile = new PileJoueur();
        List<Tuile> tuiles = Arrays.asList(
            new Tuile(Couleur.ROUGE, Forme.PLUME),
            new Tuile(Couleur.BLEU_MARINE, Forme.OISEAU),
            new Tuile(Couleur.VERT, Forme.TORTUE),
            new Tuile(Couleur.JAUNE, Forme.FLEUR),
            new Tuile(Couleur.MAGENTA, Forme.GECKO),
            new Tuile(Couleur.BLEU_SARCELLE, Forme.DAUPHIN)
        );
        pile.addAll(tuiles);

        RackJoueur rack = new RackJoueur();
        joueurALEX = new Joueur("Alex", rack, pile, 0, 0);
        joueurBOB= new Joueur("Bob");
        joueurDEFAUT = new Joueur();
        rack1 = new RackJoueur();
    }

    @Test
    void tester_constructeurs() {
        assertEquals("Bob", joueurBOB.pseudo());
        assertNotNull(joueurBOB.rackJoueur());
        assertNotNull(joueurBOB.pileJoueur());
        assertEquals("Défaut", joueurDEFAUT.pseudo());
    }

    @Test
    void tester_piocherRack_pile_pleine() {
        assertDoesNotThrow(() -> joueurALEX.piocher());
        assertTrue(joueurALEX.rackJoueur().rack().size() <= RackJoueur.TAILLE_MAX_RACK);
    }

    @Test
    void test_piocherRack_pioche_vide() {
        Joueur joueurPiocheVide = new Joueur("Vide", new RackJoueur(), new PileJoueur(), 0, 0);
        assertThrows(PiocheInvalideException.class, joueurPiocheVide::piocher);
    }

    @Test
    void tester_pseudo_getter() {
        assertEquals("Alex", joueurALEX.pseudo());
    }

    @Test
    void tester_rack_getter() {
        assertNotNull(joueurALEX.rackJoueur());
    }

    @Test
    void tester_pile_getter() {
        assertNotNull(joueurALEX.pileJoueur());
    }
    
    @Test
    void tester_tuilesPlacees_getter_et_increment() {
    	assertEquals(0, joueurALEX.tuilesPlacees());
    	joueurALEX.incrementerTuilePlacees();
    	assertEquals(1, joueurALEX.tuilesPlacees());
    }

    @Test
    void test_equals_et_hashcode() {

        
        Joueur j1 = new Joueur("Test", rack1, pile1, 0, 0);
        Joueur j2 = new Joueur("Test", rack1, pile1, 0, 0);

        assertEquals(j1, j2);
        assertEquals(j1.hashCode(), j2.hashCode());

        Joueur j3 = new Joueur("Différent", new RackJoueur(), new PileJoueur(), 0, 0);
        assertNotEquals(j1, j3);
    }

    @Test
    void testEqualsNullEtAutresTypes() {
        assertNotEquals(null, joueurALEX);
        assertNotEquals("pas un joueur", joueurALEX);
        assertEquals(joueurALEX, joueurALEX);
    }
    
    @Test
    void test_score_du_joueur() {
    	Joueur j1 = new Joueur("Bernard",rack1,pile1,12, 0);
    	assertEquals(score,j1.score());
    }
    
    @Test
    void test_ajouterScore() {    	
    	joueurBOB.ajouterScore(4);
    	
    	assertEquals(4,joueurBOB.score());
    	
    }
    
    
}