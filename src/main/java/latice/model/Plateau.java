package latice.model;

public class Plateau {
    private final String[][] grille;
    private final int taille = 9;

    public Plateau() {
        grille = new String[taille][taille];


        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = ".";
            }
        }


        grille[4][4] = "L";
        grille[2][2] = "S";
        grille[2][6] = "S";
        grille[6][2] = "S";
        grille[6][6] = "S";
    }

    public String[][] Grille() {
        return grille;
    }

    public int Taille() {
        return taille;
    }
}