package es.uned.sidi.servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import es.uned.sidi.basededatos.Basededatos;
import es.uned.sidi.common.ServicioDatosInterface;
import es.uned.sidi.common.ServicioGestorInterface;

/**
 * @author Daniel Vázquez Rodríguez
 */
public class ServicioGestorImpl extends UnicastRemoteObject implements ServicioGestorInterface {

	private static int RMIPortNum = Registry.REGISTRY_PORT;
	private ServicioDatosInterface servicioDatos = null;
	
	public ServicioGestorImpl() throws RemoteException {
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
	 * Return a list of users registered in the system.
	 * @return list of registered users
	 * @throws RemoteException
	 */
	@Override
	public List<String> getRegisteredUsers() throws RemoteException {
		return servicioDatos.getRegisteredUsers();		
	}

}
