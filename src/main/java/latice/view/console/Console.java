package latice.view.console;

public class Console {

	public static final String LIGNE_HORIZONTALE = "--------------------------------------------";

	public static void message(String texte) {
		System.out.println(texte);
	}

	public static void message() {
		System.out.println();
	}

	public static void titre(String texte) {
		message(LIGNE_HORIZONTALE);
		message(texte);
		message(LIGNE_HORIZONTALE);
	}

	public static void messageAligne(String texte) {
		System.out.print(texte);
	}

}
