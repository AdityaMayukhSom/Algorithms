import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSort {
    // Topological Sort Can Only Be Done In Directed Acyclic Graph
    // DAG - Directed Acyclic Graph
    // It can be implemented with the help of a stack
    public static void main(String[] args) {
        int vertex = 6;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < vertex; i++) {
            graph.add(new ArrayList<Integer>());
        }

        createGraph(graph);
        System.out.println("Topological Sort Is : ");
        topologicalSort(graph);
    }

    private static void topologicalSort(ArrayList<ArrayList<Integer>> graph) {
        boolean[] isVisited = new boolean[graph.size()];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
        Stack<Integer> sortStack = new Stack<Integer>();
        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                topologicalDFS(graph, isVisited, i, sortStack);
            }
        }
        while (sortStack.size() != 0) {
            System.out.print(sortStack.pop() + " ");
        }
    }

    private static void topologicalDFS(ArrayList<ArrayList<Integer>> graph, boolean[] isVisited, int n,
            Stack<Integer> sortStack) {
        if (!isVisited[n]) {
            isVisited[n] = true;
            for (Integer neighbour : graph.get(n)) {
                topologicalDFS(graph, isVisited, neighbour, sortStack);
            }
            sortStack.push(n);
        }
    }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        addEgde(graph, 0, 3);
        addEgde(graph, 0, 2);
        addEgde(graph, 1, 4);
        addEgde(graph, 2, 1);
        addEgde(graph, 2, 3);
        addEgde(graph, 3, 1);
        addEgde(graph, 5, 1);
        addEgde(graph, 5, 4);
    }

    private static void addEgde(ArrayList<ArrayList<Integer>> graph, int source, int destination) {
        graph.get(source).add(destination);
    }
}
