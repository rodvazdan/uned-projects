package sudoku;

/**
 * The Sudoku class starts the program.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.0.4
 */

 public class Sudoku
 {
  /**
   * Starts the program.
   * @param  args  Command-line arguments
   */
  public static void main (String[] args) {
    // Reference to the controller that communicates the model that resolves the sudoku
		// and the view that prints it on the screen.
		SudokuController controller = null;
		
    // If there are no errors in the command-line arguments gets the instance of the controller
    // that calls the methods to solve the Sudoku and display it:
    if ((controller = Parser.parseArgs(args)) != null)
      controller.solveSudoku();
  }
}