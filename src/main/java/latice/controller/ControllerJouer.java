package latice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.material.Case;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.model.player.Joueur;

public abstract class ControllerJouer {
	
	protected Plateau plateau;
	protected Random random;
	protected ArrayList<Joueur> joueurs;

	public abstract void jouer(List<Joueur> joueurs);
	public abstract void annoncerGagnants();
	
	public void initialiserPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	public int calculerPointsCoup(Coordonnees coordsCoup, Tuile tuileAJouer) {
		int pointsGagnees = calculerPointsCaseSoleil(coordsCoup);
		
		pointsGagnees += calculerPointsAdjacents(coordsCoup, tuileAJouer);
		
		return pointsGagnees;
	}
	
	private int calculerPointsCaseSoleil(Coordonnees coordsCoup) {
		boolean estSoleil = plateau.obtenirTuile(coordsCoup).typeCase() == TypeCase.CASE_SOLEIL;
		return estSoleil ? Points.SUR_CASE_SOLEIL.valeur() : 0;
	}
	
	private int calculerPointsAdjacents(Coordonnees coordsCoup, Tuile tuileAJouer) {
		int pointsGagnees;

		ArrayList<Coordonnees> coordsAdjacentes = (ArrayList<Coordonnees>) coordsCoup.obtenirCoordonneesAdjacentesValides();
		ArrayList<Coordonnees> coordsAdjacentesFiltree = filtrerCoordonneesAdjacentesMemeFormeOuCouleur(tuileAJouer, coordsAdjacentes);
		
		switch (coordsAdjacentesFiltree.size()) {
			case 2:
				pointsGagnees = Points.DOUBLE.valeur();
				break;
			case 3:
				pointsGagnees = Points.TRIPLE.valeur();
				break;
			case 4:
				pointsGagnees = Points.LATICE.valeur();
				break;
			default:
				pointsGagnees = 0;
		}
		
		return pointsGagnees;
	}
	
	private ArrayList<Coordonnees> filtrerCoordonneesAdjacentesMemeFormeOuCouleur(Tuile tuileAJouer, ArrayList<Coordonnees> coordsAdjacentes) {
		ArrayList<Coordonnees> casesAdjacentesEnCommun = new ArrayList<>();

		for (Coordonnees coordAdjacente : coordsAdjacentes) {
			Case caseAdjacente = plateau.obtenirTuile(coordAdjacente);
			Tuile tuileAdjacente = caseAdjacente.tuile();

			if (plateau.correspondParFormeOuCouleur(tuileAJouer, tuileAdjacente)) {
				casesAdjacentesEnCommun.add(coordAdjacente);
			}
		}
		
		return casesAdjacentesEnCommun;
	}
	
	public List<Joueur> obtenirGagnants() {
		int meilleurScore = 0;
    	ArrayList<Joueur> joueursGagnants = new ArrayList<>();
    	
    	for (Joueur joueur : joueurs) {
    		if (meilleurScore > joueur.score()) {
    			meilleurScore = Integer.max(meilleurScore, joueur.score());
    			joueursGagnants.clear();
    		} else if (meilleurScore == joueur.score()) {
    			joueursGagnants.add(joueur);
    		}
    	}
    	
    	return joueursGagnants;
	}
}
