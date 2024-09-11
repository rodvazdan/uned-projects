package tennistournament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * The FileArrayProvider class reads a file into an array.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.0.1
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
     * Inserts each line of a given file into an array.
     * @param  filename  The name of the file to be read in array
     * @return Array composed of the lines of the given file
     */
    public String[] readlines (String filename) throws IOException {
        try (BufferedReader bReader = new BufferedReader(new FileReader(filename))) {
            List<String> lines = new ArrayList<>();   // Stores all the lines in the file.
            String line = null;                       // Stores a single line of the file.

            // Reads every line of the file:
            while ((line = bReader.readLine()) != null) {
                lines.add(line);
            }

            // Returns an array composed of the lines of the file.
            return lines.toArray(new String[lines.size()]);
        }
    }
}