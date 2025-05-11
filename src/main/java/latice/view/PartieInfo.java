package latice.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PartieInfo extends StackPane {

    public PartieInfo() {
        
        Label tuilesRestantesLabel = new Label("Tuiles restantes :");
        Label nombreTuilesLabel = new Label("25");

        VBox labelsBox = new VBox(5, tuilesRestantesLabel, nombreTuilesLabel);
        labelsBox.setAlignment(Pos.TOP_CENTER);
        VBox tuileBox = new VBox();
        tuileBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(tuileBox, new Insets(20));

        Button shopButton = new Button("SHOP");
        shopButton.setPrefSize(100, 40);

        VBox boutonBox = new VBox(shopButton);
        boutonBox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox.setMargin(shopButton, new Insets(10));

        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(10));
        tuilesRestantesLabel.setStyle("-fx-font-size: 20px;");
        nombreTuilesLabel.setStyle("-fx-font-size: 20px;");
        shopButton.setStyle("-fx-font-size: 20px;");

        this.getChildren().addAll(labelsBox, tuileBox, boutonBox);
    }
}