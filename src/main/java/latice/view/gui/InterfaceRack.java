package latice.view.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import latice.model.material.Case;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.model.player.RackJoueur;
import latice.util.RackListener;
import latice.view.AfficherRack;

public class InterfaceRack extends HBox implements RackListener, AfficherRack {
	private RackJoueur rackJoueur;
	
	public InterfaceRack(RackJoueur rackJoueur) {
		this.rackJoueur = rackJoueur;
	    
	    setSpacing(10);
	    setAlignment(Pos.CENTER);
	    setMinWidth(600); 
	    setPrefWidth(600);
	    setStyle("-fx-background-color: saddlebrown; -fx-background-radius: 10px;");
	    afficherRack(rackJoueur);
	}
	
	@Override
	public void rackEstMisAJour() {
		afficherRack(rackJoueur);
	}

	@Override
	public void afficherRack(RackJoueur rack) {
		getChildren().clear();
	    int index = 0;

	    for (Tuile tuile : rackJoueur.rack()) {
	        Case caseTuile = new Case(tuile, TypeCase.CASE_OCCUPEE);
	        InterfaceCase tuileVue = new InterfaceCase(caseTuile);

	        final int tuileIndex = index; // pour l’utiliser dans la lambda

	        tuileVue.setOnDragDetected(event -> {
	            var db = tuileVue.startDragAndDrop(javafx.scene.input.TransferMode.MOVE);
	            var contenuindex = new javafx.scene.input.ClipboardContent();
	            contenuindex.putString(String.valueOf(tuileIndex)); 
	            db.setContent(contenuindex);
	            event.consume();
	        });

	        getChildren().add(tuileVue);
	        index++;
	}
	
}
}
