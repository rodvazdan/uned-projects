package sudoku;

import java.io.File;
import java.io.IOException;

/**
 * The Sudoku class solves a given Sudoku board by implementing the Backtracking algorithmic scheme.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.1
 */

public class SudokuModel
{
	private boolean[][] mod;  // Array indicating which cells cannot be modified by the algorithm.
	private boolean tflag;	  // Indicates if the trace of the parameters in each recursive call is printed.
	private int[][] grid;	  // Stores the cells of the Sudoku board.

	/**
	 * Sets the initial Sudoku board.
	 * @param  infile  File representing the initial Sudoku board
	 */
	public SudokuModel (File infile) {
		mod = new boolean[9][9];
		loadSudokuTable(infile);
	}

	/**
	 * Sets the initial Sudoku board.
	 * @param  grid  Array representing the initial Sudoku board
	 */
	public SudokuModel (int[][] grid) {
		this.grid = grid;
		mod = new boolean[9][9];
	}

	/**
	 * Sets the initial Sudoku board. The trace of the parameters in each recursive call is enabled.
	 * @param  tflag  Indicates if the trace of the parameters in each recursive call is printed.
	 * @param  infile  File representing the initial Sudoku board
	 */
	public SudokuModel (boolean tflag, File infile) {
		this.tflag = tflag;
		mod = new boolean[9][9];
		loadSudokuTable(infile);
	}

	/**
	 * Sets the initial Sudoku board. The trace of the parameters in each recursive call is enabled.
	 * @param  tflag  Indicates if the trace of the parameters in each recursive call is printed.
	 * @param  grid  Array representing the initial Sudoku board
	 */
	public SudokuModel (boolean tflag, int[][] grid) {
		this.tflag = tflag;
		this.grid = grid;
		mod = new boolean[9][9];
	}
	
	/**
	 * Checks for a given cell that its value does not repeat in the same row, column or 3x3 cell subgroup.
	 * @param  i  Integer representing a row
	 * @param  j  Integer representing a column
	 * @param  grid  Array representing the cells of the Sudoku board
	 * @return true if and only if the value of the given cell does not repeat in the same row, column or 3x3 cell subgroup; false otherwise
	 */
	private boolean isPossiblePartialSolution (int i, int j, int[][] grid) {
		int r = 1;	                   // Used to loop through the rows.
		int c = 1;	                   // Used to loop through the columns.
		boolean isValidNumber = true;  // Indicates if the value of the given cell does not repeat in the same row, column or 3x3 cell subgroup
		
		// Checks the row and column:
		while (r <= 9 && isValidNumber) {
			if ( (grid[i-1][j-1] == grid[r-1][j-1] && r != i) || (grid[i-1][j-1] == grid[i-1][r-1] && r != j) )
				isValidNumber = false;
			r++;
		}
		
		// Gets the initial row and column:
		r = obtainInitialCell(i);
		c = obtainInitialCell(j);
		
		// Checks the 3x3 cell subgroup:
		while ((r < obtainInitialCell(i) + 3) && isValidNumber) {
			while ((c < obtainInitialCell(j) + 3) && isValidNumber) {
				if ( grid[i-1][j-1] == grid[r-1][c-1] && i != r && j != c )
					isValidNumber = false;
				c++;
			}
			c = obtainInitialCell(j);
			r++;
		}
		
		return isValidNumber;
	}
	
	/**
     * Loads the initial Sudoku board from an input file.
	 * @param  infile  File representing the initial Sudoku board
     */
    private void loadSudokuTable (File infile) {
        FileArrayProvider fap = new FileArrayProvider();
        try {
			grid = fap.readlines(infile.getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("sudoku: " + ex.getMessage());
			System.exit(1);
        }
    }
	
	/** 
	 * Determines the initial cell from which we will check the feasibility of a given cell in its corresponding 3x3 cell subgroup.
	 * @param  i  Cell from which we want to check its viability
	 * @return Integer representing the initial cell
	 */
	private int obtainInitialCell (int i) {
		int k = i / 3;   // Used to determine the initial cell.
		int result = 0;  // Stores the initial cell.

		if (i % 3 != 0)  k++;

		switch (k) {
			case 1:
				result = 1;
				break;
			case 2:
				result = 4;
				break;
			case 3:
				result = 7;
				break;
		}
		
		return result;
	}

	/**
	 * Solves the initial Sudoku by implementing the Backtracking algorithmic scheme.
	 * @param  i  Integer representing a row
	 * @param  j  Integer representing a column
	 * @param  grid  Array representing the cells of the Sudoku board
	 */
	private void solve (int i, int j, int[][] grid) {
		if (tflag)  System.out.println("TRAZA i=" + i + ", j=" + j);
		
		// If the cell cannot be modified:
		if (mod[i - 1][j - 1]) {
			if (i == 9 && j == 9) {
				SudokuController.showSudoku(grid);
			} else
			if (i < 9 && j == 9) {
				solve(i + 1, 1, grid);
			} else
			if (i <= 9 && j < 9) {
				solve(i, j + 1, grid);
			}
		} else
		// If the cell can be modified:
		{
			for (int k = 1; k <= 9; k++) {
				// Check the cell:
				grid[i - 1][j - 1] = k;
				if (isPossiblePartialSolution(i, j, grid)) {
					if (i == 9 && j == 9) {
						SudokuController.showSudoku(grid);
					} else
					if (i < 9 && j == 9) {
						solve(i + 1, 1, grid);
					} else
					if (i <= 9 && j < 9) {
						solve(i, j + 1, grid);
					}
				}
				// Uncheck the cell:
				grid[i - 1][j - 1] = 0;
			}
		}
	}
	
	/**
	 * Calls the method that solves the initial Sudoku.
	 */
	public void solveSudoku() {
		// Marks the cells that cannot be modified:
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				mod[i][j] = ( grid[i][j] != 0 );
		}

		solve(1, 1, grid);
	}
}
