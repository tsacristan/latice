package latice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.model.player.Joueur;
import latice.util.exception.AucuneCouleurOuFormeCorrespondantException;
import latice.util.exception.AucuneTuileAdjacenteException;
import latice.util.exception.PiocheInvalideException;
import latice.util.exception.PlacementDejaExistantInvalide;
import latice.util.exception.RackIndexInvalideException;
import latice.util.exception.RackInvalideException;
import latice.view.Textes;
import latice.view.TextesErreurs;
import latice.view.console.LaticeVueConsole;

public class ControllerJouerConsole extends ControllerJouer {

    private LaticeVueConsole laticeVue;
    private Joueur joueurCourant;
    private int tourCourant;
    private Joueur premierJoueur;
    
    public ControllerJouerConsole(LaticeVueConsole laticeVue) {
        super();
        this.laticeVue = laticeVue;
        random = new Random();
        tourCourant = 1;
    }

    @Override
    public void jouer(List<Joueur> joueurs) {
        this.joueurs = (ArrayList<Joueur>) joueurs;
        premierJoueur = random.nextBoolean() ? joueurs.get(0) : joueurs.get(1);
        joueurCourant = premierJoueur;

        while (tourCourant <= LaticeController.TOURS_MAX) {
            jouerTour();
            if (!joueurCourant.equals(premierJoueur)) tourCourant++;
            joueurCourant = prochainJoueur();
            try {
                joueurCourant.remplirRack();
            } catch (PiocheInvalideException e) {
                laticeVue.afficherErreur(TextesErreurs.ERREUR_PILE_VIDE.texte());
                break;
            }
        }
        annoncerLeGagnant();
    }

    private void jouerTour() {
        laticeVue.afficherTour(joueurs, joueurCourant, tourCourant);
        laticeVue.afficherPlateau(plateau);
        laticeVue.afficherRack(joueurCourant, null, null);

        boolean actionValide = false;
        while (!actionValide) {
            int action = laticeVue.demanderActionTour(joueurCourant);
            switch (action) {
                case 1:
                    actionValide = jouerTuile();
                    break;
                case 2:
                    laticeVue.afficherMessage(Textes.TOUR_PASSE.texte());
                    actionValide = true;
                    break;
                case 3:
                    actionValide = echangerTuiles();
                    break;
                default:
                    laticeVue.afficherErreur(TextesErreurs.ACTION_INVALIDE.texte());
            }
        }
    }

    private boolean jouerTuile() {
        boolean emplacementValide = false;
        while (!emplacementValide) {
            int emplacementRack = laticeVue.demanderTuileAPoser(joueurCourant) - 1;
            Coordonnees emplacementPlateau = laticeVue.choisirEmplacementPlateau();
            try {
                plateau.placerLaTuileSurLePlateau(emplacementRack, emplacementPlateau, joueurCourant.rackJoueur());
                compterEtAttribuerPoints(emplacementPlateau, joueurCourant, plateau.obtenirTuile(emplacementPlateau).typeCase() == TypeCase.CASE_SOLEIL);
                laticeVue.afficherScores(joueurs, joueurCourant);
                emplacementValide = true;
            } catch (RackInvalideException e) {
                laticeVue.afficherErreur(TextesErreurs.RACK_VIDE.toString());
            } catch (RackIndexInvalideException e) {
                laticeVue.afficherErreur(TextesErreurs.INDICE_RACK_INVALIDE.toString());
            } catch (PlacementDejaExistantInvalide e) {
                laticeVue.afficherErreur(TextesErreurs.CASE_NON_VIDE.toString());
            } catch (AucuneTuileAdjacenteException e) {
                laticeVue.afficherErreur(TextesErreurs.TUILE_ISOLEE.toString());
            } catch (AucuneCouleurOuFormeCorrespondantException e) {
                laticeVue.afficherErreur(TextesErreurs.TUILE_NI_COULEUR_NI_FORME.toString());
            }
        }
        return true;
    }

    private boolean echangerTuiles() {
        try {
            List<Integer> indices = laticeVue.demanderIndicesATechanger(joueurCourant);
            joueurCourant.rackJoueur().echangerTuiles(indices, joueurCourant.pileJoueur());
            laticeVue.afficherMessage(Textes.TUILES_ECHANGEES.texte());
            return true;
        } catch (PiocheInvalideException e) {
            laticeVue.afficherErreur(TextesErreurs.ERREUR_PILE_VIDE.texte());
        } catch (Exception e) {
            laticeVue.afficherErreur(TextesErreurs.ACTION_INVALIDE.texte());
        }
        return false;
    }

	 // TODO Corriger système de points : fonctionne pas correctement, j'ai ajouté des prints pour débug
    private void compterEtAttribuerPoints(Coordonnees coordonneesTuile, Joueur joueur, boolean estUnSoleil) {
        Tuile tuilePlacee = plateau.obtenirTuile(coordonneesTuile).tuile();
        if (tuilePlacee == null) return;

        int gaucheCouleur = compterLignes(coordonneesTuile, -1, 0, tuilePlacee, true);
        int droiteCouleur = compterLignes(coordonneesTuile, 1, 0, tuilePlacee, true);
        int horizCouleur = 1 + gaucheCouleur + droiteCouleur;

        int gaucheForme = compterLignes(coordonneesTuile, -1, 0, tuilePlacee, false);
        int droiteForme = compterLignes(coordonneesTuile, 1, 0, tuilePlacee, false);
        int horizForme = 1 + gaucheForme + droiteForme;

        int maxHoriz = Math.max(horizCouleur, horizForme);

        int hautCouleur = compterLignes(coordonneesTuile, 0, -1, tuilePlacee, true);
        int basCouleur = compterLignes(coordonneesTuile, 0, 1, tuilePlacee, true);
        int vertCouleur = 1 + hautCouleur + basCouleur;

        int hautForme = compterLignes(coordonneesTuile, 0, -1, tuilePlacee, false);
        int basForme = compterLignes(coordonneesTuile, 0, 1, tuilePlacee, false);
        int vertForme = 1 + hautForme + basForme;

        int maxVert = Math.max(vertCouleur, vertForme);

        int points = 0;
        if (maxHoriz > 1) points += maxHoriz;
        if (maxVert > 1) points += maxVert;
        if (maxHoriz > 1 && maxVert > 1) points -= 1;

        points = Math.min(points, 4);

        if (estUnSoleil) {
            points += 2;
            System.out.println("  Bonus case soleil : +2");
        }

        TypeCase typeCasePlacee = plateau.obtenirTuile(coordonneesTuile).typeCase();
        System.out.println("[DEBUG] TypeCase à " + coordonneesTuile + " : " + typeCasePlacee);

        System.out.println("Points attribués : " + points);
        joueur.ajouterScore(points);
        System.out.println("== [DEBUG] " + joueur.pseudo() + " nouveau total : " + joueur.score() + " ==");

        if (finDeJeu()) {
            annoncerLeGagnant();
        }
    }

    private int compterLignes(Coordonnees coordDepart, int dx, int dy, Tuile tuileReference, boolean parCouleur) {
        int compteur = 0;
        int colonne = coordDepart.colonne() + dx;
        int ligne = coordDepart.ligne() + dy;
        while (colonne >= 0 && colonne < Plateau.COLONNES && ligne >= 0 && ligne < Plateau.LIGNES) {
            Tuile tuileCourante = plateau.grille()[ligne][colonne].tuile();
            if (tuileCourante == null) break;
            boolean correspond;
            if (parCouleur) {
                correspond = tuileCourante.couleur() == tuileReference.couleur();
            } else {
                correspond = tuileCourante.forme() == tuileReference.forme();
            }
            if (correspond) {
                compteur++;
                colonne += dx;
                ligne += dy;
            } else {
                break;
            }
        }
        return compteur;
    }

    private boolean finDeJeu() {
        return tourCourant >= LaticeController.TOURS_MAX;
    }

    private Joueur prochainJoueur() {
        return joueurCourant.equals(joueurs.get(1)) ? joueurs.get(0) : joueurs.get(1);
    }

    private void annoncerLeGagnant() {
        int maxScore = joueurs.stream().mapToInt(Joueur::score).max().orElse(0);
        List<Joueur> winners = new ArrayList<>();
        for (Joueur j : joueurs) {
            if (j.score() == maxScore) winners.add(j);
        }
        if (winners.size() == 1) {
            laticeVue.afficherMessage(String.format(Textes.GAGNANT.texte(), winners.get(0).pseudo(), maxScore));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(Textes.EGALITE.texte());
            for (Joueur j : winners) sb.append(j.pseudo()).append(" ");
            sb.append(String.format(Textes.POINTS_EGALITE.texte(), maxScore));
            laticeVue.afficherMessage(sb.toString());
        }
    }

    public void initialiserValeurs(Plateau plateau, LaticeVueConsole laticeVue) {
        this.plateau = plateau;
        this.laticeVue = laticeVue;
    }
}