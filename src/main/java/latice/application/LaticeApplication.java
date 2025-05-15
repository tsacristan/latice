package latice.application;

import latice.ihm.Console;
import latice.ihm.AfficherPlateauConsole;
import latice.model.*;
import latice.util.*;
import java.util.Random;
import java.util.Scanner;

public class LaticeApplication {
    public static void main(String[] args) throws PiocheInvalideException, RackInvalideException, PlateauIndexInvalideException, RackIndexInvalideException, PlacementDejaExistantInvalide {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        PileDebut pile = new PileDebut();
        pile.remplir();
        pile.melanger();
        
        Joueur joueur1 = new Joueur("1");
        Joueur joueur2 = new Joueur("2");
        
        pile.distribuer(new PileJoueur[]{joueur1.pileJoueur(), joueur2.pileJoueur()});

        joueur1.remplirRack();
        joueur2.remplirRack();

        Plateau plateau = new Plateau();
        AfficherPlateauConsole affichage = new AfficherPlateauConsole();

        Console.titre("Bienvenue dans le jeu LATICE !");

        int joueurActif = random.nextBoolean() ? 1 : 2;

        Console.message("Le joueur " + joueurActif + " commence !");

        int tour = 1;
        final int TOURS_MAX = 10;

        while (tour <= TOURS_MAX) {
            Console.titre("Tour " + tour + " / " + TOURS_MAX);

            Joueur joueurActuel = (joueurActif == 1) ? joueur1 : joueur2;
            Console.titre("Plateau actuel :");
            affichage.afficher(plateau);

            Console.message("Rack du joueur " + joueurActif + " :");

            for (int i = 0; i < joueurActuel.rackJoueur().rack().size(); i++) {
                Tuile tuile = joueurActuel.rackJoueur().rack().get(i);
                Case caseTuile = new Case(tuile, TypeCase.CASE_OCCUPEE);
                Console.message((i + 1) + ". " + caseTuile.toString());
            }

            int tailleRack = joueurActuel.rackJoueur().rack().size();
            int indexTuile = demanderEntierDansIntervalle(scanner,
                "Quelle tuile jouer ? (index de 1 à " + tailleRack + ", ou -1 pour quitter)",
                -1, tailleRack);

            if (indexTuile == -1) break;
            indexTuile--;

            int ligne = demanderEntierDansIntervalle(scanner, "Sur quelle ligne ? (1 à 9)", 1, 9);
            int colonne = demanderEntierDansIntervalle(scanner, "Sur quelle colonne ? (1 à 9)", 1, 9);

            ligne -= 1;
            colonne -= 1;

            try {
                plateau.placerLaTuileSurLePlateau(indexTuile, new Coordonnees(colonne, ligne), joueurActuel.rackJoueur());
                Console.message("Tuile placée !");
                joueurActuel.remplirRack();
            } catch (Exception e) {
                Console.message("Erreur : " + e.getMessage());
                continue;
            }

            joueurActif = (joueurActif == 1) ? 2 : 1;
            tour++;
        }

        Console.message("Fin du jeu après " + (tour - 1) + " tours. Merci d'avoir joué !");
        scanner.close();
    }

    private static int demanderEntierDansIntervalle(Scanner scanner, String message, int min, int max) {
        while (true) {
            try {
                Console.message(message);
                String saisie = scanner.nextLine().trim();

                if (saisie.isEmpty()) {
                    throw new IllegalArgumentException("Erreur : la saisie ne peut pas être vide.");
                }

                int valeur = Integer.parseInt(saisie);

                if (valeur < min || valeur > max) {
                    throw new IllegalArgumentException("Erreur : la valeur doit être comprise entre " + min + " et " + max + ".");
                }

                return valeur;

            } catch (NumberFormatException e) {
                Console.message("Erreur : vous devez saisir un nombre entier valide.");
            } catch (IllegalArgumentException e) {
                Console.message(e.getMessage());
            }
        }
    }
}