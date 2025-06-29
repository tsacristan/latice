package latice.view;

import java.util.ArrayList;
import java.util.List;

import latice.controller.LaticeController;
import latice.model.board.Plateau;
import latice.model.player.Joueur;

public abstract class LaticeVue {
	
	protected LaticeController controller;

	public abstract void afficherRack(Joueur joueur);
	public abstract void afficherPlateau(Plateau plateau);
	public abstract String choisirPseudo(int numeroJoueur1);
	public abstract void afficherTour(List<Joueur> joueurs, Joueur joueurQuiJoue, int nombreTour);
	public abstract void afficherMessage(String message);
	public abstract void afficherErreur(String message);
	public abstract void afficherGagnants(ArrayList<Joueur> gagnants);

	public void changerController(LaticeController controller) {
		this.controller = controller;
	}
	
}
