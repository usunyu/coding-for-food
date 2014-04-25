/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

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
/*
    Second Round
*/
class Solution2 {
    public int maxProfit(int[] prices) {
        int start = 0, length = prices.length;
        if(length == 0) return 0;
        int[] profitHistory = new int[length];
        // scan forward for profit history
        for(int i = 1; i < length; i++) {
            int profit = prices[i] - prices[start];
            if(profit >= 0) {
                profitHistory[i] = Math.max(profitHistory[i - 1], profit);
            }
            else {
                profitHistory[i] = profitHistory[i - 1];
                start = i;
            }
        }
        // scan back for two trans
        int end = length - 1, maxProfit = profitHistory[length - 1];
        for(int i = length - 2; i > 0; i--) {
            int profit = prices[end] - prices[i];
            if(profit >= 0) {
                maxProfit = Math.max(maxProfit, profit + profitHistory[i - 1]);
            }
            else {
                end = i;
            }
        }
        return maxProfit;
    }
}
/*
    Third Round
*/
class Solution3 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int max = 0, n = prices.length, buy = 0;
        int[] maxProfits = new int[n];
        // forward
        for(int i = 1; i < n; i++) {
            if(prices[i] > prices[buy]) {
                maxProfits[i] = Math.max(maxProfits[i - 1], prices[i] - prices[buy]);
            }
            else {
                maxProfits[i] = maxProfits[i - 1];
                buy = i;
            }
        }
        // backward
        int sell = n - 1; max = maxProfits[n - 1];
        for(int i = n - 2; i >= 1; i--) {
            if(prices[i] < prices[sell]) {
                max = Math.max(max, maxProfits[i - 1] + prices[sell] - prices[i]);
            }
            else {
                sell = i;
            }
        }
        return max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] prices = {2,1,2,0,1};
        System.out.println(solution.maxProfit(prices));
    }
}
