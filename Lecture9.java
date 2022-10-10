public class Lecture9 {
    public static void main(String[] args) {
        System.out.println(josephus(40, 7));
        System.out.println(matrixPath(10, 10));
    }

    public static int josephus(int n, int k) {
        if (n == 1) {
            return 1;
        }
        return (josephus(n - 1, k) + k - 1) % n + 1;
    }

    public static int matrixPath(int n, int m) {
        if (n == 1 || m == 1) {
            return 1;
        }
        return matrixPath(n - 1, m) + matrixPath(n, m - 1);
    }
}
