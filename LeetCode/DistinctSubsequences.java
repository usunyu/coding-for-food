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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDistinct3("rabbbit", "rabbit"));
    }
}
