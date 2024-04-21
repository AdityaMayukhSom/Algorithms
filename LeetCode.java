import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<LinkedList<Integer>> adj = new ArrayList<>();
        int[] order = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            adj.add(new LinkedList<>());
        }

        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            // if there is a cyclic dependency on itself, return false
            if (prerequisites[i][0] == prerequisites[i][1]) {
                return new int[] {};
            }
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        int cnt = 0;
        int courses = numCourses;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[cnt++] = node;
            courses--;
            for (Integer nbr : adj.get(node)) {
                indegree[nbr]--;
                if (indegree[nbr] == 0)
                    queue.offer(nbr);
            }
        }
        return (courses == 0) ? order : new int[] {};
    }

    public boolean canFinish(
            final int numCourses,
            final int[][] prerequisites) {

        ArrayList<LinkedList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            adj.add(new LinkedList<>());
        }

        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            // if there is a cyclic dependency on itself, return false
            if (prerequisites[i][0] == prerequisites[i][1]) {
                return false;
            }
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        int courses = numCourses;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            courses--;
            for (Integer nbr : adj.get(node)) {
                indegree[nbr]--;
                if (indegree[nbr] == 0)
                    queue.offer(nbr);
            }
        }
        return (courses == 0);
    }

    private static int topoSortHelper(
            final ArrayList<ArrayList<Integer>> adj,
            int[] topo, boolean[] vis, final int node, final int idx) {
        vis[node] = true;
        int maxi = idx;
        for (Integer nbr : adj.get(node)) {
            if (!vis[nbr]) {
                maxi = topoSortHelper(adj, topo, vis, nbr, maxi);
            }
        }
        topo[vis.length - 1 - maxi] = node;
        return maxi + 1;
    }

    // Function to return list containing vertices in Topological order.
    static int[] topoSort(final int V, final ArrayList<ArrayList<Integer>> adj) {
        int[] topo = new int[V];
        boolean[] vis = new boolean[V];
        int maxi = 0;
        for (int i = 0; i < V; ++i) {
            if (!vis[i]) {
                maxi = topoSortHelper(adj, topo, vis, i, maxi);
            }
        }
        return topo;
    }
}
