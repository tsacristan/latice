package latice.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import latice.model.Case;
import latice.model.RackJoueur;
import latice.model.Tuile;
import latice.model.TypeCase;
import latice.util.RackListener;

public class InterfaceRack extends HBox implements RackListener {
	private RackJoueur rackJoueur;
	
	public InterfaceRack(RackJoueur rackJoueur) {
		this.rackJoueur = rackJoueur;
		
		setSpacing(10);
        setAlignment(Pos.CENTER);
        
        rackEstMisAJour();
	}
	
	@Override
	public void rackEstMisAJour() {
		getChildren().clear();
		for (Tuile tuile : rackJoueur.rack()) {
        	Case caseTuile = new Case(tuile, TypeCase.CASE_OCCUPEE);
            getChildren().add(new InterfaceCase(caseTuile));
        }
	}
}
