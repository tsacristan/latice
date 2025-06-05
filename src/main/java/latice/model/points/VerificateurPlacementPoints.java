package latice.model.points;

import java.util.ArrayList;
import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.material.Case;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;

public class VerificateurPlacementPoints {
	
	private Plateau plateau;
	
	public VerificateurPlacementPoints(Plateau plateau) {
		this.plateau = plateau;
	}
	
	public int calculerPointsCoup(Coordonnees coordsCoup, Tuile tuileAJouer) {
		int pointsGagnees = calculerPointsCaseSoleil(coordsCoup, plateau);
		
		pointsGagnees += calculerPointsAdjacents(coordsCoup, tuileAJouer);
		
		return pointsGagnees;
	}
	
	private int calculerPointsCaseSoleil(Coordonnees coordsCoup, Plateau plateau) {
		boolean estSoleil = plateau.obtenirTuile(coordsCoup).typeCase() == TypeCase.CASE_SOLEIL;
		return estSoleil ? Points.SUR_CASE_SOLEIL.valeur() : Points.VALEUR_DEFAUT.valeur();
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
				pointsGagnees = Points.VALEUR_DEFAUT.valeur();
		}
		
		return pointsGagnees;
	}
	
	private ArrayList<Coordonnees> filtrerCoordonneesAdjacentesMemeFormeOuCouleur(Tuile tuileAJouer, ArrayList<Coordonnees> coordsAdjacentes) {
		ArrayList<Coordonnees> casesAdjacentesEnCommun = new ArrayList<>();

		for (Coordonnees coordAdjacente : coordsAdjacentes) {
			Case caseAdjacente = plateau.obtenirTuile(coordAdjacente);
			Tuile tuileAdjacente = caseAdjacente.tuile();

			if (tuileAJouer.correspondParFormeOuCouleur(tuileAdjacente)) {
				casesAdjacentesEnCommun.add(coordAdjacente);
			}
		}
		
		return casesAdjacentesEnCommun;
	}

}
