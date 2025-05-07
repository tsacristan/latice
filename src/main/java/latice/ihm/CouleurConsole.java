package latice.ihm;

public enum CouleurConsole {
    ANSI_RESET("\u001B[0m"),
    ANSI_JAUNE("\u001B[43m"),
    ANSI_BLEU_MARINE("\u001B[44m"),
    ANSI_MAGENTA("\u001B[45m"),
    ANSI_ROUGE("\u001B[41m"),
    ANSI_VERT("\u001B[42m"),
    ANSI_BLEU_SARCELLE("\u001B[46m"),
	ANSI_COULEUR_TEXTE("\u001B[30m");

    private final String code;

    CouleurConsole(String code) {
        this.code = code;
    }

    public String codeANSI() {
        return code;
    }
}
