/*
k ==> Number of floors

n ==> Number of Eggs

eggDrop(n, k) ==> Minimum number of trails needed to find the critical floor in worst case.

eggDrop(n, k) = 1 + min{max(eggDrop(n - 1, x - 1), eggDrop(n, k - x)): x in {1, 2, ..., k}}

http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
*/

class Main {
	public static int dropEgg(int n, int k) {
		int[][] dp = new int[n + 1][k + 1];
		// initial
		// We need one trial for one floor and 0 trials for 0 floors
		for(int i = 1; i <= n; i++) dp[i][1] = 1;
		// We always need j trials for one egg and j floors.
		for(int i = 1; i <= k; i++) dp[1][i] = i;
		// dp
		// Fill rest of the entries in table using optimal substructure property
		for(int i = 2; i <= n; i++) {
			for(int j = 2; j <= k; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int x = 1; x <= j; x++) {
					int res = 1 + Math.max(dp[i - 1][x - 1], dp[i][j - x]);
					if(res < dp[i][j])
						dp[i][j] = res;
				}
			}
		}
		// ret
		return dp[n][k];
	}

	public static void main(String[] args) {
		int n = 2, k = 36;
		System.out.println("Minimum number of trials in worst case with " + n + " eggs and " + k + " floors is " + dropEgg(n, k));
	}
}