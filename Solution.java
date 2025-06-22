import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.StringTokenizer;

record Pair(int first, int second) {}

record Trio(int first, int second, int third) {}

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
            if (s != null && s.hasMoreTokens()) {
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

    public int longestCommonSubstr(String s1, String s2) {
        int ans = 0;

        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m][n];

        boolean match1 = false;
        for (int i = 0; i < m; ++i) {
            match1 = match1 || (s1.charAt(i) == s2.charAt(0));
            dp[i][0] = match1 ? 1 : 0;
        }

        boolean match2 = false;
        for (int j = 0; j < n; ++j) {
            match2 = match2 || (s1.charAt(0) == s2.charAt(j));
            dp[0][j] = match2 ? 1 : 0;
        }

        if (match1 || match2) {
            ans = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    int till = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? dp[i - 1][j - 1] : 0;
                    dp[i][j] = 1 + till;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        fr = new FastReader("./data/input.txt");
        pw = new PrintWriter("./data/output.txt");

        try {
            Solution sol = new Solution();
            // int t = fr.nextInt();

            // while (t-- > 0) {
            // int m = fr.nextInt();
            // int n = fr.nextInt();
            // int[] A = new int[n];
            // for (int i = 0; i < n; ++i) {
            //     A[i] = fr.nextInt();
            // }
            // int k = fr.nextInt();
            String s1 = fr.nextLine().strip();
            String s2 = fr.nextLine().strip();

            // whatever change you're doing in function calling do that here
            int ans = sol.longestCommonSubstr(s1, s2);

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
