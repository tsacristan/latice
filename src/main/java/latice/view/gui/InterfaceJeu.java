package latice.view.gui;

import java.util.List;

import javafx.scene.layout.BorderPane;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.RackJoueur;
import latice.util.exception.PlateauIndexInvalideException;

public class InterfaceJeu extends BorderPane {
	
	private PartieHaut partieHaut;
	private PartieJoueur partieJoueur;

	public InterfaceJeu() {   
		partieHaut = new PartieHaut();
		partieJoueur = new PartieJoueur();
		
        setTop(partieHaut);
        
      	setCenter(null);
        
        setRight(new PartieInfo());
        
        setLeft(partieJoueur);
        
        setBottom(null);
	}
	
	public void afficherJoueursScoreTour(Joueur joueurQuiJoue, List<Joueur> joueurs, int nombreTour) {
		partieJoueur.afficherJoueurs(joueurQuiJoue, joueurs);
		partieHaut.changerTour(nombreTour);
	}
	
	public InterfacePlateau initialiserInterfacePlateau(Plateau plateau) {
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
    
    public PartieControle initialiserPartieControle(RackJoueur rack) {
    	PartieControle partie = new PartieControle(rack);
        rack.ajouterListener(partie.interfaceRack());
        
        return partie;
    }
	
}
