package latice.model.material;

import java.util.Objects;

import latice.util.exception.CouleurInvalideException;
import latice.util.exception.FormeInvalideException;
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
	    		return CouleurConsole.ANSI_TEXTE_JAUNE + EmojiForme.CASE_SOLEIL.emoji() + CouleurConsole.ANSI_RESET;
	    	case CASE_LUNE:
	    		return EmojiForme.CASE_LUNE.emoji();
	    	default:
	    		return EmojiForme.CARACTERE_INCONNU.emoji();
    	}
    }

    private CouleurConsole obtenirAnsiBackgroundColor() throws CouleurInvalideException {
        CouleurConsole couleurANSI;
        switch (tuile.couleur()) {
            case BLEU_MARINE:
                couleurANSI = CouleurConsole.ANSI_FOND_BLEU_MARINE;
                break;
            case MAGENTA:
                couleurANSI = CouleurConsole.ANSI_FOND_MAGENTA;
                break;
            case ROUGE:
                couleurANSI = CouleurConsole.ANSI_FOND_ROUGE;
                break;
            case VERT:
                couleurANSI = CouleurConsole.ANSI_FOND_VERT;
                break;
            case BLEU_SARCELLE:
                couleurANSI = CouleurConsole.ANSI_FOND_BLEU_SARCELLE;
                break;
            case JAUNE:
                couleurANSI = CouleurConsole.ANSI_FOND_JAUNE;
                break;
            default:
                throw new CouleurInvalideException("Erreur ! Couleur " + tuile.couleur() + " non reconnue !");
        }
        return couleurANSI;
    }

    private EmojiForme obtenirEmojiForme() throws FormeInvalideException {
        EmojiForme emojiForme;
        switch (tuile.forme()) {
            case OISEAU:
                emojiForme = EmojiForme.OISEAU;
                break;
            case TORTUE:
                emojiForme = EmojiForme.TORTUE;
                break;
            case FLEUR:
                emojiForme = EmojiForme.FLEUR;
                break;
            case GECKO:
                emojiForme = EmojiForme.GECKO;
                break;
            case DAUPHIN:
                emojiForme = EmojiForme.DAUPHIN;
                break;
            case PLUME:
                emojiForme = EmojiForme.PLUME;
                break;
            default:
                throw new FormeInvalideException("Erreur ! Forme " + tuile.forme() + " non reconnue !");
        }
        return emojiForme;
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
