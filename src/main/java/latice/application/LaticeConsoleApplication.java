package latice.application;

import latice.ihm.Console;
import latice.model.PileDebut;
import latice.model.PileJoueur;
import latice.model.RackJoueur;
import latice.util.PiocheInvalideException;

public class LaticeConsoleApplication {

    public static void main(String[] args) throws PiocheInvalideException {
        PileDebut pile = new PileDebut();
        pile.remplir();
        pile.melanger();

        PileJoueur pile1 = new PileJoueur();
        PileJoueur pile2 = new PileJoueur();
        pile.distribuer(new PileJoueur[]{pile1, pile2});
        
        RackJoueur rackJoueur = new RackJoueur(pile1);
        rackJoueur.remplir();
        Console.message(rackJoueur.toString());
    }
    
}