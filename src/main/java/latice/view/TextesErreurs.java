package latice.view;

public enum TextesErreurs {
	COORDONNEES_HORS_PLATEAU("Coordonnées en dehors du plateau : ligne=%d, colonne=%d"),
	PLACEMENT_DEJA_EXISTANT("Il existe déjà une tuile sur cette case"),
	COORDONNEES_HORS_RACK("Coordonnées en dehors du rack : indexRack=%d"),
	FORME_TUILE_INVALIDE("Erreur ! Forme %s non reconnue !"),
	COULEUR_TUILE_INVALIDE("Erreur ! Couleur %s non reconnue !"),
	PSEUDO_VIDE("Pseudo invalide (vide) ! Veuillez choisir le pseudonyme pour le joueur n°%d : "),
	PSEUDO_TROP_GRAND("Veuillez respecter la limite de 16 caractères !"),
	PSEUDO_DEJA_PRIS("Le pseudonyme est déjà pris ! Veuillez en saisir un différent : "),
	COORDS_INVALIDE("Les coordonnées ne sont pas valides !"),
	INDICE_RACK_INVALIDE("L'indice du rack n'est pas valide !"),
	RACK_VIDE("Le rack est vide !"),
	CASE_NON_VIDE("La case n'est pas vide !"),
	ERREUR_PILE_VIDE("La pile est vide !");
	
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
