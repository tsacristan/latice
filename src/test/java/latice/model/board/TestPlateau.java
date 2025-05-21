package latice.model.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import latice.model.PileDebut;
import latice.model.material.Case;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.model.player.PileJoueur;
import latice.model.player.RackJoueur;
import latice.util.PlateauListener;
import latice.util.exception.PiocheInvalideException;
import latice.util.exception.PlacementDejaExistantInvalide;
import latice.util.exception.PlateauIndexInvalideException;
import latice.util.exception.RackIndexInvalideException;
import latice.util.exception.RackInvalideException;
import latice.view.Textes;
import latice.view.TextesErreurs;

class TestPlateau {

    @Test
    void placer_tuile_sur_le_plateau() throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide, PiocheInvalideException{
    	int ligne = 6;
    	int colonne = 4;
    	int indexRack = 2;
        Plateau plateau = new Plateau();
        PileDebut pileDebutTest = new PileDebut();
        pileDebutTest.remplir();
        pileDebutTest.melanger();
        PileJoueur pileTest = new PileJoueur();
        PileJoueur pileTest2 = new PileJoueur();
        pileDebutTest.distribuer(new PileJoueur[]{pileTest, pileTest2});
        RackJoueur rackTest = new RackJoueur();
        rackTest.remplir(pileTest);
        Tuile tuileAPoser = rackTest.rack().get(2);
     
        plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(4, 6), rackTest);
        Case[][] grille = plateau.grille();
        System.out.println(grille[ligne][colonne]);
        
        assertEquals(tuileAPoser, grille[colonne][ligne].tuile());
    }
    
    @Test
    void placer_tuile_en_dehors_du_plateau() throws PiocheInvalideException {
        int ligne = 36;
        int colonne = 89;
        int indexRack = 2;
        Plateau plateau = new Plateau();
        PileDebut pileDebutTest = new PileDebut();
        pileDebutTest.remplir();
        pileDebutTest.melanger();
        PileJoueur pileTest = new PileJoueur();
        PileJoueur pileTest2 = new PileJoueur();
        pileDebutTest.distribuer(new PileJoueur[]{pileTest, pileTest2});
        RackJoueur rackTest = new RackJoueur();
        rackTest.remplir(pileTest);

        PlateauIndexInvalideException e = assertThrows(PlateauIndexInvalideException.class,
            () -> plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest)
        );
        assertEquals(String.format(Textes.COORDONNEES_HORS_PLATEAU.toString(), colonne, ligne), e.getMessage());
    }


    @Test
    void placer_tuile_depuis_exterieur_du_rack() throws PiocheInvalideException {
        int ligne = 6;
        int colonne = 4;
        int indexRack = 8;  
        Plateau plateau = new Plateau();
        PileDebut pileDebutTest = new PileDebut();
        pileDebutTest.remplir();
        pileDebutTest.melanger();
        PileJoueur pileTest = new PileJoueur();
        PileJoueur pileTest2 = new PileJoueur();
        pileDebutTest.distribuer(new PileJoueur[]{pileTest, pileTest2});
        RackJoueur rackTest = new RackJoueur();
        rackTest.remplir(pileTest);

        RackIndexInvalideException e = assertThrows(RackIndexInvalideException.class,
            () -> plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest)
        );

        assertEquals(String.format(Textes.COORDONNEES_HORS_RACK.toString(), indexRack), e.getMessage());
    }

    
    
    
    
    
    @Test
    void placer_tuile_sur_tuile_deja_sur_plateau() throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PiocheInvalideException, PlacementDejaExistantInvalide {
        int ligne = 6;
        int colonne = 4;
        int indexRack = 2;
        Plateau plateau = new Plateau();
        PileDebut pileDebutTest = new PileDebut();
        pileDebutTest.remplir();
        pileDebutTest.melanger();
        PileJoueur pileTest = new PileJoueur();
        PileJoueur pileTest2 = new PileJoueur();
        pileDebutTest.distribuer(new PileJoueur[]{pileTest, pileTest2});
        RackJoueur rackTest = new RackJoueur();
        rackTest.remplir(pileTest);

        plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest);

        PlacementDejaExistantInvalide exception = assertThrows(
            PlacementDejaExistantInvalide.class,
            () -> plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest)
        );
        assertEquals("Il existe déjà une tuile sur cette case", exception.getMessage());
    }

    @Test
    void placer_tuile_rack_invalide() {
        // Arrange
        int ligne = 6;
        int colonne = 4;
        int indexRack = 2;

        Plateau plateau = new Plateau();
        PileJoueur pileTest = new PileJoueur();  // pile vide
        RackJoueur rackTest = new RackJoueur(pileTest);  // rack initialisé avec pile vide

        // Act & Assert : on s'attend à une exception si le rack est vide
        RackInvalideException e = assertThrows(RackInvalideException.class, () -> {
            plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest);
        });

        // Vérifie que le message d'exception est bien celui attendu
        assertEquals(TextesErreurs.RACK_VIDE.toString(), e.getMessage());
    }


    @Test
    void test_taille_du_plateau() {
        Plateau plateau = new Plateau();
		assertEquals(Plateau.COLONNES * Plateau.LIGNES, plateau.tailleDuTableau());

    }
	
	@Test
	void test_case_lune_du_plateau() {
		Plateau plateau = new Plateau();
		TypeCase lune = TypeCase.CASE_LUNE;
		assertTrue((plateau.grille()[4][4].typeCase() == lune));
	}
	@Test
	void test_des_cases_soleils_du_plateau() {
		Plateau plateau = new Plateau();
		TypeCase soleil = TypeCase.CASE_SOLEIL;
		assertTrue((plateau.grille()[0][0].typeCase() == soleil));
		assertTrue((plateau.grille()[1][1].typeCase() == soleil));
		assertTrue((plateau.grille()[2][2].typeCase() == soleil));
		assertTrue((plateau.grille()[0][4].typeCase() == soleil));
		assertTrue((plateau.grille()[0][8].typeCase() == soleil));
		assertTrue((plateau.grille()[1][7].typeCase() == soleil));
		assertTrue((plateau.grille()[2][6].typeCase() == soleil));
		assertTrue((plateau.grille()[4][0].typeCase() == soleil));
		assertTrue((plateau.grille()[8][0].typeCase() == soleil));
		assertTrue((plateau.grille()[7][1].typeCase() == soleil));
		assertTrue((plateau.grille()[6][2].typeCase() == soleil));
		assertTrue((plateau.grille()[8][4].typeCase() == soleil));
		assertTrue((plateau.grille()[8][8].typeCase() == soleil));
		assertTrue((plateau.grille()[7][7].typeCase() == soleil));
		assertTrue((plateau.grille()[6][6].typeCase() == soleil));
		assertTrue((plateau.grille()[4][8].typeCase() == soleil));
	}
	
	class TestListener implements PlateauListener {
	    boolean called = false;
	    @Override
	    public void plateauEstMisAJour() {
	        called = true;
	    }
	}

	@Test
	void testDeclencherListenersManuel() {
	    // Arrange
	    TestListener listener1 = new TestListener();
	    TestListener listener2 = new TestListener();

	    Plateau plateau = new Plateau() {
	        @Override
			public List listeners() {
	            return List.of(listener1, listener2);
	        }

	        @Override
	        public void declencherListeners() {
	            super.declencherListeners();
	        }
	    };

	    // Act
	    plateau.declencherListeners();

	    // Assert
	    assertTrue(listener1.called, "Listener1 should have been notified");
	    assertTrue(listener2.called, "Listener2 should have been notified");
	}

    
}