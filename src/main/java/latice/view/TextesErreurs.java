package latice.view;

public enum TextesErreurs {
	PLACEMENT_DEJA_EXISTANT("Il existe déjà une tuile sur cette case"),
	FORME_TUILE_INVALIDE("Erreur ! Forme %s non reconnue !"),
	COULEUR_TUILE_INVALIDE("Erreur ! Couleur %s non reconnue !"),
	PSEUDO_VIDE("Pseudo invalide (vide) ! Veuillez choisir le pseudonyme pour le joueur n°%d : "),
	PSEUDO_TROP_GRAND("Veuillez respecter la limite de 16 caractères !"),
	PSEUDO_DEJA_PRIS("Le pseudonyme est déjà pris ! Veuillez en saisir un différent : "),
	COORDS_INVALIDE("Les coordonnées ne sont pas valides !"),
	INDICE_RACK_INVALIDE("L'indice du rack n'est pas valide !"),
	RACK_VIDE("Le rack est vide !"),
	CASE_NON_VIDE("La case n'est pas vide !"),
	ERREUR_PILE_VIDE("La pile est vide !"),
	PIOCHE_VIDE("Erreur : la pioche est vide."),
	ETAT_INCONNU("Etat pseudo inconnu !"),
	TUILE_ISOLEE("Erreur : la tuile ne peut pas être isolée !"),
	TUILE_NI_COULEUR_NI_FORME("Erreur : aucune tuile adjacente a la bonne couleur ou la bonne forme."),
	VALIDATION_SANS_ACTION("Vous devez jouer avant de valider !"),
	ACTION_INVALIDE("Action invalide !"),
	DEJA_PIOCHER("Vous avez déjà pioché ce tour.");
	
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
