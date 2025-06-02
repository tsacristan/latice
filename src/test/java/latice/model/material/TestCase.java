package latice.model.material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


import latice.view.console.CouleurConsole;
import latice.view.console.EmojiForme;

class TestCase {
	
	private static final Tuile TUILE_JAUNE_GECKO = new Tuile(Couleur.JAUNE, Forme.GECKO);
	private static final Tuile TUILE_BLEU_MARINE_DAUPHIN = new Tuile(Couleur.BLEU_MARINE, Forme.DAUPHIN);
	private static final Tuile TUILE_ROUGE_PLUME = new Tuile(Couleur.ROUGE,Forme.PLUME);
	private static final Tuile TUILE_VERT_FLEUR = new Tuile(Couleur.VERT,Forme.FLEUR);
	private static final Tuile TUILE_MAGENTA_TORTUE = new Tuile(Couleur.MAGENTA,Forme.TORTUE);
	private static final Tuile TUILE_BLEU_SARCELLE_OISEAU = new Tuile(Couleur.BLEU_SARCELLE,Forme.OISEAU);
	
	private static final Case CASE_BLEU_MARINE_DAUPHIN_OCCUPEE = new Case(TUILE_BLEU_MARINE_DAUPHIN,TypeCase.CASE_OCCUPEE);
	private static final Case CASE_BLEU_MARINE_DAUPHIN_OCCUPEE_bis = new Case(TUILE_BLEU_MARINE_DAUPHIN,TypeCase.CASE_OCCUPEE);
	private static final Case CASE_BLEU_MARINE_DAUPHIN = new Case(TUILE_BLEU_MARINE_DAUPHIN);
	private static final Case CASE_BLEU_MARINE_DAUPHIN_VIDE = new Case(TUILE_BLEU_MARINE_DAUPHIN,TypeCase.CASE_VIDE);
	private static final Case CASE_ROUGE_PLUME_OCCUPEE = new Case(TUILE_ROUGE_PLUME,TypeCase.CASE_OCCUPEE);
	private static final Case CASE_VERT_FLEUR_OCCUPEE = new Case(TUILE_VERT_FLEUR,TypeCase.CASE_OCCUPEE);
	private static final Case CASE_BLEU_SARCELLE_OISEAU_OCCUPEE = new Case(TUILE_BLEU_SARCELLE_OISEAU,TypeCase.CASE_OCCUPEE);
	private static final Case CASE_MAGENTA_TORTUE_OCCUPEE = new Case(TUILE_MAGENTA_TORTUE,TypeCase.CASE_OCCUPEE);
	private static final Case CASE_JAUNE_GECKO_CASE_SOLEIL = new Case(TUILE_JAUNE_GECKO, TypeCase.CASE_OCCUPEE);
	private static final Case CASE_JAUNE_GECKO_CASE_SOLEIL_bis = new Case(TUILE_JAUNE_GECKO, TypeCase.CASE_OCCUPEE);
	private static final Case CASE_JAUNE_GECKO_OCCUPEE = new Case(TUILE_JAUNE_GECKO, TypeCase.CASE_OCCUPEE);
	private static final Case CASE_JAUNE_GECKO_OCCUPEE_bis = new Case(TUILE_JAUNE_GECKO, TypeCase.CASE_OCCUPEE);
	private static final Case CASE_SOLEIL = new Case(TypeCase.CASE_SOLEIL);
	private static final Case CASE_LUNE = new Case(TypeCase.CASE_LUNE);

	
	@Test
	void test_tuile_existe() {		
		assertEquals(TUILE_JAUNE_GECKO, CASE_JAUNE_GECKO_CASE_SOLEIL.tuile());
	}

	@Test
	void test_changement_tuile() {
		CASE_JAUNE_GECKO_CASE_SOLEIL_bis.changerTuile(TUILE_BLEU_MARINE_DAUPHIN);
		assertEquals(TUILE_BLEU_MARINE_DAUPHIN, CASE_JAUNE_GECKO_CASE_SOLEIL_bis.tuile());
	}
	@Test
	void test_changement_type_case() {
		CASE_JAUNE_GECKO_CASE_SOLEIL.changerTypeCase(TypeCase.CASE_LUNE);
		assertEquals(TypeCase.CASE_LUNE, CASE_JAUNE_GECKO_CASE_SOLEIL.typeCase());
	}
	@Test
	void test_to_string_tuile_occupee_jaune_gecko() {
		String emojiColore = CouleurConsole.ANSI_FOND_JAUNE.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.GECKO + CouleurConsole.ANSI_RESET;
		
		assertEquals(CASE_JAUNE_GECKO_OCCUPEE.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_bleu_marine_dauphin() {		
		String emojiColore = CouleurConsole.ANSI_FOND_BLEU_MARINE.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.DAUPHIN + CouleurConsole.ANSI_RESET;
		
		assertEquals(CASE_BLEU_MARINE_DAUPHIN_OCCUPEE.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_rouge_plume() {		
		String emojiColore = CouleurConsole.ANSI_FOND_ROUGE.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.PLUME + CouleurConsole.ANSI_RESET;
		assertEquals(CASE_ROUGE_PLUME_OCCUPEE.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_vert_fleur() {		
		String emojiColore = CouleurConsole.ANSI_FOND_VERT.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.FLEUR + CouleurConsole.ANSI_RESET;
		assertEquals(CASE_VERT_FLEUR_OCCUPEE.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_magenta_tortue() {		
		String emojiColore = CouleurConsole.ANSI_FOND_MAGENTA.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.TORTUE + CouleurConsole.ANSI_RESET;
		assertEquals(CASE_MAGENTA_TORTUE_OCCUPEE.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_bleu_sarcelle_oiseau() {		
		String emojiColore = CouleurConsole.ANSI_FOND_BLEU_SARCELLE.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.OISEAU + CouleurConsole.ANSI_RESET;
		assertEquals(CASE_BLEU_SARCELLE_OISEAU_OCCUPEE.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_vide_soleil() {
		String emojiColore = CouleurConsole.ANSI_TEXTE_JAUNE + EmojiForme.CASE_SOLEIL.emoji() + CouleurConsole.ANSI_RESET;
		assertEquals(CASE_SOLEIL.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_vide_lune() {
		String emojiColore = EmojiForme.CASE_LUNE.emoji();
		assertEquals(CASE_LUNE.toString(), emojiColore);
	}
	
	@Test
	void test_equals_est_egal() {		
		assertEquals(CASE_JAUNE_GECKO_OCCUPEE, CASE_JAUNE_GECKO_OCCUPEE_bis);
	}
	
	@Test
	void test_equals_pas_egal() {
		assertNotEquals(CASE_JAUNE_GECKO_OCCUPEE, TUILE_BLEU_MARINE_DAUPHIN);
	}
	
	@Test
	void test_equals_null() {
		assertNotEquals(null, CASE_JAUNE_GECKO_OCCUPEE);
	}
	
	@Test
	void test_equals_objet_null() {
		assertNotEquals(null, CASE_JAUNE_GECKO_OCCUPEE);
	}
	
	@Test
	void testCaseConstructeurTuileSeulement() {
		
		TypeCase typeDeLaCase = CASE_BLEU_MARINE_DAUPHIN.typeCase();
		Couleur CouleurDeLaCase = CASE_BLEU_MARINE_DAUPHIN.tuile().couleur();
		Forme FormeDeLaCase = CASE_BLEU_MARINE_DAUPHIN.tuile().forme();
		
		assertEquals(TypeCase.CASE_OCCUPEE,typeDeLaCase);
		assertEquals(Couleur.BLEU_MARINE,CouleurDeLaCase);
		assertEquals(Forme.DAUPHIN,FormeDeLaCase);
		
	}
	
	@Test
	void testToStringCaseVide() {		
		String resultat = CASE_BLEU_MARINE_DAUPHIN_VIDE.toString();
		assertEquals(EmojiForme.CASE_VIDE.emoji(),resultat);
		}
	
	@Test 
	void testHashCode() {	
		int hashCode1 = CASE_BLEU_MARINE_DAUPHIN_OCCUPEE.hashCode();
		int hashCode2 = CASE_BLEU_MARINE_DAUPHIN_OCCUPEE_bis.hashCode();
		
		assertEquals(hashCode1,hashCode2);
	}
	
	@Test
	void testEquals() {
		assertTrue(CASE_BLEU_MARINE_DAUPHIN_OCCUPEE.equals(CASE_BLEU_MARINE_DAUPHIN_OCCUPEE));
	}
}
