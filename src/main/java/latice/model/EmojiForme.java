package latice.model;

public enum EmojiForme {
	PLUME("\uD83E\uDEB6"),
	OISEAU("\uD83D\uDC26"),
	TORTUE("\uD83D\uDC22"),
	FLEUR("\uD83C\uDF3C"),
	GECKO("\uD83E\uDD8E"),
	DAUPHIN("\uD83D\uDC2C");
	
	private String emoji;

	EmojiForme(String emoji) {
    	this.emoji = emoji;
	}

	public String Emoji() {
		return emoji;
	}

	@Override
	public String toString() {
    	return emoji + " (" + name() + ")";
	}
}
