package tennistournament;

/**
 * The TournamentModel class organizes the tournament for 'n' players.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.2.3
 */

public class TournamentModel
{
    private int n;              // Number of players.
    private boolean tflag;      // Indicates if the trace of the parameters in each recursive call is printed.
    private int[][] quadrant;   // Stores the quadrants that form the tournament calendar.

    /**
     * Sets the number of players who must perform the tournament.
     * @param  n  Number of players
     */
    public TournamentModel (int n) {
        this.n = n;

        if (n % 2 == 0) {
            quadrant = new int[n][n-1];
        } else {
            quadrant = new int[n+1][n];
        }
    }

    /**
     * Sets the number of players who must perform the tournament. The input file lists the names of the players.
     * @param  tflag  Indicates if the trace of the parameters in each recursive call is printed.
     * @param  n  Number of players
     */
    public TournamentModel (boolean tflag, int n) {
        this.n = n;
        this.tflag = tflag;

        if (n % 2 == 0) {
            quadrant = new int[n][n-1];
        } else {
            quadrant = new int[n+1][n];
        }
    }

    /**
     * Call the method that organizes the tournament.
     */
    public void organizeTournament() {
        organize(n);
    }

    /**
     * Organizes the tournament for 'n' players.
     * @param  n  Number of players
     */
    private void organize (int n) {
        int j, d;

        if (tflag)  System.out.println("TRAZA: n=" + n);

        // Base case: one player versus another
        if (n == 2) {
            quadrant[0][0] = 2;
            quadrant[1][0] = 1;
        } else
        // Trivial case: 'n' is an odd number
        if (n % 2 != 0) {
            // Recursive call for 'n + 1' players:
            organize(n + 1);

            // The dummy player 'n + 1' is removed:
            for (j = 1; j <= n; j++) {
                for (d = 1; d <= n; d++) {
                    if (quadrant[j-1][d-1] == n + 1)  quadrant[j-1][d-1] = 0;
                }
            }
        } else
        // Trivial case: 'n' is an even number
        {
            int m = n / 2;

            // Recursive call for 'n / 2' players: construction of the upper left quadrant
            organize(m);

            // If 'm' is even, 'm' players face each other in 'm - 1' days:
            if (m % 2 == 0) {
                // Construction of the lower left quadrant:
                for (j = m + 1; j <= n; j++) {
                    for (d = 1; d <= m - 1; d++) {
                        quadrant[j-1][d-1] = quadrant[j-m-1][d-1] + m;
                    }
                }

                // Construction of the upper right quadrant:
                for (j = 1; j <= m; j++) {
                    for (d = m; d <= n - 1; d++) {
                        if (j + d <= n) {
                            quadrant[j-1][d-1] = j + d;
                        } else {
                            quadrant[j-1][d-1] = j + d - m;
                        }
                    }
                }

                // Construction of the lower right quadrant:
                for (j = m + 1; j <= n; j++) {
                    for (d = m; d <= n - 1; d++) {
                        if (j > d) {
                            quadrant[j-1][d-1] = j - d;
                        } else {
                            quadrant[j-1][d-1] = j + m - d;
                        }
                    }
                }
            } else
            // If 'm' is odd, 'm + 1' players face each other in 'm' days:
            {
                // Construction of the lower left quadrant:
                for (j = m + 1; j <= n; j++) {
                    for (d = 1; d <= m; d++) {
                        if (quadrant[j-m-1][d-1] == 0) {
                            quadrant[j-1][d-1] = 0;
                        } else {
                            quadrant[j-1][d-1] = quadrant[j-m-1][d-1] + m;
                        }
                    }
                }

                // Remove the 0's from the left quadrants:
                for (j = 1; j <= m; j++) {
                    for (d = 1; d <= m; d++) {
                        if (quadrant[j-1][d-1] == 0) {
                            quadrant[j-1][d-1] = j + m;
                            quadrant[j+m-1][d-1] = j;
                        }
                    }
                }

                // Construction of the upper right quadrant:
                for (j = 1; j <= m; j++) {
                    for (d = m + 1; d <= n - 1; d++) {
                        if (j + d <= n) {
                            quadrant[j-1][d-1] = j + d;
                        } else {
                            quadrant[j-1][d-1] = j + d - m;
                        }
                    }
                }

                // Construction of the lower right quadrants:
                for (j = m + 1; j <= n; j++) {
                    for (d = m + 1; d <= n - 1; d++) {
                        if (j > d) {
                            quadrant[j-1][d-1] = j - d;
                        } else {
                            quadrant[j-1][d-1] = j + m - d;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Returns the quadrants that form the tournament calendar.
     * @return The quadrants that form the tournament calendar
     */
    public int[][] getPlayers() {
        return quadrant;
    }
}