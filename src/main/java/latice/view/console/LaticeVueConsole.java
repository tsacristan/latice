package latice.view.console;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.player.Joueur;
import latice.util.exception.PlateauIndexInvalideException;
import latice.view.LaticeVue;
import latice.view.Textes;
import latice.view.TextesErreurs;

public class LaticeVueConsole extends LaticeVue {
	
	Scanner scanner;
	AfficheurElementsJeu afficheur;
	
	public LaticeVueConsole() {
		scanner = new Scanner(System.in);
		afficheur = new AfficheurElementsJeu();
	}

	@Override
	public void afficherRack(Joueur joueur) {
		afficheur.afficherRack(joueur.rackJoueur());
	}

	@Override
	public void afficherPlateau(Plateau plateau) {
		afficheur.afficherPlateau(plateau);
		Console.messagef(Textes.AIDE_PLATEAU, EmojiForme.PLUME.emoji(), EmojiForme.OISEAU.emoji(), 
				EmojiForme.TORTUE.emoji(), EmojiForme.FLEUR.emoji(), EmojiForme.GECKO.emoji(), EmojiForme.DAUPHIN.emoji(), 
				EmojiForme.CASE_LUNE.emoji(), CouleurConsole.ANSI_TEXTE_JAUNE + EmojiForme.CASE_SOLEIL.emoji() + CouleurConsole.ANSI_RESET,
				EmojiForme.CASE_VIDE.emoji());
	}

	@Override
	public String choisirPseudo(int numeroJoueur) {
		Console.messagef(Textes.CHOIX_PSEUDO, numeroJoueur);
		return scanner.nextLine();
	}

	@Override
	public void afficherTour(List<Joueur> joueurs, Joueur joueurQuiJoue, int nombreTour) {
		Console.messagef(Textes.NB_TOUR, nombreTour, joueurQuiJoue.pseudo());
		for (Joueur joueur : joueurs) {
			Console.messagef(Textes.AFFICHAGE_JOUEUR, joueur.pseudo(), joueur.score());
		}
	}

	public int demanderTuileAPoser(Joueur joueur) {
		int tailleRack = joueur.rackJoueur().rack().size();
		String texte = String.format(Textes.DEMANDER_TUILE.toString(), tailleRack);
        return SaisieConsole.demanderEntierDansIntervalle(scanner, texte, 1, tailleRack);
	}
	
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
				Console.messagef(TextesErreurs.COORDS_INVALIDE);
			}
		}
		return coordsFinal;
	}

	public void afficherScores(List<Joueur> joueurs, Joueur joueurCourant) {
        for (Joueur joueur : joueurs) {
            Console.messagef("%s : %d points.", joueur.pseudo(), joueur.score());
        }
        Console.messagef("-> Tour de %s", joueurCourant.pseudo());
    }

	public List<Integer> demanderIndicesATechanger(Joueur joueur) {
	    int tailleRack = joueur.rackJoueur().rack().size();
	    List<Integer> indices = new ArrayList<>();
	    for (int i = 0; i < tailleRack; i++) {
	        indices.add(i);
	    }
	    return indices;
	}

    public int demanderActionTour() {
        return SaisieConsole.demanderEntierDansIntervalle(scanner, Textes.DEMANDER_ACTION_TOUR.texte(), 1, 3);
    }

    @Override
    public void afficherMessage(String message) {
        Console.message(message);
    }

	@Override
	public void afficherErreur(String message) {
		Console.message(message);
	}

	@Override
	public void afficherGagnants(ArrayList<Joueur> gagnants) {
		afficherMessage(Textes.AFFICHAGE_GAGNANTS.texte());
    	for (Joueur joueur : gagnants) {
    		afficherMessage(String.format(Textes.AFFICHAGE_JOUEUR.texte(), joueur.pseudo(), joueur.score()));
    	}
	}
}