package latice.view;

import latice.view.console.CouleurConsole;
import latice.view.console.EmojiForme;

public enum Textes {
	AIDE_PLATEAU("Plume : %s, Oiseau : %s, Tortue : %s, Fleur : %s, Gecko : %s, Dauphin : %s, Case lune : %s, Case soleil : %s, Case vide : %s"),
	CHOIX_PSEUDO("Veuillez choisir le pseudonyme pour le joueur n°%d : "),
	NB_TOUR("Tour n°%d : %s"),
	DEMANDER_TUILE("Quelle tuile jouer ? (index de 1 à %d) : "),
	DEMANDER_LIGNE("Sur quelle ligne ? (%d à %d)"),
	DEMANDER_COLONNE("Sur quelle colonne ? (%d à %d)"),
	COORDONNEES_HORS_PLATEAU("Coordonnées en dehors du plateau : ligne=%d, colonne=%d"),
	COORDONNEES_HORS_RACK("Coordonnées en dehors du rack : indexRack=%d"),
	AFFICHAGE_SOLEIL(CouleurConsole.ANSI_TEXTE_JAUNE + EmojiForme.CASE_SOLEIL.emoji() + CouleurConsole.ANSI_RESET),
	AFFICHAGE_JOUEUR("%s : %d points, tuiles placées : %d"),
	AFFICHAGE_TUILES_RESTANTES("Tuiles restantes dans la pile : %d"),
	AFFICHAGE_POINTS("Points : %d"),
	AFFICHAGE_TUILES_PLACEES("Placées : %d"),
    DEMANDER_ACTION_TOUR("Que voulez-vous faire ? (1: jouer une tuile, 2: passer, 3: échanger le rack, 4: ouvrir la boutique) : "),
    TUILES_ECHANGEES("Tuiles échangées !"),
    TOUR_PASSE("Tour passé !"),
    AFFICHAGE_GAGNANTS("Le(s) gagnant(s) est : "),
    TUILES_RESTANTES("Tuiles restantes :"),
	ACHETER_COUP_SUPPLEMENTAIRE("Achetez un Coup supplémentaire (2 points)"),
	POINTS_JOUEURS("Vos points : %d\nCliquez sur OK pour acheter."),
	ACHAT_ACTION_SUPPLEMENTAIRE_SUCCES("Vous avez acheté un coup supplémentaire avec succès !"),
	BIENVENUE_BOUTIQUE("Bienvenue à la Boutique"),
    POINTS_ACTUELS("Points actuels : %d points"),
    COUT_ACTION_SUPPLEMENTAIRE("🔹 Coût pour une action supplémentaire : %d points"),
    DEMANDER_ACHAT("Souhaitez-vous acheter une action supplémentaire ?"),
    CHOIX_OUI_NON("1 - Oui\n2 - Non"),
    VOTRE_CHOIX("Votre choix : "),
    ACHAT_REUSSI("Achat réussi ! Vous avez maintenant une action supplémentaire."),
    POINTS_RESTANTS("Points restants : %d"),
    MERCI_VISITE("Merci d'avoir visité la boutique !"),
    RETOUR_JEU("Retour au jeu..."),
    SEPARATEUR("====================================");
	
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
