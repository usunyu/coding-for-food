/*
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