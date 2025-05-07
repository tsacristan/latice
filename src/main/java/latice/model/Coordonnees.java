package latice.model;

public class Coordonnées {
	private final int ligne;
	private final int colonne;
	
	public Coordonnées(int ligne, int colonne) {
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
