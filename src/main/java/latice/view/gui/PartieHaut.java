package latice.view.gui;

import javafx.animation.KeyFrame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class PartieHaut extends HBox {
    
    private static final String STYLE_COMMUN = "-fx-font-size: 30px; -fx-alignment: center; ";
    private static final String TEXT_TOUR = "Tour : ";
    
    private Label lblTour;
     private Label lblHeure;
     private Timeline chrono;
     private int secondes = 0;
     private int minutes = 0;
    
     public PartieHaut() {
    	    lblHeure = new Label("00:00");
    	    lblTour = new Label(TEXT_TOUR);
    	    Button btnAide = new Button("Règles");

    	    lblHeure.setStyle(STYLE_COMMUN);
    	    lblTour.setStyle(STYLE_COMMUN);
    	    btnAide.setStyle(STYLE_COMMUN);

    	    lblTour.setMaxWidth(150);

    	    // Espaces vides pour centrer lblTour
    	    Region espaceGauche = new Region();
    	    Region espaceDroit = new Region();
    	    HBox.setHgrow(espaceGauche, Priority.ALWAYS);
    	    HBox.setHgrow(espaceDroit, Priority.ALWAYS);

    	    btnAide.setOnAction(e -> {
    	        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
    	        alert.setTitle("Règles du jeu Latice");
    	        alert.setHeaderText("Voici les règles principales de Latice :");

    	        alert.setContentText(
    	            "- Chaque joueur commence avec un rack de tuiles (formes et couleurs).\n" +
    	            "- Les tuiles ne peuvent être placées qu'à côté d'une autre tuile.\n" +
    	            "- Pour poser une tuile, elle doit correspondre par la couleur ou la forme avec une tuile adjacente.\n" +
    	            "- Si un joueur ne peut pas jouer, il peut choisir de passer ou de piocher.\n" +
    	            "- Le jeu progresse en tours (jusqu'à 10 tours maximum).\n" +
    	            "- Le but est de poser toutes ses tuiles en optimisant les points gagnés par placement."
    	        );

    	        alert.showAndWait();
    	    });

    	    setPadding(new Insets(10, 20, 10, 20));
    	    setSpacing(10);

    	    // Ajoute les éléments dans l'ordre avec les régions autour du label centré
    	    getChildren().addAll(lblHeure, espaceGauche, lblTour, espaceDroit, btnAide);

    	    initialiserChrono();
    	}

    
    private void initialiserChrono() {
        chrono = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            secondes++;
            if (secondes == 60) {
                minutes++;
                secondes = 0;
            }
            majHeure();
        }));
        chrono.setCycleCount(Timeline.INDEFINITE);
        chrono.play();
    }
        private void majHeure() {
            String timeFormatted = String.format("%02d:%02d", minutes, secondes);
            lblHeure.setText(timeFormatted);
        }
    
    public void changerTour(int nombreTour) {
        String nouveauTexte = TEXT_TOUR + nombreTour;
        
        lblTour.setText(nouveauTexte);
    }
    
}