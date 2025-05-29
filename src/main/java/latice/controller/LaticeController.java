package latice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import latice.model.PileDebut;
import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.PileJoueur;
import latice.model.player.RackJoueur;
import latice.util.exception.AucuneCouleurOuFormeCorrespondantException;
import latice.util.exception.AucuneTuileAdjacenteException;
import latice.util.exception.PiocheInvalideException;
import latice.util.exception.PlacementDejaExistantInvalide;
import latice.util.exception.RackIndexInvalideException;
import latice.util.exception.RackInvalideException;
import latice.view.LaticeVue;
import latice.view.TextesErreurs;

public class LaticeController {
	
	private LaticeVue laticeVue;
	private ArrayList<Joueur> joueurs;
	private Plateau plateau;
	private Random random;
	
	private static final int TOURS_MAX = 10;

	public LaticeController(LaticeVue laticeVue) {
		this.laticeVue = laticeVue;
		laticeVue.changerController(this);
		random = new Random();
	}
	
	public void demarrerJeu() {
		initialiserPartie();
		
		jouer();
	}

	private void initialiserPartie() {
		
		joueurs = (ArrayList<Joueur>) ControllerInitialisationJoueur.initialiserJoueurs(laticeVue);
		
		PileDebut pile = new PileDebut();
		pile.remplir();
		pile.melanger();
		
		pile.distribuer(new PileJoueur[]{joueurs.get(0).pileJoueur(),joueurs.get(1).pileJoueur()});
		
		try {
			for (Joueur joueur : joueurs) {
				joueur.remplirRack();
			}
		} catch (PiocheInvalideException e) {
			laticeVue.afficherErreur(TextesErreurs.ERREUR_PILE_VIDE.toString());
			return;
		}
		plateau = new Plateau();
	}
	
	
	
	private void jouer() {
		Joueur joueurCourant = random.nextBoolean() ? joueurs.get(0) : joueurs.get(1);
		int nombreTour = 1;
		boolean estPremierTour = true;
		
		jouerTour(joueurCourant, nombreTour, estPremierTour);
		nombreTour++;
		joueurCourant = joueurCourant.equals(joueurs.get(1)) ? joueurs.get(0) : joueurs.get(1);
		estPremierTour = !estPremierTour;
		while (nombreTour <= TOURS_MAX) {
			jouerTour(joueurCourant, nombreTour, estPremierTour);
			
			nombreTour++;
			joueurCourant = joueurCourant.equals(joueurs.get(1)) ? joueurs.get(0) : joueurs.get(1);
		}
	}
	
	private void jouerTour(Joueur joueurQuiJoue, int nombreTour, boolean jouerCentre) {
		laticeVue.setJoueurCourant(joueurQuiJoue);
		laticeVue.afficherTour(joueurs, joueurQuiJoue, nombreTour);
		laticeVue.afficherPlateau(plateau);
		laticeVue.afficherRack(joueurQuiJoue.rackJoueur());
		
		if (!jouerCentre) placerTuile(joueurQuiJoue);
		else {
			int emplacementRack = laticeVue.demanderTuileAPoser(joueurQuiJoue) - 1;
			placerTuileEtGererErreurs(emplacementRack, plateau.plateauCentre(), joueurQuiJoue.rackJoueur());
		}
	}
	
	private void placerTuile(Joueur joueur) {		
		boolean emplacementValide = false;
		
		while (!emplacementValide) {
			int emplacementRack = laticeVue.demanderTuileAPoser(joueur) - 1;
			Coordonnees emplacementPlateau = laticeVue.choisirEmplacementPlateau();
			emplacementValide = placerTuileEtGererErreurs(emplacementRack, emplacementPlateau, joueur.rackJoueur());
		}
	}
	
	private boolean placerTuileEtGererErreurs(int emplacementRack, Coordonnees emplacementPlateau, RackJoueur rackJoueur) {
		try {
			plateau.placerLaTuileSurLePlateau(emplacementRack, emplacementPlateau, rackJoueur);
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
	
	public List<Joueur> joueurs() {
		return joueurs;
	}
	
	public void changerVue(LaticeVue laticeVue) {
		this.laticeVue = laticeVue;
	}
}