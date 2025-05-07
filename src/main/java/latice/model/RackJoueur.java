package latice.model;

import java.util.ArrayList;

import latice.util.PiocheInvalideException;
import latice.util.RackInvalideException;

public class RackJoueur {
    private ArrayList<Tuile> rack;
    private final PileJoueur pileJoueur;
    
    public static final int TAILLE_DU_RACK = 5;

    public RackJoueur(ArrayList<Tuile> rack, PileJoueur pileJoueur) {
    	this.rack = rack;
        this.pileJoueur = pileJoueur;
    }
    
    public RackJoueur(PileJoueur pileJoueur) {
    	this.rack = new ArrayList<>();
    	this.pileJoueur = pileJoueur;
    }

    public RackJoueur() {
    	this.rack = new ArrayList<>();
    	this.pileJoueur = new PileJoueur();
    }

    public void remplir() {
        for (int i = 0; i < TAILLE_DU_RACK; i++) {
            rack.add(pileJoueur.retirerTuile());
        }
    }
    
    public void piocher() throws PiocheInvalideException {
       	if (pileJoueur.isEmpty()) {
    		throw new PiocheInvalideException("Erreur : la pioche est vide.");
    	}
       	else if (pileJoueur.size() < TAILLE_DU_RACK) {
          	pileJoueur.addAll(rack);
          	rack.clear();
       		pileJoueur.melanger();
       		for (int i = 0; i < TAILLE_DU_RACK; i++) {
                rack.add(pileJoueur.retirerTuile());
            }
       	}
       	else {
       		ArrayList<Tuile> rackTemporaire = new ArrayList<>();
       		rackTemporaire.addAll(rack) ;
       		rack.clear();
       		for (int i = 0; i < TAILLE_DU_RACK; i++) {
                rack.add(pileJoueur.retirerTuile());
            }
       		pileJoueur.addAll(rackTemporaire);
        }
    }
    
    public Tuile choisirTuile(int index) throws RackInvalideException {
    	if (rack.isEmpty()) {
            throw new RackInvalideException("Erreur : le rack doit contenir au moins 1 tuile.");
        }
        Tuile aJouer = rack.get(index);
        rack.remove(index);
        
        return aJouer;
    }

    @Override
    public String toString() {
        return rack.toString();
    }

	public ArrayList<Tuile> rack() {
		return rack;
	}
}