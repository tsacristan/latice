package latice.application;

import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.model.RackJoueur;

public class LaticeJeuxEssais {

	public static void main(String[] args) {
		PileDebut pile = new PileDebut();
		pile.remplir();
		pile.melanger();
		System.out.println(pile);
		System.out.println(pile.size());

		PileJoueur pile1 = new PileJoueur();
		PileJoueur pile2 = new PileJoueur();
		pile.distribuer(new PileJoueur[]{pile1, pile2});
		System.out.println(pile1);
		System.out.println(pile2);

		RackJoueur rackJoueur = new RackJoueur();
		rackJoueur.remplir(pile1);
		System.out.println(rackJoueur);
	}
}
