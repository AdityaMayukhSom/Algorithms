public class Lecture7 {
    public static void main(String[] args) {
        // (a + b) % n = (a % n + b % n) % n
        // (a * b) % n = (a % n * b % n) % n'
        System.out.println(moduloPower(2312, 3434, 6789));
    }

    public static int moduloPower(int a, int b, int n) {
        if (b == 1) {
            return a % n;
        }

        if ((b & 1) == 0) {
            long y = moduloPower(a, b >> 1, n);
            return (int) ((y % n) * (y % n)) % n;
        } else {
            long k = moduloPower(a, b - 1, n);
            int y = (int) k % n;
            return ((a % n) * y) % n;
        }
    }
}
