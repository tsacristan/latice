package latice.model.boutique;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import latice.model.player.Joueur;
import latice.model.points.Couts;

class TestBoutique {

    private static final int COUT_ACTION_SUPPLEMENTAIRE = Couts.COUT_ACHAT_ACTION_SUPPLEMENTAIRE.valeur();

    @Test
    void achatReussiPourJoueurAvecPoints() {
        Joueur joueur = new Joueur("Joueur1");
        joueur.ajouterPoints(COUT_ACTION_SUPPLEMENTAIRE);

        effectuerAchat(joueur, true);

        assertEquals(0, joueur.points());
        assertEquals(2, joueur.nombreCoups());
    }

    @Test
    void achatEchouePourJoueurAvecPointsInsuffisants() {
        Joueur joueur = new Joueur("Joueur2");
        joueur.ajouterPoints(COUT_ACTION_SUPPLEMENTAIRE - 1);

        effectuerAchat(joueur, true);

        assertEquals(COUT_ACTION_SUPPLEMENTAIRE - 1, joueur.points());
        assertEquals(1, joueur.nombreCoups());
    }

    @Test
    void joueurQuitteBoutiqueSansAchat() {
        Joueur joueur = new Joueur("Joueur3");
        joueur.ajouterPoints(COUT_ACTION_SUPPLEMENTAIRE);

        effectuerAchat(joueur, false);

        assertEquals(COUT_ACTION_SUPPLEMENTAIRE, joueur.points());
        assertEquals(1, joueur.nombreCoups());
    }

    @Test
    void boutiqueSelectionneLeBonJoueur() {
        Joueur joueur1 = new Joueur("Joueur1");
        Joueur joueur2 = new Joueur("Joueur2");
        joueur1.ajouterPoints(COUT_ACTION_SUPPLEMENTAIRE);
        joueur2.ajouterPoints(COUT_ACTION_SUPPLEMENTAIRE);

        effectuerAchat(joueur1, true);

        assertEquals(0, joueur1.points());
        assertEquals(2, joueur1.nombreCoups());

        assertEquals(COUT_ACTION_SUPPLEMENTAIRE, joueur2.points());
        assertEquals(1, joueur2.nombreCoups());
    }

    private void effectuerAchat(Joueur joueur, boolean veutAcheter) {
        if (veutAcheter && joueur.points() >= COUT_ACTION_SUPPLEMENTAIRE) {
            joueur.ajouterPoints(-COUT_ACTION_SUPPLEMENTAIRE);
            joueur.changerNombreCoups(joueur.nombreCoups() + 1);
        }
    }
}