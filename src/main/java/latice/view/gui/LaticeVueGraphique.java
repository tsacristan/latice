package latice.view.gui;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.RackJoueur;
import latice.util.exception.PlateauIndexInvalideException;
import latice.view.LaticeVue;

public class LaticeVueGraphique extends LaticeVue {
	private RackJoueur rack;
	private InterfaceJeu interfaceJeu;
	private InterfaceRack interfaceRack;
	
	public LaticeVueGraphique() {
		interfaceJeu = new InterfaceJeu();
	}
	public void setJoueurCourant(Joueur joueur) {
	    this.rack = joueur.rackJoueur();
	}

	@Override
	public void afficherRack(RackJoueur rackJoueur) {
	if (interfaceRack == null) {
		interfaceRack = new InterfaceRack(rackJoueur);
		rackJoueur.ajouterListener(interfaceRack);
		interfaceJeu.setBottom(interfaceJeu.initialiserPartieControle(rackJoueur));
	} else {
		interfaceRack.afficherRack(rackJoueur);
	}
	}
	

	@Override
	public void afficherPlateau(Plateau plateau) {
		if (interfaceJeu.getCenter() == null) interfaceJeu.setCenter(interfaceJeu.initialiserInterfacePlateau(plateau, rack));
	}

	@Override
	public String choisirPseudo(int numeroJoueur1) {
		String choixPseudo = null;
		
		TextInputDialog dialog = new TextInputDialog("Pseudo");
		dialog.setTitle("Choix du pseudo");
		dialog.setContentText("Veuillez choisir votre pseudo");
		
		Optional<String> choixPseudoOp = dialog.showAndWait();
		
		if (choixPseudoOp.isPresent()) {
			choixPseudo = choixPseudoOp.get();
		}
		
		return choixPseudo;
	}

	@Override
	public int demanderTuileAPoser(Joueur joueur) {
		// TODO Valeur par défaut le temps de la V4, à réimplémenter en V5
		return 5;
	}

	@Override
	public Coordonnees choisirEmplacementPlateau() {
		// TODO Valeur par défaut le temps de la V4, à réimplémenter en V5
		Coordonnees coords = null;
		try {
			coords = new Coordonnees(5, 5);
		} catch (PlateauIndexInvalideException e) {
			e.printStackTrace();
		}
		return coords;
	}

	@Override
	public void afficherErreur(String message) {
		Alert dialog = new Alert(AlertType.ERROR);
		dialog.setTitle("Erreur !");
		dialog.setHeaderText(null);
		dialog.setContentText(message);
		dialog.showAndWait();
	}
	
	public InterfaceJeu interfaceJeu() {
		return interfaceJeu;
	}

	@Override
	public void afficherTour(List<Joueur> joueurs, Joueur joueurQuiJoue, int nombreTour) {
		interfaceJeu.afficherJoueursScoreTour(joueurQuiJoue, joueurs, nombreTour);
	}

}
