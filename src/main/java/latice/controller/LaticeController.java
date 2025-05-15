package latice.controller;

import java.util.Random;
import java.util.Scanner;

import latice.ihm.Console;
import latice.model.Joueur;
import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.util.PiocheInvalideException;

public class LaticeController {
	
	public static void demarrerJeu() throws PiocheInvalideException {
		Random random = new Random();
		
		Scanner choix = new Scanner(System.in);
		
		//Choix des pseudos
		
		Console.message("Veuillir choisir le pseudo du joueur 1 : ");
		String reponse1 = choix.nextLine();
		Joueur joueur1 = new Joueur(reponse1);
		
		Console.message("Veuillir choisir le pseudo du joueur 2 : ");
		String reponse2 = choix.nextLine();
		Joueur joueur2 = new Joueur(reponse2);
		
		PileDebut pile = new PileDebut();
		pile.remplir();
		
		pile.distribuer(new PileJoueur[]{joueur1.pileJoueur(),joueur2.pileJoueur()});
		
		joueur1.remplirRack();
		joueur2.remplirRack();
		
		Joueur[] joueurs = {joueur1,joueur2};
		
		int joueurActif = random.nextBoolean() ? 0 : 1;
		
		Console.message("Le premier joueur est " + joueurs[joueurActif]);
		
		Console.message(joueurs[joueurActif].rackJoueur().toString());
		
		choix.close();
	}
}
