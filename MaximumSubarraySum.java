public class MaximumSubarraySum {
    static int cnt = 0;

    public static int rec(int[] a, int[] b, int start, int end, int k) {
        if (start > end || k == 0) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += a[i];
            }
            cnt++;
            // System.out.printf("sum for %d %d %d is %d\n", start, end, k, sum);
            return sum;
        }

        int ans = Integer.MIN_VALUE;

        for (int i = start; i < end; i++) {
            // add ith index and decrease k
            a[i] = a[i] + b[i];
            int p1 = rec(a, b, start, end, k - 1);
            a[i] = a[i] - b[i];
            ans = Math.max(ans, p1);
        }

        // remove from left
        int p2 = rec(a, b, start + 1, end, k);

        // remove from right
        int p3 = rec(a, b, start, end - 1, k);

        ans = Math.max(ans, p2);
        ans = Math.max(ans, p3);

        return ans;
    }

    public static int solve(int[] a, int[] b, int k, int n) {
        return rec(a, b, 0, n, k);
    }

    public static void main(String[] args) {
        int[] a = new int[] { -1000, 2, -5 };
        int[] b = new int[] { 1000, 10, 100 };
        int k = 3;

        System.out.println(solve(a, b, k, 3));
        System.out.println(cnt);
    }
}
