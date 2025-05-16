package latice.model.board;

import latice.model.material.Case;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.model.player.RackJoueur;
import latice.util.Observable;
import latice.util.PlateauListener;
import latice.util.exception.PlacementDejaExistantInvalide;
import latice.util.exception.RackIndexInvalideException;
import latice.util.exception.RackInvalideException;
import latice.view.TextesErreurs;

public class Plateau extends Observable<PlateauListener> {
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

        initialisationCaseSoleilEtLune();
    }

	private void initialisationCaseSoleilEtLune() {
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

    public void placerLaTuileSurLePlateau(int indexRack, Coordonnees coordsTuile, RackJoueur rack) throws RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide {
    	
    	if (indexRack < 0 || indexRack >= RackJoueur.TAILLE_MAX_RACK)
    	    throw new RackIndexInvalideException(
    	        String.format(TextesErreurs.COORDONNEES_HORS_RACK.toString(), indexRack));

        
    	if (obtenirTuile(coordsTuile).typeCase() == TypeCase.CASE_OCCUPEE) {
    	    throw new PlacementDejaExistantInvalide(TextesErreurs.PLACEMENT_DEJA_EXISTANT.toString());
    	}

        Tuile tuile = rack.choisirTuile(indexRack);
        obtenirTuile(coordsTuile).changerTuile(tuile);
        obtenirTuile(coordsTuile).changerTypeCase(TypeCase.CASE_OCCUPEE);
        
        declencherListeners();
    }
    
    public Case obtenirTuile(Coordonnees coords) {
    	return grille[coords.colonne()][coords.ligne()];
    }
    
	@Override
	protected void declencherListeners() {
		for (PlateauListener listener : listeners()) {
    	    listener.plateauEstMisAJour();
    	}
	}
}
