package latice.model.material;

import java.util.Objects;

import latice.util.exception.CouleurInvalideException;
import latice.util.exception.FormeInvalideException;
import latice.view.Textes;
import latice.view.TextesErreurs;
import latice.view.console.CouleurConsole;
import latice.view.console.EmojiForme;

public class Case {
    private Tuile tuile;
    private TypeCase typeCase;

    public Case(Tuile tuile, TypeCase typeCase) {
        this.tuile = tuile;
        this.typeCase = typeCase;
    }

    public Case(TypeCase typeCase) {
        this(null, typeCase);
    }
    
    public Case(Tuile tuile) {
        this(tuile, TypeCase.CASE_OCCUPEE);
    }

    public Tuile tuile() {
        return tuile;
    }
    
    public void changerTuile(Tuile tuile) {
    	this.tuile = tuile;
    }

    public TypeCase typeCase() {
        return typeCase;
    }
    
    public void changerTypeCase(TypeCase typeCase) {
    	this.typeCase = typeCase;
    }

    @Override
    public String toString() {
        switch (typeCase) {
            case CASE_OCCUPEE:
                try {
                    CouleurConsole couleurTexteDefaut = CouleurConsole.ANSI_TEXTE_NOIR;
                    String styleANSI = obtenirAnsiBackgroundColor().codeANSI() + couleurTexteDefaut;
                    return styleANSI + obtenirEmojiForme().emoji() + CouleurConsole.ANSI_RESET;
                } catch (CouleurInvalideException | FormeInvalideException e) {
                    return EmojiForme.CARACTERE_INCONNU.emoji();
                }
            case CASE_VIDE:
                return EmojiForme.CASE_VIDE.emoji();
            case CASE_SOLEIL:
                return Textes.AFFICHAGE_SOLEIL.texte();
            case CASE_LUNE:
                return EmojiForme.CASE_LUNE.emoji();
            default:
                return EmojiForme.CARACTERE_INCONNU.emoji();
        }
    }

    private CouleurConsole obtenirAnsiBackgroundColor() throws CouleurInvalideException {
        switch (tuile.couleur()) {
            case BLEU_MARINE:
                return CouleurConsole.ANSI_FOND_BLEU_MARINE;
            case MAGENTA:
                return CouleurConsole.ANSI_FOND_MAGENTA;
            case ROUGE:
                return CouleurConsole.ANSI_FOND_ROUGE;
            case VERT:
                return CouleurConsole.ANSI_FOND_VERT;
            case BLEU_SARCELLE:
                return CouleurConsole.ANSI_FOND_BLEU_SARCELLE;
            case JAUNE:
                return CouleurConsole.ANSI_FOND_JAUNE;
            default:
                throw new CouleurInvalideException(
                		String.format(TextesErreurs.FORME_TUILE_INVALIDE.toString(), tuile.couleur()));
        }
    }

    private EmojiForme obtenirEmojiForme() throws FormeInvalideException {
        switch (tuile.forme()) {
            case OISEAU:
                return EmojiForme.OISEAU;
            case TORTUE:
                return EmojiForme.TORTUE;
            case FLEUR:
                return EmojiForme.FLEUR;
            case GECKO:
                return EmojiForme.GECKO;
            case DAUPHIN:
                return EmojiForme.DAUPHIN;
            case PLUME:
                return EmojiForme.PLUME;
            default:
                throw new FormeInvalideException(
                		String.format(TextesErreurs.COULEUR_TUILE_INVALIDE.toString(), tuile.forme()));
        }
    }

	@Override
	public int hashCode() {
		return Objects.hash(tuile, typeCase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		return Objects.equals(tuile, other.tuile) && typeCase == other.typeCase;
	}
}
