public class Lecture11 {
    public static void main(String[] args) {
        int N = 8;
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
        NQueen.nQueen(board, 0, N);
    }
}

class NQueen {
    public static boolean nQueen(int board[][], int row, int N) {
        if (row == N) {
            System.out.println("\nSolution To The Given Problem Is : \n");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0)
                        System.out.print("- ");
                    else
                        System.out.print("Q ");
                }
                System.out.print("\n");
            }
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col, N)) {
                board[row][col] = 1;
                if (nQueen(board, row + 1, N)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isSafe(int[][] board, int row, int col, int N) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }
}
