package latice.application;

import latice.ihm.Console;
import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.model.RackJoueur;

public class LaticeConsoleApplication {

	public static void main(String[] args) {
		PileDebut pile = new PileDebut();
		pile.remplir();
		pile.melanger();

		PileJoueur pile1 = new PileJoueur();
		PileJoueur pile2 = new PileJoueur();
		pile.distribuer(new PileJoueur[]{pile1, pile2});
		
		RackJoueur rackJoueur = new RackJoueur();
		rackJoueur.remplir(pile1);
		Console.message(rackJoueur.toString());
	}
	
}
