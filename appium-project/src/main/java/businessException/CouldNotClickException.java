package businessException;

public class CouldNotClickException extends RuntimeException {

	public CouldNotClickException(String message) {
		super(message);
	}
}
