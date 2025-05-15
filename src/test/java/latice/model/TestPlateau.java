package latice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.material.Case;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.model.player.PileJoueur;
import latice.model.player.RackJoueur;
import latice.util.exception.PiocheInvalideException;
import latice.util.exception.PlacementDejaExistantInvalide;
import latice.util.exception.PlateauIndexInvalideException;
import latice.util.exception.RackIndexInvalideException;
import latice.util.exception.RackInvalideException;

class TestPlateau {

    @Test
    void placer_tuile_sur_le_plateau() throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide, PiocheInvalideException{
        //Arrange
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
    void placer_tuile_en_dehors_du_plateau() throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide, PiocheInvalideException{
        //Arrange
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
        //Act
        //Assert
        PlateauIndexInvalideException e = assertThrows(
        	    PlateauIndexInvalideException.class,
        	    () -> {
        	        plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest);
        	    }
        	);

        	assertEquals("Coordonnées en dehors du plateau : ligne=" + colonne + ", colonne=" + ligne, e.getMessage()); 
    }

    @Test
    void placer_tuile_depuis_exterieur_du_rack() throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide, PiocheInvalideException{
    	//Arrange
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
        //Act

        //Assert
        RackIndexInvalideException e = assertThrows(
        		RackIndexInvalideException.class,
        	    () -> {
        	        plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest);
        	    }
        	);

        	assertEquals("Coordonnées en dehors du rack : indexRack=" + indexRack, e.getMessage()); 
    
    }
    @Test
    void placer_tuile_sur_tuile_deja_sur_plateau() throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide, PiocheInvalideException{
    	//Arrange
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
        //Act
        plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest);
        //Assert
        PlacementDejaExistantInvalide e = assertThrows(
        		PlacementDejaExistantInvalide.class,
        	    () -> {
        	        plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest);
        	    }
        	);

        	assertEquals("Il existe déjà une tuile sur cette case", e.getMessage()); 
    
    }
    @Test
    void placer_tuile_rack_invalide() throws PlateauIndexInvalideException, RackIndexInvalideException {
        int ligne = 6;
        int colonne = 4;
        int indexRack = 2;

        Plateau plateau = new Plateau();
        PileJoueur pileTest = new PileJoueur();
        RackJoueur rackTest = new RackJoueur(pileTest);
        // Act & Assert
        RackInvalideException e = assertThrows(
        		RackInvalideException.class,
        	    () -> {
        	        plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest);
        	    }
        	);

        	assertEquals("Erreur : le rack doit contenir au moins 1 tuile.", e.getMessage()); 
    
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
    
}