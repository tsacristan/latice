package latice.util.exception;

@SuppressWarnings("serial")
public class CouleurInvalideException extends Exception {
	public CouleurInvalideException(String message) {
		super(message);
	}
	
	public CouleurInvalideException() {
		super();
	}
}
