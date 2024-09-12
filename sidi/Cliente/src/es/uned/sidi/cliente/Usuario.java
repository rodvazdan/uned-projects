package es.uned.sidi.cliente;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import es.uned.sidi.common.ServicioAutenticacionInterface;
import es.uned.sidi.common.ServicioGestorInterface;
import es.uned.sidi.common.User;
import es.uned.sidi.common.exceptions.InvalidCredentialsException;
import es.uned.sidi.common.exceptions.UserAlreadyExistsException;
import es.uned.sidi.common.exceptions.UserAlreadyLoggedException;
import es.uned.sidi.common.exceptions.UserDoesNotExistException;
import es.uned.sidi.viewmanager.View;

/**
 * @author Daniel Vázquez Rodríguez
 */
public class Usuario {
	public static boolean DEBUG_MODE = true;
	private static int RMIPortNum = Registry.REGISTRY_PORT;
	
	private static ServicioAutenticacionInterface servicioAutenticacion = null;
	private static ServicioGestorInterface servicioGestor = null;
	
	public static void main (String[] args) throws Exception {
		/*InetAddress local = InetAddress.getLocalHost();
		String host = local.getHostAddress();
		
		if (DEBUG_MODE) System.out.println("[DEBUG_MODE] Client is using IP address: " + host);*/
		
		/*try {
			// Start rmiregistry
			Registry registry = LocateRegistry.getRegistry(RMIPortNum);
			try {
				registry.list();
			} catch (RemoteException e) {
				if (DEBUG_MODE) System.err.println("[DEBUG_MODE] RMI Registry cannot be located at port " + RMIPortNum);
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
			
			servicioAutenticacion = (ServicioAutenticacionInterface)registry.lookup("rmi://localhost/" + RMIPortNum + "/ServicioAutenticacion");
			servicioGestor = (ServicioGestorInterface)registry.lookup("rmi://localhost/" + RMIPortNum + "/ServicioGestor");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}*/
		
		try {
			servicioAutenticacion = (ServicioAutenticacionInterface)Naming.lookup("//localhost:" + RMIPortNum + "/ServicioAutenticacion");
			servicioGestor = (ServicioGestorInterface)Naming.lookup("//localhost:" + RMIPortNum + "/ServicioGestor");
		} catch (NotBoundException e) {
			System.err.println("NotBoundException: " + e.getMessage());
			System.exit(-1);
		} catch (AccessException e) {
			System.err.println("AccessException: " + e.getMessage());
			System.exit(-1);
		} catch (RemoteException e) {
			System.err.println("RemoteException: " + e.getMessage());
			System.exit(-1);
		} catch (MalformedURLException e) {
			System.err.println("MalformedURLException: " + e.getMessage());
			System.exit(-1);
		}
		
		startMenu();
		
		System.exit(0);
	}
	
	private static void startMenu() {
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
			//View.displayMenu("Acceso para clientes:", new String[] {"Registrar un nuevo usuario.", "Hacer login.", "Salir."});
			//int opt = View.parseInput(in);
			View.displayMenu("Acceso para clientes:", new String[] {"Registrar un nuevo usuario.", "Hacer login.", "Salir."});
			int opt = View.parseIntInput(in, "\nOpción > ");
			switch (opt) {
				case 1:	/* Registrar un nuevo usuario */
					registerNewUser(in);
					break;
				case 2:	/* Hacer login */
					userLogin(in);
					break;
				case 3:	/* Salir */
					exitMenu = true;
					in.close();
					break;
				default:
					System.err.println("Opción incorrecta '" + opt + "'");
					System.err.print("Introduzca un valor entre 1 y 3");
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
	
	private static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
	
	private static void registerNewUser (Scanner in) {
		/*String name, nick, password;
		
		in.nextLine();
		System.out.println("Registro de usuarios");
		System.out.println("--------------------");
		
		System.out.print("\nInserta un nombre: ");  name = in.nextLine();
		System.out.print("\nInserta un nick (apodo): ");  nick = in.nextLine();
		System.out.print("\nInserta una contraseña: ");  password = in.nextLine();
		
		User data = new User(name, nick, password);*/
		
		String name, nick, password;
		/*try {
			clear();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		in.nextLine();
		View.displayMenuTitle("Registro de Usuarios");
		name = View.parseStringInput(in, "    - Inserta un nombre: ");
		nick = View.parseStringInput(in, "    - Inserta un nick (apodo): ");
		password = View.parseStringInput(in, "    - Inserta una contraseña: ");
		
		User data = new User(name, nick, password);
		
		try {
			servicioAutenticacion.registerNewUser(data);
			//if (DEBUG_MODE) System.out.println("[DEBUG] Usuario creado (" + name + ", " + nick + ", " + password + ")");
			System.out.print("\nInfo: usuario '" + nick + "' registrado con éxito");
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (UserAlreadyExistsException e) {
			System.err.print("\n" + e.getMessage());
		}
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*public static void displayMenu (String menuTitle, String[] menuOptions) {		
		displayMenuTitle(menuTitle);
		
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println("    " + (i + 1) + ". " + menuOptions[i]);
		}
	}
	
	public static String parseStringInput (Scanner in, String textField) {
		System.out.print(textField);
		String opt = in.nextLine();
		return opt;
	}
	
	public static int parseIntInput (Scanner in, String textField) {
		System.out.print(textField);
		int opt = in.nextInt();
		return opt;
	}
	
	public static void displayMenuTitle (String title) {
		System.out.println("-------------" + "-".repeat(title.length()) + "-------------");
		System.out.println("|            " + title + "            |");
		System.out.println("-------------" + "-".repeat(title.length()) + "-------------");
	}*/
	
	private static void userLogin (Scanner in) {
		String nick, password;
		
		/*in.nextLine();
		System.out.println("Inicio de sesión");
		System.out.println("----------------\n");
		
		System.out.print("\nInserta un nick (apodo): ");  nick = in.nextLine();
		System.out.print("\nInserta una contraseña: ");  password = in.nextLine();*/
		
		/*try {
			clear();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		in.nextLine();
		View.displayMenuTitle("Inicio de Sesión");
		nick = View.parseStringInput(in, "    - Nick: ");
		password = View.parseStringInput(in, "    - Contraseña: ");
		
		User data = new User(nick, password);
		
		try {
			servicioAutenticacion.userLogin(data);
			//if (DEBUG_MODE) System.out.println("[DEBUG] Usuario logueado en el sistema (" + nick + ", " + password + ")");
			System.out.print("\nInfo: usuario '" + nick + "' accedió al sistema");
						
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			userOperationsMenu(in);			
		} catch (RemoteException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (InvalidCredentialsException e) {
			System.err.print("\n" + e.getMessage());
			
			try {
				System.in.read();
			} catch (IOException e1) {
				e.printStackTrace();
			}
		} catch (UserAlreadyLoggedException e) {
			System.err.print("\n" + e.getMessage());
			
			try {
				System.in.read();
			} catch (IOException e1) {
				e.printStackTrace();
			}
		} catch (UserDoesNotExistException e) {
			System.err.print("\n" + e.getMessage());
			
			try {
				System.in.read();
			} catch (IOException e1) {
				e.printStackTrace();
			}
		}
	}
	
	public static void userOperationsMenu (Scanner in) {
		boolean exitMenu = false;
		do {
			/*try {
				clear();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			/*View.displayMenu("Operaciones del cliente:", new String[] {"Información del Usuario.", "Enviar Trino.", "Listar Usuarios del Sistema.", "Seguir a...", "Dejar de seguir a...", "Borrar trino a los usuarios que todavía no lo han recibido.", "Salir."});
			int opt = View.parseInput(in);*/
			View.displayMenu("Operaciones del cliente:", new String[] {"Información del Usuario.", "Enviar Trino.", "Listar Usuarios del Sistema.", "Seguir a...", "Dejar de seguir a...", "Borrar trino a los usuarios que todavía no lo han recibido.", "Salir."});
			int opt = View.parseIntInput(in, "\nOpción > ");
			switch (opt) {
				case 1:	/* Información del Usuario */ case 2: /* Enviar Trino */
					System.err.print("Opción no implementada");
					break;
				case 3:	/* Listar Usuarios del Sistema */
					try {
						for (String nickname : servicioGestor.getRegisteredUsers()) {
							System.out.println("    @" + nickname);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 4: /* Seguir a... */ case 5: /* Dejar de seguir a... */ case 6: /* Borrar trino */
					System.err.print("Opción no implementada");
					break;
				case 7:	/* Salir */
					exitMenu = true;
					break;
				default:
					System.err.println("Opción incorrecta '" + opt + "'");
					System.err.print("Introduzca un valor entre 1 y 7");
			}
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!exitMenu);
	}
	
	/*public static void main (String[] args) throws Exception {
		String registryUrl = "rmi://localhost:" + RMIPortNum + "/Auth";
		ServicioAutenticacionInterface authService = (ServicioAutenticacionInterface)Naming.lookup(registryUrl);
		
		// Start rmiregistry
		Registry registry = LocateRegistry.getRegistry(RMIPortNum);
		try {
			registry.list();
		} catch (RemoteException e) {
			if (DEBUG_MODE) System.out.println("[DEBUG_MODE] RMI Registry cannot be located at port " + RMIPortNum);
			registry = LocateRegistry.createRegistry(RMIPortNum);
			if (DEBUG_MODE) System.out.println("[DEBUG_MODE] RMI Registry created at port " + RMIPortNum);
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
	}*/
}
