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
import latice.view.gui.InterfaceCase;
import latice.view.gui.InterfacePlateau;
import latice.view.gui.LaticeVueGraphique;
import latice.view.gui.PartieControle;

public class ControllerJouerGraphique extends ControllerJouer {
	
	private LaticeVueGraphique laticeVue;
	private Joueur joueurCourant;
	private int tourCourant;
	private Joueur premierJoueur;
	
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
		PartieControle nouvellePartie = laticeVue.interfaceJeu().initialiserPartieControle(joueurCourant.rackJoueur());
		
		laticeVue.interfaceJeu().setBottom(nouvellePartie);
		laticeVue.interfaceJeu().interfacePlateau().actualiserRack(joueurCourant.rackJoueur());
		appliquerDnDToutesCases();
	}
	
	private void jouerTour() {
		if (tourCourant >= 10) {
			laticeVue.afficherErreur("Partie finie");
			return;
		}
		
		laticeVue.afficherPlateau(plateau);
		laticeVue.afficherRack(joueurCourant.rackJoueur());
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
                    
                    if (!joueurCourant.equals(premierJoueur)) tourCourant++;
                    joueurCourant = prochainJoueur();
                    jouerTour();
                } catch (Exception e) {
                    laticeVue.afficherErreur(e.getMessage());
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });
	}
}
