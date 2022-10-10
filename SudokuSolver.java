public class SudokuSolver {
    public static void main(String[] args) {
        int[][] board = { { 4, 0, 0, 2, 0, 0, 0, 6, 0 },
                { 0, 0, 5, 0, 0, 0, 0, 4, 0 },
                { 3, 0, 2, 6, 8, 0, 9, 0, 5 },
                { 0, 7, 4, 0, 0, 3, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 7, 0, 0, 6, 5, 0 },
                { 1, 0, 8, 0, 2, 6, 7, 0, 4 },
                { 0, 2, 0, 0, 0, 0, 5, 0, 0 },
                { 0, 4, 0, 0, 0, 7, 0, 0, 3 } };

        // System.out.print("{ ");
        // for (int i = 0, j = 0; i < 9 & j < 9; i++, j++) {
        // System.out.print("{" + i + "," + j + "}, ");
        // }
        // System.out.print(" }");
        // System.out.println();
        // System.out.print("{ ");
        // for (int i = 0, j = 8; i < 9 & j >= 0; i++, j--) {
        // System.out.print("{" + i + "," + j + "}, ");
        // }
        // System.out.print(" }");

        if (sudokuSolver(board, 0, 0)) {
            System.out.println("\nSolution To The Given Problem Is : \n");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.print("\n");
            }
        } else {
            System.out.println("Solution Does Not Exist");
        }

    }

    public static boolean sudokuSolver(int[][] board, int row, int col) {
        if (row == 8 && col == 9) {
            return true;
        }

        if (col == 9) {
            row = row + 1;
            col = 0;
        }

        if (board[row][col] != 0) {
            return sudokuSolver(board, row, col + 1);
        }

        // Backtracking

        for (int n = 1; n <= 9; n++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = n;
                if (sudokuSolver(board, row, col + 1))
                    return true;
            }
            board[row][col] = 0;
        }
        return false;
    }

    public static boolean isSafe(int[][] board, int row, int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == n) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == n) {
                return false;
            }
        }

        int startRow = 3 * (int) Math.floor(row / 3);
        int startCol = 3 * (int) Math.floor(col / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == n) {
                    return false;
                }
            }
        }

        // For Diagonal Sudoku
        int[][] mainDiagonal = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 }, { 5, 5 }, { 6, 6 },
                { 7, 7 }, { 8, 8 } };
        int[][] reverseDiagonal = new int[][] { { 0, 8 }, { 1, 7 }, { 2, 6 }, { 3, 5 }, { 4, 4 }, { 5, 3 }, { 6, 2 },
                { 7, 1 }, { 8, 0 } };
        for (int j = 0; j < 9; j++) {
            if (mainDiagonal[j][0] == row && mainDiagonal[j][1] == col) {
                for (int i = 0; i < 9; i++) {
                    if (board[mainDiagonal[i][0]][mainDiagonal[i][1]] == n) {
                        return false;
                    }
                }
            }

        }
        for (int j = 0; j < 9; j++) {
            if (reverseDiagonal[j][0] == row && reverseDiagonal[j][1] == col) {
                for (int i = 0; i < 9; i++) {
                    if (board[reverseDiagonal[i][0]][reverseDiagonal[i][1]] == n) {
                        return false;
                    }
                }
            }
        }
        // Diagonal Sudoku Ends

        return true;
    }
}