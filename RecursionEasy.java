public class RecursionEasy {
	public static void main(String[] args) {
		// System.out.println(sumUpto(10));
		// System.out.println(factorial(5));
		// for (int i = 1; i <= 10; i++) {
		// System.out.print(fibonacci(i));
		// System.out.print(" ");
		// }

		// System.out.println();

		// System.out.println(power(2, 40));
		System.out.println(morePower(2, 31));
	}

	public static int sumUpto(int n) {
		if (n == 0)
			return 0;
		return (n + sumUpto(n - 1));
	}

	public static int factorial(int n) {
		if (n == 0)
			return 1;
		return n * factorial(n - 1);
	}

	// Here n represents number of terms
	public static int fibonacci(int n) {
		if (n == 1)
			return 0;
		if (n == 2)
			return 1;
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static int power(int x, int n) {
		System.out.println("Power Called");
		if (x == 0)
			return 0;
		if (n == 0)
			return 1;
		return x * power(x, n - 1);
	}

	public static long morePower(int x, int n) {
		System.out.println("More Power Called.");
		if (x == 0)
			return 0;

		if (n == 0)
			return 1;
		long returnValue = morePower(x, n / 2);
		if ((n & 1) == 1)
			return x * returnValue * returnValue;
		else
			return returnValue * returnValue;
	}
}