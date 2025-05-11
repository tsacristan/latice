package latice.model;

public class Coordonnees {
	private final int ligne;
	private final int colonne;
	
	public Coordonnees(int colonne, int ligne) {
		this.ligne = colonne;
		this.colonne = ligne;
	}

	public int ligne() {
		return ligne;
	}

	public int colonne() {
		return colonne;
	}
}
