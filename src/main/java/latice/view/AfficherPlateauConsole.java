package latice.view;

import latice.model.Plateau;

public class AfficherPlateauConsole implements AfficherPlateau {

    @Override
    public void afficher(Plateau plateau) {
        String[][] grille = plateau.Grille();

        System.out.println("\n----------- Plateau de jeu -----------");
        for (int i = 0; i < plateau.Taille(); i++) {
            for (int j = 0; j < plateau.Taille(); j++) {
                System.out.print(" " + grille[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------\n");
        System.out.println("L = case lune | S = case soleil | . = case vide\n");
    }
}
