	package latice.view.console;

import latice.model.board.Plateau;
import latice.model.material.Case;
import latice.view.AfficherPlateau;

public class AfficherPlateauConsole implements AfficherPlateau {

    @Override
    public void afficher(Plateau plateau) {
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
