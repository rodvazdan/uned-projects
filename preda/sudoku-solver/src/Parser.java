package sudoku;

import java.io.File;
import java.util.Scanner;

/**
 * The Parser class analyzes command-line arguments in search of an input file
 * containing the initial Sudoku. If not, it expects that Sudoku in the standard input.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.1
 */

public class Parser
{
	/**
	 * Default constructor.
	 */
    public Parser() {
        // Nothing to do
    }

    /**
     * Returns an instance of SudokuController class based on the command-line arguments passed.
     * @param  tflag  Indicates if the trace is printed
     * @param  infile  List of the names of the players
     * @return Instance of SudokuController class based on the command-line arguments passed; null otherwise
     */
    private static SudokuController getController (boolean tflag, File infile, int[][] grid) {
		// If the input file with the initial Sudoku has been passed:
        if (infile != null) {
            // If the command-line argument -t has been passed:
            if (tflag) {
                return new SudokuController(tflag, infile);
            } else {
                return new SudokuController(infile);
            }
        } else
        // If the initial Sudoku has been read through the standard input:
        if (grid != null) {
            // If the command-line argument -t has been passed:
            if (tflag) {
                return new SudokuController(tflag, grid);
            } else {
                return new SudokuController(grid);
            }
        } else {
            return null;
        }
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
     * Tests if the given string represents an existing file.
     * @param  pathname  String to be evaluated
     * @return true if and only if this pathname exists and represents a normal file; false otherwise
     */
    private static boolean isValidFile (String pathname) {
		File file = new File(pathname);
		
		// Test if this pathname exists:
		if (!file.exists()) {
			System.err.println("sudoku: " + file + ": el fichero no existe");
			return false;
		} else
		// Test if this pathname represents a normal file:
		if (!file.isFile()) {
			System.err.println("sudoku: " + file + ": no es un fichero normal");
			return false;
		} else
		// Test if the file denoted by this pathname contains a TXT file extension:
		if (!pathname.contains(".txt")) {
			System.err.println("sudoku: " + pathname + ": no es un fichero de texto plano");
			return false;
		} else
		// Test if the file denoted by this pathname can be read:
		if (!file.canRead()) {
			System.err.println("sudoku: " + file + ": acceso de lectura denegado");
			return false;
		}
		
		return true;
    }
    
    /**
     * Takes command-line arguments and analyzes them.
     * @param  args  Array that stores command-line arguments as strings
     * @return Instance of SudokuController class based on the command-line arguments passed; null otherwise
     */
    public static SudokuController parseArgs (String[] args) {
        String arg = null;                    // Stores a single command-line argument.
        char flag;                            // Stores command-line arguments preceded by '-'.

        boolean tflag = false;                // Indicates if the trace of the parameters in each recursive call is printed.
        File infile = null;                   // Stores the list of the names of the players.
        SudokuController controller = null;   // Stores the controller that organizes the tournament
                                              // and displays it on the screen.
        
        int[][] grid = null;                  // Stores the initial Sudoku board.

        int i = 0;                            // Used to loop through command-line arguments.

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
						System.err.println("sudoku: opcion no permitida -- \'" + flag + "\'");
						System.err.println("Intente 'sudoku -h' para obtener mas informacion.");
						System.exit(1);
                }
            } else {
                // Tests if the command-line argument 'fichero entrada' has been passed:
                if (isValidFile(arg)) {
                    infile = new File(arg);
                }
                // Otherwise reads the initial Sudoku board through the standard input:
                else {
                    grid = readStdInput();
                }
            }
        }

        // If no command-line arguments have been passed the usage of the program is printed:
        if (args.length == 0) {
            printUsage();
        } else {
            controller = getController(tflag, infile, grid);
        }

        return controller;
    }
	
	/**
     * Prints the usage of the program.
     */
    private static void printUsage() {
        System.out.println("SINTAXIS: sudoku [-t] [-h] [fichero entrada]");
        System.out.println("     -t          Traza la parametrizacion de cada invocacion recursiva");
        System.out.println("     -h          Muestra esta ayuda");
        System.out.println("     [fichero entrada]    Tabla inicial del Sudoku");
    }
	
	/**
     * Reads the initial Sudoku board through the standard input.
     * @return Array representing the initial Sudoku board.
     */
    private static int[][] readStdInput() {
        Scanner scan = new Scanner(System.in);  // Used to get user input.
        int[][] grid = new int[9][9];           // Stores the initial Sudoku board.

        System.out.println("Leyendo el sudoku inicial de la entrada estandar...");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Reads the value each cell will have in the initial Sudoku board:
                System.out.print("    Valor para la celda[" + (i + 1) + ", " + (j + 1) + "]: ");
                String sCellValue = scan.nextLine();

                // Tests whether the value read is a number or '-':
                if (isNumeric(sCellValue)) {
                    // Tests if the number read is between 1 and 9:
                    int iCellValue = Integer.parseInt(sCellValue);
                    if (1 <= iCellValue && iCellValue <= 9) {
                        grid[i][j] = iCellValue;
                    } else {
                        System.err.println("sudoku: " + iCellValue + ": solo estan permitidos numeros entre el 1 y el 9");
                        System.exit(1);
                    }
                } else
                if (sCellValue.equals("-")) {
                    grid[i][j] = 0;
                } else {
                    System.err.println("sudoku: " + sCellValue + ": solo estan permitidos valores numericos o el guion (-)");
                    System.exit(1);
                }
            }
        }

        scan.close();
        return grid;
    }
}
