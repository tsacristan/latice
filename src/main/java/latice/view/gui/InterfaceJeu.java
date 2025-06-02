package latice.view.gui;

import java.util.List;

import javafx.scene.layout.BorderPane;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.RackJoueur;
import latice.view.LaticeVue;

public class InterfaceJeu extends BorderPane {
	
	private PartieHaut partieHaut;
	private PartieJoueur partieJoueur;
	private PartieInfo partieInfo;

	public InterfaceJeu() {   
		partieHaut = new PartieHaut();
		partieJoueur = new PartieJoueur();
		partieInfo = new PartieInfo();
		
        setTop(partieHaut);
      	setCenter(null);
        setRight(partieInfo);
        setLeft(partieJoueur);
        setBottom(null);
	}
	
	public void afficherJoueursPointsTour(Joueur joueurQuiJoue, List<Joueur> joueurs, int nombreTour) {
		partieJoueur.afficherJoueurs(joueurQuiJoue, joueurs);
		partieHaut.changerTour(nombreTour);
	}
	
	public InterfacePlateau initialiserInterfacePlateau(Plateau plateau, RackJoueur rackJoueur, LaticeVue laticeVue) {
		InterfacePlateau interfacePlateau = null;
		
		interfacePlateau = new InterfacePlateau(plateau, rackJoueur, laticeVue);
		plateau.ajouterListener(interfacePlateau);
		
		return interfacePlateau;
    }
    
    public PartieControle initialiserPartieControle(Joueur joueur) {
    	PartieControle partie = new PartieControle(joueur);
    	joueur.rackJoueur().ajouterListener(partie.interfaceRack());
        
        return partie;
    }
    
    public InterfacePlateau interfacePlateau() {
    	if (getCenter() instanceof InterfacePlateau) return (InterfacePlateau) getCenter();
    	return null;
    }
    
    public PartieInfo partieInfo() {
    	return partieInfo;
    }
}
