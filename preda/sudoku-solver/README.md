# Sudoku Solver Using Backtracking

This project implements a Sudoku solver using a backtracking algorithm. The solver reads an initial Sudoku puzzle from an input file or standard input, processes the data, and attempts to find a valid solution.

The folder `examples` contains various input examples of Sudoku puzzles with different difficulty levels.

## Usage

To run the Sudoku solver, use the following command:

```
java -jar sudoku.jar [-t][-h] [input_file]
```

where:
- `-t`: Trace each recursive call (show parameters).
- `-h`: Display help and command syntax.
- `input_file`: Path to the file containing the initial Sudoku grid. The file should contain 9 rows with 9 values each. Each value can be a digit from 1 to 9 or '-' to indicate an empty cell.

If no file is provided, the program reads from standard input. The input must be formatted correctly; otherwise, an error message will be displayed.
