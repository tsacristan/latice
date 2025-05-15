package latice.view.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.material.Case;
import latice.util.PlateauListener;
import latice.util.exception.PlateauIndexInvalideException;
import latice.view.AfficherPlateau;

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
