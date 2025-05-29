package latice.controller;

import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.RackJoueur;
import latice.util.exception.AucuneCouleurOuFormeCorrespondantException;
import latice.util.exception.AucuneTuileAdjacenteException;
import latice.util.exception.PlacementDejaExistantInvalide;
import latice.util.exception.RackIndexInvalideException;
import latice.util.exception.RackInvalideException;
import latice.view.LaticeVue;
import latice.view.TextesErreurs;

public class LaticeControllerPlacerTuileConsole {
	
	private LaticeControllerPlacerTuileConsole() {}

	public static void placerTuile(Joueur joueur, Plateau plateau, LaticeVue laticeVue) {		
		boolean emplacementValide = false;
		
		while (!emplacementValide) {
			int emplacementRack = laticeVue.demanderTuileAPoser(joueur) - 1;
			Coordonnees emplacementPlateau = laticeVue.choisirEmplacementPlateau();
			emplacementValide = placerTuileEtGererErreurs(emplacementRack, emplacementPlateau, joueur.rackJoueur(), plateau, laticeVue);
		}
	}
	
	public static boolean placerTuileEtGererErreurs(int emplacementRack, Coordonnees emplacementPlateau, RackJoueur rackJoueur, Plateau plateau, LaticeVue laticeVue) {
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
	
}
