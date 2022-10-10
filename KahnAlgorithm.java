import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KahnAlgorithm {
    public static void main(String[] args) {
        int vertex = 6;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < vertex; i++) {
            graph.add(new ArrayList<Integer>());
        }

        createGraph(graph);
        topologicalSort(graph);
    }

    public static void topologicalSort(ArrayList<ArrayList<Integer>> graph) {
        int[] indegree = new int[graph.size()];
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < indegree.length; i++) {
            indegree[i] = 0;
        }

        for (int i = 0; i < graph.size(); i++) {
            for (Integer neighbour : graph.get(i)) {
                indegree[neighbour] = indegree[neighbour] + 1;
            }
        }
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        while (queue.size() != 0) {
            int currElem = queue.remove();
            System.out.print(currElem + " ");
            for (Integer neighbour : graph.get(currElem)) {
                indegree[neighbour] = indegree[neighbour] - 1;
                if (indegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

    }

    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int source, int destination) {
        graph.get(source).add(destination);
    }

    public static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        addEdge(graph, 0, 2);
        addEdge(graph, 0, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 1);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 1);
        addEdge(graph, 5, 1);
        addEdge(graph, 5, 4);
    }
}
