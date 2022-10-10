import java.util.Arrays;

public class Lecture6 {
    public static void main(String[] args) {
        seiveOfEratosthenes(7);
        System.out.println(gcd(110, 100));
    }

    public static int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (b == 0) {
            return a;
        }
        int p = a / b;
        int r = a - (p * b);
        return gcd(b, r);
    }

    public static void seiveOfEratosthenes(int n) {
        boolean[] arr = new boolean[n + 1];
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;
        for (int i = 2; (i * i) <= n; i++) {
            if (arr[i]) {
                for (int j = 2 * i; j <= n; j = j + i) {
                    arr[j] = false;
                }
            }
        }

        int primeCount = 0;
        for (int i = 0; i <= n; i++) {
            if (arr[i]) {
                primeCount++;
                System.out.println(i);
            }
        }
        System.out.println("Total Number Of Primes Upto " + n + " Is " + primeCount);
    }
}
