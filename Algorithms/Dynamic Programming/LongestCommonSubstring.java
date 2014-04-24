/*
Given two strings ‘X’ and ‘Y’, find the length of the longest common substring. For example, 
if the given strings are “GeeksforGeeks” and “GeeksQuiz”, the output should be 5 as longest common 
substring is “Geeks”

http://www.geeksforgeeks.org/longest-common-substring/
*/

class Main {
	public static int LCS(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		int[][] dp = new int[m + 1][n + 1];
		int max = 0;
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(dp[i][j], max);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		String s1 = "GeeksforGeeks", s2 = "GeeksQuiz";
		System.out.println(LCS(s1, s2));
	}
}