
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.StringTokenizer;

record Pair(int first, int second) {

}

record Trio(int first, int second, int third) {

}

final class FastReader {

    private StringTokenizer s;
    private final BufferedReader b;

    public FastReader() {
        try {
            InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");
            this.b = new BufferedReader(isr);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public FastReader(final String filename) {
        try {
            FileReader fr = new FileReader(filename);
            this.b = new BufferedReader(fr);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    private String next() {
        while (s == null || !s.hasMoreElements()) {
            try {
                s = new StringTokenizer(b.readLine());
            } catch (IOException ioe) {
                System.out.println("Trouble reading from the file: " + ioe.getMessage());
                // ioe.printStackTrace();
            }
        }

        return s.nextToken();
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

    public String nextLine() {
        String str = "";

        try {
            if (s.hasMoreTokens()) {
                str = s.nextToken("\n");
            } else {
                str = b.readLine();
            }
        } catch (IOException ioe) {
            System.out.println("Trouble reading from file: " + ioe.getMessage());
        }

        return str;
    }
}

public class Solution {

    private static FastReader fr;
    private static PrintWriter pw;

    public String findLCS(int m, int n, String s1, String s2) {
        if (m == 0 || n == 0) {
            return "";
        }

        int[][] dp = new int[m][n];

        int p = s2.indexOf(s1.charAt(0));
        int q = s1.indexOf(s2.charAt(0));

        for (int j = p; p >= 0 && j < n; ++j) {
            dp[0][j] = 1;
        }

        for (int i = q; q >= 0 && i < m; ++i) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(
                            dp[i][j - 1],
                            dp[i - 1][j]
                    );
                }
            }
        }

        p = m - 1;
        q = n - 1;
        pw.println(dp[p][q]);
        StringBuilder sb = new StringBuilder(dp[p][q]);

        while (p > 0 && q > 0) {
            if (s1.charAt(p) == s2.charAt(q)) {
                sb.insert(0, s1.charAt(p));
                p--;
                q--;
            } else {
                if (dp[p - 1][q] > dp[p][q - 1]) {
                    p--;
                } else {
                    q--;
                }
            }
        }

        if (dp[p][q] == 1) {
            // in this case, char at p and q will be equal
            // for zeroth row or column, all values are either
            // zero or one, hence checking for one is enough.
            sb.insert(0, (p == 0) ? s1.charAt(p) : s2.charAt(q));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        fr = new FastReader("./in.txt");
        pw = new PrintWriter("./op.txt");

        try {
            Solution sol = new Solution();
            // int t = fr.nextInt();

            // while (t-- > 0) {
            int m = fr.nextInt();
            int n = fr.nextInt();
            // int[] A = new int[n];

            // for (int i = 0; i < n; ++i) {
            //     A[i] = fr.nextInt();
            // }
            // int k = fr.nextInt();
            String s1 = fr.nextLine().strip();
            String s2 = fr.nextLine().strip();

            // whatever change you're doing in function calling do that here
            String ans = sol.findLCS(m, n, s1, s2);

            pw.println(ans);
            // }
        } catch (Exception e) {
            System.err.println("Some Error Occured: " + e.getMessage());
        } finally {
            pw.flush();
            pw.close();
        }
    }
}
