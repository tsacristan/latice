package latice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import latice.model.board.Plateau;
import latice.model.player.Joueur;

public abstract class ControllerJouer {
	
	protected Plateau plateau;
	protected Random random;
	protected ArrayList<Joueur> joueurs;

	public abstract void jouer(List<Joueur> joueurs);
	
	public void initialiserPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
		
}
