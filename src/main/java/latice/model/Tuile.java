package latice.model;

public class Tuile {
	private final Couleur couleur;
	private final Forme forme;

	public Tuile(Couleur couleur, Forme forme) {
		this.couleur = couleur;
		this.forme = forme;
	}

	public Couleur couleur() {
		return couleur;
	}

	public Forme forme() {
		return forme;
	}

	@Override
	public String toString() {
		return "Tuile [couleur=" + couleur + ", forme=" + forme + "]";
	}
}
