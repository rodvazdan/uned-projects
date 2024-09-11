package es.uned.sidi.common.exceptions;

/**
 * @author Daniel Vázquez Rodríguez
 *         dvazquez277@alumno.uned.es
 */
public class UserDoesNotExistException extends Exception {

	private static final long serialVersionUID = -1231858072310801872L;
	
	public UserDoesNotExistException (String errorMessage) {
		super(errorMessage);
	}

}