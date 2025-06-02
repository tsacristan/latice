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

    private Joueur joueur;

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
        joueur = new Joueur("Alex", rack, pile, 0);
    }

    @Test
    public void testConstructeurs() {
        Joueur j1 = new Joueur("Bob");
        assertEquals("Bob", j1.pseudo());
        assertNotNull(j1.rackJoueur());
        assertNotNull(j1.pileJoueur());

        Joueur j2 = new Joueur();
        assertEquals("Défaut", j2.pseudo());
    }

    @Test
    public void testRemplirRackPilePleine() {
        assertDoesNotThrow(() -> joueur.remplirRack());
        assertTrue(joueur.rackJoueur().rack().size() <= RackJoueur.TAILLE_MAX_RACK);
    }

    @Test
    public void testRemplirRackPiocheVide() {
        Joueur joueurPiocheVide = new Joueur("Vide", new RackJoueur(), new PileJoueur(), 0);
        assertThrows(PiocheInvalideException.class, joueurPiocheVide::remplirRack);
    }

    @Test
    public void testPseudoGetter() {
        assertEquals("Alex", joueur.pseudo());
    }

    @Test
    public void testRackGetter() {
        assertNotNull(joueur.rackJoueur());
    }

    @Test
    public void testPileGetter() {
        assertNotNull(joueur.pileJoueur());
    }

    @Test
    public void testEqualsEtHashCode() {
        RackJoueur rack1 = new RackJoueur();
        PileJoueur pile1 = new PileJoueur();
        Joueur j1 = new Joueur("Test", rack1, pile1, 0);
        Joueur j2 = new Joueur("Test", rack1, pile1, 0);

        assertEquals(j1, j2);
        assertEquals(j1.hashCode(), j2.hashCode());

        Joueur j3 = new Joueur("Différent", new RackJoueur(), new PileJoueur(), 0);
        assertNotEquals(j1, j3);
    }

    @Test
    public void testEqualsNullEtAutresTypes() {
        assertNotEquals(joueur, null);
        assertNotEquals(joueur, "pas un joueur");
        assertEquals(joueur, joueur);
    }
    
    @Test
    void test_score_du_joueur() {
    	RackJoueur rack1 = new RackJoueur();
        PileJoueur pile1 = new PileJoueur();
    	Joueur j1 = new Joueur("Bernard",rack1,pile1,12);
    	
    	Integer score = 12;
    	
    	assertEquals(j1.score(),12);
    }
    
    @Test
    void test_ajouterScore() {
    	
    	Joueur j1 = new Joueur("Lola");
    	
    	j1.ajouterScore(4);
    	
    	assertEquals(4,j1.score());
    	
    }
    
    
}