package latice.model;

import latice.util.PlacementDejaExistantInvalide;
import latice.util.PlateauIndexInvalideException;
import latice.util.RackIndexInvalideException;
import latice.util.RackInvalideException;

public class Plateau {
    private final Case[][] grille;
    public static final int COLONNES = 9;
    public static final int LIGNES = 9;

    public Plateau() {
        grille = new Case[LIGNES][COLONNES];


        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
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
        return LIGNES * COLONNES;
    }

    public void placerLaTuileSurLePlateau(int indexRack, Coordonnees coordsTuile, RackJoueur rack) throws PlateauIndexInvalideException, RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide {
        int ligne = coordsTuile.ligne();
        int colonne = coordsTuile.colonne();
    	
    	if (ligne < 0 || ligne >= LIGNES || colonne < 0 || colonne >= COLONNES) {
            throw new PlateauIndexInvalideException("Coordonnées en dehors du plateau : ligne=" + ligne + ", colonne=" + colonne);
        }
        if (indexRack < 0 || indexRack >= rack.taille_du_rack) {
            throw new RackIndexInvalideException("Coordonnées en dehors du rack : indexRack=" + indexRack);
        }
        if (grille[ligne][colonne].tuile() != null) throw new PlacementDejaExistantInvalide("Il existe déjà une tuile sur cette case");

        Tuile tuile = rack.choisirTuile(indexRack);
        grille[ligne][colonne].changerTuile(tuile);
        grille[ligne][colonne].changerTypeCase(TypeCase.CASE_OCCUPEE);
    }
}
