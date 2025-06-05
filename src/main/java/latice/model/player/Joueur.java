package latice.model.player;

import java.util.Objects;

import latice.util.exception.PiocheInvalideException;

public class Joueur {
	
	private String pseudo;
	private RackJoueur rackJoueur;
	private PileJoueur pileJoueur;
	private int points;
	private int tuilesPlacees;
	private int nombreCoups;
	
	public Joueur(String pseudo, RackJoueur rackJoueur, PileJoueur pileJoueur, int points, int tuilesPlacees) {
		this.pseudo = pseudo;
		this.rackJoueur = rackJoueur;
		this.pileJoueur = pileJoueur;
		this.points = points;
		this.tuilesPlacees = tuilesPlacees;
		this.nombreCoups = 1;
	}
	
	public Joueur(String pseudo) {
		this(pseudo, new RackJoueur(), new PileJoueur(), 0, 0);
	}
	
	public Joueur() {
		this("Défaut");
	}
	
	public void piocher() throws PiocheInvalideException {
		rackJoueur.piocher(pileJoueur);
	}
	
	public void remplir() {
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
	
	public int points() {
		return points;
	}
	
	public int tuilesPlacees() {
		return tuilesPlacees;
	}
	
	public void ajouterPoints(int points) {
	    this.points += points;
	}
	
	public void incrementerTuilePlacees() {
		tuilesPlacees++;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pileJoueur, pseudo, rackJoueur, points);
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
				&& Objects.equals(rackJoueur, other.rackJoueur) && points == other.points;
	}

	public int nombreCoups() {
		return nombreCoups;
	}

	public int changerNombreCoups(int nombreCoups) {
		return this.nombreCoups = nombreCoups;
	}

}