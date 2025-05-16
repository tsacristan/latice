package latice.view;

import latice.view.console.CouleurConsole;
import latice.view.console.EmojiForme;

public enum Textes {
	AIDE_PLATEAU("Plume : %s, Oiseau : %s, Tortue : %s, Fleur : %s, Geko : %s, Dauphin : %s, Case lune : %s, Case soleil : %s, Case vide : %s"),
	CHOIX_PSEUDO("Veuillez choisir le pseudonyme pour le joueur n°%d : "),
	PSEUDO_VIDE("Pseudo invalide (vide) ! Veuillez choisir le pseudonyme pour le joueur n°%d : "),
	PSEUDO_TROP_GRAND("Veuillez respecter la limite de 16 caractères !"),
	PSEUDO_DEJA_PRIS("Le pseudonyme est déjà pris ! Veuillez en saisir un différent : "),
	NB_TOUR("Tour n°%d : %s"),
	DEMANDER_TUILE("Quelle tuile jouer ? (index de 1 à %d) : "),
	DEMANDER_LIGNE("Sur quelle ligne ? (%d à %d)"),
	DEMANDER_COLONNE("Sur quelle colonne ? (%d à %d)"),
	COORDS_INVALIDE("Les coordonnées ne sont pas valides !"),
	INDICE_RACK_INVALIDE("L'indice du rack n'est pas valide !"),
	RACK_VIDE("Le rack est vide !"),
	CASE_NON_VIDE("La case n'est pas vide !"),
	ERREUR_PILE_VIDE("La pile est vide !"),
	AFFICHAGE_SOLEIL(CouleurConsole.ANSI_TEXTE_JAUNE + EmojiForme.CASE_SOLEIL.emoji() + CouleurConsole.ANSI_RESET);
	
	private String texte;

	Textes(String texte) {
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
