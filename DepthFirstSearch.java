import java.util.ArrayList;

public class DepthFirstSearch {

    public static void main(String[] args) {
        int vertex = 7;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < vertex; i++) {
            graph.add(new ArrayList<Integer>());
        }
        createGraph(graph);
        dfs(graph);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph) {
        boolean[] isVisited = new boolean[graph.size()];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }

        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                dfs(graph, isVisited, i);
            }
        }

    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] isVisited, int n) {
        if (!isVisited[n]) {
            isVisited[n] = true;
            System.out.print(n + " ");
            for (Integer neighbour : graph.get(n)) {
                dfs(graph, isVisited, neighbour);
            }
        }

    }

    private static void addEgde(ArrayList<ArrayList<Integer>> graph, int source, int destination) {
        graph.get(source).add(destination);
        graph.get(destination).add(source);
    }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        addEgde(graph, 0, 1);
        addEgde(graph, 0, 4);
        addEgde(graph, 4, 3);
        addEgde(graph, 1, 3);
        addEgde(graph, 1, 2);
        addEgde(graph, 3, 6);
        addEgde(graph, 2, 5);
        addEgde(graph, 6, 5);
    }
}
