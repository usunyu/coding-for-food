/*
http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
*/

class Main {
	public static int minCost(int[][] cost) {
		int m = cost.length, n = cost[0].length;
		int[][] dp = new int[m][n];
		// initial
		dp[0][0] = cost[0][0];
		for(int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + cost[i][0];
		for(int i = 1; i < n; i++) dp[0][i] = dp[0][i - 1] + cost[0][i];
		// dp
		for(int i = 1; i < m; i++) {
			for(int j = 1; j < n; j++) {
				dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + cost[i][j];
			}
		}
		// ret
		return dp[m - 1][n - 1];
	}

	public static void main(String[] args) {
		int cost[][] = {
			{1, 2, 3},
			{4, 8, 2},
			{1, 5, 3}
		};
		System.out.println(minCost(cost));
	}
}