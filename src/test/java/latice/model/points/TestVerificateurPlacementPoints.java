package latice.model.points;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.material.Couleur;
import latice.model.material.Forme;
import latice.model.material.Tuile;
import latice.util.exception.PlateauIndexInvalideException;

class TestVerificateurPlacementPoints {
	
	Plateau plateau;
	VerificateurPlacementPoints verificateur;

	private static final Tuile TUILE_DAUPHIN_BLEU = new Tuile(Couleur.BLEU_MARINE, Forme.DAUPHIN);
	
	private static final Tuile TUILE_DAUPHIN_JAUNE = new Tuile(Couleur.JAUNE, Forme.DAUPHIN);
	private static final Tuile TUILE_DAUPHIN_VERT = new Tuile(Couleur.VERT, Forme.DAUPHIN);
	private static final Tuile TUILE_DAUPHIN_ROUGE = new Tuile(Couleur.ROUGE, Forme.DAUPHIN);
	private static final Tuile TUILE_DAUPHIN_MAGENTA = new Tuile(Couleur.MAGENTA, Forme.DAUPHIN);

	private static final Tuile TUILE_PLUME_BLEU = new Tuile(Couleur.BLEU_MARINE, Forme.PLUME);
	private static final Tuile TUILE_OISEAU_BLEU = new Tuile(Couleur.BLEU_MARINE, Forme.OISEAU);
	private static final Tuile TUILE_GECKO_BLEU = new Tuile(Couleur.BLEU_MARINE, Forme.GECKO);
	private static final Tuile TUILE_TORTUE_BLEU = new Tuile(Couleur.BLEU_MARINE, Forme.TORTUE);
	
	private static final Tuile TUILE_OISEAU_VERT = new Tuile(Couleur.VERT, Forme.OISEAU);
	private static final Tuile TUILE_OISEAU_ROUGE = new Tuile(Couleur.ROUGE, Forme.OISEAU);

	private static Coordonnees coordNonSoleil;
	private static Coordonnees coordSoleil;
	private static Coordonnees coordMilieuStructure;
	private static Coordonnees coord1Adjacente;
	private static Coordonnees coord2Adjacente;
	private static Coordonnees coord3Adjacente;
	private static Coordonnees coord4Adjacente;

	@BeforeAll
	static void initialisation() throws PlateauIndexInvalideException  {
		coordSoleil = new Coordonnees(0, 0);
		coordNonSoleil = new Coordonnees(0, 1);
		
		coordMilieuStructure = new Coordonnees(3, 3);
		
		coord1Adjacente = new Coordonnees(3, 2);
		coord2Adjacente = new Coordonnees(2, 3);
		coord3Adjacente = new Coordonnees(4, 3);
		coord4Adjacente = new Coordonnees(3, 4);
	}
	
	@BeforeEach
	void reInitialiserPlateauEtVerificateur() {
		plateau = new Plateau();
		verificateur = new VerificateurPlacementPoints(plateau);
	}

	@Test
	void tester_case_est_soleil() {
		int pointsSoleil = verificateur.calculerPointsCoup(coordSoleil, TUILE_DAUPHIN_JAUNE);

		assertEquals(Points.SUR_CASE_SOLEIL.valeur(), pointsSoleil);
	}
	
	@Test
	void tester_case_n_est_pas_soleil() {
		int pointsSoleil = verificateur.calculerPointsCoup(coordNonSoleil, TUILE_DAUPHIN_JAUNE);

		assertEquals(Points.VALEUR_DEFAUT.valeur(), pointsSoleil);
	}
	
	@Test
	void tester_2_case_adjacente_avec_2_non_similaire() {
		plateau.placerTuile(coord1Adjacente, TUILE_OISEAU_ROUGE);
		plateau.placerTuile(coord2Adjacente, TUILE_OISEAU_VERT);

		int pointsDouble = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.VALEUR_DEFAUT.valeur(), pointsDouble);
	}
	
	@Test
	void tester_2_case_adjacente_avec_1_non_similaire() {
		plateau.placerTuile(coord1Adjacente, TUILE_DAUPHIN_JAUNE);
		plateau.placerTuile(coord2Adjacente, TUILE_OISEAU_VERT);

		int pointsDouble = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.VALEUR_DEFAUT.valeur(), pointsDouble);
	}
	
	@Test
	void tester_3_case_adjacente_avec_2_non_similaire() {
		plateau.placerTuile(coord1Adjacente, TUILE_OISEAU_ROUGE);
		plateau.placerTuile(coord2Adjacente, TUILE_DAUPHIN_JAUNE);
		plateau.placerTuile(coord3Adjacente, TUILE_OISEAU_VERT);

		int pointsDouble = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.VALEUR_DEFAUT.valeur(), pointsDouble);
	}
	
	@Test
	void tester_4_case_adjacentes_avec_2_non_similaire() {
		plateau.placerTuile(coord1Adjacente, TUILE_OISEAU_ROUGE);
		plateau.placerTuile(coord2Adjacente, TUILE_DAUPHIN_JAUNE);
		plateau.placerTuile(coord3Adjacente, TUILE_OISEAU_VERT);
		plateau.placerTuile(coord4Adjacente, TUILE_DAUPHIN_VERT);

		int pointsDouble = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.DOUBLE.valeur(), pointsDouble);
	}

	@Test
	void tester_case_adjacentes_forme_double() {
		plateau.placerTuile(coord1Adjacente, TUILE_DAUPHIN_JAUNE);
		plateau.placerTuile(coord2Adjacente, TUILE_DAUPHIN_VERT);

		int pointsDouble = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.DOUBLE.valeur(), pointsDouble);
	}

	@Test
	void tester_case_adjacentes_forme_triple() {
		plateau.placerTuile(coord1Adjacente, TUILE_DAUPHIN_JAUNE);
		plateau.placerTuile(coord2Adjacente, TUILE_DAUPHIN_VERT);
		plateau.placerTuile(coord3Adjacente, TUILE_DAUPHIN_ROUGE);

		int pointsTriple = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.TRIPLE.valeur(), pointsTriple);
	}

	@Test
	void tester_case_adjacentes_forme_latice() {
		plateau.placerTuile(coord1Adjacente, TUILE_DAUPHIN_JAUNE);
		plateau.placerTuile(coord2Adjacente, TUILE_DAUPHIN_VERT);
		plateau.placerTuile(coord3Adjacente, TUILE_DAUPHIN_ROUGE);
		plateau.placerTuile(coord4Adjacente, TUILE_DAUPHIN_MAGENTA);

		int pointsLatice = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.LATICE.valeur(), pointsLatice);
	}

	@Test
	void tester_case_adjacentes_couleur_double() {
		plateau.placerTuile(coord1Adjacente, TUILE_PLUME_BLEU);
		plateau.placerTuile(coord2Adjacente, TUILE_OISEAU_BLEU);

		int pointsDouble = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.DOUBLE.valeur(), pointsDouble);
	}

	@Test
	void tester_case_adjacentes_couleur_triple() {
		plateau.placerTuile(coord1Adjacente, TUILE_PLUME_BLEU);
		plateau.placerTuile(coord2Adjacente, TUILE_OISEAU_BLEU);
		plateau.placerTuile(coord3Adjacente, TUILE_GECKO_BLEU);

		int pointsTriple = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.TRIPLE.valeur(), pointsTriple);
	}

	@Test
	void tester_case_adjacentes_couleur_latice() {
		plateau.placerTuile(coord1Adjacente, TUILE_PLUME_BLEU);
		plateau.placerTuile(coord2Adjacente, TUILE_OISEAU_BLEU);
		plateau.placerTuile(coord3Adjacente, TUILE_GECKO_BLEU);
		plateau.placerTuile(coord4Adjacente, TUILE_TORTUE_BLEU);

		int pointsLatice = verificateur.calculerPointsCoup(coordMilieuStructure, TUILE_DAUPHIN_BLEU);

		assertEquals(Points.LATICE.valeur(), pointsLatice);
	}
}
