public class Lecture13 {
    public static void main(String[] args) {
        int[] array = new int[] { -2, -3, 4, -1, -2, 1, 5, -3 };
        System.out.println(maximumSumSubarray(array));
        System.out.println(kadaneAlgorithm(array));
    }

    // Brute Force - O(N^2)
    public static int maximumSumSubarray(int[] array) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum = sum + array[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }

        }
        return maxSum;
    }

    // Optimised Using Kadane's Algorithm - O(N)
    public static int kadaneAlgorithm(int[] array) {
        int maxSum = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
            if (sum > maxSum)
                maxSum = sum;
            if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }
}
