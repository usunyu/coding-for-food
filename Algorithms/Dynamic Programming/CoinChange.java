/*
Given a value N, if we want to make change for N cents, and we have infinite supply of 
each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? 
The order of coins doesn’t matter.

For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. 
So output should be 4. For N = 10 and S = {2, 5, 3, 6}, there are five solutions: 
{2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.

http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
*/

class Main {
	public static int count(int[] coins, int n) {
		int m = coins.length;
		int[][] dp = new int[n + 1][m];
		// initial
		for(int i = 0; i < m; i++) dp[0][i] = 1;
		// dp
		for(int i = 1; i < n + 1; i++) {
			for(int j = 0; j < m; j++) {
				// Count of solutions excluding S[j]
				dp[i][j] = (j > 0 ? dp[i][j - 1] : 0);
				// Count of solutions including S[j]
				dp[i][j] += (coins[j] <= i ? dp[i - coins[j]][j] : 0);
			}
		}
		return dp[n][m - 1];
	}

	public static int count2(int[] coins, int n) {
		int m = coins.length;
		int[] dp = new int[n + 1];
		// initial
		dp[0] = 1;
		// Pick all coins one by one and update the table[] values
    	// after the index greater than or equal to the value of the
    	// picked coin
		for(int i = 0; i < m; i++)
			for(int j = coins[i]; j <= n; j++)
				dp[j] += dp[j - coins[i]];
		return dp[n];
	}

	public static void main(String[] args) {
		int[] coins = {2, 5, 3, 6};
		System.out.println(count2(coins, 10));
	}
}