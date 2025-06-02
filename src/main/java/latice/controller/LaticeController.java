package latice.controller;

import java.util.ArrayList;
import java.util.List;

import latice.model.PileDebut;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.PileJoueur;
import latice.util.exception.PiocheInvalideException;
import latice.view.LaticeVue;
import latice.view.TextesErreurs;

public class LaticeController {
	
	private LaticeVue laticeVue;
	private ArrayList<Joueur> joueurs;
	private ControllerJouer controllerPlacement;
	
	public static final int TOURS_MAX = 10;

	public LaticeController(LaticeVue laticeVue, ControllerJouer controllerPlacement) {
		this.laticeVue = laticeVue;
		this.controllerPlacement = controllerPlacement;
		laticeVue.changerController(this);
	}
	
	public void demarrerJeu() {
		initialiserPartie();
		controllerPlacement.jouer(joueurs);
	}
	
	private void initialiserPartie() {
		Plateau plateau;	
		joueurs = (ArrayList<Joueur>) ControllerInitialisationJoueur.initialiserJoueurs(laticeVue);
		
		PileDebut pile = new PileDebut();
		pile.remplir();
		pile.melanger();
		
		pile.distribuer(new PileJoueur[]{joueurs.get(0).pileJoueur(),joueurs.get(1).pileJoueur()});
		
		try {
			for (Joueur joueur : joueurs) {
				joueur.piocher();
			}
		} catch (PiocheInvalideException e) {
			laticeVue.afficherErreur(TextesErreurs.ERREUR_PILE_VIDE.toString());
			return;
		}
		plateau = new Plateau();
		
		controllerPlacement.initialiserPlateau(plateau);
	}
	
	public List<Joueur> joueurs() {
		return joueurs;
	}
	
	public void changerVue(LaticeVue laticeVue) {
		this.laticeVue = laticeVue;
	}
}