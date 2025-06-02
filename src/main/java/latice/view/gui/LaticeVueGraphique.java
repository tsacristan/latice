package latice.view.gui;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.RackJoueur;
import latice.view.LaticeVue;

public class LaticeVueGraphique extends LaticeVue {
	private RackJoueur rack;
	private InterfaceJeu interfaceJeu;
	private InterfaceRack interfaceRack;
	private PartieJoueur partieJoueur;
	
	public LaticeVueGraphique() {
		interfaceJeu = new InterfaceJeu();
		partieJoueur = new PartieJoueur();
	}

	@Override
	public void afficherRack(Joueur joueur, Runnable validerTour, Runnable passerTour) {
		if (interfaceRack == null) {
			interfaceRack = new InterfaceRack(joueur.rackJoueur());
			joueur.rackJoueur().ajouterListener(interfaceRack);
			interfaceJeu.setBottom(interfaceJeu.initialiserPartieControle(joueur, validerTour, passerTour));
		} else {
			interfaceRack.afficherRack(joueur.rackJoueur());
		}
	}

	@Override
	public void afficherPlateau(Plateau plateau) {
		if (interfaceJeu.getCenter() == null) interfaceJeu.setCenter(interfaceJeu.initialiserInterfacePlateau(plateau, rack, this));
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
	
	public void actualiserScores(List<Joueur> joueurs, Joueur joueurCourant) {
	    partieJoueur.afficherJoueurs(joueurCourant, joueurs);
	}

}
