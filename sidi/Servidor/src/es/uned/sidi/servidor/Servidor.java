package es.uned.sidi.servidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

import es.uned.sidi.basededatos.Basededatos;
import es.uned.sidi.basededatos.ServicioDatosImpl;
import es.uned.sidi.common.ServicioAutenticacionInterface;
import es.uned.sidi.common.ServicioDatosInterface;
import es.uned.sidi.common.ServicioGestorInterface;
import es.uned.sidi.viewmanager.View;

/**
 * @author Daniel Vázquez Rodríguez
 */
public class Servidor {	
	
	public static boolean DEBUG_MODE = true;
	//private static Registry registry = null;
	private static int RMIPortNum = Registry.REGISTRY_PORT;
	private static String URLObjServAutenticacion = "rmi://localhost:" + RMIPortNum + "/ServicioAutenticacion";
	private static String URLObjServGestor = "rmi://localhost:" + RMIPortNum + "/ServicioGestor";
	//private static ServicioAutenticacionInterface servicioAutenticacion = new ServicioAutenticacionImpl();
	//private static ServicioGestorInterface servicioGestor = new ServicioGestorImpl();
	private static ServicioDatosInterface servicioDatos = new ServicioDatosImpl();
	
	public static void main (String[] args) throws IOException {
		InetAddress local = InetAddress.getLocalHost();
		String host = local.getHostAddress();
		
		if (DEBUG_MODE) System.out.println("[DEBUG_MODE] Server is using IP address: " + host);
		
		try {
			// Publish authentication service to RMI registry
			/*Remote exportedObj = UnicastRemoteObject.exportObject(servicioAutenticacion, 0);
			getRegistry().bind("ServicioAutenticacion", exportedObj);
			// Publish user management service to RMI registry
			exportedObj = UnicastRemoteObject.exportObject(servicioGestor, 0);
			getRegistry().bind("ServicioGestor", exportedObj);*/
			
			getRegistry(RMIPortNum);
			
			ServicioAutenticacionInterface servicioAutenticacion = new ServicioAutenticacionImpl();
			ServicioGestorInterface servicioGestor = new ServicioGestorImpl();
			
			Naming.rebind(URLObjServAutenticacion, servicioAutenticacion);
			Naming.rebind(URLObjServGestor, servicioGestor);
			
			listRegistry(URLObjServAutenticacion);
			listRegistry(URLObjServGestor);
			
			// Search for database service in RMI registry
			//servicioDatos = (ServicioDatosInterface)registry.lookup("ServicioDatos");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		
		/*if (DEBUG_MODE) {
			System.out.print("[DEBUG_MODE] Names bound in the registry:");
					
			if (registry.list().length == 0) {
				System.out.println(" None");
			} else {
				System.out.println();
				for (String name : registry.list()) {
					System.out.println("[DEBUG_MODE] \t - " + name);
				}
			}
		}*/
		
		if (DEBUG_MODE) System.out.println("[DEBUG_MODE] Server ready");
		
		//init();
		
		if (DEBUG_MODE) System.out.println("[DEBUG_MODE] Finished server");
		System.exit(0);
	}
	
	/*public static void main (String[] args) throws Exception {		
		ServicioAutenticacionInterface exportedObj = new ServicioAutenticacionImpl();
		
		// Start rmiregistry
		Registry registry = LocateRegistry.getRegistry(RMIPortNum);
		try {
			registry.list();
		} catch (RemoteException e) {
			if (DEBUG_MODE) System.out.println("[DEBUG_MODE] RMI Registry cannot be located at port " + RMIPortNum);
			registry = LocateRegistry.createRegistry(RMIPortNum);
			if (DEBUG_MODE) System.out.println("[DEBUG_MODE] RMI Registry created at port " + RMIPortNum);
		}
		
		exportedObj = (ServicioAutenticacionInterface)UnicastRemoteObject.exportObject(exportedObj, 0);
		
		String registryUrl = "rmi://localhost:" + RMIPortNum + "/Auth";
		Naming.rebind(registryUrl, exportedObj);
		
		if (DEBUG_MODE) {
			System.out.print("[DEBUG_MODE] Names bound in the registry:");
					
			if (registry.list().length == 0) {
				System.out.println(" None");
			} else {
				System.out.println();
				for (String name : registry.list()) {
					System.out.println("[DEBUG_MODE] \t - " + name);
				}
			}
		}

		System.out.println("Server ready. Press Enter to finish");
		System.in.read();
		
		Naming.unbind(registryUrl);
		
		System.out.println("Finished server");
	}*/
	
	/*private static Registry getRegistry() throws RemoteException {
		if (registry == null) {
			registry = LocateRegistry.getRegistry("localhost", RMIPortNum);
			try {
				registry.list();
			} catch (RemoteException e) {
				if (DEBUG_MODE) System.out.println("[DEBUG_MODE] RMI Registry cannot be located at port " + RMIPortNum);
				registry = LocateRegistry.createRegistry(RMIPortNum);
				if (DEBUG_MODE) System.out.println("[DEBUG_MODE] RMI Registry created at port " + RMIPortNum);
			}
		}
		
		return registry;
	}*/
	
	/*private static void printInformation (String[] names) {
		System.out.print("Servicios Disponibles:");
		
		if (names.length == 0) {
			System.out.println(" no hay servicios disponibles");
		} else {
			for (String name : names) {
				System.out.println("   - " + name);
			}
		}
	}*/
		
	private static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
	
	private static void init() throws RemoteException {
		boolean exitMenu = false;
		Scanner in = new Scanner(System.in);
		
		do {
			try {
				clear();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//View.displayMenu("Acceso para clientes:", new String[] {"Informacion del Servidor.", "Listar Usuarios Registrados.", "Listar Usuarios Logueados.", "Bloquear (banear) usuario.", "Desbloquear usuario.", "Salir"});
			//int opt = View.parseInput(in);
			
			View.displayMenu("Operaciones del Servidor", new String[] {"Informacion del Servidor.", "Listar Usuarios Registrados.", "Listar Usuarios Logueados.", "Bloquear (banear) usuario.", "Desbloquear usuario.", "Salir"});
			int opt = View.parseIntInput(in, "\nOpción > ");
			
			switch (opt) {
				case 1:	/* Información del servidor */
					//View.printInformation(getRegistry().list());
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 2:	/* Listar Usuarios Registrados */
					System.out.println(getRegisteredUsers());
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 3:	/* Listar Usuarios Logueados */
					try {
						List<String> users = servicioDatos.getLoggedUsers();
						for (String userNick : users) {
							System.out.println("    @" + userNick);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 4: /* Bloquear (banear) usuario */ case 5:	/* Desbloquear usuario */
					System.out.print("Error: operación no implementada");
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 6:	/* Salir */
					exitMenu = true;
					in.close();
					break;
				default:
					System.err.println("Opción incorrecta '" + opt + "'");
					System.err.print("Introduzca un valor entre 1 y 6");
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		} while (!exitMenu);
	}
	
	/*private static int displayMenu (Scanner in, String menuTitle, String[] menuOptions) {
		int opt = 0;
		
		System.out.println("\t" + menuTitle + "\n");
		
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println("\t" + (i + 1) + ". " + menuOptions[i]);
		}
		
		System.out.print("\nOpción: ");
		opt = in.nextInt();
		
		return opt;
	}*/
	
	public static String getRegisteredUsers() {
		StringBuilder sb = new StringBuilder();
		try {
			for (String nickname : servicioDatos.getRegisteredUsers()) {
				sb.append("    @" + nickname + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * This method starts an RMI record on the local machine, if it does not exist on the specified port number.
	 * @param RMIPortNumber the port number on which to create the RMI registry, if one already exists
	 * @throws RemoteException if the RMI registry is invalid on the given port number
	 */
	private static void getRegistry (int RMIPortNumber) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(RMIPortNumber);
			registry.list();
		} catch (RemoteException e) {	// registro no válido en este puerto
			System.err.println("El registro RMI no se puede localizar en el puerto " + RMIPortNumber);
			LocateRegistry.createRegistry(RMIPortNumber);
			System.out.println("Registro RMI creado en el puerto " + RMIPortNumber);
		}
	}
	
	/**
	 * This method lists the names registered with a Registry object.
	 * @param URLRegistry
	 * @throws RemoteException
	 * @throws MalformedURLException
	 */
	private static void listRegistry (String URLRegistry) throws RemoteException, MalformedURLException {
		System.out.println("Registro " + URLRegistry + " contiene:");
		String[] names = Naming.list(URLRegistry);
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}
}
