package latice.model;

import latice.model.material.Couleur;
import latice.model.material.Forme;
import latice.model.material.Tuile;
import latice.model.player.PileJoueur;

@SuppressWarnings("serial")
public class PileDebut extends LaticePile {
	public static final int NOMBRE_TUILE_TOTAL = 72;
	
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
