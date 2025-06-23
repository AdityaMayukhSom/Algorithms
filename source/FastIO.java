
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public final class FastIO implements AutoCloseable {

    private StringTokenizer st;
    private final BufferedReader br;

    public final PrintWriter pw;

    public FastIO() {
        InputStreamReader isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        br = new BufferedReader(isr);
        pw = new PrintWriter(System.out);
    }

    public FastIO(final String inputFilePath, final String outputFilePath) {
        try {
            FileReader fr = new FileReader(inputFilePath, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            pw = new PrintWriter(outputFilePath);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public void printGrid(int[][] grid) {
        final String barrier = "------------------";

        pw.println(barrier);

        int m = grid.length;

        if (m == 0) {
            pw.println(barrier);
            return;
        }

        int n = grid[0].length;

        if (n == 0) {
            pw.println(barrier);
            return;
        }

        for (int[] ints : grid) {
            for (int j = 0; j < n; ++j) {
                pw.print(ints[j] + " ");
            }
            pw.println();
        }

        pw.println(barrier);
    }

    private String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException ioe) {
                System.out.println("Trouble reading from the file: " + ioe.getMessage());
            }
        }

        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public int[] nextIntArray() {
        String[] S = nextLine()
            .replace("[", "")
            .replace("]", "")
            .replace(",", " ")
            .split("\\s+");

        int[] A = new int[S.length];

        for (int i = 0; i < S.length; ++i) {
            A[i] = Integer.parseInt(S[i].strip());
        }

        return A;
    }

    public String nextLine() {
        String str = "";

        try {
            if (st != null && st.hasMoreTokens()) {
                str = st.nextToken("\n");
            } else {
                str = br.readLine();
            }
        } catch (IOException ioe) {
            System.out.println("Trouble reading from file: " + ioe.getMessage());
        }

        return str.strip();
    }

    @Override
    public void close() {
        try {
            pw.flush();
            br.close();
            pw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
