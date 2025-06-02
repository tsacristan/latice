package latice.view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import latice.model.player.Joueur;

public class PartieControle extends HBox {
	
	private InterfaceRack interfaceRack;
	private Button boutonValider;
	private Button boutonPasser;
	private Button boutonPiocher;
	
	private static final String STYLE_COMMUN = "-fx-font-size: 25px; -fx-alignment: center;";

	public PartieControle(Joueur joueur) {
		boutonValider = new Button("Valider");
		boutonPasser = new Button("Passer");
		boutonPiocher = new Button("Piocher");
	    
	    boutonValider.setStyle(STYLE_COMMUN);
	    boutonPasser.setStyle(STYLE_COMMUN);
	    boutonPiocher.setStyle(STYLE_COMMUN);
	    
	    VBox vboxBoutonsGauche = new VBox(10); 
	    vboxBoutonsGauche.getChildren().addAll(boutonPasser, boutonPiocher);
	    vboxBoutonsGauche.setAlignment(Pos.CENTER_LEFT);
	    vboxBoutonsGauche.setPadding(new Insets(0, 20, 0, 20));
	    
	    interfaceRack = new InterfaceRack(joueur.rackJoueur());
	    
	    setSpacing(50);  
	    setPadding(new Insets(10, 50, 10, 20));
	    setAlignment(Pos.CENTER);
	    
	    getChildren().addAll(vboxBoutonsGauche, interfaceRack, boutonValider);
	}
	
	public InterfaceRack interfaceRack() {
		return interfaceRack;
	}
	
	public Button boutonValider() {
		return boutonValider;
	}

	public Button boutonPasser() {
		return boutonPasser;
	}

	public Button boutonPiocher() {
		return boutonPiocher;
	}

}
