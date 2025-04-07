package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class LaticePile extends ArrayList<Tuile> {
	public void remplirPile() {
		for (Couleur couleur : Couleur.values()) {
			for (Forme forme : Forme.values()) {
				add(new Tuile(couleur,forme));
				add(new Tuile(couleur,forme));
			}
		}
	}
	
	public void melangerPile() {
		 Collections.shuffle(this);
	}

	public void distribuer(LaticePile[] pilesJoueur) {
		for (LaticePile pileJoueur : pilesJoueur) {
			for (int i = 0; i < 36; i++) {
				pileJoueur.ajouterTuile(retirerTuile());
			}
		}
	}

	public Tuile retirerTuile() {
		return remove(0);
	}

	public void ajouterTuile(Tuile tuile) {
		add(tuile);
	}
}