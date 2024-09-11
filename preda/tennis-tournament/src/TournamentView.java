package tennistournament;

import java.io.File;
import java.io.IOException;

/**
 * The TournamentView class draws the tournament calendar on the screen.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.2.2.2
 */

public class TournamentView
{
    private int n;                  // Number of players.
    private File infile;            // List of the names of the players in the tournament.
    private String[] playerNames;   // Array with the names of the players in the tournament.

    /**
     * Sets the number of players who must perform the tournament.
     * @param  n  Number of players
     */
    public TournamentView (int n) {
        this.n = n;
        this.infile = null;
        this.playerNames = null;
    }

    /**
     * Sets the number of players who must perform the tournament. The input file lists the names of the players.
     * @param  n  Number of players
     * @param  f  List of the names of the players in the tournament
     */
    public TournamentView (int n, File f) {
        this.n = n;
        this.infile = f;

        // Loads the names of the players from an input file.
        loadPlayerNames();
    }

    /**
     * Returns the length of the longest name.
	 * @param  numberOfNames  Number of names to display
     * @return Length of the longest name
     */
    private int getLongestNameLength (int numberOfNames) {
        int length = 0;
        for (int i = 0; i < numberOfNames; i++) {
			if (length < playerNames[i].length())
				length = playerNames[i].length();
		}
        return length;
    }

    /**
     * Loads the names of the players from an input file.
     */
    private void loadPlayerNames() {
        FileArrayProvider fap = new FileArrayProvider();
        try {
            playerNames = fap.readlines(infile.getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("IOException thrown: " + ex.getMessage());
        }
    }

    /**
     * Add spaces to a given string.
     * @param  inputString  String to add the space
     * @param  length  Length of space to be added
     * @return String representing the input string with the added spaces
     */
    private String padLeftSpaces (String inputString, int length) {
        if (inputString.length() >= length)  return inputString;

        StringBuilder sb = new StringBuilder();
        while (sb.length() < length-inputString.length()) {
            sb.append(' ');
        }
        sb.append(inputString);

        return sb.toString();
    }

    /**
     * Add spaces to a given string.
     * @param  length  Length of space to be added
     * @return String representing the input string with the added spaces
     */
    private String padLeftSpaces (int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }

    /**
     * Prints a table indicating on which day each player plays.
     * @param  players  Array with the players in the tournament
     */
    public void printTournament (int[][] players) {
		// Calculates the number of rows based on whether the number of players is odd or even:
        int rows = ( n != players.length ? players.length - 1 : players.length );

        // If a file with the names of the players has been provided, it's printed in the table.
        // Otherwise, an integer value representing each player is printed:
        if (infile != null) {
            int length;
			
			if (playerNames.length > n) {
				length = getLongestNameLength(n);
			} else {
				length = getLongestNameLength(playerNames.length);
			}

            System.out.print(padLeftSpaces(length));

            // Print the days each pair of players will play:
            for (int i = 1; i <= players.length - 1; i++) {
                System.out.print("  " + padLeftSpaces(length - String.valueOf(i).length()) + "d" + i);
            }
            System.out.println();

            // Print the name of the player that each of the players will be facing:
            for (int i = 1; i <= rows; i++) {
                // Print the header of the columns:
                if (i <= playerNames.length) {
                    System.out.print(padLeftSpaces(playerNames[i-1], length) + " | ");
                } else {
                    System.out.print(padLeftSpaces("J" + i, length) + " | ");
                }

                // Print the header of the rows:
                for (int j = 1; j <= players[0].length; j++) {
                    int idx = players[i-1][j-1] - 1;

                    if (idx >= 0 && idx < playerNames.length) {
                        System.out.print(padLeftSpaces(playerNames[idx], length) + " |");
                    } else {
                        System.out.print(padLeftSpaces(String.valueOf(idx + 1), length) + " |");
                    }

                    if (j < rows) System.out.print(" ");
                }

                System.out.println();
            }
        } else {
            System.out.print(padLeftSpaces(String.valueOf(players.length).length() + 1));

            // Print the days each pair of players will play:
            for (int i = 1; i <= players.length - 1; i++) {
                System.out.print("  " + padLeftSpaces(String.valueOf(players.length).length() - String.valueOf(i).length()) + "d" + i);
            }
            System.out.println();

            // Prints the player that each of the players will be facing:
            for (int i = 1; i <= rows; i++) {
                // Prints the header of the columns:
                System.out.print(padLeftSpaces("J" + i, String.valueOf(players.length).length() + 1) + " | ");

                // Prints the header of the rows:
                for (int j = 1; j <= players[0].length; j++) {
                    System.out.print(padLeftSpaces(String.valueOf(players[i-1][j-1]), String.valueOf(players.length).length()) + " |");

                    if (j < rows) System.out.print(" ");
                }

                System.out.println();
            }
        }
    }
}