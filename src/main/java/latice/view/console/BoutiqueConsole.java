package latice.view.console;

import java.util.Scanner;

import latice.model.player.Joueur;
import latice.model.points.Couts;
import latice.view.Textes;
import latice.view.TextesErreurs;

public class BoutiqueConsole {

    public void ouvrir(Joueur joueur) {
        Scanner scanner = new Scanner(System.in);

        Console.titre(Textes.BIENVENUE_BOUTIQUE.texte());
        Console.messagef(Textes.POINTS_ACTUELS.texte(), joueur.points());
        Console.messagef(Textes.COUT_ACTION_SUPPLEMENTAIRE.texte(), Couts.COUT_ACHAT_ACTION_SUPPLEMENTAIRE.valeur());
        Console.message(Console.LIGNE_HORIZONTALE);

        Console.message(Textes.DEMANDER_ACHAT.texte());
        Console.message(Textes.CHOIX_OUI_NON.texte());
        Console.messageAligne(Textes.VOTRE_CHOIX.texte());

        int choix = scanner.nextInt();

        if (choix == 1) {
            if (joueur.points() >= Couts.COUT_ACHAT_ACTION_SUPPLEMENTAIRE.valeur()) {
                joueur.ajouterPoints(-Couts.COUT_ACHAT_ACTION_SUPPLEMENTAIRE.valeur());
                joueur.changerNombreCoups(joueur.nombreCoups() + 1);
                Console.message("\n" + Textes.ACHAT_REUSSI.texte());
                Console.messagef(Textes.POINTS_RESTANTS.texte(), joueur.points());
            } else {
                Console.message("\n" + TextesErreurs.ACHAT_ECHOUE.texte());
            }
        } else {
            Console.message("\n" + Textes.MERCI_VISITE.texte());
        }

        Console.message(Console.LIGNE_HORIZONTALE);
        Console.message(Textes.RETOUR_JEU.texte());
        Console.message(Textes.SEPARATEUR.texte());
    }
}