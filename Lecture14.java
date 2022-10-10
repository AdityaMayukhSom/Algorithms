public class Lecture14 {
    public static void main(String[] args) {
        int[] array = new int[] { 3, 1, 4, 8, 7, 2, 5 };
        System.out.println(bestTimeToBuyAndSellStock(array));
        System.out.println(optimisedBuyAndSellStocks(array));
    }

    // This function is going to return the maximum profit

    // Brute Force - O(N^2)
    public static int bestTimeToBuyAndSellStock(int[] stockPrices) {
        int maxProfit = 0;
        int profit = 0;
        for (int buyingDay = 0; buyingDay < stockPrices.length; buyingDay++) {
            for (int sellingDay = 0; sellingDay < stockPrices.length; sellingDay++) {
                profit = stockPrices[sellingDay] - stockPrices[buyingDay];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    // Optimised Solution - O(N)
    public static int optimisedBuyAndSellStocks(int[] stockPrices) {
        int lowestPrice = Integer.MAX_VALUE;
        int maximumProfit = Integer.MIN_VALUE;
        int profit = 0;
        for (int currentDay = 0; currentDay < stockPrices.length; currentDay++) {
            if (stockPrices[currentDay] < lowestPrice) {
                lowestPrice = stockPrices[currentDay];
            }
            profit = stockPrices[currentDay] - lowestPrice;
            if (profit > maximumProfit) {
                maximumProfit = profit;
            }
        }
        return maximumProfit;
    }
}
