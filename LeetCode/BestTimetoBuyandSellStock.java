/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.
*/

class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int start = 0, max = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < prices[start]) {
                start = i;
            }
            else {
                int val = prices[i] - prices[start];
                if(val > max) {
                    max = val;
                }
            }
        }
        return max;
    }
}
/*
    Second Round
*/
class Solution2 {
    public int maxProfit(int[] prices) {
        int start = 0, max = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[start]) {
                int profit = prices[i] - prices[start];
                if(profit > max) {
                    max = profit;
                }
            }
            else {
                start = i;
            }
        }
        return max;
    }
}

/*
    Third Round
*/
class Solution3 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        int buy = 0, n = prices.length, max = 0;
        for(int i = 1; i < n; i++) {
            if(prices[i] > prices[buy]) {
                max = Math.max(prices[i] - prices[buy], max);
            }
            else {
                buy = i;
            }
        }
        return max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] prices = {1, 2, 1, 3, 0, 3};
        System.out.println(solution.maxProfit(prices));
    }
}
