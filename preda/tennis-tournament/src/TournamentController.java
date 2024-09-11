package tennistournament;

import java.io.File;

/**
 * The TournamentController class organizes the tournament and displays it on the screen.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.1.1
 */

public class TournamentController
{
    private TournamentModel tModel;  // Reference to the model that organizes the tournament.
    private TournamentView tView;    // Reference to the view that displays the tournament on the screen.

    /**
     * Sets the number of players who must perform the tournament.
     * @param  n  Number of players
     */
    public TournamentController (int n) {
        tModel = new TournamentModel(n);
        tView =  new TournamentView(n);
    }

    /**
     * Sets the number of players who must perform the tournament. The trace of the parameters in each recursive call is enabled.
     * @param  tflag  Indicates if the trace of the parameters in each recursive call is printed
     * @param  n  Number of players
     */
    public TournamentController (boolean tflag, int n) {
        tModel = new TournamentModel(tflag, n);
        tView  = new TournamentView(n);
    }

    /**
     * Sets the number of players who must perform the tournament. The input file lists the names of the players.
     * @param  n Number of players
     * @param  infile  List of the names of the players in the tournament
     */
    public TournamentController (int n, File infile) {
        tModel = new TournamentModel(n);
        tView  = new TournamentView(n, infile);
    }

    /**
     * Sets the number of players who must perform the tournament. The trace of the parameters in each recursive call is enabled.
     * The input file lists the names of the players.
     * @param  tflag  Indicates if the trace of the parameters in each recursive call is printed
     * @param  n  Number of players
     * @param  infile  List of the names of the players in the tournament
     */
    public TournamentController (boolean tflag, int n, File infile) {
        tModel = new TournamentModel(tflag, n);
        tView  = new TournamentView(n, infile);
    }
    
    /**
     * Organize the tournament.
     */
    public void organizeTournament() {
        tModel.organizeTournament();
    }
    
    /**
     * Display the tournament on the screen.
     */
    public void printTournament() {
        tView.printTournament(tModel.getPlayers());
    }
}
