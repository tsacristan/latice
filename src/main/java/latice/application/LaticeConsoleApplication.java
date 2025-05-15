package latice.application;

import latice.model.PileDebut;
import latice.model.player.PileJoueur;
import latice.model.player.RackJoueur;
import latice.util.exception.PiocheInvalideException;
import latice.view.console.Console;

public class LaticeConsoleApplication {

    public static void main(String[] args) throws PiocheInvalideException {
        PileDebut pile = new PileDebut();
        pile.remplir();
        pile.melanger();

        PileJoueur pile1 = new PileJoueur();
        PileJoueur pile2 = new PileJoueur();
        pile.distribuer(new PileJoueur[]{pile1, pile2});
        
        RackJoueur rackJoueur = new RackJoueur(pile1);
        rackJoueur.remplir(pile1);
        Console.message(rackJoueur.toString());
    }
    
}