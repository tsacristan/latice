package latice.ihm;

import latice.model.Case;
import latice.model.Plateau;

public class AfficherPlateauConsole implements AfficherPlateau {

    @Override
    public void afficher(Plateau plateau) {
        Case[][] grille = plateau.grille();

        Console.titre("Plateau de jeu");
        for (Case[] line: grille) {
            for (Case tuile: line) {
                Console.messageAligne(" " + tuile + " ");
            }
            Console.message();
        }
        Console.message(Console.LIGNE_HORIZONTALE);
    }

}
