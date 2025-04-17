package latice.model;

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
    
    
    // Récuperer le rack, puis la case puis retirer la tuile du joueur du rack
    // puis la poser sur le plateau
    // puis repioche
    public void placerLaTuileSurLePlateau(int indexRack,int ligne,int colonne, RackJoueur rack) {
    	Tuile tuile = rack.retirer(indexRack);
    	grille[ligne][colonne].changerTuile(tuile);
    	grille[ligne][colonne].changerTypeCase(null);
    	
    }
    
    
}