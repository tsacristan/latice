
package latice.application;

import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.model.Plateau;
import latice.model.RackJoueur;
import latice.ihm.AfficherPlateauConsole;

public class LaticeJeuxEssais {

	public static void main(String[] args) {
		
		//Creation d'une pile principale, la remplire et la mélanger
		PileDebut pile = new PileDebut();
		pile.remplir();
		pile.melanger();
		
		System.out.println("La pile est bien mélanger ! \n");
		
		//Répartition de la pile principale en 2 piles égales
		PileJoueur pile1 = new PileJoueur();
		PileJoueur pile2 = new PileJoueur();
		pile.distribuer(new PileJoueur[]{pile1, pile2});
		
		System.out.println("Les joueurs ont bien 2 piles également réparties ! \n");

		//Attribution des racks aux joueurs
		RackJoueur rackJoueur1 = new RackJoueur();
		rackJoueur1.remplir(pile1);
		System.out.println("Voici le rack du joueur 1");
		System.out.println(rackJoueur1);
		
		System.out.println("\n");
		
		RackJoueur rackJoueur2 = new RackJoueur();
		rackJoueur2.remplir(pile2);
		System.out.println("Voici le rack du joueur 2");
		System.out.println(rackJoueur2);
		
		System.out.println("\n");
		
		//Echange de rack avec 5 tuiles de la pile
		System.out.println("Nouveau rack du joueur 1");
		rackJoueur1.piocher(pile1);
		System.out.println(rackJoueur1);
		
		System.out.println("\n");
		
		System.out.println("Nouveau rack du joueur 2");
		rackJoueur1.piocher(pile2);
		System.out.println(rackJoueur2);

		Plateau plateau = new Plateau();
		AfficherPlateauConsole affichagePlateau = new AfficherPlateauConsole();
		affichagePlateau.afficher(plateau);
	}
}
