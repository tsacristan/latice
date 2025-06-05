package latice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import latice.model.board.Coordonnees;
import latice.model.player.Joueur;
import latice.util.exception.PiocheInvalideException;
import latice.view.TextesErreurs;
import latice.view.gui.Boutique;
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
		laticeVue.interfaceJeu().partieInfo().boutonMagasin().setOnAction(event -> ouvrirBoutique());
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
		
		partieControle.boutonValider().setOnAction(e -> validerTour());
		partieControle.boutonPasser().setOnAction(e -> {
			if (tuilePlaceeDansCeTour) {
				laticeVue.afficherErreur(TextesErreurs.ACTION_DEJA_EFFECTUEE.texte());
				return;
			}
			passerTour();
		});
		partieControle.boutonPiocher().setOnAction(e -> {
			if (tuilePlaceeDansCeTour) {
				laticeVue.afficherErreur(TextesErreurs.ACTION_DEJA_EFFECTUEE.texte());
				return;
			}
			piocher();
		});
		
		laticeVue.interfaceJeu().setBottom(partieControle);
		laticeVue.interfaceJeu().interfacePlateau().actualiserRack(joueurCourant.rackJoueur());
		appliquerDnDToutesCases();
	}
	
	private void ouvrirBoutique() {
		Boutique boutique = new Boutique();
		boutique.ouvrir(joueurCourant);
		laticeVue.actualiserScores(joueurs, joueurCourant);
	}
	
	private void piocher() {
		if (joueurCourant.nombreCoups() > 0) {
		try {
            joueurCourant.piocher();
            joueurCourant.changerNombreCoups(joueurCourant.nombreCoups() - 1);
        } catch (PiocheInvalideException e) {
            laticeVue.afficherErreur(e.getMessage());
        }
			avancerTour();
		}
	}
	
	private void validerTour() {
		 if (joueurCourant.nombreCoups() > 0) {
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
		 joueurCourant.remplir();
		 
		 joueurCourant = prochainJoueur();
		 tuilePlaceeDansCeTour = false;
		 jouerTour();
		 joueurCourant.changerNombreCoups(1);
	 }
	
	private void jouerTour() {
		if (tourCourant > LaticeController.TOURS_MAX) {
			annoncerGagnants();
			Platform.exit();
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
                    
                    Coordonnees coordDePlacement = new Coordonnees(ligne, colonne);
                    int pointsAjoutes = verificateurPlacementPoints.calculerPointsCoup(coordDePlacement, joueurCourant.rackJoueur().rack().get(indexRack));
                    plateau.placerLaTuileSurLePlateau(indexRack, coordDePlacement, joueurCourant.rackJoueur());
        			joueurCourant.ajouterPoints(pointsAjoutes);
        			joueurCourant.changerNombreCoups(joueurCourant.nombreCoups() - 1);
                    success = true;
                    if (joueurCourant.nombreCoups() > 0) {
                    	laticeVue.afficherPlateau(plateau); 
                    	appliquerDnDToutesCases();          
                    	laticeVue.afficherRack(joueurCourant);
                    }
                    tuilePlaceeDansCeTour = true;
                    joueurCourant.incrementerTuilePlacees();
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