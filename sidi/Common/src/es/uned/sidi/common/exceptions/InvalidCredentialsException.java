package es.uned.sidi.common.exceptions;

/**
 * @author Daniel Vázquez Rodríguez
 */
public class InvalidCredentialsException extends Exception {

	private static final long serialVersionUID = -2784704418970719821L;

	public InvalidCredentialsException (String errorMessage) {
		super(errorMessage);
	}
	
}
