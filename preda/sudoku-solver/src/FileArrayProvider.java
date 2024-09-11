package sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * The FileArrayProvider class reads a file into an array.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.0.3
 */

public class FileArrayProvider
{
	/**
	 * Default constructor.
	 */
    public FileArrayProvider() {
        // Nothing to do
    }
	
	/**
	 * Checks if a given value is a number between 1 and 9 or a dash.
	 * @param  values  
	 * @return true if and only if the given value is a number between 1 and 0 or a dash; false otherwise
	 */
	private boolean isValidValue (String value) {
		return value.matches("\\d|-");
	}

    /**
     * Inserts each line of a given file into an array.
     * @param  filename  The name of the file to be read in array
     * @return Array composed of the lines of the given file
     */
    public int[][] readlines (String filename) throws IOException {
		try (BufferedReader bReader = new BufferedReader(new FileReader(filename))) {
			int[][] lines = new int[9][9];	// Stores all the lines in the file.
			String line = null;				// Stores a single line of the file.
			
			// Reads every line of the file:
			for (int i = 0; i < 9; i++) {
				if ((line = bReader.readLine()) != null) {
					String[] values = line.split(" ");
					// Checks if there are 9 elements in the row:
					if (values.length != 9) {
						System.err.println("torneo: formato del Sudoku inicial incorrecto");
						System.exit(1);
					}
					
					for (int j = 0; j < 9; j++) {
						// Checks if a value is a number between 1 and 9 or '-':
						if (!isValidValue(values[j])) {
							System.err.println("torneo: \'" + values[j] + "\': valor incorrecto");
							System.exit(1);
						} else {
							if (values[j].equals("-")) {
								lines[i][j] = 0;
							} else {
								lines[i][j] = Integer.parseInt(values[j]);
							}
						}
					}
				}
			}

			return lines;
		}
	}
}