package latice.view;

import javafx.scene.layout.GridPane;
import latice.ihm.AfficherPlateau;
import latice.model.Case;
import latice.model.Coordonnees;
import latice.model.Plateau;
import latice.util.PlateauListener;

public class InterfacePlateau extends GridPane implements PlateauListener, AfficherPlateau {
	
	private Plateau plateau;
	
	public InterfacePlateau(Plateau plateau) {
		super();
		this.plateau = plateau;
		afficher(plateau);
	}

	@Override
	public void afficher(Plateau plateau) {
		for (int i = 0; i < Plateau.LIGNES; i++) {
			for (int j = 0; j < Plateau.COLONNES; j++) {
				Case caseTuile = plateau.obtenirTuile(new Coordonnees(i, j));
				add(new InterfaceCase(caseTuile), i, j);
			}
		}		
	}

	@Override
	public void plateauEstMisAJour() {
		afficher(plateau);
	}
}
