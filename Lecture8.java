public class Lecture8 {
    public static void main(String[] args) {
        printHello(5);
        System.out.println(sumUptoN(5));
    }

    public static void printHello(int n) {
        if (n == 0) {
            return;
        }
        System.out.println("Hello.");
        printHello(n - 1);
    }

    public static int sumUptoN(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sumUptoN(n - 1);
    }
}
