/*
http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
*/

class Main {
	public static int LCS(String str1, String str2) {
		int m = str1.length(), n = str2.length();
		int[][] dp = new int[m + 1][n + 1];
		// dp
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(str1.charAt(i - 1) == str2.charAt(j - 1))	// match
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else	// not match
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[m][n];
	}

	public static void main(String[] args) {
		String str1 = "AGGTAB", str2 = "GXTXAYB";
		System.out.println(LCS(str1, str2));
	}
}