public class CodingExercise5 {
    public static void main(String[] args) {
        System.out.println(fib(10));
    }

    public static int fib(int n) {
        // here n denotes the nth term
        if (n == 1)
            return 0;
        if (n == 2)
            return 1;
        return (fib(n - 1) + fib(n - 2));
    }
}
