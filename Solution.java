import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    static class Pair {
        int node;
        int dist;

        Pair(final int node, final int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Pair))
                return false;
            Pair c = (Pair) o;
            return this.node == c.node;
        }

        @Override
        public String toString() {
            return "Pair [node=" + node + ", dist=" + dist + "]";
        }
    }

    static class FastReader implements AutoCloseable {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() throws IOException {
            File file = new File("input.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            this.br = new BufferedReader(isr);
        }

        String next() {
            while (this.st == null || !st.hasMoreElements()) {
                try {
                    this.st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        @Override
        public void close() throws IOException {
            this.br.close();
        }
    }

    public static List<Integer> shortestPath(int n, int m, int edges[][]) {
        int[] dist = new int[n];
        int[] prev = new int[n];

        int src = 0;
        int dest = n - 1;

        // in arraylist every pair contains node and weight
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Pair>());
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        for (int[] edge : edges) {
            adj.get(edge[0] - 1).add(new Pair(edge[1] - 1, edge[2]));
            adj.get(edge[1] - 1).add(new Pair(edge[0] - 1, edge[2]));
        }

        // in priority queue, every pair contains node and dist
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((e1, e2) -> (e1.dist - e2.dist));

        pq.offer(new Pair(src, 0));
        dist[src] = 0;

        Pair p, newP;
        int node, nbr, weight;
        while (!pq.isEmpty()) {
            p = pq.poll();
            node = p.node;
            for (Pair np : adj.get(node)) {
                // each dist in np is effectively the weight
                nbr = np.node;
                weight = np.dist;
                if ((dist[nbr] - weight) > dist[node]) {
                    newP = new Pair(nbr, dist[node] + weight);
                    pq.remove(newP);
                    pq.offer(newP);
                    dist[nbr] = newP.dist;
                    prev[nbr] = node;
                }
            }
        }

        List<Integer> ans = new LinkedList<>();

        if (prev[dest] == -1) {
            ans.add(-1);
            return ans;
        }

        int temp = dest;
        while (temp != -1) {
            ans.add(0, temp + 1);
            temp = prev[temp];
        }

        return ans;
    }

    public static void main(String[] args) {
        try (FastReader reader = new FastReader();) {
            // Solution sol = new Solution();
            int n = reader.nextInt();
            int m = reader.nextInt();
            int[][] edges = new int[m][3];
            for (int i = 0; i < m; ++i) {
                edges[i][0] = reader.nextInt();
                edges[i][1] = reader.nextInt();
                edges[i][2] = reader.nextInt();
            }
            System.out.println(shortestPath(n, m, edges));
        } catch (IOException e) {
            System.out.println("IO Exception occurred while reading input.");
            e.printStackTrace();
        }
    }
}
