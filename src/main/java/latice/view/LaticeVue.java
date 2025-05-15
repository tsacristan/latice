package latice.view;

import latice.controller.LaticeController;
import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.RackJoueur;

public abstract class LaticeVue {
	
	protected LaticeController controller;

	public abstract void afficherRack(RackJoueur rackJoueur);
	public abstract void afficherPlateau(Plateau plateau);
	public abstract String choisirPseudo(int numeroJoueur);
	public abstract void afficherTour(Joueur joueur, int nombreTour);
	public abstract int demanderTuileAPoser(Joueur joueur);
	public abstract Coordonnees choisirEmplacementPlateau();
	public abstract void afficherErreur(String message);
	
	public void changerController(LaticeController controller) {
		this.controller = controller;
	}

}
