package latice.model;

import java.util.Arrays;

public class RackJoueur {
    private final Tuile[] rack;

    public RackJoueur(Tuile[] rack) {
        this.rack = rack;
    }

    public RackJoueur() {
        this(new Tuile[5]);
    }

    public void remplir(PileJoueur pile) {
        for (int i = 0; i < 5; i++) {
            rack[i] = pile.retirerTuile();
        }
    }
    
    public void piocher(PileJoueur pile) {
    	for (int i = 0; i < 5; i++) {
    		pile.ajouterTuile(rack[i]);
    		rack[i] = pile.retirerTuile();
    	}
    }

    @Override
    public String toString() {
        return Arrays.toString(rack);
    }
}
