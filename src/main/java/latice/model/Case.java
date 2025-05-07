package latice.model;

import latice.ihm.CouleurConsole;
import latice.ihm.EmojiForme;
import latice.util.CouleurInvalideException;
import latice.util.FormeInvalideException;

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
    				String styleANSI = getAnsiColor().codeANSI() + CouleurConsole.ANSI_COULEUR_TEXTE;
    				return styleANSI + getEmojiForme().emoji() + CouleurConsole.ANSI_RESET;
    			} catch (CouleurInvalideException | FormeInvalideException e) {
    				return EmojiForme.CARACTERE_DEFAULT.emoji();
    			}
	    	case CASE_VIDE:
	    		return EmojiForme.CASE_VIDE.emoji();
	    	case CASE_SOLEIL:
	    		return EmojiForme.CASE_SOLEIL.emoji();
	    	case CASE_LUNE:
	    		return EmojiForme.CASE_LUNE.emoji();
	    	default:
	    		return EmojiForme.CARACTERE_DEFAULT.emoji();
    	}
    }

    private CouleurConsole getAnsiColor() throws CouleurInvalideException {
    	CouleurConsole couleurANSI;
        switch (tuile.couleur()) {
            case BLEU_MARINE:
                couleurANSI = CouleurConsole.ANSI_BLEU_MARINE;
                break;
            case MAGENTA:
                couleurANSI = CouleurConsole.ANSI_MAGENTA;
                break;
            case ROUGE:
                couleurANSI = CouleurConsole.ANSI_ROUGE;
                break;
            case VERT:
                couleurANSI = CouleurConsole.ANSI_VERT;
                break;
            case BLEU_SARCELLE:
                couleurANSI = CouleurConsole.ANSI_BLEU_SARCELLE;
                break;
            case JAUNE:
            	couleurANSI = CouleurConsole.ANSI_JAUNE;
            	break;
            default:
            	throw new CouleurInvalideException("Erreur ! Couleur " + tuile.couleur() + " non reconnue !");
        }
        return couleurANSI;
    }

    private EmojiForme getEmojiForme() throws FormeInvalideException {
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
}
