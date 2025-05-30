package latice.view.gui;

import javafx.geometry.Pos;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import latice.model.material.Case;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.model.player.RackJoueur;
import latice.util.RackListener;
import latice.view.AfficherRack;

public class InterfaceRack extends HBox implements RackListener, AfficherRack {
	private RackJoueur rackJoueur;
	private static final String STYLE_HBOX = "-fx-background-color: saddlebrown; -fx-background-radius: 10px;";
	
	public InterfaceRack(RackJoueur rackJoueur) {
		this.rackJoueur = rackJoueur;
	    
	    setSpacing(10);
	    setAlignment(Pos.CENTER);
	    setMinWidth(600); 
	    setPrefWidth(600);
	    setStyle(STYLE_HBOX);
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

	        appliquerDnD(tuileVue, index);
	        

	        getChildren().add(tuileVue);
	        index++;
	    }
	
	}
	
	public void changerRack(RackJoueur rackJoueur) {
		this.rackJoueur = rackJoueur;
	}
	
	public void appliquerDnD(InterfaceCase caseVue, int index) {
		caseVue.setOnDragDetected(event -> {
            var db = caseVue.startDragAndDrop(TransferMode.MOVE);
            var contenuindex = new ClipboardContent();
            contenuindex.putString(String.valueOf(index)); 
            db.setContent(contenuindex);
            event.consume();
        });
	}
}
