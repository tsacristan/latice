package latice.model;

import latice.util.PiocheInvalideException;

public class Joueur {
	
	private String pseudo;
	private RackJoueur rackJoueur;
	private PileJoueur pileJoueur;
	
	public Joueur(String pseudo, RackJoueur rackJoueur, PileJoueur pileJoueur) {
		this.pseudo = pseudo;
		this.rackJoueur = rackJoueur;
		this.pileJoueur = pileJoueur;
	}
	
	public Joueur(String pseudo) {
		this(pseudo, new RackJoueur(), new PileJoueur());
	}
	
	public Joueur() {
		this("Défaut", new RackJoueur(), new PileJoueur());
	}
	
	public void remplirRack() throws PiocheInvalideException {
		rackJoueur.remplir(pileJoueur);
	}

	public String pseudo() {
		return pseudo;
	}
	
	public RackJoueur rackJoueur() {
		return rackJoueur;
	}
	
	public PileJoueur pileJoueur() {
		return pileJoueur;
	}

}
