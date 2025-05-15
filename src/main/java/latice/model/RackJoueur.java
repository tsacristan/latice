package latice.model;

import java.util.ArrayList;
import java.util.List;

import latice.util.Observable;
import latice.util.PiocheInvalideException;
import latice.util.RackInvalideException;
import latice.util.RackListener;

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
    		throw new PiocheInvalideException("Erreur : la pioche est vide.");
    	}
    	else if (pileJoueur.size() < TAILLE_MAX_RACK) {
          	pileJoueur.addAll(rack);
          	rack.clear();
       		pileJoueur.melanger();
       		for (int i = 0; i < TAILLE_MAX_RACK; i++) {
                rack.add(pileJoueur.retirerTuile());
            }
       	}
       	else {
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
    
    public Tuile choisirTuile(int index) throws RackInvalideException {
    	if (rack.isEmpty()) {
            throw new RackInvalideException("Erreur : le rack doit contenir au moins 1 tuile.");
        }
        Tuile aJouer = rack.get(index);
        rack.remove(index);
        
        declencherListeners();
        
        return aJouer;
    }

    @Override
    public String toString() {
        return rack.toString();
    }

	public List<Tuile> rack() {
		return rack;
	}

	@Override
	protected void declencherListeners() {
		for (RackListener listener : listeners) {
    	    listener.rackEstMisAJour();
    	}
	}
}