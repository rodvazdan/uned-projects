package es.uned.sidi.basededatos;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.uned.sidi.common.ServicioDatosInterface;
import es.uned.sidi.common.Trino;
import es.uned.sidi.common.User;
import es.uned.sidi.common.exceptions.InvalidCredentialsException;
import es.uned.sidi.common.exceptions.UserAlreadyExistsException;
import es.uned.sidi.common.exceptions.UserAlreadyLoggedException;
import es.uned.sidi.common.exceptions.UserDoesNotExistException;

/**
 * @author Daniel Vázquez Rodríguez
 *         dvazquez277@alumno.uned.es
 */
public class ServicioDatosImpl implements ServicioDatosInterface {
	
	//private static HashMap<User, List<Trino>> users = new HashMap<>();
	private static HashMap<String, User> users = new HashMap<>();
	private static HashMap<User, List<Trino>> trinos = new HashMap<>();
	
	/* Default constructor */
	public ServicioDatosImpl() {}
	
	/**
	 * Muestra el historial de trinos, concretamente el nick del propietario y el timestamp.
	 */
	@Override
	public void listTrinos() {
		/*for (List<Trino> trinos : users.values()) {
			for (int i = 0; i < trinos.size(); i++) {
				System.out.println(trinos.get(i).GetNickPropietario() + " at " + trinos.get(i).GetTimestamp());
			}
		}*/
		
		/*List<String> listatrinos = new ArrayList<>();
		
		for (List<Trino> trinos : users.values()) {
			StringBuffer sb = new StringBuffer();
			for (Trino trino : trinos) {
				sb.append(trino.GetNickPropietario() + ":" + trino.GetTimestamp() + "\n");
			}
			listatrinos.add(sb.toString());
		}
		
		return listatrinos;*/
		
		for (List<Trino> listatrinos : trinos.values()) {
			for (Trino trino : listatrinos) {
				System.out.println(trino.GetNickPropietario() + " at " + trino.GetTimestamp());
			}
		}
	}
	
	/**
	 * @param newUser User's data (name, nick, password).
	 * @throws RemoteException
	 * @throws UserAlreadyExistsException if the user nick already exists
	 */
	@Override
	public void registerNewUser (User newUser) throws UserAlreadyExistsException {
		/*for (User user : users.keySet()) {
			if (user.getNick().equals(newUser.getNick())) {
				throw new UserAlreadyExistsException("El usuario ya existe");
			}
		}
		
		users.put(newUser, null);*/
		
		if (users.containsKey(newUser.getNick())) {
			throw new UserAlreadyExistsException("Error: el usuario '" + newUser.getNick() + "' ya existe");
		}
		 
		users.put(newUser.getNick(), newUser);
		trinos.put(newUser, null);
	}
	
	/**
	 * @return list of registered users
	 * @throws RemoteException
	 */
	@Override
	public List<String> getRegisteredUsers() {
		/*List<String> registeredUsers = new ArrayList<>();
		
		for (User user : users.keySet()) {
			registeredUsers.add(user.getNick());
		}
		
		return registeredUsers;*/

		return new ArrayList<>(users.keySet());
	}
	
	/**
	 * Return a list of users registered in the system.
	 * @return list of registered users
	 * @throws RemoteException
	 */
	@Override
	public List<String> getLoggedUsers() {
		/*List<String> loggedUsers = new ArrayList<>();
		
		for (User user : users.keySet()) {
			if (user.isOnline()) loggedUsers.add(user.getNick());
		}
		
		return loggedUsers;*/
		
		List<String> loggedUsers = new ArrayList<>();
		
		for (User user : users.values()) {
			if (user.isOnline()) loggedUsers.add(user.getNick());
		}
				
		return loggedUsers;
	}

	/**
	 * @param someUser User's data (nick, password).
	 * @throws RemoteException
	 * @throws UserAlreadyLogged if the user is already logged to the system
	 * @throws UserDoesNotExistException
	 * @throws InvalidCredentials
	 */
	@Override
	public void userLogin (User someUser) throws RemoteException, InvalidCredentialsException, UserAlreadyLoggedException, UserDoesNotExistException {
		/*if (users.containsKey(someUser)) {	// Check if the user is registered in the system.
			if (someUser.isOnline()) {	// Check if the user is already logged in
				throw new UserAlreadyLoggedException("El usuario " + someUser.getNick() + " ya está logueado en el sistema"); 
			} else {
				for (User user : users.keySet()) {	// Check for correct user credentials
					if (user.getNick().equals(someUser.getNick()) && user.getPassword().equals(someUser.getPassword())) {
						user.isOnline(true);
					} else throw new InvalidCredentialsException("Las credenciales de usuario no son correctas");
				}
			}
		} else throw new UserDoesNotExistException("El usuario " + someUser.getNick() + " no está registrado en el sistema");*/
		
		
		if (users.containsKey(someUser.getNick())) {	// Check if the user is registered in the system.
			if (users.get(someUser.getNick()).isOnline()) {	// Check if the user is already logged in
				throw new UserAlreadyLoggedException("Error: usuario '" + someUser.getNick() + "' ya ha iniciado sesión en el sistema");
			} else {
				for (User u : users.values()) {	// Check for correct user credentials
					if (u.equals(someUser)) {
						u.isOnline(true);
						break;
					}
				}
				
				if (!(users.get(someUser.getNick()).isOnline())) throw new InvalidCredentialsException("Error: contraseña incorrecta");
			}
		} else throw new UserDoesNotExistException("Error: usuario '" + someUser.getNick() + "' no existe");
	}

	/*@Override
	public boolean checkLogin (String nick, String password) throws RemoteException {
		return false;
	}

	@Override
	public String getUsers() throws RemoteException {
		return null;
	}

	@Override
	public String getUsersOnline() throws RemoteException {
		return null;
	}

	@Override
	public boolean logout (String nick) throws RemoteException {
		return false;
	}*/
}