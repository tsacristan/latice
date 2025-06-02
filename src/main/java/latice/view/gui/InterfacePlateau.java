package latice.view.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.material.Case;
import latice.model.player.RackJoueur;
import latice.util.PlateauListener;
import latice.util.exception.PlateauIndexInvalideException;
import latice.view.AfficherPlateau;
import latice.view.LaticeVue;

public class InterfacePlateau extends GridPane implements PlateauListener, AfficherPlateau {
	
	private Plateau plateau;
	public InterfacePlateau(Plateau plateau, RackJoueur rackJoueur, LaticeVue laticeVue) {
		super();
		this.plateau = plateau;
		setAlignment(Pos.CENTER);
		afficherPlateau(plateau);
	}

	@Override
	public void afficherPlateau(Plateau plateau) {
	    getChildren().clear();

	    for (int i = 0; i < Plateau.LIGNES; i++) {
	        for (int j = 0; j < Plateau.COLONNES; j++) {
	            try {
	                Case caseTuile = plateau.obtenirTuile(new Coordonnees(i, j));
	                InterfaceCase vueCase = new InterfaceCase(caseTuile);
	                
	                add(vueCase, i, j);

	            } catch (PlateauIndexInvalideException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public void actualiserRack(RackJoueur rackJoueur) {
		afficherPlateau(plateau);
	}

	@Override
	public void plateauEstMisAJour() {
		afficherPlateau(plateau);
	}
}
