package latice.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import latice.ihm.EmojiForme;
import latice.model.Case;
import latice.model.Tuile;
import latice.util.CouleurInvalideException;
import latice.util.FormeInvalideException;

public class InterfaceCase extends ImageView {
	
	public static final int TAILLE_IMAGE = 80;
	
	public InterfaceCase(Image image) {
		super(image);
		setFitWidth(TAILLE_IMAGE);
		setFitHeight(TAILLE_IMAGE);
		setCache(true);
	}
	
	public InterfaceCase(String cheminImage) {
		this(new Image(cheminImage));
	}
	
	public InterfaceCase(Case caseTuile) {
		this(new Image(associerTuileAImage(caseTuile)));
	}

	private static String associerTuileAImage(Case caseTuile) {
		switch (caseTuile.typeCase()) {
			case CASE_OCCUPEE:
				String formeChemin;
				String couleurChemin;
				try {
					formeChemin = obtenirForme(caseTuile.tuile());
					couleurChemin = obtenirCouleur(caseTuile.tuile());
				} catch (CouleurInvalideException | FormeInvalideException e) {
					return CheminsImages.FOND_OCEAN.chemin;
				}
				return formeChemin + "_" + couleurChemin + ".png";
	    	case CASE_VIDE:
	    		return CheminsImages.FOND_OCEAN.chemin;
	    	case CASE_SOLEIL:
	    		return CheminsImages.FOND_SOLEIL.chemin;
	    	case CASE_LUNE:
	    		return CheminsImages.FOND_LUNE.chemin;
	    	default:
	    		return EmojiForme.CARACTERE_INCONNU.emoji();
		}
		
	}
	
	private static String obtenirForme(Tuile tuile) throws FormeInvalideException {
		switch (tuile.forme()) {
        case OISEAU:
            return CheminsImages.OISEAU.chemin;
        case TORTUE:
            return CheminsImages.TORTUE.chemin;
        case FLEUR:
            return CheminsImages.FLEUR.chemin;
        case GECKO:
            return CheminsImages.GECKO.chemin;
        case DAUPHIN:
            return CheminsImages.DAUPHIN.chemin;
        case PLUME:
        	return CheminsImages.PLUME.chemin;
        default:
        	throw new FormeInvalideException("Erreur ! Forme " + tuile.forme() + " non reconnue !");
		}
	}
	
	private static String obtenirCouleur(Tuile tuile) throws CouleurInvalideException {
        switch (tuile.couleur()) {
            case BLEU_MARINE:
                return CheminsImages.BLEU_MARINE.chemin;
            case MAGENTA:
            	return CheminsImages.MAGENTA.chemin;
            case ROUGE:
            	return CheminsImages.ROUGE.chemin;
            case VERT:
            	return CheminsImages.VERT.chemin;
            case BLEU_SARCELLE:
            	return CheminsImages.BLEU_SARCELLE.chemin;
            case JAUNE:
            	return CheminsImages.JAUNE.chemin;
            default:
            	throw new CouleurInvalideException("Erreur ! Couleur " + tuile.couleur() + " non reconnue !");
        }
	}

}
