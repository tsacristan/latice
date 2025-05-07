package latice.model;

public class Coordonnees {
	private final int ligne;
	private final int colonne;
	
	public Coordonnees(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public int ligne() {
		return ligne;
	}

	public int colonne() {
		return colonne;
	}
}
