package latice.model;
import java.util.ArrayList;
import java.util.Collections;

public class LaticePile {
	private ArrayList<Tuile> pile;
	
	public ArrayList<Tuile> remplirPile() {
		pile = new ArrayList<Tuile>();
		for (Couleur couleur : Couleur.values()) {
			for (Forme forme : Forme.values()) {
				pile.add(new Tuile(couleur,forme));
				pile.add(new Tuile(couleur,forme));
				}
			}
		return pile;
	}
	
	public void melangerPile(){
		 Collections.shuffle(pile);
		 }
		
	@Override
	public String toString() {
		return pile.toString();
	}
}