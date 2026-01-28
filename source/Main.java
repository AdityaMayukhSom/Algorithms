import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;

class DiffRunner {
    private static record DiffSet(String d1, String d2, int count) {
    };

    // Colors https://gist.github.com/mgumiero9/665ab5f0e5e7e46cb049c1544a00e29f
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    private static final String NULL_REPR = "NIL";
    private static final char ADD_SIGN = '+';
    private static final char SUB_SIGN = '-';

    private void writeLineDiff(long lineNumber, DiffSet ds) {
        if (ds == null || ds.count() == 0) {
            return;
        }

        final String headStringFmt = "@@ L%d D%d @@\n";
        final String headString = String.format(headStringFmt, lineNumber, ds.count());

        final StringBuilder res = new StringBuilder();

        res.append(ANSI_CYAN).append(headString).append(ANSI_RESET);
        res.append(ANSI_RED).append(SUB_SIGN).append(ANSI_RESET);
        res.append(ds.d1());
        res.append('\n');
        res.append(ANSI_GREEN).append(ADD_SIGN).append(ANSI_RESET);
        res.append(ds.d2());

        System.out.println(res);
    }

    private DiffSet lineDiff(String l1, String l2) {

        if (l1 == null && l2 == null) {
            // No diff present if both are null
            return null;
        }

        if (l1 != null) {
            l1 = l1.stripTrailing();
        }

        if (l2 != null) {
            l2 = l2.stripTrailing();
        }

        StringBuilder diff1 = new StringBuilder();
        StringBuilder diff2 = new StringBuilder();

        int diffCount = 0;

        if (l1 == null || l2 == null) {
            if (l1 != null) {
                diff1.append(ANSI_RED).append(l1).append(ANSI_RESET);
                diff2.append(ANSI_PURPLE).append(NULL_REPR).append(ANSI_RESET);
                diffCount = l1.length();
            } else if (l2 != null) {
                diff1.append(ANSI_PURPLE).append(NULL_REPR).append(ANSI_RESET);
                diff2.append(ANSI_GREEN).append(l2).append(ANSI_RESET);
                diffCount = l2.length();
            }

            return new DiffSet(diff1.toString(), diff2.toString(), diffCount);
        }

        int mn = Math.min(l1.length(), l2.length());

        for (int i = 0; i < mn; i++) {
            final char c1 = l1.charAt(i);
            final char c2 = l2.charAt(i);

            if (c1 != c2) {
                diffCount++;
                diff1.append(ANSI_RED).append(c1).append(ANSI_RESET);
                diff2.append(ANSI_GREEN).append(c2).append(ANSI_RESET);
            } else {
                diff1.append(c1);
                diff2.append(c2);
            }
        }

        for (int i = mn; i < l1.length(); i++) {
            diffCount++;
            diff1.append(ANSI_RED).append(l1.charAt(i)).append(ANSI_RESET);
        }

        for (int i = mn; i < l2.length(); i++) {
            diffCount++;
            diff2.append(ANSI_GREEN).append(l2.charAt(i)).append(ANSI_RESET);
        }

        return new DiffSet(diff1.toString(), diff2.toString(), diffCount);
    }

    public void fileDiff(String target, String result) {

        final Path p1 = Path.of(target);
        final Path p2 = Path.of(result);

        try (
                final BufferedReader b1 = Files.newBufferedReader(p1);
                final BufferedReader b2 = Files.newBufferedReader(p2)) {

            System.out.println("--- " + p1.toRealPath());
            System.out.println("+++ " + p2.toRealPath());

            String l1, l2;
            long lineNumber = 0;

            do {
                l1 = b1.readLine();
                l2 = b2.readLine();
                DiffSet ds = lineDiff(l1, l2);
                writeLineDiff(++lineNumber, ds);
            } while (l1 != null || l2 != null);
        } catch (FileNotFoundException e) {
            System.err.println("unable to find file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("io exception occurred: " + e.getMessage());
        }
    }
}

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

    public FastIO(final String inputFilePath) {
        try {
            this.br = new BufferedReader(new FileReader(inputFilePath, StandardCharsets.UTF_8));
            this.pw = new PrintWriter(System.out);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
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

    public String readLine() {
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

    public int[][] readIntGrid(int m, int n) {
        int[][] M = new int[m][];
        for (int i = 0; i < m; ++i) {
            M[i] = this.readIntArray();
            assert M[i].length == n;
        }
        return M;
    }

    public int[][] readPairGraph(int E) {
        int[][] edges = new int[E][2];
        for (int i = 0; i < E; ++i) {
            edges[i][0] = readInt();
            edges[i][1] = readInt();
        }
        return edges;
    }

    public int[][] readTrioGraph(int E) {
        int[][] edges = new int[E][3];
        for (int i = 0; i < E; ++i) {
            edges[i][0] = readInt();
            edges[i][1] = readInt();
            edges[i][2] = readInt();
        }
        return edges;
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

    public void ln(int i) {
        pw.println(i);
    }

    public void ln(int[] ints) {
        StringBuilder sb = new StringBuilder();
        for (int i : ints) {
            sb.append(i);
            sb.append(' ');
        }
        ln(sb.toString());
    }

    public <T> void ln(List<T> list) {
        StringBuilder sb = new StringBuilder();
        for (T e : list) {
            sb.append(e);
            sb.append(' ');
        }
    }

    public void ln(int[][] grid) {
        ln(grid, false);
    }

    public void ln(int[][] grid, boolean printBarricade) {
        StringBuilder sb = new StringBuilder();

        if (printBarricade) {
            sb.append(BARRICADE);
            sb.append('\n');
        }

        for (int[] ints : grid) {
            for (int i : ints) {
                sb.append(i);
                sb.append(' ');
            }
            sb.append('\n');
        }

        if (printBarricade) {
            sb.append(BARRICADE);
        }

        ln(sb.toString());
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

class SegmentTree {
    private final int n;
    private final int[] nums;
    private final int[] tree;

    private final BiFunction<Integer, Integer, Integer> aggregate;
    private final int identity;

    public SegmentTree(int[] nums) {
        this(nums, (a, b) -> a + b, 0);
    }

    public SegmentTree(int[] nums, BiFunction<Integer, Integer, Integer> aggregate, int identity) {
        this.n = nums.length;
        this.nums = nums;
        this.tree = new int[4 * n];
        this.aggregate = aggregate;
        this.identity = identity;

        build(0, 0, n - 1);
    }

    private void build(int node, int s, int e) {
        if (s == e) {
            tree[node] = nums[s];
        } else {
            int m = (s + e) / 2;
            int lc = 2 * node + 1;
            int rc = 2 * node + 2;
            build(lc, s, m);
            build(rc, m + 1, e);
            tree[node] = aggregate.apply(tree[lc], tree[rc]);
        }
    }

    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private int query(int node, int s, int e, int l, int r) {
        if (e < l || r < s) {
            return identity;
        }

        if (l <= s && e <= r) {
            return tree[node];
        }

        int m = (s + e) / 2;
        int lc = 2 * node + 1;
        int rc = 2 * node + 2;
        int lans = query(lc, s, m, l, r);
        int rans = query(rc, m + 1, e, l, r);
        return aggregate.apply(lans, rans);
    }

    public void update(int i, int v) {
        if (i < 0 || i >= n) {
            final String fmt = "segment tree update index %d out of range for value %d.\n";
            System.err.printf(fmt, i, v);
            return;
        }

        update(0, 0, n - 1, i, v);
    }

    private void update(int node, int s, int e, int i, int v) {
        if (s == e) {
            nums[i] = v;
            tree[node] = v;
        } else {
            int m = (s + e) / 2;
            int lc = 2 * node + 1;
            int rc = 2 * node + 2;
            if (i <= m) {
                update(lc, s, m, i, v);
            } else {
                update(rc, m + 1, e, i, v);
            }
            tree[node] = aggregate.apply(tree[lc], tree[rc]);
        }
    }
}

class Commons {
    private static final int MOD = 1000000007;

    boolean isPrime(final int n) {
        if (n <= 1) {
            return false;
        }

        if (n <= 3) {
            return true;
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0) {
                return false;
            }

            if (n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    int gcd(int a, int b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }

        return (a == 0) ? b : a;
    }

    int fastPow(int a, int b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % MOD;
            }

            b >>= 1;
            a = (a * a) % MOD;
        }
        return (int) res;
    }

    int nCr(int n, int r) {
        long res = 1;
        r = Math.min(r, n - r);
        for (int i = 1; i <= r; i++) {
            res = ((res * (n - r + i)) / i) % MOD;
        }
        return (int) res;
    }
}

class Solution extends Commons {
    public int solve(int[] nums, int n) {
        return 0;
    }
}

public class Main {
    final static String ONLINE_JUDGE = "ONLINE_JUDGE";
    final static String IN_PATH = "./data/input.txt";
    final static String OUT_PATH = "./data/output.txt";
    final static String EXP_PATH = "./data/correct.txt";

    public static void tc(final FastIO io) {
        final int n = io.readInt();
        final int[] nums = io.readIntArray();
        final Solution sol = new Solution();
        var res = sol.solve(nums, n);
        io.ln(res);
    }

    public static void main(final String[] args) {
        boolean computeDiff = false;
        final boolean isLocal = (null == System.getProperty(ONLINE_JUDGE));

        try (final FastIO io = isLocal ? new FastIO(IN_PATH, OUT_PATH) : new FastIO()) {
            int t = io.readInt();

            while (t-- > 0) {
                tc(io);
            }
        } catch (Exception e) {
            System.err.println("encountered error :: " + e.getMessage());
            e.printStackTrace();
        }

        if (computeDiff && isLocal) {
            DiffRunner runner = new DiffRunner();
            runner.fileDiff(EXP_PATH, OUT_PATH);
        }
    }
}
