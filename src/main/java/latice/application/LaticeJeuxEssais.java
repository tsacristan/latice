package latice.application;

import latice.model.LaticePile;

public class LaticeJeuxEssais {

	public static void main(String[] args) {
		
		LaticePile pile = new LaticePile();
		pile.remplirPile();
		pile.melangerPile();
		System.out.println(pile);
	}

}
