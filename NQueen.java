import java.util.ArrayList;

public class NQueen {
    private static boolean isSafe(boolean[][] pos, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            if (pos[i][col])
                return false;
        }

        int tempRow = row;
        int tempCol = col;
        while (tempRow >= 0 && tempCol >= 0) {
            if (pos[tempRow][tempCol])
                return false;
            tempRow--;
            tempCol--;
        }

        tempRow = row;
        tempCol = col;
        while (tempRow >= 0 && tempCol < n) {
            if (pos[tempRow][tempCol])
                return false;
            tempRow--;
            tempCol++;
        }

        return true;
    }

    private static void solve(int n, int row, boolean[][] pos,
            ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> curr) {
        if (row == n) {
            ans.add(new ArrayList<Integer>(curr));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(pos, row, col, n)) {
                pos[row][col] = true;
                curr.add(col + 1);
                solve(n, row + 1, pos, ans, curr);
                curr.remove(row);
                pos[row][col] = false;
            }
        }

    }

    static ArrayList<ArrayList<Integer>> nQueen(int n) {
        boolean[][] pos = new boolean[n][n];

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> curr = new ArrayList<>();
        solve(n, 0, pos, ans, curr);

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(nQueen(4));
    }
}
