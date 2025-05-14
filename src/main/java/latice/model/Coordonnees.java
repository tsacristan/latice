package latice.model;

import latice.util.PlateauIndexInvalideException;

public class Coordonnees {
	private final int ligne;
	private final int colonne;
	
	public Coordonnees(int colonne, int ligne) throws PlateauIndexInvalideException {
		this.ligne = ligne;
		this.colonne = colonne;
		if (ligne < 0 || ligne >= Plateau.LIGNES || colonne < 0 || colonne >= Plateau.COLONNES) {
            throw new PlateauIndexInvalideException("Coordonnées en dehors du plateau : ligne=" + ligne + ", colonne=" + colonne);
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
