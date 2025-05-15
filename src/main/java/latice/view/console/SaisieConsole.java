package latice.view.console;

import java.util.Scanner;

public class SaisieConsole {

	public static int demanderEntierDansIntervalle(Scanner scanner, String message, int min, int max) {
		boolean saisieEstValide = false;
		int valeurSaisie = -1;
        while (!saisieEstValide) {
            try {
                Console.message(message);
                String saisie = scanner.nextLine().trim();

                if (saisie.isEmpty()) {
                    throw new IllegalArgumentException("Erreur : la saisie ne peut pas être vide.");
                }

                valeurSaisie = Integer.parseInt(saisie);

                if (valeurSaisie < min || valeurSaisie > max) {
                    throw new IllegalArgumentException("Erreur : la valeur doit être comprise entre " + min + " et " + max + ".");
                }

                saisieEstValide = true;

            } catch (NumberFormatException e) {
                Console.message("Erreur : vous devez saisir un nombre entier valide.");
            } catch (IllegalArgumentException e) {
                Console.message(e.getMessage());
            }
        }
        
        return valeurSaisie;
    }
	
}
