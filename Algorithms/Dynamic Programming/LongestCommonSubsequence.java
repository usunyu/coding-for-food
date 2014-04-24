/*
LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them. 
A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. 
For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. 
So a string of length n has 2^n different possible subsequences.

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