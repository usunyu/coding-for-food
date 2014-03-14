/*
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some 
(can be none) of the characters without disturbing the relative positions of the remaining characters. 
(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

class Solution {
    // recursion solution
    // Time Limit Exceeded
    public int numDistinct(String S, String T) {
        if(T.length() == 0) return 1;
        if(S.length() == 0) return 0;
        int ret = 0;
        if(S.charAt(0) == T.charAt(0)) {
            ret += numDistinct(S.substring(1), T.substring(1));
        }
        ret += numDistinct(S.substring(1), T);
        return ret;
    }

    // DP solution
    public int numDistinct2(String S, String T) {
        if(T.length() == 0) return 1;
        if(S.length() == 0) return 0;
        int[][] dpTable = new int[T.length()][S.length()];
        // initial
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == T.charAt(0)) {
                if(i > 0) dpTable[0][i] = dpTable[0][i - 1] + 1;
                else dpTable[0][i] = 1;
            }
            else {
                if(i > 0) dpTable[0][i] = dpTable[0][i - 1];
            }
        }
        for(int i = 1; i < T.length(); i++) {
            for(int j = i; j < S.length(); j++) {
                dpTable[i][j] = dpTable[i][j - 1];
                if(S.charAt(j) == T.charAt(i)) {
                    dpTable[i][j] += dpTable[i - 1][j - 1];
                }
            }
        }
        return dpTable[T.length() - 1][S.length() - 1];
    }

    // DP solution 2
    // space complexity : O(n)
    public int numDistinct3(String S, String T) {
        if(T.length() == 0) return 1;
        if(S.length() < T.length()) return 0;
        int[] dpTable = new int[S.length()];
        // initial
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == T.charAt(0)) {
                if(i > 0) dpTable[i] = dpTable[i - 1] + 1;
                else dpTable[i] = 1;
            }
            else {
                if(i > 0) dpTable[i] = dpTable[i - 1];
            }
        }
        for(int i = 1; i < T.length(); i++) {
            int last = dpTable[i - 1];
            for(int j = i; j < S.length(); j++) {
                int tmp = dpTable[j];
                if(i == j) dpTable[j] = 0;
                else dpTable[j] = dpTable[j - 1];
                if(S.charAt(j) == T.charAt(i)) {
                    dpTable[j] += last;
                }
                last = tmp;
            }
        }
        return dpTable[S.length() - 1];
    }
    /*
        Second Round
    */
    public int numDistinct4(String S, String T) {
        if(S.length() == 0) return 0;
        if(T.length() == 0) return 1;
        int[][] dp = new int[T.length()][S.length()];
        // initial
        for(int j = 0; j < S.length(); j++) {
            if(T.charAt(0) == S.charAt(j)) {
                if(j == 0) dp[0][j] = 1;
                else dp[0][j] = dp[0][j - 1] + 1;
            }
            else if(j > 0) dp[0][j] = dp[0][j - 1];
        }
        // dp
        for(int i = 1; i < T.length(); i++) {
            for(int j = i; j < S.length(); j++) {
                if(T.charAt(i) == S.charAt(j)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                }
                else dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[T.length() - 1][S.length() - 1];
    }

    public int numDistinct5(String S, String T) {
        if(S.length() == 0) return 0;
        if(T.length() == 0) return 1;
        int[] dp = new int[S.length()];
        // initial
        for(int j = 0; j < S.length(); j++) {
            if(T.charAt(0) == S.charAt(j)) {
                if(j == 0) dp[j] = 1;
                else dp[j] = dp[j - 1] + 1;
            }
            else if(j > 0) dp[j] = dp[j - 1];
        }
        // dp
        for(int i = 1; i < T.length(); i++) {
            int prev = dp[i - 1];
            for(int j = i; j < S.length(); j++) {
                if(j == i) dp[j - 1] = 0;
                int tmp = dp[j];
                if(T.charAt(i) == S.charAt(j)) {
                    dp[j] = dp[j - 1] + prev;
                }
                else dp[j] = dp[j - 1];
                prev = tmp;
            }
        }
        return dp[S.length() - 1];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDistinct5("rabbbit", "rabbit"));
    }
}
