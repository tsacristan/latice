package latice.controller;

import java.util.ArrayList;
import java.util.Random;

import latice.model.PileDebut;
import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.player.EtatsPseudo;
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
		
		initialiserJoueurs();
		
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
	
	private void initialiserJoueurs() {
		joueurs = new ArrayList<>();
		
		for (int i = 1; i < 3; i++) {
			Joueur nouveauJoueur = initialiserJoueur(i);
			joueurs.add(nouveauJoueur);
		}
	}
	
	private Joueur initialiserJoueur(int numeroJoueur) {
		String pseudoChoisi = laticeVue.choisirPseudo(numeroJoueur);
		
		EtatsPseudo validitePseudo = estPseudoValide(pseudoChoisi);
		while (validitePseudo != EtatsPseudo.PSEUDO_CORRECT) {
			switch (validitePseudo) {
				case PSEUDO_DEJA_PRIS:
					Console.messagef(TextesErreurs.PSEUDO_DEJA_PRIS, numeroJoueur);
					break;
				case PSEUDO_TROP_GRAND:
					Console.messagef(TextesErreurs.PSEUDO_TROP_GRAND, numeroJoueur);
					break;
				case PSEUDO_VIDE:
					Console.messagef(TextesErreurs.PSEUDO_VIDE, numeroJoueur);
					break;
				default:
					break;
			}
			
			pseudoChoisi = laticeVue.choisirPseudo(numeroJoueur);
			validitePseudo = estPseudoValide(pseudoChoisi);
		}
		
		return new Joueur(pseudoChoisi);
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
			int emplacementRack = laticeVue.demanderTuileAPoser(joueur) - 1;
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
	
	private EtatsPseudo estPseudoValide(String pseudo) {
		boolean pseudoExistant = false;
		for (Joueur joueur : joueurs) {
			if (joueur.pseudo().equals(pseudo)) {
				pseudoExistant = true;
			}
		}
		
		if (pseudoExistant) return EtatsPseudo.PSEUDO_DEJA_PRIS;
		
		if (pseudo.length() > 16) return EtatsPseudo.PSEUDO_TROP_GRAND;
		if (pseudo.isEmpty()) return EtatsPseudo.PSEUDO_VIDE;
		
		return EtatsPseudo.PSEUDO_CORRECT;
	}
	
	public ArrayList<Joueur> joueurs() {
		return joueurs;
	}
	
	public void changerVue(LaticeVue laticeVue) {
		this.laticeVue = laticeVue;
	}
}
