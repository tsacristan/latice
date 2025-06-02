package latice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import latice.model.board.Coordonnees;
import latice.model.player.Joueur;
import latice.util.exception.PiocheInvalideException;
import latice.view.TextesErreurs;
import latice.view.gui.InterfaceCase;
import latice.view.gui.InterfacePlateau;
import latice.view.gui.LaticeVueGraphique;
import latice.view.gui.PartieControle;

public class ControllerJouerGraphique extends ControllerJouer {
	
	private LaticeVueGraphique laticeVue;
	private Joueur joueurCourant;
	private int tourCourant;
	private Joueur premierJoueur;
	private boolean tuilePlaceeDansCeTour;
	
	public ControllerJouerGraphique(LaticeVueGraphique laticeVue) {
		super();
		this.laticeVue = laticeVue;
		random = new Random();
		tourCourant = 1;
	}
	
	@Override
	public void jouer(List<Joueur> joueurs) {
		this.joueurs = (ArrayList<Joueur>) joueurs;
		premierJoueur = random.nextBoolean() ? joueurs.get(0) : joueurs.get(1);
		joueurCourant = premierJoueur;
		
		jouerTour();
	}

	public void jouerTuile() {
		PartieControle partieControle = laticeVue.interfaceJeu().initialiserPartieControle(joueurCourant);
		
		partieControle.boutonValider().setOnAction(e -> {
			validerTour();
		});
		partieControle.boutonPasser().setOnAction(e -> {
			passerTour();
		});
		partieControle.boutonPiocher().setOnAction(e -> {
			piocher();
	    });
		
		laticeVue.interfaceJeu().setBottom(partieControle);
		laticeVue.interfaceJeu().interfacePlateau().actualiserRack(joueurCourant.rackJoueur());
		appliquerDnDToutesCases();
	}
	
	private void piocher() {
		try {
            joueurCourant.remplirRack();
        } catch (PiocheInvalideException e) {
            laticeVue.afficherErreur(e.getMessage());
        }
		avancerTour();
	}
	
	private void validerTour() {
		 if (!tuilePlaceeDansCeTour) {
		        laticeVue.afficherErreur(TextesErreurs.VALIDATION_SANS_ACTION.texte());
		        return;
		    }
		    avancerTour();
	    }
	 
	 private void passerTour() {
		 avancerTour();
	 }
	
	 private void avancerTour() {
		 if (!joueurCourant.equals(premierJoueur)) tourCourant++;
		 joueurCourant = prochainJoueur();
		 tuilePlaceeDansCeTour = false;
		 jouerTour();
	 }
	
	private void jouerTour() {
		if (tourCourant >= 10) {
			annoncerGagnants();
			return;
		}
		
		laticeVue.afficherPlateau(plateau);
		laticeVue.afficherRack(joueurCourant);
		laticeVue.afficherTour(joueurs, joueurCourant, tourCourant);
		
		jouerTuile();
	}
	
	private Joueur prochainJoueur() {
		return joueurCourant.equals(joueurs.get(1)) ? joueurs.get(0) : joueurs.get(1);
	}
	
	private void appliquerDnDToutesCases() {
		InterfacePlateau interfacePlateau = laticeVue.interfaceJeu().interfacePlateau();
		
		for (Node node : interfacePlateau.getChildren()) {
		    Integer ligne = GridPane.getRowIndex(node);
		    Integer colonne = GridPane.getColumnIndex(node);

		    int ligneNonNull = ligne != null ? ligne : 0;
		    int colonneNonNull = colonne != null ? colonne : 0;
		    
		    if (node instanceof InterfaceCase) {
		    	InterfaceCase caseVue = (InterfaceCase) node;
		    	appliquerDnD(caseVue, colonneNonNull, ligneNonNull);
		    }
		}
	}
	
	private void appliquerDnD(InterfaceCase interfaceCase, int ligne, int colonne) {
		interfaceCase.setOnDragOver(event -> {
            if (event.getGestureSource() != interfaceCase && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

		interfaceCase.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                try {
                    int indexRack = Integer.parseInt(db.getString());
                    plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), joueurCourant.rackJoueur());
                    success = true;
                    
                    tuilePlaceeDansCeTour = true;
                } catch (Exception e) {
                    laticeVue.afficherErreur(e.getMessage());
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });
	}

	@Override
	public void annoncerGagnants() {
		ArrayList<Joueur> joueursGagnants = (ArrayList<Joueur>) obtenirGagnants();
		
		laticeVue.afficherGagnants(joueursGagnants);
	}
}