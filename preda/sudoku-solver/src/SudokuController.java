package sudoku;

import java.io.File;

/**
 * The SudokuController class communicates the model that resolves the Sudoku and the view that prints it on the screen.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.1
 */

public class SudokuController
{
    private SudokuModel sModel;  // Reference to the model that resolve the initial sudoku.

    /**
     * Sets the initial Sudoku board.
     * @param  infile  File representing the initial Sudoku board
     */
    public SudokuController (File infile) {
	    sModel = new SudokuModel(infile);
    }

    /**
     * Sets the initial Sudoku board.
     * @param  grid  Array representing the initial Sudoku board
     */
    public SudokuController (int[][] grid) {
	    sModel = new SudokuModel(grid);
    }
    
    /**
     * Sets the initial Sudoku board. The trace of the parameters in each recursive call is enabled.
     * @param  tflag  Print the trace of the paremeters in each recursive call
     * @param  infile  File representing the initial Sudoku board 
     */
    public SudokuController (boolean tflag, File infile) {
	    sModel = new SudokuModel(tflag, infile);
    }
    
    /**
     * Sets the initial Sudoku board. The trace of the parameters in each recursive call is enabled.
     * @param  tflag  Print the trace of the paremeters in each recursive call
     * @param  grid  Array representing the initial Sudoku board
     */
    public SudokuController (boolean tflag, int[][] grid) {
	    sModel = new SudokuModel(tflag, grid);
    }
	
	/**
     * Displays the solved Sudoku table on the screen.
     */
    public static void showSudoku (int[][] grid) {
	    SudokuView.showSudoku(grid);
    }

    /**
     * Solves the initial Sudoku.
     */
    public void solveSudoku() {
        sModel.solveSudoku();
    }
}