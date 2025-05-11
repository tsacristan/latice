package latice.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import latice.model.Coordonnees;
import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.model.Plateau;
import latice.model.RackJoueur;
import latice.util.PlacementDejaExistantInvalide;
import latice.util.PlateauIndexInvalideException;
import latice.util.RackIndexInvalideException;
import latice.util.RackInvalideException;
import latice.view.InterfacePlateau;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		
		PileDebut pile = new PileDebut();
        pile.remplir();
        pile.melanger();
		PileJoueur pile1 = new PileJoueur();
        PileJoueur pile2 = new PileJoueur();
        pile.distribuer(new PileJoueur[]{pile1, pile2});
		RackJoueur rackJoueur1 = new RackJoueur(pile1);
		rackJoueur1.remplir();
		Plateau plateau = new Plateau();
		plateau.placerLaTuileSurLePlateau(2, new Coordonnees(4, 4), rackJoueur1);
		InterfacePlateau interfacePlateau = new InterfacePlateau(plateau);
		plateau.placerLaTuileSurLePlateau(2, new Coordonnees(4, 3), rackJoueur1);
		plateau.ajouterListener(interfacePlateau);
		root.getChildren().add(interfacePlateau);		

		primaryStage.setResizable(false);
        Scene scene = new Scene(root, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Latice Application");
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
