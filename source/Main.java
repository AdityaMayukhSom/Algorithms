
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

class FastIO implements AutoCloseable {

    private static final int MAX_READ_TOKEN_ERROR_COUNT = 8;
    private static final String BARRICADE = "-".repeat(36);

    private final PrintWriter pw;
    private final BufferedReader br;
    private StringTokenizer st;

    public FastIO() {
        this.br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        this.pw = new PrintWriter(System.out);
    }

    public FastIO(final String inputFilePath, final String outputFilePath) {
        try {
            this.br = new BufferedReader(new FileReader(inputFilePath, StandardCharsets.UTF_8));
            this.pw = new PrintWriter(outputFilePath);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    private void setupTokenizer() {
        int errorCount = 0;

        while (st == null || !st.hasMoreElements()) {
            try {
                String line = br.readLine();
                st = new StringTokenizer(line);
            } catch (IOException ioe) {
                System.out.println("trouble reading from input stream :: " + ioe.getMessage());
                errorCount++;

                if (errorCount > MAX_READ_TOKEN_ERROR_COUNT) {
                    String em = "maximum error threshold reached for reading tokens from input stream.";
                    throw new RuntimeException(em, ioe);
                }
            }
        }
    }

    private String readLine() {
        setupTokenizer();
        return st.nextToken("\n").strip();
    }

    public String readStr() {
        setupTokenizer();
        return st.nextToken();
    }

    public short readShort() {
        return Short.parseShort(readStr());
    }

    public int readInt() {
        return Integer.parseInt(readStr());
    }

    public long readLong() {
        return Long.parseLong(readStr());
    }

    public double readDouble() {
        return Double.parseDouble(readStr());
    }

    public float readFloat() {
        return Float.parseFloat(readStr());
    }

    public boolean readBool() {
        return Boolean.parseBoolean(readStr());
    }

    public String[] readStrArray() {
        return readLine()
                .replace("\"", "")
                .replace("[", "")
                .replace("]", "")
                .replace(",", " ")
                .split("\\s+");
    }

    public int[] readIntArray() {
        String[] S = readStrArray();
        int[] A = new int[S.length];

        for (int i = 0; i < S.length; ++i) {
            A[i] = Integer.parseInt(S[i].strip());
        }

        return A;
    }

    public List<Integer> readIntList() {
        String[] S = readStrArray();
        List<Integer> A = new ArrayList<>(S.length);

        for (String s : S) {
            A.add(Integer.valueOf(s.strip()));
        }

        return A;
    }

    public void ln() {
        pw.println();
    }

    public void ln(short s) {
        pw.print(s);
    }

    public void ln(int i) {
        pw.println(i);
    }

    public void ln(int[] list) {
        pw.println(Arrays.toString(list));
    }

    public void ln(int[][] grid) {
        ln(BARRICADE);

        StringBuilder sb = new StringBuilder();
        for (int[] ints : grid) {
            for (int i : ints) {
                sb.append(i);
                sb.append(' ');
            }
            sb.append('\n');
        }

        ln(sb.toString());
        ln(BARRICADE);
    }

    public void ln(long l) {
        pw.println(l);
    }

    public void ln(float f) {
        pw.println(f);
    }

    public void ln(double d) {
        pw.println(d);
    }

    public void ln(boolean b) {
        pw.println(b);
    }

    public void ln(char c) {
        pw.println(c);
    }

    public void ln(String s) {
        pw.println(s);
    }

    public void ln(String[] list) {
        pw.println(Arrays.toString(list));
    }

    public void ln(Object o) {
        pw.println(o);
    }

    @Override
    public void close() {
        try (pw; br) {
            pw.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

class Utils {

    static record Trio(int node, int cost, int jump) {

    }

    static record Pair(int node, int cost) {

    }

}

class Solution extends Utils {

    private static final int MOD = 1000000007;

    private static void performTopologicalSort(int node, final List<List<Pair>> graph, boolean[] vis, List<Integer> topo) {
        vis[node] = true;
        for (Pair nbr : graph.get(node)) {
            int nbrNode = nbr.node();
            if (!vis[nbrNode]) {
                performTopologicalSort(nbrNode, graph, vis, topo);
            }
        }
        topo.add(node);
    }

    public static int[] maximumDistance(final int V, final int E, final int S, final int[][] edges) {
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new Pair(v, w));
        }
        boolean[] vis = new boolean[V];
        List<Integer> topo = new ArrayList<>();
        for (int i = 0; i < V; ++i) {
            if (!vis[i]) {
                performTopologicalSort(i, graph, vis, topo);
            }
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[S] = 0;
        for (int i = V - 1; i >= 0; --i) {
            int node = topo.get(i);
            int cost = dist[node];
            if (cost == Integer.MIN_VALUE) {
                continue;
            }
            for (Pair nbr : graph.get(node)) {
                int nbrNode = nbr.node();
                int nbrCost = nbr.cost();
                int newCost = cost + nbrCost;
                dist[nbrNode] = Math.max(dist[nbrNode], newCost);
            }
        }
        return dist;
    }

    public static long minDeliveryTime(int delivery1, int delivery2, int charge1, int charge2) {
        long time = 0;
        int done1 = 0, done2 = 0;

        while (done1 < delivery1 || done2 < delivery2) {
            time++;

            // Drone1 charging?
            boolean c1 = (time % charge1 == 0);
            // Drone2 charging?
            boolean c2 = (time % charge2 == 0);

            if (!c1 && done1 < delivery1) {
                // Let Drone1 deliver
                done1++;
            } else if (!c2 && done2 < delivery2) {
                // Else let Drone2 deliver
                done2++;
            } else {
                // Both might be charging, so no delivery this hour
            }
        }
        return time;
    }

    public int calculateMinimumSwaps(List<Integer> fileSize, List<Integer> affinity) {
        int n = fileSize.size();

        Map<Integer, Integer> freqFile = new HashMap<>();
        Map<Integer, Integer> freqAffinity = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int cff = freqFile.getOrDefault(fileSize.get(i), 0);
            freqFile.put(fileSize.get(i), cff + 1);
            int cfa = freqAffinity.getOrDefault(affinity.get(i), 0);
            freqAffinity.put(affinity.get(i), cfa + 1);
        }

        for (int val : freqFile.keySet()) {
            int countFile = freqFile.get(val);
            int countAffinity = freqAffinity.getOrDefault(val, 0);

            // if too many of one value appear in fileSize, impossible to place them
            if (countFile > n - countAffinity) {
                return -1;
            }
        }

        List<Integer> matches = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int f = fileSize.get(i);
            int a = affinity.get(i);
            if (f == a) {
                matches.add(i);
            }
        }

        // Because one swap can fix 2 bad positions (if chosen well).
        int bad = matches.size();
        int minSwaps = (bad + 1) / 2;

        return minSwaps;
    }
}

public class Main {

    public static void main(String[] args) {
        final String ONLINE_JUDGE = "ONLINE_JUDGE";
        final String IN_PATH = "./data/input.txt";
        final String OUT_PATH = "./data/output.txt";

        boolean isLocal = System.getProperty(ONLINE_JUDGE) == null;

        try (FastIO io = isLocal ? new FastIO(IN_PATH, OUT_PATH) : new FastIO()) {
            int t = io.readInt();
            while (t-- > 0) {
                int n = io.readInt();
                var S = io.readIntList();
                var A = io.readIntList();

                // int[][] edges = new int[E][];
                //
                // for (int i = 0; i < E; ++i) {
                //     int[] edge = fio.nextIntArray();
                //     assert edge.length == 3;
                //     edges[i] = edge;
                // }
                // int D = fio.nextInt();
                // int K = fio.nextInt();
                // int[] F = fio.nextIntArray();
                // int[] B = fio.nextIntArray();
                // int k = fio.nextInt();
                // String s1 = fio.nextLine();
                // String s2 = fio.nextLine();
                Solution solution = new Solution();
                var ans = solution.calculateMinimumSwaps(S, A);
                io.ln(ans);
            }
        } catch (Exception e) {
            System.err.println("encountered error :: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
