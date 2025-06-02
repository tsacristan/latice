package latice.view.gui;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import latice.model.player.Joueur;
import latice.view.Textes;

public class PartieJoueur extends VBox {

	private static final String STYLE_COMMUN = "-fx-font-size: 25px;";
    private Label pointsLabel = new Label("Points :");
    
    private Region espace;
    private VBox dynamicBox;
        
    public PartieJoueur() {
        setSpacing(10);
        setAlignment(Pos.TOP_LEFT);
        setPrefWidth(200);
        
        pointsLabel.setStyle(STYLE_COMMUN);

        espace = new Region();
        espace.setMinHeight(100);
        
        VBox.setMargin(pointsLabel, new Insets(0, 0, 0, 10));
        
        dynamicBox = new VBox();
        dynamicBox.setSpacing(10);
        this.getChildren().add(dynamicBox);
    }

    public void afficherJoueurs(Joueur joueurQuiJoue, List<Joueur> joueurs) {
    	dynamicBox.getChildren().clear();
    	afficherJoueursPresent(joueurQuiJoue, joueurs);
    	dynamicBox.getChildren().add(espace);
    	afficherJoueursPoints(joueurs);
    }
    
    private void afficherJoueursPresent(Joueur joueurQuiJoue, List<Joueur> joueurs) {
    	String prefixe;
    	Label lblJoueur;
    	for (Joueur joueur : joueurs) {
    		prefixe = joueur.equals(joueurQuiJoue) ? "Joue : " : "Attente : ";
    		lblJoueur = new Label(prefixe + joueur.pseudo());
    		lblJoueur.setStyle(STYLE_COMMUN);
    		VBox.setMargin(lblJoueur, new Insets(0, 0, 0, 10));
    		dynamicBox.getChildren().add(lblJoueur);
    	}
    }
    
    private void afficherJoueursPoints(List<Joueur> joueurs) {
    	dynamicBox.getChildren().add(pointsLabel);
    	Label lblJoueur;
    	for (Joueur joueur : joueurs) {
    		lblJoueur = new Label(joueur.pseudo());
    		lblJoueur.setStyle(STYLE_COMMUN);
    		VBox.setMargin(lblJoueur, new Insets(0, 0, 0, 20));
    		
    		Label lblJoueurPoints = new Label(String.format(Textes.AFFICHAGE_POINTS.texte(), joueur.points()));
    		lblJoueurPoints.setStyle(STYLE_COMMUN);
    		VBox.setMargin(lblJoueurPoints, new Insets(0, 0, 0, 30));
    		Label lblJoueurTuilesPlacees = new Label(String.format(Textes.AFFICHAGE_TUILES_PLACEES.texte(), joueur.tuilesPlacees()));
    		lblJoueurTuilesPlacees.setStyle(STYLE_COMMUN);
    		VBox.setMargin(lblJoueurTuilesPlacees, new Insets(0, 0, 0, 30));
    		dynamicBox.getChildren().addAll(lblJoueur, lblJoueurPoints, lblJoueurTuilesPlacees);
    	}
	}
}