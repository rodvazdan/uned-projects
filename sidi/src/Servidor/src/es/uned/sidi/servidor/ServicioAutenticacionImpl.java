package es.uned.sidi.servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import es.uned.sidi.basededatos.Basededatos;
import es.uned.sidi.common.ServicioAutenticacionInterface;
import es.uned.sidi.common.ServicioDatosInterface;
import es.uned.sidi.common.User;
import es.uned.sidi.common.exceptions.InvalidCredentialsException;
import es.uned.sidi.common.exceptions.UserAlreadyExistsException;
import es.uned.sidi.common.exceptions.UserAlreadyLoggedException;
import es.uned.sidi.common.exceptions.UserDoesNotExistException;

/**
 * @author Daniel Vázquez Rodríguez
 *         dvazquez277@alumno.uned.es
 */
public class ServicioAutenticacionImpl extends UnicastRemoteObject implements ServicioAutenticacionInterface {
	
	private static int RMIPortNum = Registry.REGISTRY_PORT;
	private ServicioDatosInterface servicioDatos = null;
	
	public ServicioAutenticacionImpl() throws RemoteException {
		super();
		
		/*try {
			Registry registry = LocateRegistry.getRegistry(RMIPortNum);
			servicioDatos = (ServicioDatosInterface)registry.lookup("ServicioDatos");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}*/
	}

	/**
	 * @param newUser User's data (name, nick, password).
	 * @throws UserAlreadyExistsException if the user nick already exists
	 */
	@Override
	public void registerNewUser (User newUser) throws RemoteException, UserAlreadyExistsException {
		servicioDatos.registerNewUser(newUser);
	}
	
	/**
	 * 
	 * @param someUser User's data (nick, password).
	 * @throws RemoteException
	 * @throws InvalidCredentialsException
	 * @throws UserAlreadyLoggedException if the user is already logged to the system
	 * @throws UserDoesNotExistException
	 */
	@Override
	public void userLogin (User someUser) throws RemoteException, InvalidCredentialsException, UserAlreadyLoggedException, UserDoesNotExistException {
		servicioDatos.userLogin(someUser);
	}

}