package tennistournament;

/**
 * The Torneo class starts the program.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.2.4
 */

 public class Torneo
 {
     // Reference to the controller that organizes the tournament and displays it on the screen.
     private static TournamentController controller = null;

     /**
      * Starts the program.
      * @param  args  Command-line arguments.
      */
      public Torneo (String[] args) {
          // Gets the instance of the controller if there is no errors.
          controller = Parser.parseArgs(args);

          // If there are no errors in the command-line arguments 
          // organizes the tennis tournament and displays it on the screen:
          if (controller != null) {
              controller.organizeTournament();
              controller.printTournament();
          }
      }

      public static void main (String[] args) {
          //new Torneo(args);
		  // Gets the instance of the controller if there is no errors.
          controller = Parser.parseArgs(args);

          // If there are no errors in the command-line arguments 
          // organizes the tennis tournament and displays it on the screen:
          if (controller != null) {
              controller.organizeTournament();
              controller.printTournament();
          }
      }
 }