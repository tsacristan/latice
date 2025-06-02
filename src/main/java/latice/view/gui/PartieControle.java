package latice.view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import latice.model.player.Joueur;

public class PartieControle extends HBox {
	
	private InterfaceRack interfaceRack;
	private static final String STYLE_COMMUN = "-fx-font-size: 25px; -fx-alignment: center;";

	public PartieControle(Joueur joueur, Runnable validerTour, Runnable passerTour) {
	    Button valider = new Button("Valider");
	    Button passer = new Button("Passer");
	    Button piocher = new Button("Piocher");
	    
	    
	    valider.setStyle(STYLE_COMMUN);
	    passer.setStyle(STYLE_COMMUN);
	    piocher.setStyle(STYLE_COMMUN);
	    
	    valider.setOnAction(e -> validerTour.run());
	    
	    passer.setOnAction(e -> passerTour.run());
	    
	    piocher.setOnAction(e -> {
	        try {
	            joueur.remplirRack();
	        } catch (Exception ex) {
	            System.out.println("Erreur : " + ex.getMessage());
	        }
	    });
	    
	    VBox vboxBoutonsGauche = new VBox(10); 
	    vboxBoutonsGauche.getChildren().addAll(passer, piocher);
	    vboxBoutonsGauche.setAlignment(Pos.CENTER_LEFT);
	    vboxBoutonsGauche.setPadding(new Insets(0, 20, 0, 20));

	    
	    interfaceRack = new InterfaceRack(joueur.rackJoueur());

	   
	    setSpacing(50);  
	    setPadding(new Insets(10, 50, 10, 20));
	    setAlignment(Pos.CENTER);
	    
	    getChildren().addAll(vboxBoutonsGauche, interfaceRack, valider);
	}
	
	public InterfaceRack interfaceRack() {
		return interfaceRack;
	}
}
