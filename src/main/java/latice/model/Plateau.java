package latice.model;

public class Plateau {
    private final Case[][] grille;
    private final int taille = 9;

    public Plateau() {
        grille = new Case[taille][taille];


        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = new Case();
            }
        }


        grille[4][4] = new Case(TypeCase.CASE_LUNE);
        grille[2][2] = new Case(TypeCase.CASE_SOLEIL);
        grille[2][6] = new Case(TypeCase.CASE_SOLEIL);
        grille[6][2] = new Case(TypeCase.CASE_SOLEIL);
        grille[6][6] = new Case(TypeCase.CASE_SOLEIL);
    }

    public Case[][] grille() {
        return grille;
    }

    public int taille() {
        return taille;
    }
}
