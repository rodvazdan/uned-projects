package tennistournament;

import java.io.File;

/**
 * Analyzes command-line arguments according to the following rules:
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.1.5
 */
public class Parser
{	
	/**
	 * Default constructor
	 */
    public Parser() {
        // Nothing to do
    }

    /**
     * Prints the usage of the program.
     */
    private static void printUsage() {
        System.out.println("SINTAXIS: torneo [-t] [-h] n [fichero entrada]");
        System.out.println("     -t          Traza la parametrizacion de cada invocacion recursiva");
        System.out.println("     -h          Muestra esta ayuda");
        System.out.println("      n                   Numero de jugadores");
        System.out.println("     [fichero entrada]    Listado de los nombres de los jugadores del torneo");
    }

    /**
     * Tests if the given string represents a valid integer.
     * @param  str  String to be evaluated
     * @return true if and only if the given string represents a valid integer; false otherwise 
     */
    private static boolean isNumeric (String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (final NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Tests if the given string represents an existing plaintext file.
     * @param  pathname  String to be evaluated
     * @return true if and only if this pathname exists and represents a normal file; false otherwise
     */
    private static boolean isValidFile (String pathname) {
        File file = new File(pathname);
		
		// Test if this pathname exists:
		if (!file.exists()) {
			System.err.println("torneo: " + file + ": no se puede abrir el fichero: el fichero no existe");
			return false;
		} else
		// Test if this pathname represents a normal file:
		if (!file.isFile()) {
			System.err.println("torneo: " + file + ": no se puede abrir el fichero: no es un fichero normal");
			return false;
		} else
		// Test if the file denoted by this pathname contains a TXT file extension:
		if (!pathname.contains(".txt")) {
			System.err.println("torneo: " + pathname + ": no es un fichero de texto plano");
			return false;
		} else
		// Test if the file denoted by this pathname can be read:
		if (!file.canRead()) {
			System.err.println("torneo: " + file + ": no se puede abrir el fichero: no se puede leer");
			return false;
		}
		
		return true;
    }

    /**
     * Returns an instance of TournamentController class based on the command-line arguments passed.
     * @param  tflag  Indicates if the trace is printed
     * @param  n  Number of players
     * @param  infile  List of the names of the players
     * @return Instance of TournamentController class based on the command-line arguments passed
     */
    private static TournamentController getTournamentController (boolean tflag, int n, File infile) {
		// If the command-line argument -t has been passed:
        if (tflag) {
			// If an input file with the names of the players has been passed:
            if (infile != null) {
                return new TournamentController(tflag, n, infile);
            } else {
                return new TournamentController(tflag, n);
            }
        } else
		// If an input file with the names of the players has been passed:
        if (infile != null) {
			// If the command-line argument -t has been passed:
            if (tflag) {
                return new TournamentController(tflag, n, infile);
            } else {
                return new TournamentController(n, infile);
            }
        } else {
            return new TournamentController(n);
        }
    }
    
    /**
     * Takes command-line arguments and analyzes them.
     * @param  args  Array that stores command-line arguments as strings
     * @return TournamentController class instance
     */
    public static TournamentController parseArgs (String[] args) {
        String arg = null;                        // Stores a single command-line argument.
        char flag;                                // Stores command-line arguments preceded by '-'.

        int n = 0;                                // Stores the number of players.
        boolean tflag = false;                    // Indicates if the trace of the parameters in each recursive call is printed.
        File infile = null;                       // Stores the list of the names of the players.
        TournamentController controller = null;   // Stores the controller that organizes the tournament
                                                  // and displays it on the screen.

        int i = 0;                                // Used to loop through command-line arguments.

        // Loop through command-line arguments:
        while (i < args.length) {
            arg = args[i++];  // Take a command-line argument.

            // Tests if the command-line argument starts with '-':
            if (arg.startsWith("-")) {
                flag = arg.charAt(1); // Takes the rest of the command-line argument after '-'.

                // Tests if the command-line arguments 't' or 'h' has been passed:
                switch (flag) {
                    case 't':
                        tflag = true;
                        break;
                    case 'h':
                        printUsage();
                        System.exit(0);
                    default:
                        System.err.println("torneo: opcion no permitida -- \'" + flag + "\'");
                        System.err.println("Intente 'torneo -h' para obtener mas informacion.");
                        System.exit(1);
                }
            } else {
                // Tests if the command-line argument 'n' has been passed:
                if (isNumeric(arg)) {
                    n = Integer.parseInt(arg);
                    if (n <= 1) {
                        System.err.println("torneo: el numero de jugadores debe ser mayor que 1");
                        System.exit(1);
                    }
                } else
                // Tests if the command-line argument 'fichero entrada' has been passed:
                if (isValidFile(arg)) {
                    infile = new File(arg);
                } else {
                    System.exit(1);
                }
            }
        }

        // If no command-line arguments have been passed the usage of the program is printed:
        if (args.length == 0) {
            printUsage();
        } else {
            // If the number of players is less than/equal to '1' the usage of the program is printed:
            if (n <= 1) {
                printUsage();
            } else {
                controller = getTournamentController(tflag, n, infile);
            }
        }

        return controller;
    }
}