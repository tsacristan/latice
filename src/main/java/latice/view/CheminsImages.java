package latice.view;

public enum CheminsImages {
	
	FOND_OCEAN("bg_sea.png"),
	FOND_LUNE("bg_moon.png"),
	FOND_SOLEIL("bg_sun.png"),
	
	OISEAU("bird"),
	DAUPHIN("dolphin"),
	PLUME("feather"),
	FLEUR("flower"),
	GECKO("gecko"),
	TORTUE("turtle"),
	
	JAUNE("y"),
    BLEU_MARINE("n"),
    MAGENTA("m"),
    ROUGE("r"),
    VERT("g"),
    BLEU_SARCELLE("t");
	
	String chemin;
	
	CheminsImages(String chemin) {
		this.chemin = chemin;
	}
	
	public String chemin() {
		return chemin;
	}
	
	@Override
	public String toString() {
		return chemin();
	}

}
