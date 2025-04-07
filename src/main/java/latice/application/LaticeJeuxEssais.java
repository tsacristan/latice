package latice.application;

import latice.model.LaticePile;

public class LaticeJeuxEssais {

	public static void main(String[] args) {
		LaticePile pile = new LaticePile();
		pile.remplirPile();
		pile.melangerPile();
		System.out.println(pile);
		System.out.println(pile.size());

		LaticePile pile1 = new LaticePile();
		LaticePile pile2 = new LaticePile();
		pile.distribuer(new LaticePile[]{pile1, pile2});
		System.out.println(pile1);
		System.out.println(pile2);

	}

}
