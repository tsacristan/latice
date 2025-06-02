package latice.model.player;

import latice.model.material.Couleur;
import latice.model.material.Forme;
import latice.model.material.Tuile;
import latice.model.player.Joueur;
import latice.model.player.PileJoueur;
import latice.model.player.RackJoueur;
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
    public void setUp() {
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
        joueurALEX = new Joueur("Alex", rack, pile, 0);
        joueurBOB= new Joueur("Bob");
        joueurDEFAUT = new Joueur();
        rack1 = new RackJoueur();
    }

    @Test
    public void testConstructeurs() {
        assertEquals("Bob", joueurBOB.pseudo());
        assertNotNull(joueurBOB.rackJoueur());
        assertNotNull(joueurBOB.pileJoueur());
        assertEquals("Défaut", joueurDEFAUT.pseudo());
    }

    @Test
    public void testRemplirRackPilePleine() {
        assertDoesNotThrow(() -> joueurALEX.piocher());
        assertTrue(joueurALEX.rackJoueur().rack().size() <= RackJoueur.TAILLE_MAX_RACK);
    }

    @Test
    public void testRemplirRackPiocheVide() {
        Joueur joueurPiocheVide = new Joueur("Vide", new RackJoueur(), new PileJoueur(), 0);
        assertThrows(PiocheInvalideException.class, joueurPiocheVide::piocher);
    }

    @Test
    public void testPseudoGetter() {
        assertEquals("Alex", joueurALEX.pseudo());
    }

    @Test
    public void testRackGetter() {
        assertNotNull(joueurALEX.rackJoueur());
    }

    @Test
    public void testPileGetter() {
        assertNotNull(joueurALEX.pileJoueur());
    }

    @Test
    public void testEqualsEtHashCode() {

        
        Joueur j1 = new Joueur("Test", rack1, pile1, 0);
        Joueur j2 = new Joueur("Test", rack1, pile1, 0);

        assertEquals(j1, j2);
        assertEquals(j1.hashCode(), j2.hashCode());

        Joueur j3 = new Joueur("Différent", new RackJoueur(), new PileJoueur(), 0);
        assertNotEquals(j1, j3);
    }

    @Test
    public void testEqualsNullEtAutresTypes() {
        assertNotEquals(joueurALEX, null);
        assertNotEquals(joueurALEX, "pas un joueur");
        assertEquals(joueurALEX, joueurALEX);
    }
    
    @Test
    void test_score_du_joueur() {
    	Joueur j1 = new Joueur("Bernard",rack1,pile1,12);
    	assertEquals(j1.score(),score);
    }
    
    @Test
    void test_ajouterScore() {    	
    	joueurBOB.ajouterScore(4);
    	
    	assertEquals(4,joueurBOB.score());
    	
    }
    
    
}