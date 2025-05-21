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
        
    public PartieJoueur() {
        setSpacing(10);
        setAlignment(Pos.TOP_LEFT);
        setPrefWidth(200);
        
        pointsLabel.setStyle(STYLE_COMMUN);

        espace = new Region();
        espace.setMinHeight(100);
        
        VBox.setMargin(pointsLabel, new Insets(0, 0, 0, 10));
    }

    public void afficherJoueurs(Joueur joueurQuiJoue, List<Joueur> joueurs) {
    	getChildren().clear();
    	afficherJoueursPresent(joueurQuiJoue, joueurs);
    	getChildren().add(espace);
    	afficherJoueursScores(joueurs);
    }
    
    private void afficherJoueursPresent(Joueur joueurQuiJoue, List<Joueur> joueurs) {
    	String prefixe;
    	Label lblJoueur;
    	for (Joueur joueur : joueurs) {
    		prefixe = joueur.equals(joueurQuiJoue) ? "Joue : " : "Attente : ";
    		lblJoueur = new Label(prefixe + joueur.pseudo());
    		lblJoueur.setStyle(STYLE_COMMUN);
    		VBox.setMargin(lblJoueur, new Insets(0, 0, 0, 10));
    		getChildren().add(lblJoueur);
    	}
    }
    
    private void afficherJoueursScores(List<Joueur> joueurs) {
    	getChildren().add(pointsLabel);
    	Label lblJoueur;
    	for (Joueur joueur : joueurs) {
    		lblJoueur = new Label(String.format(Textes.AFFICHAGE_JOUEUR.texte(), joueur.pseudo(), joueur.score()));
    		lblJoueur.setStyle(STYLE_COMMUN);
    		VBox.setMargin(lblJoueur, new Insets(0, 0, 0, 20));
    		getChildren().add(lblJoueur);
    	}
	}
}