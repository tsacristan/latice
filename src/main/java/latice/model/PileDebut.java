package latice.model;

import java.util.Collections;

public class PileDebut extends LaticePile {
	private final int nombreTuilesTotal = 72;
	
    public void remplir() {
        for (Couleur couleur : Couleur.values()) {
            for (Forme forme : Forme.values()) {
                add(new Tuile(couleur,forme));
                add(new Tuile(couleur,forme));
            }
        }
    }

    public void melanger() {
        Collections.shuffle(this);
    }

    public void distribuer(PileJoueur[] pilesJoueur) {
        for (LaticePile pileJoueur : pilesJoueur) {
            for (int i = 0; i < nombreTuilesTotal / pilesJoueur.length; i++) {
                pileJoueur.ajouterTuile(retirerTuile());
            }
        }
    }
}
