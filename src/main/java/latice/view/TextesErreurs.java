package latice.view;

public enum TextesErreurs {
	COORDONNEES_HORS_PLATEAU("Coordonnées en dehors du plateau : ligne=%d, colonne=%d"),
	PLACEMENT_DEJA_EXISTANT("Il existe déjà une tuile sur cette case"),
	COORDONNEES_HORS_RACK("Coordonnées en dehors du rack : indexRack=%d");

	private String texte;

	TextesErreurs(String texte) {
		this.texte = texte;
	}

	public String texte() {
		return texte;
	}

	@Override
	public String toString() {
		return texte();
	}
}
