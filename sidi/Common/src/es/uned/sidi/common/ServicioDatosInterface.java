package es.uned.sidi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.uned.sidi.common.exceptions.InvalidCredentialsException;
import es.uned.sidi.common.exceptions.UserAlreadyExistsException;
import es.uned.sidi.common.exceptions.UserAlreadyLoggedException;
import es.uned.sidi.common.exceptions.UserDoesNotExistException;

/**
 * @author Daniel Vázquez Rodríguez
 */
public interface ServicioDatosInterface extends Remote {
	//boolean checkLogin (String nick, String password) throws RemoteException;
	//boolean logout (String nick) throws RemoteException;
	
	
	public void listTrinos() throws RemoteException;
	
	/**
	 * @param newUser User's data (name, nick, password).
	 * @throws RemoteException
	 * @throws UserAlreadyExistsException if the user nick already exists
	 */
	public void registerNewUser (User newUser) throws RemoteException, UserAlreadyExistsException;
	
	/**
	 * 
	 * @param someUser User's data (nick, password).
	 * @throws RemoteException
	 * @throws UserAlreadyLoggedException if the user is already logged to the system
	 * @throws UserDoesNotExistException
	 * @throws InvalidCredentialsException
	 */
	public void userLogin (User someUser) throws RemoteException, UserAlreadyLoggedException, UserDoesNotExistException, InvalidCredentialsException;
	
	/**
	 * Return a list of users registered in the system.
	 * @return list of registered users
	 * @throws RemoteException
	 */
	public List<String> getRegisteredUsers() throws RemoteException;
	
	/**
	 * @return list of registered users
	 * @throws RemoteException
	 * @throws InvalidCredentialsException
	 * @throws UserAlreadyLoggedException
	 */
	public List<String> getLoggedUsers() throws RemoteException, InvalidCredentialsException, UserAlreadyLoggedException;
	
}
