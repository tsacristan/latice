package latice.model.player;

import java.util.ArrayList;
import java.util.List;

import latice.model.material.Tuile;
import latice.util.Observable;
import latice.util.RackListener;
import latice.util.exception.PiocheInvalideException;
import latice.util.exception.RackInvalideException;
import latice.view.TextesErreurs;

public class RackJoueur extends Observable<RackListener> {
    private List<Tuile> rack;
    
    public static final int TAILLE_MAX_RACK = 5;

    public RackJoueur(List<Tuile> rack) {
    	this.rack = rack;
    }

    public RackJoueur() {
    	this(new ArrayList<>());
    }

    public void remplir(PileJoueur pileJoueur) throws PiocheInvalideException {
    	if (pileJoueur.isEmpty()) {
    		throw new PiocheInvalideException(
    				String.format(TextesErreurs.PIOCHE_VIDE.toString()));
    	} else if (pileJoueur.size() < TAILLE_MAX_RACK) {
          	pileJoueur.addAll(rack);
          	rack.clear();
       		pileJoueur.melanger();
       		for (int i = 0; i < TAILLE_MAX_RACK; i++) {
       		    rack.add(pileJoueur.retirerTuile());
       		}
       	} else {
       		ArrayList<Tuile> rackTemporaire = new ArrayList<>();
       		rackTemporaire.addAll(rack) ;
       		rack.clear();
       		for (int i = 0; i < TAILLE_MAX_RACK; i++) {
                rack.add(pileJoueur.retirerTuile());
            }
       		pileJoueur.addAll(rackTemporaire);
        }
       	
       	declencherListeners();
    }
    
    public void piocherUneTuile(PileJoueur pile) {
        if (rack.size() < TAILLE_MAX_RACK && !pile.isEmpty()) {
            rack.add(pile.retirerTuile());
            declencherListeners();
        }
    }
    
    public Tuile choisirTuile(int index) throws RackInvalideException {
    	if (rack.isEmpty()) {
            throw new RackInvalideException(
            		String.format(TextesErreurs.RACK_VIDE.toString()));
        }
    	
        Tuile aJouer = rack.get(index);
        rack.remove(index);
        
        declencherListeners();
        
        return aJouer;
    }
    
    public void echangerTuiles(List<Integer> indices, PileJoueur pile) throws PiocheInvalideException {
        if (indices == null || indices.isEmpty()) return;

        if (pile.size() < indices.size()) {
            throw new PiocheInvalideException(TextesErreurs.PIOCHE_VIDE.toString());
        }

        indices.sort((a, b) -> Integer.compare(b, a));
        List<Tuile> tuilesARetirer = new ArrayList<>();
        for (int idx : indices) {
            if (idx >= 0 && idx < rack.size()) {
                tuilesARetirer.add(rack.remove(idx));
            }
        }

        for (int i = 0; i < tuilesARetirer.size(); i++) {
            rack.add(pile.retirerTuile());
        }

        pile.addAll(tuilesARetirer);
        declencherListeners();
    }

    @Override
    public String toString() {
        return rack.toString();
    }

	public List<Tuile> rack() {
		return rack;
	}

	@Override
	public void declencherListeners() {
		for (RackListener listener : listeners) {
    	    listener.rackEstMisAJour();
    	}
	}
}