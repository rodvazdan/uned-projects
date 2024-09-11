package sudoku;

/**
 * The SudokuView class displays the table of the solved Sudoku on the screen.
 * 
 * @author  Daniel Vazquez Rodriguez
 * @version 2020.0.1
 */

 public class SudokuView
 {
	public SudokuView() {
		// Nothing to do
	}

	/**
	 * Prints the table showing the solved Sudoku.
	 * @param  grid  Array representing the cells of the Sudoku board
	 */
	public static void showSudoku (int[][] grid) {
		System.out.println("\nSolucion:");

		for (int b = 1; b <= 9; b++) {
			for (int b1 = 1; b1 <= 9; b1++) {
				System.out.print(grid[b - 1][b1 - 1]);
				if (b1 < 9) {
					System.out.print(" ");
					if (b1 == 3 || b1 == 6)
						System.out.print(" "); 
				}
			}
			System.out.println();
			if (b == 3 || b == 6)
				System.out.println(); 
		}
	}
 }