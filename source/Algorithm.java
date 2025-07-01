import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Algorithm {
    final FastIO fio;

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

    public Algorithm(final FastIO fio) {
        this.fio = fio;
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

    public int longestSubArrayLenNonNegative(final int[] A, final int k) {
        int n = A.length;

        if (n == 0) {
            return 0;
        }

        long sum = A[0];
        int maxLen = 0, left = 0, right = 0;

        while (right < n) {
            while (left <= right && sum > k) {
                sum -= A[left];
                left++;
            }

            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
            if (right < n) {
                sum += A[right];
            }
        }

        return maxLen;
    }

    public int majorityElement(int[] nums) {
        int cnt = 1;
        int maj = nums[0];
        int n = nums.length;

        for (int i = 1; i < n; ++i) {
            if (nums[i] == maj) {
                cnt++;
            } else {
                cnt--;
                if (cnt == 0) {
                    maj = nums[i];
                    cnt = 1;
                }
            }
        }

        cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == maj) {
                cnt++;
            }
        }

        if (cnt < ((n + 1) / 2)) {
            throw new IllegalArgumentException("no majority element exists");
        }

        return maj;
    }

    public int[] makePosNegArrayUnordered(int[] nums) {
        final int l = nums.length;
        int p = 0, n = 1, t;

        for (int i = 0; i < l; ++i) {
            if (i % 2 == 0 && nums[i] < 0) {
                while (n < l && nums[n] < 0) {
                    n += 2;
                }

                if (n < l) {
                    t = nums[i];
                    nums[i] = nums[n];
                    nums[n] = t;
                    n += 2;
                }
            }

            if (i % 2 == 1 && nums[i] > 0) {
                while (p < l && nums[p] > 0) {
                    p += 2;
                }

                if (p < l) {
                    t = nums[i];
                    nums[i] = nums[p];
                    nums[p] = t;
                    p += 2;
                }

            }
        }

        return nums;
    }

    public int[] twoSum(final int[] nums, final int target) {
        int[] indices = IntStream
            .range(0, nums.length)
            .boxed()
            .sorted((i, j) -> nums[i] - nums[j])
            .mapToInt(Integer::intValue)
            .toArray();

        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                break;
            }
        }

        return new int[] { indices[left], indices[right] };
    }

    public List<Integer> nextGreaterPermutation(List<Integer> original) {
        List<Integer> A = new ArrayList<>(original);
        int n = A.size();

        if (n == 0 || n == 1) {
            return A;
        }

        int point = -1;
        for (int i = n - 2; i >= 0; --i) {
            if (A.get(i) < A.get(i + 1)) {
                point = i;
                break;
            }
        }

        if (point == -1) {
            Collections.reverse(A);
            return A;
        }

        int minPos = point + 1;
        for (int i = point + 2; i < n; ++i) {
            if (A.get(point) < A.get(i) && A.get(i) <= A.get(minPos)) {
                minPos = i;
            }

            if (A.get(i) <= A.get(point)) {
                break;
            }
        }

        fio.pw.println("current minPos is " + minPos);

        // swap point and minPos
        A.set(point, A.get(point) ^ A.get(minPos));
        A.set(minPos, A.get(point) ^ A.get(minPos));
        A.set(point, A.get(point) ^ A.get(minPos));

        // reverse from point point + 1 to n - 1;
        int left = point + 1;
        int right = n - 1;

        while (left < right) {
            A.set(left, A.get(left) ^ A.get(right));
            A.set(right, A.get(left) ^ A.get(right));
            A.set(left, A.get(left) ^ A.get(right));
            left++;
            right--;
        }

        return A;
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
                List<Integer> L = IntStream.of(A).boxed().toList();
                // int k = fio.nextInt();
                // String s1 = fio.nextLine();
                // String s2 = fio.nextLine();

                Algorithm algo = new Algorithm(fio);
                var ans = algo.nextGreaterPermutation(L);

                fio.pw.println(ans);
            }
        } catch (Exception e) {
            System.err.println("Some Error Occured: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
