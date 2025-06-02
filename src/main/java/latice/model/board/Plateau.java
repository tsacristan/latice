package latice.model.board;

import java.util.ArrayList;
import java.util.List;

import latice.model.material.Case;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.model.player.RackJoueur;
import latice.util.Observable;
import latice.util.PlateauListener;
import latice.util.exception.AucuneCouleurOuFormeCorrespondantException;
import latice.util.exception.AucuneTuileAdjacenteException;
import latice.util.exception.PlacementDejaExistantInvalide;
import latice.util.exception.PlateauIndexInvalideException;
import latice.util.exception.RackIndexInvalideException;
import latice.util.exception.RackInvalideException;
import latice.view.Textes;
import latice.view.TextesErreurs;

public class Plateau extends Observable<PlateauListener> {
    private final Case[][] grille;
    public static final int COLONNES = 9;
    public static final int LIGNES = 9;
    
    private Coordonnees plateauCentre;

    public Plateau() {
        grille = new Case[LIGNES][COLONNES];


        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                grille[i][j] = new Case(TypeCase.CASE_VIDE);
            }
        }
        
        try {
			plateauCentre = new Coordonnees(COLONNES / 2, LIGNES / 2);
		} catch (PlateauIndexInvalideException e) {
			e.printStackTrace();
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

    public void placerLaTuileSurLePlateau(int indexRack, Coordonnees coordsTuile, RackJoueur rack) throws RackInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide, AucuneTuileAdjacenteException, AucuneCouleurOuFormeCorrespondantException {

    	if (indexRack < 0 || indexRack >= RackJoueur.TAILLE_MAX_RACK) {
    	    throw new RackIndexInvalideException(
    	        String.format(Textes.COORDONNEES_HORS_RACK.toString(), indexRack));
    	}
        
    	if (obtenirTuile(coordsTuile).typeCase() == TypeCase.CASE_OCCUPEE) {
    	    throw new PlacementDejaExistantInvalide(TextesErreurs.PLACEMENT_DEJA_EXISTANT.toString());
    	}
    	
    	boolean estAuMilieu = coordsTuile.equals(plateauCentre);
    	if (!estAuMilieu) {
    		Tuile tuileAPlacer = rack.rack().get(indexRack);
    		verificationPlacementValide(tuileAPlacer, coordsTuile);
    	}

        Tuile tuile = rack.choisirTuile(indexRack);        
        obtenirTuile(coordsTuile).changerTuile(tuile);
        obtenirTuile(coordsTuile).changerTypeCase(TypeCase.CASE_OCCUPEE);
        
        declencherListeners();
    }
    
    public Case obtenirTuile(Coordonnees coords) {
    	return grille[coords.ligne()][coords.colonne()];
    }
    
    private void verificationPlacementValide(Tuile tuileAPlacer, Coordonnees aPlacerSur) throws AucuneTuileAdjacenteException, AucuneCouleurOuFormeCorrespondantException {
    	ArrayList<Case> casesAdjacentes = obtenirCasesAdjacentesPresenteAUnPoint(aPlacerSur);
    	if (casesAdjacentes.isEmpty()) 
    		throw new AucuneTuileAdjacenteException(TextesErreurs.TUILE_ISOLEE.toString());
    	
    	if (!existeCaseAvecFormeOuCouleur(casesAdjacentes, tuileAPlacer)) 
    			throw new AucuneCouleurOuFormeCorrespondantException(TextesErreurs.TUILE_NI_COULEUR_NI_FORME.toString());
    }
    
    private ArrayList<Case> obtenirCasesAdjacentesPresenteAUnPoint(Coordonnees coordsDuPoint) {
    	ArrayList<Case> casesAdjacentes = new ArrayList<>();
    	
    	List<Coordonnees> coordsAdjacentes = coordsDuPoint.obtenirCoordonneesAdjacentesValides();
    	for (Coordonnees coord : coordsAdjacentes) {
    		Case caseAVerifier = obtenirTuile(coord);
    		if (caseAVerifier.tuile() != null) casesAdjacentes.add(caseAVerifier);
    	}
    	
    	return casesAdjacentes;
    }
    
    private boolean existeCaseAvecFormeOuCouleur(ArrayList<Case> casesAdjacentes, Tuile tuileAPlacer) {
    	boolean existeUneCaseQuiCorrespond = false;
    	
    	int i = 0;
    	while (!existeUneCaseQuiCorrespond && i < casesAdjacentes.size()) {
    		Case caseAdjacente = casesAdjacentes.get(i);
    		Tuile tuileAdjacente = caseAdjacente.tuile();
    		
    		boolean estMemeCouleur = tuileAdjacente.couleur() == tuileAPlacer.couleur();
    		boolean estMemeForme = tuileAdjacente.forme() == tuileAPlacer.forme();
    		
    		if (estMemeCouleur || estMemeForme) {
    			existeUneCaseQuiCorrespond = true;
    		}
    		i++;
    	}
    	
    	return existeUneCaseQuiCorrespond;
    }
    
	@Override
	protected void declencherListeners() {
		for (PlateauListener listener : listeners()) {
    	    listener.plateauEstMisAJour();
    	}
	}

	public Coordonnees plateauCentre() {
		return plateauCentre;
	}
}
