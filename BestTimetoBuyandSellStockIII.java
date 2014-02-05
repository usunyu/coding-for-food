class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int len = prices.length;
        int[] maxPrices = new int[len];
        int min = 0, max = len - 1;
        // scan for max price
        for(int i = 1; i < len; i++) {
            int current = prices[i] - prices[min];
            if(current >= 0) {
                if(current > maxPrices[i - 1]) maxPrices[i] = current;
                else maxPrices[i] = maxPrices[i - 1];
            }
            else {
                maxPrices[i] = maxPrices[i - 1];
                min = i;
            }
        }
        int maxProfit = maxPrices[len - 1];
        // scan for max profit for two trans
        for(int i = len - 2; i > 0; i--) {
            int current = prices[max] - prices[i];
            if(current >= 0) {
                int profit = current + maxPrices[i - 1];
                if(profit > maxProfit) maxProfit = profit;
            }
            else {
                max = i;
            }
        }
        return maxProfit;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = {1,2,4,2,5,7,2,4,9,0};
        System.out.println(solution.maxProfit(prices));
    }
}
