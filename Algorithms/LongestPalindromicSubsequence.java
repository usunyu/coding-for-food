/*
// Everay single character is a palindrom of length 1
L(i, i) = 1 for all indexes i in given sequence

// IF first and last characters are not same
If (X[i] != X[j])  L(i, j) =  max{L(i + 1, j),L(i, j - 1)} 

// If there are only 2 characters and both are same
Else if (j == i + 1) L(i, j) = 2  

// If there are more than two characters, and first and last 
// characters are same
Else L(i, j) =  L(i + 1, j - 1) + 2 

http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
*/

class Main {
	// Returns the length of the longest palindromic subsequence in seq
	public static int lps(String str) {
		int n = str.length();
		int[][] dp = new int[n][n];	// Create a table to store results of subproblems
		// Strings of length 1 are palindrome of lentgh 1
		for(int i = 0; i < n; i++) dp[i][i] = 1;
		// Build the table. Note that the lower diagonal values of table are
    	// useless and not filled in the process. The values are filled in a
    	// manner similar to Matrix Chain Multiplication DP solution (See
    	// http://www.geeksforgeeks.org/archives/15553). cl is length of
    	// substring
		for(int length = 2; length <= n; length++) {
			for(int i = 0; i < n - length + 1; i++) {
				int j = i + length - 1;
				if(str.charAt(i) == str.charAt(j) && length == 2)
					dp[i][j] = 2;
				else if(str.charAt(i) == str.charAt(j))
					dp[i][j] = dp[i + 1][j - 1] + 2;
				else
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
			}
		}
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		String str = "GEEKS FOR GEEKS";
		System.out.println("The lnegth of the LPS is " + lps(str));
	}
}