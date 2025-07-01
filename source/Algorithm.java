import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Algorithm {

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

                Algorithm algo = new Algorithm();
                var ans = algo.nextGreaterPermutation(L);

                fio.pw.println(ans);
            }
        } catch (Exception e) {
            System.err.println("Some Error Occured: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
