package latice.application;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import latice.model.Coordonnees;
import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.model.Plateau;
import latice.model.RackJoueur;
import latice.util.PlateauIndexInvalideException;
import latice.view.InterfacePlateau;
import latice.view.PartieControle;
import latice.view.PartieHaut;
import latice.view.PartieInfo;
import latice.view.PartieJoueur;

public class LaticeGraphique extends Application { 
	
	private PileJoueur pile1;
	private PileJoueur pile2;
	private RackJoueur rack1;
	private RackJoueur rack2;
	private Plateau plateau;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        initialiserJeu();
        
        root.setTop(new PartieHaut());
        
      	root.setCenter(initialiserInterfacePlateau());
        
        root.setRight(new PartieInfo());
        
        root.setLeft(new PartieJoueur());
        
        root.setBottom(initialiserPartieControle());
        plateau.placerLaTuileSurLePlateau(0, new Coordonnees(4, 4), rack1);
        
        primaryStage.setFullScreen(true);
        Scene scene = new Scene(root,1920,1005);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Latice");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void initialiserJeu() throws Exception {
    	PileDebut pile = new PileDebut();
        pile.remplir();
        pile.melanger();
		pile1 = new PileJoueur();
		pile2 = new PileJoueur();
        pile.distribuer(new PileJoueur[]{pile1, pile2});
		rack1 = new RackJoueur(pile1);
		rack1.remplir(pile1);
		rack2 = new RackJoueur(pile2);
		rack2.remplir(pile2);
		
		plateau = new Plateau();
    }
    
    private InterfacePlateau initialiserInterfacePlateau() {
		InterfacePlateau interfacePlateau = null;
		try {
			interfacePlateau = new InterfacePlateau(plateau);
			plateau.ajouterListener(interfacePlateau);
			
			return interfacePlateau;
		} catch (PlateauIndexInvalideException e) {
			e.printStackTrace();
		}
		
		return interfacePlateau;
    }
    
    private PartieControle initialiserPartieControle() {
    	PartieControle partie = new PartieControle(rack1);
        rack1.ajouterListener(partie.interfaceRack());
        
        return partie;
    }
}