package latice.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

public class PartieJoueur extends VBox {

	private static final String STYLE_COMMUN = "-fx-font-size: 25px; -fx-alignment: center;";
	
    private String joueur1 = "Joueur1";
    private String joueur2 = "Joueur2";

    private String joueurActif = joueur1;
    private String joueurEnAttente = joueur2;

    private int scoreJoueur1 = 0;
    private int scoreJoueur2 = 3;

    private Label joueLabel = new Label();
    private Label attenteLabel = new Label();
    private Label pointsLabel = new Label("Points :");
    private Label pointsJoueur1Label = new Label();
    private Label pointsJoueur2Label = new Label();

    
    
    public PartieJoueur() {
        setSpacing(10);
        setAlignment(Pos.TOP_LEFT);
        setPrefWidth(200);
        
        joueLabel.setStyle(STYLE_COMMUN);
        attenteLabel.setStyle(STYLE_COMMUN);
        pointsLabel.setStyle(STYLE_COMMUN);
        pointsJoueur1Label.setStyle(STYLE_COMMUN);
        pointsJoueur2Label.setStyle(STYLE_COMMUN);

        Region espace = new Region();
        espace.setMinHeight(100);
        
        VBox.setMargin(joueLabel, new Insets(0, 0, 0, 10));
        VBox.setMargin(attenteLabel, new Insets(0, 0, 0, 10));
        VBox.setMargin(pointsLabel, new Insets(0, 0, 0, 10));
        VBox.setMargin(pointsJoueur1Label, new Insets(0, 0, 0, 20));
        VBox.setMargin(pointsJoueur2Label, new Insets(0, 0, 0, 20));

        getChildren().addAll(joueLabel, attenteLabel, espace,
                             pointsLabel, pointsJoueur1Label, pointsJoueur2Label);

        updateAffichage();
    }

    private void changerDeJoueur() {
        String temp = joueurActif;
        joueurActif = joueurEnAttente;
        joueurEnAttente = temp;
        updateAffichage();
    }

    private void updateClassement() {
        if (scoreJoueur1 >= scoreJoueur2) {
            pointsJoueur1Label.setText(joueur1 + " : " + scoreJoueur1);
            pointsJoueur2Label.setText(joueur2 + " : " + scoreJoueur2);
        } else {
            pointsJoueur1Label.setText(joueur2 + " : " + scoreJoueur2);
            pointsJoueur2Label.setText(joueur1 + " : " + scoreJoueur1);
        }
    }

    private void updateAffichage() {
        joueLabel.setText("Joue : " + joueurActif);
        attenteLabel.setText("Attente : " + joueurEnAttente);
        updateClassement();
    }
}