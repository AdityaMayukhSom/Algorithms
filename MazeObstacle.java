import java.util.*;

class MazeObstacle {
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> maze) {
        int prev[] = new int[n];
        for (int i = 0; i < n; i++) {
            int temp[] = new int[m];
            for (int j = 0; j < m; j++) {
                if (i > 0 && j > 0 && maze.get(i).get(j) == -1) {
                    temp[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    temp[j] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if (i > 0)
                    up = prev[j];
                if (j > 0)
                    left = temp[j - 1];

                temp[j] = up + left;
            }
            prev = temp;
        }
        return prev[n - 1];
    }

}