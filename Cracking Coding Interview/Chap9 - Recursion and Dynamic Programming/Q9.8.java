/*
Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents) and pennies (1 cent), 
write code to calculate the number of ways of representing n cents.
*/
class Solution {
	static int count = 0;

	public static void getChange(int n, int coin) {
		if(n < 0)
			return;

		if(n == 0) {	// one combo
			count++;
			return;
		}

		int nextCoin = 0;

		switch(coin) {
			case 25:
				nextCoin = 10;
				break;
			case 10:
				nextCoin = 5;
				break;
			case 5:
				nextCoin = 1;
				break;
			case 1:
				nextCoin = -1;
				break;
			default:
				return;
		}

		for(int i = 0; i * coin <= n; i++) {
			getChange(n - i * coin, nextCoin);
		}
	}

	public static void main(String[] args) {
		for(int n = 1; n <= 100; n++) {
			getChange(n, 25);
			System.out.println("getChange(" + n + "): " + count);
			count = 0;	// reset
		}
	}
}
/*
	Second Round
*/
class Solution2 {
	// coin: 1, 5, 10, 25
	// space m * n
	public static int getChange(int n, int[] coins) {
		int m = coins.length;
		int[][] dp = new int[n + 1][m];
		for(int i = 0; i < m; i++) dp[0][i] = 1;
		// dp
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j < m; j++) {
				// including current coin
				dp[i][j] += (i - coins[j] >= 0 ? dp[i - coins[j]][j] : 0);
				// excluding current coin
				dp[i][j] += (j -1 >= 0 ? dp[i][j - 1] : 0);
			}
		}
		return dp[n][m - 1];
	}

	// space n
	public static int getChange2(int n, int[] coins) {
		int m = coins.length;
		int[] dp = new int[n + 1];
		dp[0] = 1;
		// dp
		for(int j = 0; j < m; j++) {
			for(int i = coins[j]; i <= n; i++) {
				dp[i] += dp[i - coins[j]];
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		int[] coins = {1, 5, 10, 25};
		for(int n = 1; n <= 100; n++) {
			System.out.println("getChange(" + n + "): " + getChange(n, coins));
		}
	}
}