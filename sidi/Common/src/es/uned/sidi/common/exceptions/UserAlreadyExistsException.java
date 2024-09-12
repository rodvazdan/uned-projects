package es.uned.sidi.common.exceptions;

/**
 * @author Daniel Vázquez Rodríguez
 */
public class UserAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1602230672959423812L;

	public UserAlreadyExistsException (String errorMessage) {
		super(errorMessage);
	}
	
}
