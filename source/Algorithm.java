
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Job {

    public final int start;
    public final int end;
    public final int profit;

    public Job(final int _start, final int _end, final int _profit) {
        this.start = _start;
        this.end = _end;
        this.profit = _profit;
    }

    @Override
    public String toString() {
        return String.format("Job[start = %d, end = %d, profit = %d]", this.start, this.end, this.profit);
    }

}

public class Algorithm {

    /* Note that jobs are sorted based on the end time of the job. */
    private static int jobEndingBeforeStartTime(List<Job> jobs, int st, int l, int r) {
        int pos = -1, m;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (jobs.get(m).end <= st) {
                pos = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return pos;
    }

    private static long dfs(List<Job> jobs, int i, long[] dp) {
        if (i == 0) {
            return dp[0] = jobs.get(0).profit;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        // we can always pick the current job, and in case no previous job with
        // smaller end time exists, we can use current job's profit as if it is 
        // the only job that we've taken.
        long pick = jobs.get(i).profit;
        int prev = jobEndingBeforeStartTime(jobs, jobs.get(i).start, 0, i - 1);
        if (prev != -1) {
            pick += dfs(jobs, prev, dp);
        }

        long skip = dfs(jobs, i - 1, dp);
        return dp[i] = Math.max(pick, skip);
    }

    public static long findMaxProfit(int[] start, int[] end, int[] profit) {
        assert start.length == end.length;
        assert start.length == profit.length;

        int n = start.length;
        List<Job> jobs = new ArrayList<>(n);

        for (int i = 0; i < n; ++i) {
            Job job = new Job(start[i], end[i], profit[i]);
            jobs.add(job);
        }

        Collections.sort(jobs, (j1, j2) -> (j1.end - j2.end));

        long[] dp = new long[n];
        Arrays.fill(dp, -1);

        return dfs(jobs, n - 1, dp);
    }
}
