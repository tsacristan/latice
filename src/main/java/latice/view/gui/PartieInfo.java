package latice.view.gui;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import latice.model.player.Joueur;

public class PartieInfo extends StackPane {

	
	private static final String STYLE_COMMUN = "-fx-font-size: 25px";
	private Label nombreTuilesLabel;
	private Joueur joueur;
	
	public PartieInfo() {
        setPrefWidth(200);
    	
        Label tuilesRestantesLabel = new Label("Tuiles restantes :");
        nombreTuilesLabel = new Label("25");

        VBox labelsBox = new VBox(tuilesRestantesLabel, nombreTuilesLabel);
        labelsBox.setAlignment(Pos.TOP_CENTER);
        VBox tuileBox = new VBox();
        tuileBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(tuileBox, new Insets(20));
        
        Button boutonMagasin = new Button("SHOP");
        boutonMagasin.setOnAction(event -> ouvrirMagasin());
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
    
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
        afficherTuilesRestantes(joueur.points());
    }
    
    private void ouvrirMagasin() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Boutique");
        alert.setHeaderText("Achetez un Coup supplémentaire (2 points)");
        alert.setContentText("Vos points : " + joueur.points() + "\nCliquez sur OK pour acheter.");

        Optional<ButtonType> response = alert.showAndWait();
        if (response.isPresent() && response.get() == ButtonType.OK) {
            if (joueur.points() >= 2) {
                joueur.ajouterPoints(-2);
                
                afficherTuilesRestantes(joueur.points());
                
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Vous avez acheté un coup supplémentaire avec succès !");
                successAlert.showAndWait();

            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Vous n'avez pas assez de points pour acheter un coup supplémentaire !");
                errorAlert.showAndWait();
            }
        }
    }
}