package latice.controller;

import java.util.ArrayList;
import java.util.Random;

import latice.model.PileDebut;
import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.PileJoueur;
import latice.util.exception.PiocheInvalideException;
import latice.util.exception.PlacementDejaExistantInvalide;
import latice.util.exception.RackIndexInvalideException;
import latice.util.exception.RackInvalideException;
import latice.view.LaticeVue;
import latice.view.TextesErreurs;
import latice.view.console.Console;

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
		
		joueurs = new ArrayList<>();
		
		String pseudoJoueur1 = laticeVue.choisirPseudo(1);
		
		Joueur joueur1 = new Joueur(pseudoJoueur1);
		ArrayList<String> joueursPseudo = new ArrayList<>();
		joueurs.add(joueur1);
		joueursPseudo.add(joueur1.pseudo());
	
		String pseudoJoueur2 = laticeVue.choisirPseudo(2);
		
		while (joueursPseudo.contains(pseudoJoueur2)) {
				Console.messagef(TextesErreurs.PSEUDO_DEJA_PRIS, 2);
				pseudoJoueur2 = laticeVue.choisirPseudo(2);
		}
		
		Joueur joueur2 = new Joueur(pseudoJoueur2);
		joueurs.add(joueur2);
		joueursPseudo.add(joueur2.pseudo());
		
		PileDebut pile = new PileDebut();
		pile.remplir();
		pile.melanger();
		
		pile.distribuer(new PileJoueur[]{joueur1.pileJoueur(),joueur2.pileJoueur()});
		
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
		
		while (nombreTour < TOURS_MAX) {
			jouerTour(joueurCourant, nombreTour);
			
			nombreTour++;
			joueurCourant = joueurCourant.equals(joueurs.get(1)) ? joueurs.get(0) : joueurs.get(1);
		}
	}
	
	private void jouerTour(Joueur joueur, int nombreTour) {
		laticeVue.afficherTour(joueur, nombreTour);
		laticeVue.afficherPlateau(plateau);
		laticeVue.afficherRack(joueur.rackJoueur());
		
		
		placerTuile(joueur);
		
	}
	
	private void placerTuile(Joueur joueur) {		
		boolean emplacementValide = false;
		
		while (!emplacementValide) {
			int emplacementRack = laticeVue.demanderTuileAPoser(joueur);
			Coordonnees emplacementPlateau = laticeVue.choisirEmplacementPlateau();
			try {
				plateau.placerLaTuileSurLePlateau(emplacementRack, emplacementPlateau, joueur.rackJoueur());
				emplacementValide = true;
			} catch (RackInvalideException e) {
				laticeVue.afficherErreur(TextesErreurs.RACK_VIDE.toString());
			} catch (RackIndexInvalideException e) {
				laticeVue.afficherErreur(TextesErreurs.INDICE_RACK_INVALIDE.toString());
			} catch (PlacementDejaExistantInvalide e) {
				laticeVue.afficherErreur(TextesErreurs.CASE_NON_VIDE.toString());
			}
		}
	}
	
	public ArrayList<Joueur> joueurs() {
		return joueurs;
	}
	
	public void changerVue(LaticeVue laticeVue) {
		this.laticeVue = laticeVue;
	}
}
