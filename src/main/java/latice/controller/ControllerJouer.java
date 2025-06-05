package latice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.points.VerificateurPlacementPoints;

public abstract class ControllerJouer {
	
	protected Plateau plateau;
	protected Random random;
	protected ArrayList<Joueur> joueurs;
	protected VerificateurPlacementPoints verificateurPlacementPoints;

	public abstract void jouer(List<Joueur> joueurs);
	public abstract void annoncerGagnants();
	
	public List<Joueur> obtenirGagnants() {
		int meilleurScore = 0;
    	ArrayList<Joueur> joueursGagnants = new ArrayList<>();
    	
    	for (Joueur joueur : joueurs) {
    		if (meilleurScore < joueur.tuilesPlacees()) {
    			meilleurScore = Integer.max(meilleurScore, joueur.tuilesPlacees());
    			joueursGagnants.clear();
    			joueursGagnants.add(joueur);
    		} else if (meilleurScore == joueur.tuilesPlacees()) {
    			joueursGagnants.add(joueur);
    		}
    	}
    	
    	return joueursGagnants;
	}
	
	public void initialiserPlateauJoueurs(Plateau plateau, List<Joueur> joueurs) {
		this.plateau = plateau;
		this.joueurs = (ArrayList<Joueur>) joueurs;
		verificateurPlacementPoints = new VerificateurPlacementPoints(plateau);
	}
}
