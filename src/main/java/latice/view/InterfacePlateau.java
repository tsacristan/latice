package latice.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import latice.ihm.AfficherPlateau;
import latice.model.Case;
import latice.model.Coordonnees;
import latice.model.Plateau;
import latice.util.PlateauIndexInvalideException;
import latice.util.PlateauListener;

public class InterfacePlateau extends GridPane implements PlateauListener, AfficherPlateau {
	
	private Plateau plateau;
	
	public InterfacePlateau(Plateau plateau) throws PlateauIndexInvalideException {
		super();
		this.plateau = plateau;
		setAlignment(Pos.CENTER);
		afficher(plateau);
	}

	@Override
	public void afficher(Plateau plateau) {
		for (int i = 0; i < Plateau.LIGNES; i++) {
			for (int j = 0; j < Plateau.COLONNES; j++) {
				Case caseTuile;
				try {
					caseTuile = plateau.obtenirTuile(new Coordonnees(i, j));
					add(new InterfaceCase(caseTuile), i, j);
				} catch (PlateauIndexInvalideException e) {
					e.printStackTrace();
				}
				
			}
		}		
	}

	@Override
	public void plateauEstMisAJour() {
		afficher(plateau);
	}
}
