public class Lecture15 {
    public static void main(String[] args) {
        int[] stockPrice = new int[] { 5, 2, 6, 1, 4, 7, 3, 6 };
        System.out.println(maximumProfit(stockPrice));
    }

    public static int maximumProfit(int[] array) {
        int profit = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                profit = profit + (array[i] - array[i - 1]);
            }
        }
        return profit;
    }
}
