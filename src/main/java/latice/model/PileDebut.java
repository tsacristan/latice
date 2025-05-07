package latice.model;

import java.util.Collections;

public class PileDebut extends LaticePile {
	public final static int NOMBRE_TUILE_TOTAL = 72;
	
    public void remplir() {
        for (Couleur couleur : Couleur.values()) {
            for (Forme forme : Forme.values()) {
                add(new Tuile(couleur,forme));
                add(new Tuile(couleur,forme));
            }
        }
    }

    

    public void distribuer(PileJoueur[] pilesJoueur) {
        for (LaticePile pileJoueur : pilesJoueur) {
            for (int i = 0; i < NOMBRE_TUILE_TOTAL / pilesJoueur.length; i++) {
                pileJoueur.ajouterTuile(retirerTuile());
            }
        }
    }
}
