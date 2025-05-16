package latice.view.console;

import java.util.Scanner;

import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.model.player.RackJoueur;
import latice.util.exception.PlateauIndexInvalideException;
import latice.view.LaticeVue;
import latice.view.Textes;

public class LaticeVueConsole extends LaticeVue {
	
	Scanner scanner;
	AfficheurElementsJeu afficheur;
	
	public LaticeVueConsole() {
		scanner = new Scanner(System.in);
		afficheur = new AfficheurElementsJeu();
	}

	@Override
	public void afficherRack(RackJoueur rackJoueur) {
		afficheur.afficherRack(rackJoueur);
	}

	@Override
    public void afficherPlateau(Plateau plateau) {
        afficheur.afficherPlateau(plateau);
        Console.messagef(Textes.AIDE_PLATEAU, EmojiForme.PLUME.emoji(), EmojiForme.OISEAU.emoji(), 
                EmojiForme.TORTUE.emoji(), EmojiForme.FLEUR.emoji(), EmojiForme.GECKO.emoji(), EmojiForme.DAUPHIN.emoji(), 
                EmojiForme.CASE_LUNE.emoji(), Textes.AFFICHAGE_SOLEIL,
                EmojiForme.CASE_VIDE.emoji());
    }

	@Override
	public String choisirPseudo(int numeroJoueur) {
		Console.messagef(Textes.CHOIX_PSEUDO, numeroJoueur);
		
		String pseudoChoisi = scanner.nextLine();
		
		while (pseudoChoisi.isBlank()) {
			Console.messagef(Textes.PSEUDO_VIDE, numeroJoueur);
		}
		
		return pseudoChoisi;
	}

	@Override
	public void afficherTour(Joueur joueur, int nombreTour) {
		Console.messagef(Textes.NB_TOUR, nombreTour, joueur.pseudo());
	}
	
	@Override
	public int demanderTuileAPoser(Joueur joueur) {
		int tailleRack = joueur.rackJoueur().rack().size();
		String texte = String.format(Textes.DEMANDER_TUILE.toString(), tailleRack);
		Console.message(String.valueOf(tailleRack));
        return SaisieConsole.demanderEntierDansIntervalle(scanner, texte, 0, tailleRack);
	}
	
	@Override
	public Coordonnees choisirEmplacementPlateau() {
		boolean estValide = false;
		Coordonnees coordsFinal = null;
		final int premierIndice = 1;
		
		while (!estValide) {
			String texteLigne = String.format(Textes.DEMANDER_LIGNE.toString(), premierIndice, Plateau.LIGNES);
			String texteColonne = String.format(Textes.DEMANDER_COLONNE.toString(), premierIndice, Plateau.COLONNES);
			int ligne = SaisieConsole.demanderEntierDansIntervalle(scanner, texteLigne, premierIndice, Plateau.LIGNES) - 1;
			int colonne = SaisieConsole.demanderEntierDansIntervalle(scanner, texteColonne, premierIndice, Plateau.COLONNES) - 1;
			try {
				coordsFinal = new Coordonnees(ligne, colonne);
				estValide = true;
			} catch (PlateauIndexInvalideException e) {
				Console.messagef(Textes.COORDS_INVALIDE);
			}
		}
		
		return coordsFinal;
	}
	
	@Override
	public void afficherErreur(String message) {
		Console.message(message);
	}
	
}
