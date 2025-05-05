package latice.model;

import java.util.Arrays;

import latice.util.PiocheInvalideException;
import latice.util.RackInvalideException;

public class RackJoueur {
    private final Tuile[] rack;
    private final PileJoueur pileJoueur;

    public RackJoueur(Tuile[] rack, PileJoueur pileJoueur) {
        this.rack = rack;
        this.pileJoueur = pileJoueur;
    }
    
    public RackJoueur(PileJoueur pileJoueur) {
        this(new Tuile[5], pileJoueur);
    }

    public RackJoueur() {
        this(new Tuile[5], new PileJoueur());
    }

    public void remplir() {
        for (int i = 0; i < 5; i++) {
            rack[i] = pileJoueur.retirerTuile();
        }
    }
    
    public void piocher() throws PiocheInvalideException {
       	if (pileJoueur.isEmpty()) {
    		throw new PiocheInvalideException("Erreur : la pioche est vide.");
    	}
        for (int i = 0; i < 5; i++) {
            pileJoueur.ajouterTuile(rack[i]);
            rack[i] = pileJoueur.retirerTuile();
        }
    }
    
    public Tuile retirer(int index) throws RackInvalideException {
    	if (rack.length == 0) {
            throw new RackInvalideException("Erreur : le rack doit contenir au moins 1 tuile.");
        }
        Tuile aJouer = rack[index];
        rack[index] = pileJoueur.retirerTuile();
        
        return aJouer;
    }

    @Override
    public String toString() {
        return Arrays.toString(rack);
    }

	public Tuile[] getRack() {
		return rack;
	}
}