package latice.view;

import latice.view.console.CouleurConsole;
import latice.view.console.EmojiForme;

public enum Textes {
	AIDE_PLATEAU("Plume : %s, Oiseau : %s, Tortue : %s, Fleur : %s, Geko : %s, Dauphin : %s, Case lune : %s, Case soleil : %s, Case vide : %s"),
	CHOIX_PSEUDO("Veuillez choisir le pseudonyme pour le joueur n°%d : "),
	NB_TOUR("Tour n°%d : %s"),
	DEMANDER_TUILE("Quelle tuile jouer ? (index de 1 à %d) : "),
	DEMANDER_LIGNE("Sur quelle ligne ? (%d à %d)"),
	DEMANDER_COLONNE("Sur quelle colonne ? (%d à %d)"),
	COORDONNEES_HORS_PLATEAU("Coordonnées en dehors du plateau : ligne=%d, colonne=%d"),
	COORDONNEES_HORS_RACK("Coordonnées en dehors du rack : indexRack=%d"),
	AFFICHAGE_SOLEIL(CouleurConsole.ANSI_TEXTE_JAUNE + EmojiForme.CASE_SOLEIL.emoji() + CouleurConsole.ANSI_RESET),
	AFFICHAGE_JOUEUR("%s : %d points.");
	
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
