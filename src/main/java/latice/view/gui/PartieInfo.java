package latice.view.gui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import latice.view.Textes;

public class PartieInfo extends StackPane {

	
	private static final String STYLE_COMMUN = "-fx-font-size: 25px";
	private Label nombreTuilesLabel;
	private Button boutonMagasin;
	
	public PartieInfo() {
        setPrefWidth(200);
    	
        Label tuilesRestantesLabel = new Label(Textes.TUILES_RESTANTES.toString());
        nombreTuilesLabel = new Label("25");

        VBox labelsBox = new VBox(tuilesRestantesLabel, nombreTuilesLabel);
        labelsBox.setAlignment(Pos.TOP_CENTER);
        VBox tuileBox = new VBox();
        tuileBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(tuileBox, new Insets(20));
        
        boutonMagasin = new Button("SHOP");
        
        VBox boutonBox = new VBox(boutonMagasin);
        boutonBox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox.setMargin(boutonMagasin, new Insets(10));

        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(5));
        tuilesRestantesLabel.setStyle(STYLE_COMMUN);
        nombreTuilesLabel.setStyle(STYLE_COMMUN);
        boutonMagasin.setStyle(STYLE_COMMUN);

        this.getChildren().addAll(labelsBox, tuileBox, boutonBox);
    }
    
    public void afficherTuilesRestantes(int tuilesRestantes) {
    	nombreTuilesLabel.setText(Integer.toString(tuilesRestantes));
    }
    
    public Button boutonMagasin() {
        return boutonMagasin;
    }
}