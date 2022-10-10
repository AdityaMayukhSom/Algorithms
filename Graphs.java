import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graphs {
    private static void printGraph(ArrayList<ArrayList<Integer>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print(i + " - ");
            for (int j = 0; j < graph.get(i).size(); j++) {
                System.out.print(graph.get(i).get(j) + " -> ");
            }
            System.out.println("\n");

        }
    }

    public static void main(String[] args) {
        int v = 5;
        // int e = 10; // No of edges, generally taken as input
        // int[][] a = new int[v + 1][v + 1];
        // addEdge(a, 3, 4);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<Integer>());
        }
        createGraph(graph);
        System.out.print("BFS is : ");
        bfs(graph, 0);
        System.out.print("\n");
        System.out.print("DFS is : ");
        dfsIterative(graph, 0);
        System.out.print("\n");
        System.out.print("DFS is : ");
        dfsRecursive(graph, 0);
        System.out.print("\n");
        printGraph(graph);
        if (isCyclic(graph, 5)) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
        System.out.print("\n");
    }

    private static boolean isCyclic(ArrayList<ArrayList<Integer>> graph, int N) {
        boolean[] visited = new boolean[N];
        boolean[] currentIterationVisited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (visited[i] == false) {
                if (checkCycle(graph, i, visited, currentIterationVisited) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkCycle(ArrayList<ArrayList<Integer>> graph, int n, boolean[] visited,
            boolean[] currentIterationVisited) {
        visited[n] = true;
        currentIterationVisited[n] = true;
        for (Integer it : graph.get(n)) {
            if (visited[it] == false)
                if (checkCycle(graph, it, visited, currentIterationVisited) == true) {
                    return true;
                } else if (currentIterationVisited[it] == true) {
                    return true;
                }
        }
        currentIterationVisited[n] = false;
        return false;
    }

    public static boolean isCycleUndirected(ArrayList<ArrayList<Integer>> graph, int start) {

        boolean[] isVisited = new boolean[graph.size()];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
        Stack<Integer[]> stack = new Stack<Integer[]>();
        stack.push(new Integer[] { start, -1 });
        while (stack.size() != 0) {
            Integer[] currNode = stack.pop();
            int value = currNode[0];
            int parent = currNode[1];
            if (isVisited[value] == true) {
                return true;
            }
            if (isVisited[value] == false) {
                for (int i = 0; i < graph.get(value).size(); i++) {
                    if (graph.get(value).get(i) != parent) {
                        stack.push(new Integer[] { graph.get(value).get(i), value });
                    }
                }
                isVisited[value] = true;
            }
        }
        return false;
    }

    public static void dfsIterative(ArrayList<ArrayList<Integer>> graph, int start) {
        boolean[] isVisited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            isVisited[i] = false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(start);
        isVisited[start] = true;
        while (stack.size() != 0) {
            int value = stack.pop();
            System.out.print(value + " ");
            for (int i = 0; i < graph.get(value).size(); i++) {
                if (isVisited[graph.get(value).get(i)] == false) {
                    stack.push(graph.get(value).get(i));
                    isVisited[graph.get(value).get(i)] = true;
                }
            }
        }
    }

    public static void dfsRecursive(ArrayList<ArrayList<Integer>> graph, int start) {
        boolean[] isVisited = new boolean[graph.size()];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
        dfs(graph, start, isVisited);
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int i, boolean[] isVisited) {
        if (isVisited[i] == false) {
            isVisited[i] = true;
            System.out.print(i + " ");
            for (int j = graph.get(i).size() - 1; j >= 0; j--) {
                dfs(graph, graph.get(i).get(j), isVisited);
            }
        }
    }

    public static void bfs(ArrayList<ArrayList<Integer>> graph, int start) {
        boolean[] isVisited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            isVisited[i] = false;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        while (queue.size() != 0) {
            int val = queue.remove();
            if (isVisited[val] == false) {
                for (int i = 0; i < graph.get(val).size(); i++) {
                    queue.add(graph.get(val).get(i));
                }
                isVisited[val] = true;
                System.out.print(val + " ");
            }
        }

    }

    // private static void addEdge(ArrayList<ArrayList<Integer>> graph, int node1,
    // int node2) {
    // graph.get(node1).add(node2);
    // graph.get(node2).add(node1);
    // }

    private static void addEdgeDirected(ArrayList<ArrayList<Integer>> graph, int source, int dest) {
        System.out.println(dest + " added to " + source);
        graph.get(source).add(dest);
    }

    // private static void addEdge(int[][] a, int source, int dest) {
    // a[source][dest] = 1;
    // a[dest][source] = 1;
    // }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        addEdgeDirected(graph, 0, 1);
        addEdgeDirected(graph, 1, 2);
        addEdgeDirected(graph, 2, 3);
        addEdgeDirected(graph, 3, 4);
        addEdgeDirected(graph, 4, 0);
        // addEdge(graph, 0, 1);
        // addEdge(graph, 0, 3);
        // addEdge(graph, 2, 3);
        // addEdge(graph, 3, 4);
        // addEdge(graph, 4, 2);
    }

    // addEdge(graph, 1, 2);
    // addEdge(graph, 1, 4);
    // addEdge(graph, 1, 9);
    // addEdge(graph, 2, 8);
    // addEdge(graph, 4, 3);
    // addEdge(graph, 4, 5);
    // addEdge(graph, 9, 5);
    // addEdge(graph, 8, 3);
    // addEdge(graph, 3, 6);
    // addEdge(graph, 6, 7);
    // addEdge(graph, 5, 10);
    // addEdge(graph, 3, 10);
    // addEdge(graph, 8, 4);
}