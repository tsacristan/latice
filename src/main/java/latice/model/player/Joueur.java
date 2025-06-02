package latice.model.player;

import java.util.Objects;

import latice.util.exception.PiocheInvalideException;

public class Joueur {
	
	private String pseudo;
	private RackJoueur rackJoueur;
	private PileJoueur pileJoueur;
	private int score;
	
	public Joueur(String pseudo, RackJoueur rackJoueur, PileJoueur pileJoueur, int score) {
		this.pseudo = pseudo;
		this.rackJoueur = rackJoueur;
		this.pileJoueur = pileJoueur;
		this.score = score;
	}
	
	public Joueur(String pseudo) {
		this(pseudo, new RackJoueur(), new PileJoueur(), 0);
	}
	
	public Joueur() {
		this("Défaut");
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
	
	public int score() {
		return score;
	}
	
	public void ajouterScore(int points) {
	    System.out.println("[DEBUG] " + pseudo + " - score avant : " + score + ", ajout de : " + points);
	    this.score += points;
	    System.out.println("[DEBUG] " + pseudo + " - score après : " + score);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pileJoueur, pseudo, rackJoueur, score);
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
				&& Objects.equals(rackJoueur, other.rackJoueur) && score == other.score;
	}

}