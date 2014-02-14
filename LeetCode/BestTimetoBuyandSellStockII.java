class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int start = 0, prev = prices[start], max = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] >= prev) {
                prev = prices[i];
            }
            else {
                max += prev - prices[start];
                start = i;
                prev = prices[start];
            }
        }
        if(start < prices.length) {
            max += prev - prices[start];
        }
        return max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = {1, 2, 1, 3, 0, 3};
        System.out.println(solution.maxProfit(prices));
    }
}
