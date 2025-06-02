package latice.controller;

import java.util.ArrayList;
import java.util.List;

import latice.model.player.EtatsPseudo;
import latice.model.player.Joueur;
import latice.view.LaticeVue;
import latice.view.TextesErreurs;

public class ControllerInitialisationJoueur {
	
	private static ArrayList<Joueur> joueurs;
	
	private ControllerInitialisationJoueur() {}
	
	public static List<Joueur> initialiserJoueurs(LaticeVue laticeVue) {
		joueurs = new ArrayList<>();
		
		for (int i = 1; i < 3; i++) {
			Joueur nouveauJoueur = initialiserJoueur(i, laticeVue);
			joueurs.add(nouveauJoueur);
		}
		
		return joueurs;
	}
	
	private static Joueur initialiserJoueur(int numeroJoueur, LaticeVue laticeVue) {
		String pseudoChoisi = laticeVue.choisirPseudo(numeroJoueur);
		
		EtatsPseudo validitePseudo = estPseudoValide(pseudoChoisi);
		while (validitePseudo != EtatsPseudo.PSEUDO_CORRECT) {
			switch (validitePseudo) {
				case PSEUDO_DEJA_PRIS:
					laticeVue.afficherErreur(String.format(TextesErreurs.PSEUDO_DEJA_PRIS.texte(), numeroJoueur));
					break;
				case PSEUDO_TROP_GRAND:
					laticeVue.afficherErreur(String.format(TextesErreurs.PSEUDO_TROP_GRAND.texte(), numeroJoueur));
					break;
				case PSEUDO_VIDE:
					laticeVue.afficherErreur(String.format(TextesErreurs.PSEUDO_VIDE.texte(), numeroJoueur));
					break;
				default:
					laticeVue.afficherErreur(TextesErreurs.ETAT_INCONNU.texte());
					break;
			}
			
			pseudoChoisi = laticeVue.choisirPseudo(numeroJoueur);
			validitePseudo = estPseudoValide(pseudoChoisi);
		}
		
		return new Joueur(pseudoChoisi);
	}
	
	private static EtatsPseudo estPseudoValide(String pseudo) {
		if (pseudo == null) return EtatsPseudo.PSEUDO_VIDE;
		
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

}
