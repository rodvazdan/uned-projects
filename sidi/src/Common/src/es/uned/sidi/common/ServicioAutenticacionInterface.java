package es.uned.sidi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.uned.sidi.common.exceptions.InvalidCredentialsException;
import es.uned.sidi.common.exceptions.UserAlreadyExistsException;
import es.uned.sidi.common.exceptions.UserAlreadyLoggedException;
import es.uned.sidi.common.exceptions.UserDoesNotExistException;

/**
 * @author Daniel Vázquez Rodríguez
 *         dvazquez277@alumno.uned.es
 */
public interface ServicioAutenticacionInterface extends Remote {
	
	/**
	 * @param newUser User's data (name, nick, password).
	 * @throws UserAlreadyExistsException if the user nick already exists
	 */
	public void registerNewUser (User newUser) throws RemoteException, UserAlreadyExistsException;
	
	/**
	 * 
	 * @param someUser User's data (nick, password).
	 * @throws RemoteException
	 * @throws InvalidCredentialsException
	 * @throws UserAlreadyLoggedException if the user is already logged to the system
	 * @throws UserDoesNotExistException
	 */
	public void userLogin (User someUser) throws RemoteException, InvalidCredentialsException, UserAlreadyLoggedException, UserDoesNotExistException;
	
}