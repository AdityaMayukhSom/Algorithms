public class Lecture5 {
    public static void main(String[] args) {
        int x = numberOfTrailingZerosInFactorial(12);
        System.out.println(x);
        long y = factorial(7);
        System.out.println(y);
        if (isPalindrome(123321))
            System.out.println("Number Is Palindrome.");
        else
            System.out.println("Number Is Not Palindrome.");
    }

    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int numberOfTrailingZerosInFactorial(int n) {
        int answer = 0;
        for (int i = 5; i <= n; i = i * 5) {
            answer = answer + (n / i);
        }
        return answer;
    }

    public static boolean isPalindrome(int number) {
        int originalNumber = number;
        int reversedNumber = 0;

        while (number > 0) {
            reversedNumber = reversedNumber * 10 + number % 10;
            number = number / 10;
        }

        if (reversedNumber == originalNumber)
            return true;
        else
            return false;

    }
}
