package latice.model.material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import latice.model.material.Case;
import latice.model.material.Couleur;
import latice.model.material.Forme;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.view.console.CouleurConsole;
import latice.view.console.EmojiForme;

class TestCase {
	
	private static final Tuile TUILE_JAUNE_GECKO = new Tuile(Couleur.JAUNE, Forme.GECKO);
	private static final Tuile TUILE_BLEU_MARINE_DAUPHIN = new Tuile(Couleur.BLEU_MARINE, Forme.DAUPHIN);
	
	private static final Case CASE_JAUNE_GECKO_OCCUPEE = new Case(TUILE_JAUNE_GECKO, TypeCase.CASE_OCCUPEE);
	private static final Case CASE_SOLEIL = new Case(TypeCase.CASE_SOLEIL);
	private static final Case CASE_LUNE = new Case(TypeCase.CASE_LUNE);

	@Test
	void test_tuile_existe() {
		Case tuileCase = new Case(TUILE_JAUNE_GECKO, TypeCase.CASE_SOLEIL);
		assertEquals(TUILE_JAUNE_GECKO, tuileCase.tuile());
	}
	
	@Test
	void test_changement_tuile() {
		Case tuileCase = new Case(TUILE_JAUNE_GECKO,TypeCase.CASE_SOLEIL);
		tuileCase.changerTuile(TUILE_BLEU_MARINE_DAUPHIN);
		assertEquals(TUILE_BLEU_MARINE_DAUPHIN, tuileCase.tuile());
	}
	
	@Test
	void test_changement_type_case() {
		Case tuileCase = new Case(TUILE_JAUNE_GECKO, TypeCase.CASE_SOLEIL);
		tuileCase.changerTypeCase(TypeCase.CASE_LUNE);
		assertEquals(TypeCase.CASE_LUNE, tuileCase.typeCase());
	}
	
	@Test
	void test_to_string_tuile_occupee_jaune_gecko() {
		String emojiColore = CouleurConsole.ANSI_FOND_JAUNE.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.GECKO + CouleurConsole.ANSI_RESET;
		
		assertEquals(CASE_JAUNE_GECKO_OCCUPEE.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_bleu_marine_dauphin() {
		Case caseTuile = new Case(TUILE_BLEU_MARINE_DAUPHIN, TypeCase.CASE_OCCUPEE);
		
		String emojiColore = CouleurConsole.ANSI_FOND_BLEU_MARINE.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.DAUPHIN + CouleurConsole.ANSI_RESET;
		
		assertEquals(caseTuile.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_rouge_plume() {
		Case caseTuile = new Case(new Tuile(Couleur.ROUGE, Forme.PLUME), TypeCase.CASE_OCCUPEE);
		
		String emojiColore = CouleurConsole.ANSI_FOND_ROUGE.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.PLUME + CouleurConsole.ANSI_RESET;
		
		assertEquals(caseTuile.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_vert_fleur() {
		Case caseTuile = new Case(new Tuile(Couleur.VERT, Forme.FLEUR), TypeCase.CASE_OCCUPEE);
		
		String emojiColore = CouleurConsole.ANSI_FOND_VERT.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.FLEUR + CouleurConsole.ANSI_RESET;
		
		assertEquals(caseTuile.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_magenta_tortue() {
		Case caseTuile = new Case(new Tuile(Couleur.MAGENTA, Forme.TORTUE), TypeCase.CASE_OCCUPEE);
		
		String emojiColore = CouleurConsole.ANSI_FOND_MAGENTA.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.TORTUE + CouleurConsole.ANSI_RESET;
		
		assertEquals(caseTuile.toString(), emojiColore);
	}
	
	@Test
	void test_to_string_tuile_occupee_bleu_sarcelle_oiseau() {
		Case caseTuile = new Case(new Tuile(Couleur.BLEU_SARCELLE, Forme.OISEAU), TypeCase.CASE_OCCUPEE);
		
		String emojiColore = CouleurConsole.ANSI_FOND_BLEU_SARCELLE.codeANSI() + CouleurConsole.ANSI_TEXTE_NOIR + EmojiForme.OISEAU + CouleurConsole.ANSI_RESET;
		
		assertEquals(caseTuile.toString(), emojiColore);
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
		Case tuileCase = new Case(TUILE_JAUNE_GECKO, TypeCase.CASE_OCCUPEE);
		
		assertEquals(CASE_JAUNE_GECKO_OCCUPEE, tuileCase);
	}
	
	@Test
	void test_equals_pas_egal() {
		assertNotEquals(CASE_JAUNE_GECKO_OCCUPEE, TUILE_BLEU_MARINE_DAUPHIN);
	}
	
	@Test
	void test_equals_null() {
		assertNotEquals(CASE_JAUNE_GECKO_OCCUPEE, null);
	}
	
	@Test
	void test_equals_objet_null() {
		assertNotEquals(null, CASE_JAUNE_GECKO_OCCUPEE);
	}
}
