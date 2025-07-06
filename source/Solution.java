
public class Solution {

    public static void main(String[] args) {
        final String IN_PATH = "./data/input.txt";
        final String OUT_PATH = "./data/output.txt";

        try (FastIO fio = new FastIO(IN_PATH, OUT_PATH)) {
            int t = fio.nextInt();
            while (t-- > 0) {
                // int m = fr.nextInt();
                // int n = fio.nextInt();
                int[] A = fio.nextIntArray();
                // List<Integer> L = IntStream.of(A).boxed().toList();
                // int k = fio.nextInt();
                // String s1 = fio.nextLine();
                // String s2 = fio.nextLine();

                Algorithm algo = new Algorithm();
                var ans = algo.threeSum(A);

                fio.pw.println(ans);
            }
        } catch (Exception e) {
            System.err.println("Some Error Occured: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
