package latice.model.material;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(couleur, forme);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuile other = (Tuile) obj;
		return couleur == other.couleur && forme == other.forme;
	}
}
