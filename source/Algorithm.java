import java.util.HashMap;
import java.util.Map;

public class Algorithm {

    static class Pair {

        public int first;
        public int second;

        public Pair(final int first, final int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Trio {

        public int first;
        public int second;
        public int third;

        public Trio(final int first, final int second, final int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
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

    public int longestSubarray(final int[] arr, final int k) {
        // mp -> (sum, pos)
        Map<Integer, Integer> mp = new HashMap<>();
        int dist = 0, sum = 0;

        mp.put(0, -1);

        for (int i = 0; i < arr.length; ++i) {
            sum += arr[i];

            if (!mp.containsKey(sum)) {
                mp.put(sum, i);
            }

            if (mp.containsKey(sum - k)) {
                int p = mp.get(sum - k);
                dist = Math.max(dist, i - p);
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        final String IN_PATH = "./data/input.txt";
        final String OUT_PATH = "./data/output.txt";

        try (FastIO fio = new FastIO(IN_PATH, OUT_PATH)) {
            int t = fio.nextInt();
            while (t-- > 0) {
                // int m = fr.nextInt();
                // int n = fio.nextInt();
                int[] A = fio.nextIntArray();
                int k = fio.nextInt();
                // String s1 = fio.nextLine();
                // String s2 = fio.nextLine();

                Algorithm algo = new Algorithm();
                int ans = algo.longestSubarray(A, k);

                fio.pw.println(ans);
            }
        } catch (Exception e) {
            System.err.println("Some Error Occured: " + e.getMessage());
        }
    }
}
