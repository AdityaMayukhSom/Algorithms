import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Pair<U, V>(U first, V second) {}
record Trio<U, V, W>(U first, V second, W third) {}

public class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();

        var valuesWithRow = new ArrayList<Pair<Integer, Integer>>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                valuesWithRow.add(new Pair<>(grid.get(r).get(c), r));
            }
        }

        // sort by value in descending order, higher values will be in front
        Collections.sort(valuesWithRow, (a, b) -> b.first() - a.first());

        // HashMap to store the maximum sum for each mask
        var dp = new HashMap<Integer, Integer>(); // mask -> maximum_sum
        dp.put(0, 0); // Base case: mask 0 means no rows selected, score is 0

        // Iterate over each value-row pair
        for (var pair : valuesWithRow) {
            int val = pair.first();
            int row = pair.second();

            var newDp = new HashMap<Integer, Integer>(dp);
            for (var entry : dp.entrySet()) {
                int mask = entry.getKey();
                int currentSum = entry.getValue();
                int rowMask = 1 << row;
                if ((mask & rowMask) == 0) {
                    int newMask = mask | rowMask;
                    newDp.put(newMask, Math.max(newDp.getOrDefault(newMask, 0), currentSum + val));
                }
            }

            dp = newDp;
        }

        return Collections.max(dp.values());
    }

    public int solve(int col, List<List<Integer>> grid, int[] set) {
        if (col == 0) {
            int maxPossible = 0;

            for (int v : grid.get(0)) {
                if (set[v] == 0) {
                    maxPossible = Math.max(maxPossible, v);
                }
            }

            return maxPossible;
        }

        int ans, maxAns = 0;

        for (int v : grid.get(col)) {
            if (set[v] == 0) {
                set[v] = 1;
                ans = solve(col - 1, grid, set);
                maxAns = Math.max(v + ans, maxAns);
                set[v] = 0;
            }
        }

        // In case in one middle column, every value is taken
        // we still want to consider going to the next column
        // without taking any value at all.
        ans = solve(col - 1, grid, set);
        maxAns = Math.max(ans, maxAns);

        return maxAns;
    }

    public int maxScoreBacktrack(List<List<Integer>> grid) {
        int n = grid.size();
        int[] set = new int[101];
        return solve(n - 1, grid, set);
    }

    public int numTeams(int[] rating) {
        int res = 0;

        for (int i = 0; i < rating.length; ++i) {
            int leftSmaller = 0;
            int leftBigger = 0;
            int rightSmaller = 0;
            int rightBigger = 0;

            for (int j = i - 1; j >= 0; --j) {
                if (rating[j] > rating[i]) {
                    leftBigger++;
                } else if (rating[j] < rating[i]) {
                    leftSmaller++;
                }
            }

            for (int k = i + 1; k < rating.length; k++) {
                if (rating[k] < rating[i]) {
                    rightSmaller++;
                } else if (rating[k] > rating[i]) {
                    rightBigger++;
                }
            }

            res += ((leftSmaller * rightBigger) + (leftBigger * rightSmaller));
        }

        return res;
    }
}
