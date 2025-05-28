package latice.model.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import latice.util.exception.PlateauIndexInvalideException;
import latice.view.Textes;

public class Coordonnees {
	private final int ligne;
	private final int colonne;
	
	public Coordonnees( int colonne,int ligne) throws PlateauIndexInvalideException {
		this.ligne = ligne;
		this.colonne = colonne;
		if (ligne < 0 || ligne >= Plateau.LIGNES || colonne < 0 || colonne >= Plateau.COLONNES) {
			throw new PlateauIndexInvalideException(
					String.format(Textes.COORDONNEES_HORS_PLATEAU.toString(), ligne, colonne));
        }
	}
	
	public Coordonnees verificationCaseAdjacenteHaute() {
		try {
			return new Coordonnees(colonne,ligne + 1);
		} catch (PlateauIndexInvalideException e) {
			return null;
		}
	}

	public Coordonnees verificationCaseAdjacenteDroite() {
		try {
			return new Coordonnees(colonne + 1,ligne);
		} catch (PlateauIndexInvalideException e) {
			return null;
		}
	}
	
	
	public Coordonnees verificationCaseAdjacenteBas() {
		try {
			return new Coordonnees(colonne,ligne - 1);
		} catch (PlateauIndexInvalideException e) {
			return null;
		}
	}
	
	public Coordonnees verificationCaseAdjacenteGauche() {
		try {
			return new Coordonnees(colonne - 1,ligne);
		} catch (PlateauIndexInvalideException e) {
			return null;
		}		
	}
	
	public List<Coordonnees> obtenirCoordonneesAdjacentesValides() {
		ArrayList<Coordonnees> coordsAdjacentes = new ArrayList<>();
		
		Coordonnees coordsHaut = verificationCaseAdjacenteHaute();
		if (coordsHaut != null) coordsAdjacentes.add(coordsHaut);
		
		Coordonnees coordsDroite = verificationCaseAdjacenteDroite();
		if (coordsDroite != null) coordsAdjacentes.add(coordsDroite);
		
		
		Coordonnees coordsBas = verificationCaseAdjacenteBas();
		if (coordsBas != null) coordsAdjacentes.add(coordsBas);
		
		Coordonnees coordsGauche = verificationCaseAdjacenteGauche();
		if (coordsGauche != null) coordsAdjacentes.add(coordsGauche);
		
		return coordsAdjacentes;
	}

	public int ligne() {
		return ligne;
	}

	public int colonne() {
		return colonne;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(colonne, ligne);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordonnees other = (Coordonnees) obj;
		return colonne == other.colonne && ligne == other.ligne;
	}

}
