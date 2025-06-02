package latice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import latice.model.board.Coordonnees;
import latice.model.board.Plateau;
import latice.model.material.Tuile;
import latice.model.material.TypeCase;
import latice.model.player.Joueur;
import latice.view.TextesErreurs;
import latice.view.gui.InterfaceCase;
import latice.view.gui.InterfacePlateau;
import latice.view.gui.LaticeVueGraphique;
import latice.view.gui.PartieControle;

public class ControllerJouerGraphique extends ControllerJouer {
	
	private LaticeVueGraphique laticeVue;
	private Joueur joueurCourant;
	private int tourCourant;
	private Joueur premierJoueur;
	private boolean tuilePlaceDansCeTour = false;
	
	public ControllerJouerGraphique(LaticeVueGraphique laticeVue) {
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
		
		jouerTour();
	}
	
	 public void validerTour() {
		 if (!tuilePlaceDansCeTour) {
		        laticeVue.afficherErreur(TextesErreurs.VALIDATION_SANS_ACTION.texte());
		        return;
		    }
		    avancerTour();
	    }
	 
	 public void passerTour() {
		 avancerTour();
	 }
	
	 public void avancerTour() {
		 if (!joueurCourant.equals(premierJoueur)) tourCourant++;
		    joueurCourant = prochainJoueur();
		    tuilePlaceDansCeTour = false;
		    jouerTour();
	    }

	public void jouerTuile() {
		PartieControle nouvellePartie = laticeVue.interfaceJeu().initialiserPartieControle(joueurCourant, this::validerTour, this::passerTour);
		
		laticeVue.interfaceJeu().setBottom(nouvellePartie);
		laticeVue.interfaceJeu().interfacePlateau().actualiserRack(joueurCourant.rackJoueur());
		appliquerDnDToutesCases();
		
	}
	
	private void jouerTour() {
		
		laticeVue.afficherPlateau(plateau);
		laticeVue.afficherRack(joueurCourant, this::validerTour, this::passerTour);
		laticeVue.afficherTour(joueurs, joueurCourant, tourCourant);
		
		if (tourCourant >= 10) {
			laticeVue.afficherErreur("Partie finie");
			annoncerLeGagnant();
			return;
		}
		
		jouerTuile();
	}
	
	private Joueur prochainJoueur() {
		return joueurCourant.equals(joueurs.get(1)) ? joueurs.get(0) : joueurs.get(1);
	}
	
	private void appliquerDnDToutesCases() {
		InterfacePlateau interfacePlateau = laticeVue.interfaceJeu().interfacePlateau();
		
		for (Node node : interfacePlateau.getChildren()) {
		    Integer ligne = GridPane.getRowIndex(node);
		    Integer colonne = GridPane.getColumnIndex(node);

		    int ligneNonNull = ligne != null ? ligne : 0;
		    int colonneNonNull = colonne != null ? colonne : 0;
		    
		    if (node instanceof InterfaceCase) {
		    	InterfaceCase caseVue = (InterfaceCase) node;
		    	appliquerDnD(caseVue, colonneNonNull, ligneNonNull);
		    }
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
	        return tourCourant >= 10;
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
	
	private void appliquerDnD(InterfaceCase interfaceCase, int colonne, int ligne) {
		interfaceCase.setOnDragOver(event -> {
            if (event.getGestureSource() != interfaceCase && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

		interfaceCase.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                try {
                    int indexRack = Integer.parseInt(db.getString());
                    System.out.println("[DnD] Placement à (colonne : " + colonne + ", ligne : " + ligne + ")");
                    Coordonnees coordonneesTuile = new Coordonnees(colonne, ligne);
                    System.out.println("[DnD] Coordonnées : " + coordonneesTuile);

                    TypeCase typeCaseAvant = plateau.obtenirTuile(coordonneesTuile).typeCase();
                    boolean estUnSoleil = typeCaseAvant == TypeCase.CASE_SOLEIL;
                    System.out.println("[DEBUG] TypeCase avant placement : " + typeCaseAvant);

                    plateau.placerLaTuileSurLePlateau(indexRack, new Coordonnees(colonne, ligne), joueurCourant.rackJoueur());
                    compterEtAttribuerPoints(coordonneesTuile, joueurCourant, estUnSoleil);

                    laticeVue.actualiserScores(joueurs, joueurCourant);
                    
                    laticeVue.afficherRack(joueurCourant, this::validerTour, this::passerTour);

                    tuilePlaceDansCeTour = true;
                    success = true;

                } catch (Exception e) {
                    laticeVue.afficherErreur(e.getMessage());
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });
	}
	
	private void annoncerLeGagnant() {
	    int maxScore = joueurs.stream().mapToInt(Joueur::score).max().orElse(0);
	    List<Joueur> winners = new ArrayList<>();
	    for (Joueur j : joueurs) {
	        if (j.score() == maxScore) winners.add(j);
	    }
	    StringBuilder sb = new StringBuilder();
	    if (winners.size() == 1) {
	        sb.append("Le gagnant est : ").append(winners.get(0).pseudo()).append(" avec ").append(maxScore).append(" points !");
	    } else {
	        sb.append("Égalité ! Gagnants : ");
	        for (Joueur j : winners) sb.append(j.pseudo()).append(" ");
	        sb.append("avec ").append(maxScore).append(" points !");
	    }
	    javafx.application.Platform.runLater(() -> {
	        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
	        alert.setTitle("Fin de la partie");
	        alert.setHeaderText("Résultat");
	        alert.setContentText(sb.toString());
	        alert.showAndWait();
	    });
	}
}
