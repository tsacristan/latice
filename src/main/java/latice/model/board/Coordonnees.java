package latice.model.board;

import latice.util.exception.PlateauIndexInvalideException;
import latice.view.Textes;

public class Coordonnees {
	private final int ligne;
	private final int colonne;
	
	public Coordonnees(int colonne, int ligne) throws PlateauIndexInvalideException {
		this.ligne = ligne;
		this.colonne = colonne;
		if (ligne < 0 || ligne >= Plateau.LIGNES || colonne < 0 || colonne >= Plateau.COLONNES) {
			throw new PlateauIndexInvalideException(
					String.format(Textes.COORDONNEES_HORS_PLATEAU.toString(), ligne, colonne));
        }
	}
	
	
	public Coordonnees verificationCaseAdjacenteHaute() throws PlateauIndexInvalideException {
		return new Coordonnees(colonne+1,ligne);
	}
	
	public Coordonnees verificationCaseAdjacenteDroite() throws PlateauIndexInvalideException {
		return new Coordonnees(colonne,ligne+1);
	}
	
	public Coordonnees verificationCaseAdjacenteBas() throws PlateauIndexInvalideException {
		return new Coordonnees(colonne-1,ligne);
	}
	
	public Coordonnees verificationCaseAdjacenteGauche() throws PlateauIndexInvalideException {
		return new Coordonnees(colonne,ligne-1);
	}

	public int ligne() {
		return ligne;
	}

	public int colonne() {
		return colonne;
	}
}
