package es.uned.sidi.common.exceptions;

/**
 * @author Daniel Vázquez Rodríguez
 */
public class UserAlreadyLoggedException extends Exception {

	private static final long serialVersionUID = -3201173884629885300L;
	
	public UserAlreadyLoggedException (String errorMessage) {
		super(errorMessage);
	}

}
