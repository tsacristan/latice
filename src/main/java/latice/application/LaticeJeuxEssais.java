package latice.application;

import latice.ihm.Console;
import latice.model.*;
import latice.util.PiocheInvalideException;
import latice.util.PlacementDejaExistantInvalide;
import latice.util.PlateauIndexInvalideException;
import latice.util.RackIndexInvalideException;
import latice.util.RackInvalideException;
import latice.controller.LaticeController;
import latice.ihm.AfficherPlateauConsole;

public class LaticeJeuxEssais {

    public static void main(String[] args) throws PiocheInvalideException,RackInvalideException,PlateauIndexInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide {
        
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
        RackJoueur rackJoueur1 = new RackJoueur(pile1);
        rackJoueur1.remplir();
        Console.message("Voici le rack du joueur 1");
        Console.message(rackJoueur1.toString());
        
        Console.message("\n");
        
        RackJoueur rackJoueur2 = new RackJoueur(pile2);
        rackJoueur2.remplir();
        Console.message("Voici le rack du joueur 2");
        Console.message(rackJoueur2.toString());
        
        Console.message("\n");
        
        //Echange de rack avec 5 tuiles de la pile
        Console.message("Nouveau rack du joueur 1");
        rackJoueur1.remplir();
        Console.message(rackJoueur1.toString());
        
        Console.message("\n");
        
        Console.message("Nouveau rack du joueur 2");
        rackJoueur1.remplir();
        Console.message(rackJoueur2.toString());

        Plateau plateau = new Plateau();
        AfficherPlateauConsole affichagePlateau = new AfficherPlateauConsole();
        affichagePlateau.afficher(plateau);
            
        plateau.placerLaTuileSurLePlateau(2, new Coordonnees(4, 3), rackJoueur2);
        affichagePlateau.afficher(plateau);
                
        //Jeu de test piocher
        PileDebut pileTest = new PileDebut();
        pileTest.remplir();
        pileTest.melanger();
        
        PileJoueur pileJoueur1 = new PileJoueur();
        PileJoueur pileJoueur2 = new PileJoueur();
        pileTest.distribuer(new PileJoueur[]{pileJoueur1, pileJoueur2});
        
        RackJoueur rack1 = new RackJoueur(pileJoueur1);
        rack1.remplir();
        RackJoueur rack2 = new RackJoueur(pileJoueur1);
        rack2.remplir();
        
        for (int i = 0; i < 25; i++) {
        	pileJoueur1.retirerTuile();
        }
        
        Console.titre("Test de piocher si le contenu de la pile est inférieur a 5");
        Console.message("Contenu de la pile joueur 1 :");
        Console.message(pileJoueur1.toString());
        
        Console.message();
        
        Console.message("Contenu du rack Joueur 1 avant avoir piocher :");
        Console.message(rackJoueur1.toString());
        
        Console.message();
        
        rackJoueur1.remplir();
        Console.message("Contenu du rack Joueur 1 après avoir piocher  :");
        Console.message(rackJoueur1.toString());
    
        //Jeu de test choisirTuile
        PileDebut pileTuile = new PileDebut();
        pileTuile.remplir();
        pileTuile.melanger();
        
        PileJoueur pileJ1 = new PileJoueur();
        PileJoueur pileJ2 = new PileJoueur();
        pileTuile.distribuer(new PileJoueur[]{pileJ1, pileJ2});
        
        RackJoueur rackJ1 = new RackJoueur(pileJ1);
        rackJ1.remplir();
        
        Console.titre("Test de choisir la Tuile ");
        
        Console.message("Contenu du rack Joueur 1 avant de choisir la Tuile :");
        Console.message(rackJ1.toString());
        
        Console.message();
        
        rackJoueur1.choisirTuile(2);
        Console.message("Contenu du rack Joueur 1 après avoir choisi la Tuile :");
        Console.message(rackJoueur1.toString());
        
        
        ///////////////////////////////////
        
       LaticeController.demarrerJeu();
        
        
        
        
    }
}