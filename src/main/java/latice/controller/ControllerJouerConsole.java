package latice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.util.exception.AucuneCouleurOuFormeCorrespondantException;
import latice.util.exception.AucuneTuileAdjacenteException;
import latice.util.exception.PiocheInvalideException;
import latice.util.exception.PlacementDejaExistantInvalide;
import latice.util.exception.RackIndexInvalideException;
import latice.util.exception.RackInvalideException;
import latice.view.Textes;
import latice.view.TextesErreurs;
import latice.view.console.LaticeVueConsole;

public class ControllerJouerConsole extends ControllerJouer {
	
	private LaticeVueConsole laticeVue;
	private boolean estPremierTour;
	
	public ControllerJouerConsole(LaticeVueConsole laticeVue) {
		super();
		this.laticeVue = laticeVue;
		random = new Random();
	}
	
	@Override
	public void jouer(List<Joueur> joueurs) {
		this.joueurs = (ArrayList<Joueur>) joueurs;
		
		Joueur joueurCourant = random.nextBoolean() ? joueurs.get(0) : joueurs.get(1);
		Joueur premierJoueur = joueurCourant;
		int nombreTour = 1;
		estPremierTour = true;
		
		jouerTour(joueurCourant, nombreTour, estPremierTour);
		joueurCourant = joueurCourant.equals(joueurs.get(1)) ? joueurs.get(0) : joueurs.get(1);
		while (nombreTour <= LaticeController.TOURS_MAX) {
			jouerTour(joueurCourant, nombreTour, estPremierTour);
			
			if (joueurCourant.equals(premierJoueur)) nombreTour++;;
			joueurCourant = joueurCourant.equals(joueurs.get(1)) ? joueurs.get(0) : joueurs.get(1);
			laticeVue.afficherMessage("");
		}
		annoncerGagnants();
	}
	
	private void jouerTuile(Joueur joueurQuiJoue, boolean jouerCentre) {
		if (!jouerCentre) placerTuile(joueurQuiJoue);
		else {
			int emplacementRack = laticeVue.demanderTuileAPoser(joueurQuiJoue) - 1;
			placerTuileEtGererErreurs(emplacementRack, plateau.plateauCentre(), joueurQuiJoue);
		}
	}

	private void placerTuile(Joueur joueur) {
		boolean emplacementValide = false;
		
		while (!emplacementValide) {
			int emplacementRack = laticeVue.demanderTuileAPoser(joueur) - 1;
			Coordonnees emplacementPlateau = laticeVue.choisirEmplacementPlateau();
			emplacementValide = placerTuileEtGererErreurs(emplacementRack, emplacementPlateau, joueur);
		}
	}
	
	private boolean placerTuileEtGererErreurs(int emplacementRack, Coordonnees emplacementPlateau, Joueur joueur) {
		try {
			int pointsAjoutes = calculerPointsCoup(emplacementPlateau, joueur.rackJoueur().rack().get(emplacementRack));
			plateau.placerLaTuileSurLePlateau(emplacementRack, emplacementPlateau, joueur.rackJoueur());
			joueur.ajouterPoints(pointsAjoutes);
			estPremierTour = false;
			joueur.incrementerTuilePlacees();
			return true;
		} catch (RackInvalideException e) {
			laticeVue.afficherErreur(TextesErreurs.RACK_VIDE.toString());
		} catch (RackIndexInvalideException e) {
			laticeVue.afficherErreur(TextesErreurs.INDICE_RACK_INVALIDE.toString());
		} catch (PlacementDejaExistantInvalide e) {
			laticeVue.afficherErreur(TextesErreurs.CASE_NON_VIDE.toString());
		} catch (AucuneTuileAdjacenteException e) {
			laticeVue.afficherErreur(TextesErreurs.TUILE_ISOLEE.toString());
		} catch (AucuneCouleurOuFormeCorrespondantException e) {
			laticeVue.afficherErreur(TextesErreurs.TUILE_NI_COULEUR_NI_FORME.toString());
		}
		return false;
	}
	
	private void jouerTour(Joueur joueurQuiJoue, int nombreTour, boolean jouerCentre) {
		laticeVue.afficherTour(joueurs, joueurQuiJoue, nombreTour);
		laticeVue.afficherPlateau(plateau);
		laticeVue.afficherRack(joueurQuiJoue);
		
		int action = laticeVue.demanderActionTour();
        switch (action) {
            case 1:
            	jouerTuile(joueurQuiJoue, jouerCentre);
                break;
            case 2:
                laticeVue.afficherMessage(Textes.TOUR_PASSE.texte());
                break;
            case 3:
            	try {
					joueurQuiJoue.piocher();
					joueurQuiJoue.changerNombreCoups(joueurQuiJoue.nombreCoups() - 1);
				} catch (PiocheInvalideException e) {
					laticeVue.afficherErreur(e.getMessage());
				}
				break;
            default:
                laticeVue.afficherErreur(TextesErreurs.ACTION_INVALIDE.texte());
        }
        
        joueurQuiJoue.remplir();
	}
	
	@Override
    public void annoncerGagnants() {
    	ArrayList<Joueur> joueursGagnants = (ArrayList<Joueur>) obtenirGagnants();
    	
    	laticeVue.afficherGagnants(joueursGagnants);
    }
	
	public void initialiserValeurs(Plateau plateau, LaticeVueConsole laticeVue) {
		this.plateau = plateau;
		this.laticeVue = laticeVue;
	}
}