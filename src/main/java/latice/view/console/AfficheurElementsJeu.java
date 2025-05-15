package latice.view.console;

import latice.model.board.Plateau;
import latice.model.material.Case;
import latice.model.material.Tuile;
import latice.model.player.RackJoueur;
import latice.view.AfficherPlateau;
import latice.view.AfficherRack;

public class AfficheurElementsJeu implements AfficherPlateau, AfficherRack {

	@Override
	public void afficherRack(RackJoueur rack) {
		for (Tuile tuile : rack.rack()) {
			Case caseTuile = new Case(tuile);
			Console.messageAligne(caseTuile.toString() + "  ");
		}
		Console.message();
	}

	@Override
    public void afficherPlateau(Plateau plateau) {
        Case[][] grille = plateau.grille();

        Console.titre("Plateau de jeu");
        for (Case[] line: grille) {
            for (Case tuile: line) {
                Console.messageAligne("  " + tuile + "  ");
            }
            Console.message();
        }
        Console.message(Console.LIGNE_HORIZONTALE);
    }

}
