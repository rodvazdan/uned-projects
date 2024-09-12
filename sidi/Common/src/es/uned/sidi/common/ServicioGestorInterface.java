package es.uned.sidi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Daniel Vázquez Rodríguez
 */
public interface ServicioGestorInterface extends Remote {
	
	/**
	 * Return a list of users registered in the system.
	 * @return list of registered users
	 * @throws RemoteException
	 */
	public List<String> getRegisteredUsers() throws RemoteException;
	
}
