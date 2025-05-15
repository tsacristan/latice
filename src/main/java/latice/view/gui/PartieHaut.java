 
package latice.view.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class PartieHaut extends HBox {
	
	private static final String STYLE_COMMUN = "-fx-font-size: 30px; -fx-alignment: center;";
    
    public PartieHaut() {
        Label lblHeure = new Label("00:00");
        Label lblTour = new Label("Tour :");
        Button btnAide = new Button("Règles");
        
        lblHeure.setStyle(STYLE_COMMUN);
        lblTour.setStyle(STYLE_COMMUN);
        btnAide.setStyle(STYLE_COMMUN);
        
        
        lblTour.setMaxWidth(Double.MAX_VALUE);
        
        setPadding(new Insets(10, 20, 10, 20));
        
        HBox.setHgrow(lblTour, Priority.ALWAYS);
        HBox.setHgrow(lblHeure, Priority.ALWAYS);
        HBox.setHgrow(btnAide, Priority.ALWAYS);

        getChildren().addAll(lblHeure,lblTour,btnAide);
    }
    
}