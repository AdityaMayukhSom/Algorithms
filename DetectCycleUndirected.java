import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    private static boolean dfs(ArrayList<ArrayList<Integer>> adj,
            boolean[] visited, int n, int m, int node, int parent) {
        boolean doesNotHaveCycle = true;
        for (int nbr : adj.get(node)) {

        }

        return doesNotHaveCycle;
    }

    public static String cycleDetection(int[][] edges, int n, int m) {
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        boolean doesNotHaveCycle = false;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int node = 0; node < n; node++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }
        dfs(null, visited, n, m, 0, -1);
        return doesNotHaveCycle ? "No" : "Yes";
    }
}
