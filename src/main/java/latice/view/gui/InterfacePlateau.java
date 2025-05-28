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

public class InterfacePlateau extends GridPane implements PlateauListener, AfficherPlateau {
	
	private Plateau plateau;
	private RackJoueur rackJoueur; // 🆕 on garde une référence pour pouvoir déposer

	public InterfacePlateau(Plateau plateau, RackJoueur rackJoueur) throws PlateauIndexInvalideException {
		super();
		this.plateau = plateau;
		this.rackJoueur = rackJoueur;

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

	                final int ligne = i;
	                final int colonne = j;

	                // 🟨 Gérer le survol
	                vueCase.setOnDragOver(event -> {
	                    if (event.getGestureSource() != vueCase && event.getDragboard().hasString()) {
	                        event.acceptTransferModes(javafx.scene.input.TransferMode.MOVE);
	                    }
	                    event.consume();
	                });

	                // 🟩 Gérer le drop
	                vueCase.setOnDragDropped(event -> {
	                    var db = event.getDragboard();
	                    boolean success = false;

	                    if (db.hasString()) {
	                        try {
	                            int indexRack = Integer.parseInt(db.getString());
	                            plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(ligne, colonne), this.rackJoueur);
	                            success = true;
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                            
	                        }
	                    }

	                    event.setDropCompleted(success);
	                    event.consume();
	                });

	                add(vueCase, i, j);

	            } catch (PlateauIndexInvalideException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


	@Override
	public void plateauEstMisAJour() {
		afficherPlateau(plateau);
	}
}
