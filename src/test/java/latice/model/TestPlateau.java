package latice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import latice.ihm.Console;
import latice.model.Case;
import latice.model.Coordonnees;
import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.model.Plateau;
import latice.model.RackJoueur;
import latice.model.Tuile;
import latice.model.TypeCase;
import latice.util.PiocheInvalideException;
import latice.util.PlacementDejaExistantInvalide;
import latice.util.PlateauIndexInvalideException;
import latice.util.RackIndexInvalideException;
import latice.util.RackInvalideException;

class TestPlateau {

    @Test
    void placer_tuile_sur_le_plateau() throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide{
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
        RackJoueur rackTest = new RackJoueur(pileTest);
        rackTest.remplir();
        Tuile tuileAPoser = rackTest.rack().get(2);
     
        plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(4, 6), rackTest);
        Case[][] grille = plateau.grille();
        System.out.println(grille[ligne][colonne]);
        
        assertEquals(tuileAPoser, grille[ligne][colonne].tuile());
    }
    
    @Test
    void placer_tuile_en_dehors_du_plateau() throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide{
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
        RackJoueur rackTest = new RackJoueur(pileTest);
        rackTest.remplir();
        //Act
        //Assert
        PlateauIndexInvalideException e = assertThrows(
        	    PlateauIndexInvalideException.class,
        	    () -> {
        	        plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), rackTest);
        	    }
        	);

        	assertEquals("Coordonnées en dehors du plateau : ligne=" + ligne + ", colonne=" + colonne, e.getMessage()); 
    }

    @Test
    void placer_tuile_depuis_exterieur_du_rack() throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide{
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
        RackJoueur rackTest = new RackJoueur(pileTest);
        rackTest.remplir();
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
}