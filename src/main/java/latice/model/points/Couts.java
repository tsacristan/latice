package latice.model.points;

public enum Couts {
	
	COUT_ACHAT_ACTION_SUPPLEMENTAIRE(2);
	
	private int valeur;

	Couts(int valeur) {
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
