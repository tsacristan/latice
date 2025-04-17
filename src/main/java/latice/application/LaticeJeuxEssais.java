package latice.application;

import latice.ihm.Console;
import latice.model.*;
import latice.ihm.AfficherPlateauConsole;

public class LaticeJeuxEssais {

    public static void main(String[] args) {
        
        //Creation d'une pile principale, la remplire et la mélanger
        PileDebut pile = new PileDebut();
        pile.remplir();
        pile.melanger();
        
        Console.message("La pile est bien mélanger ! \n");
        
        //Répartition de la pile principale en 2 piles égales
        PileJoueur pile1 = new PileJoueur();
        PileJoueur pile2 = new PileJoueur();
        pile.distribuer(new PileJoueur[]{pile1, pile2});
        
        Console.message("Les joueurs ont bien 2 piles également réparties ! \n");

        //Attribution des racks aux joueurs
        RackJoueur rackJoueur1 = new RackJoueur();
        rackJoueur1.remplir(pile1);
        Console.message("Voici le rack du joueur 1");
        Console.message(rackJoueur1.toString());
        
        Console.message("\n");
        
        RackJoueur rackJoueur2 = new RackJoueur();
        rackJoueur2.remplir(pile2);
        Console.message("Voici le rack du joueur 2");
        Console.message(rackJoueur2.toString());
        
        Console.message("\n");
        
        //Echange de rack avec 5 tuiles de la pile
        Console.message("Nouveau rack du joueur 1");
        rackJoueur1.piocher(pile1);
        Console.message(rackJoueur1.toString());
        
        Console.message("\n");
        
        Console.message("Nouveau rack du joueur 2");
        rackJoueur1.piocher(pile2);
        Console.message(rackJoueur2.toString());

        Plateau plateau = new Plateau();
        AfficherPlateauConsole affichagePlateau = new AfficherPlateauConsole();
        affichagePlateau.afficher(plateau);
    }
}