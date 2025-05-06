package latice.model;

import latice.util.PlacementDejaExistantInvalide;
import latice.util.PlateauIndexInvalideException;
import latice.util.RackIndexInvalideException;
import latice.util.RackInvalideException;

public class Plateau {
    private final Case[][] grille;
    private final int colonnes = 9;
    private final int lignes = 9;

    public Plateau() {
        grille = new Case[lignes][colonnes];


        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                grille[i][j] = new Case(TypeCase.CASE_VIDE);
            }
        }

        grille[4][4] = new Case(TypeCase.CASE_LUNE);

        grille[0][0] = new Case(TypeCase.CASE_SOLEIL);
        grille[1][1] = new Case(TypeCase.CASE_SOLEIL);
        grille[2][2] = new Case(TypeCase.CASE_SOLEIL);

        grille[0][4] = new Case(TypeCase.CASE_SOLEIL);

        grille[0][8] = new Case(TypeCase.CASE_SOLEIL);
        grille[1][7] = new Case(TypeCase.CASE_SOLEIL);
        grille[2][6] = new Case(TypeCase.CASE_SOLEIL);

        grille[4][0] = new Case(TypeCase.CASE_SOLEIL);

        grille[8][0] = new Case(TypeCase.CASE_SOLEIL);
        grille[7][1] = new Case(TypeCase.CASE_SOLEIL);
        grille[6][2] = new Case(TypeCase.CASE_SOLEIL);

        grille[8][4] = new Case(TypeCase.CASE_SOLEIL);

        grille[8][8] = new Case(TypeCase.CASE_SOLEIL);
        grille[7][7] = new Case(TypeCase.CASE_SOLEIL);
        grille[6][6] = new Case(TypeCase.CASE_SOLEIL);

        grille[4][8] = new Case(TypeCase.CASE_SOLEIL);
    }

    public Case[][] grille() {
        return grille;
    }

    public int tailleDuTableau() {
        return lignes * colonnes;
    }

    public void placerLaTuileSurLePlateau(int indexRack,int ligne,int colonne, RackJoueur rack) throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide {
        if (ligne < 0 || ligne >= lignes || colonne < 0 || colonne >= colonnes) {
            throw new PlateauIndexInvalideException("Coordonnées en dehors du plateau : ligne=" + ligne + ", colonne=" + colonne);
        }
        if (indexRack < 0 || indexRack >= rack.getRack().length) {
            throw new RackIndexInvalideException("Coordonnées en dehors du rack : indexRack=" + indexRack);
        }
        if (grille[ligne][colonne].tuile() != null) throw new PlacementDejaExistantInvalide("Il existe déjà une tuile sur cette case");

        Tuile tuile = rack.retirer(indexRack);
        grille[ligne][colonne].changerTuile(tuile);
        grille[ligne][colonne].changerTypeCase(TypeCase.CASE_OCCUPEE);
    }
}
