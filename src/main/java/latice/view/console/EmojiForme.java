package latice.view.console;

public enum EmojiForme {
	PLUME("~"),
	OISEAU("V"),
	TORTUE("Q"),
	FLEUR("✿"),
	GECKO("S"),
	DAUPHIN("Ɔ"),
	CASE_LUNE("◐"),
	CASE_SOLEIL("✺"),
	CASE_VIDE("□"),
	CARACTERE_INCONNU("?");
	
	private String emoji;

	EmojiForme(String emoji) {
    	this.emoji = emoji;
	}

	public String emoji() {
		return emoji;
	}

	@Override
	public String toString() {
    	return emoji();
	}
}
