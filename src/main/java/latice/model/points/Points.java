package latice.model.points;

public enum Points {
	
	SUR_CASE_SOLEIL(2),
	DOUBLE(1),
	TRIPLE(2),
	LATICE(4),
	VALEUR_DEFAUT(0);
	
	private int valeur;

	Points(int valeur) {
    	this.valeur = valeur;
	}

	public int valeur() {
		return valeur;
	}

	@Override
	public String toString() {
    	return Integer.toString(valeur());
	}

}
