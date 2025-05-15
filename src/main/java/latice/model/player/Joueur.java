package latice.model.player;

import java.util.Objects;

import latice.util.exception.PiocheInvalideException;

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

	@Override
	public int hashCode() {
		return Objects.hash(pileJoueur, pseudo, rackJoueur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		return Objects.equals(pileJoueur, other.pileJoueur) && Objects.equals(pseudo, other.pseudo)
				&& Objects.equals(rackJoueur, other.rackJoueur);
	}

}
