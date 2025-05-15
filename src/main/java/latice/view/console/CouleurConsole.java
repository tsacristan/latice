package latice.view.console;

public enum CouleurConsole {
    ANSI_RESET("\u001B[0m"),
    ANSI_FOND_JAUNE("\u001B[43m"),
    ANSI_FOND_BLEU_MARINE("\u001B[44m"),
    ANSI_FOND_MAGENTA("\u001B[45m"),
    ANSI_FOND_ROUGE("\u001B[41m"),
    ANSI_FOND_VERT("\u001B[42m"),
    ANSI_FOND_BLEU_SARCELLE("\u001B[46m"),
	ANSI_TEXTE_NOIR("\u001B[30m"),
	ANSI_TEXTE_JAUNE("\u001B[33m");

    private final String code;

    CouleurConsole(String code) {
        this.code = code;
    }

    public String codeANSI() {
        return code;
    }
    
    @Override
    public String toString() {
    	return codeANSI();
    }
}
