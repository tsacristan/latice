package latice.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import latice.model.RackJoueur;

public class PartieControle extends HBox {
	
	private GridPane tableauBas;
	private InterfaceRack interfaceRack;
	private static final String STYLE_COMMUN = "-fx-font-size: 25px; -fx-alignment: center;";

	public PartieControle(RackJoueur rackJoueur) {
		placerBoutons();
		
		interfaceRack = new InterfaceRack(rackJoueur);
		setPadding(new Insets(0,20,0,0));
		setAlignment(Pos.CENTER);
        tableauBas.add(interfaceRack, 1, 0);
	}
	
	private void placerBoutons() {
		Button valider = new Button("Valider");
        Button passer = new Button("Passer");
        Button piocher = new Button("Piocher");
        tableauBas = new GridPane();

        tableauBas.setHgap(400);
        tableauBas.setPadding(new Insets(10, 20, 10, 100));
        valider.setStyle(STYLE_COMMUN);
        passer.setStyle(STYLE_COMMUN);
        piocher.setStyle(STYLE_COMMUN);
        
        getChildren().add(tableauBas);
        tableauBas.add(passer, 0,0);
        tableauBas.add(piocher, 0,1);
        tableauBas.add(valider, 2,0);
	}
	
	public InterfaceRack interfaceRack() {
		return interfaceRack;
	}
}
