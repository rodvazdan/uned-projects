package es.uned.sidi.basededatos;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

import es.uned.sidi.common.ServicioDatosInterface;
import es.uned.sidi.viewmanager.View;

/**
 * @author Daniel Vázquez Rodríguez
 */
public class Basededatos {
	
	public static boolean DEBUG_MODE = true;
	private static Registry registry = null;
	private static int RMIPortNum = Registry.REGISTRY_PORT;
	private static String URLObjServDatos = "rmi://localhost:" + RMIPortNum + "/ServicioDatos";
	private static ServicioDatosInterface servicioDatos = new ServicioDatosImpl();
	
	public static void main (String[] args) throws Exception {
		InetAddress local = InetAddress.getLocalHost();
		String host = local.getHostAddress();
		
		if (DEBUG_MODE) System.out.println("[DEBUG_MODE] Database is using IP address: " + host);
		
		try {
			// Publish database service to RMI registry
			Remote exportedObj = UnicastRemoteObject.exportObject(servicioDatos, 0);
			getRegistry().bind("ServicioDatos", exportedObj);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
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
		
		if (DEBUG_MODE) System.out.println("[DEBUG_MODE] Database ready");
		
		init();
		
		if (DEBUG_MODE) System.out.println("[DEBUG_MODE] Finished database");
		System.exit(0);
	}
	
	public static String getUrl() {
		return URLObjServDatos;
	}
	
	private static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
	
	/*private static void printInformation (String[] names) {
		try {
			clear();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		View.displayMenuTitle("Servicios Disponibles:");
		
		if (names.length == 0) {
			System.out.println(" no hay servicios disponibles");
		} else {
			for (String name : names) {
				System.out.println("    - " + name);
			}
		}
	}*/
	
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
			
			//int opt = displayMenu(in, "Acceso para clientes:", new String[] {"Informacion de la Base de datos.", "Listar Trinos.", "Salir."});
			View.displayMenu("Operaciones de la Base de datos", new String[] {"Informacion de la Base de datos.", "Listar Trinos.", "Salir."});
			int opt = View.parseIntInput(in, "\nOpción > ");
			switch (opt) {
				case 1:	/* Informacion de la Base de datos */
					View.printInformation(getRegistry().list());
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 2:	/* Listar Trinos */
					System.err.print("Error: operación no implementada");
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 3:	/* Salir */
					exitMenu = true;
					in.close();
					break;
				default:
					System.err.println("Opción incorrecta '" + opt + "'");
					System.err.println("Introduzca un valor entre 1 y 3");
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		} while (!exitMenu);
	}
	
	private static Registry getRegistry() throws RemoteException {
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
	}
}
