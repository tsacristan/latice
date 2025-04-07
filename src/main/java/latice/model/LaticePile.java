package latice.model;
import java.util.ArrayList;

public class LaticePile {
ArrayList<Tuile> pile = new ArrayList<Tuile>();

	public void Pile() {
		for (Couleur couleur : Couleur.values()) {
			for (Forme forme : Forme.values()) {
				pile.add(new Tuile(couleur,forme));
				pile.add(new Tuile(couleur,forme));
				}
			}
	}
}