package latice.model.material;

import latice.model.player.EtatsPseudo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEtatsPseudo {

    @Test
    public void testValeursEnum() {
        assertEquals(EtatsPseudo.PSEUDO_VIDE, EtatsPseudo.valueOf("PSEUDO_VIDE"));
        assertEquals(EtatsPseudo.PSEUDO_DEJA_PRIS, EtatsPseudo.valueOf("PSEUDO_DEJA_PRIS"));
        assertEquals(EtatsPseudo.PSEUDO_TROP_GRAND, EtatsPseudo.valueOf("PSEUDO_TROP_GRAND"));
        assertEquals(EtatsPseudo.PSEUDO_CORRECT, EtatsPseudo.valueOf("PSEUDO_CORRECT"));
    }

    @Test
    public void testNombreDeValeurs() {
        EtatsPseudo[] valeurs = EtatsPseudo.values();
        assertEquals(4, valeurs.length);
    }

    @Test
    public void testNonNullValues() {
        for (EtatsPseudo etat : EtatsPseudo.values()) {
            assertNotNull(etat);
        }
    }
}