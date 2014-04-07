/*
Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. 
In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items 
respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that 
sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item, or 
don’t pick it (0-1 property).

http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
*/

class Main {
	public static int knapSack(int W, int[] weights, int[] values) {
		int n = weights.length;
		int[][] dp = new int[n + 1][W + 1];
		for(int i = 1; i <= n; i++) {
			for(int w = 1; w <= W; w++) {
				if(weights[i - 1] <= w)	// we can put this item
					dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
				else	// we cannot put this item
					dp[i][w] = dp[i - 1][w];
			}
		}
		return dp[n][W];
	}

	public static void main(String[] args) {
		int val[] = {60, 100, 120};
    	int wt[] = {10, 20, 30};
    	int  W = 50;
    	System.out.println(knapSack(W, wt, val));
	}
}