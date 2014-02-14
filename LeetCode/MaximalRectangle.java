class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    // referecne: http://discuss.leetcode.com/questions/260/maximal-rectangle
    // H(i, j): the length of "hanging line" at (i, j)
    // L(i, j): the leftmost of point at (i, j)
    // R(i, j): the rightmost of point at (i, j)
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, result = 0;
        int[] H = new int[n];
        int[] L = new int[n];
        int[] R = new int[n];
        for(int i = 0; i < n; i++) R[i] = n;
        for(int i = 0; i < m; i++) {
            int left = 0, right = n;
            // calculate L(i, j) from left to right
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    H[j]++;
                    L[j] = Math.max(L[j], left);
                }
                else {
                    left = j + 1;
                    H[j] = 0; L[j] = 0; R[j] = n;
                }
            }
            // calculate R(i, j) from right to right
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] == '1') {
                    R[j] = Math.min(R[j], right);
                    result = Math.max(result, (R[j] - L[j]) * H[j]);
                }
                else {
                    right = j;
                }
            }
        }
        return result;
    }

    // reference: https://github.com/AnnieKim/LeetCode/blob/master/MaximalRectangle.h
    // DP. (72 milli secs for the large). (if using Cpp!)
    // a) dp[i][j] records the number of consecutive '1' on the left and up of the element matrix[i][j].
    // b) For each element(i,j), calculate the area of rectangle including the element itself.
    public int maximalRectangle2(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, result = 0;
        Pair[][] dp = new Pair[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '0') {
                    dp[i][j] = new Pair(0, 0);
                    continue;
                }
                int x = (j == 0) ? 1 : dp[i][j - 1].x + 1;
                int y = (i == 0) ? 1 : dp[i - 1][j].y + 1;
                dp[i][j] = new Pair(x, y);

                int minHeight = y;
                for (int k = j; k > j - x; --k){
                    minHeight = Math.min(minHeight, dp[i][k].y);
                    result = Math.max(result, minHeight * (j - k + 1));
                }
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] matrix = {
            {'0','1','1','1','0'},
            {'0','1','1','1','1'},
            {'0','0','1','0','0'}
        };
        System.out.println(solution.maximalRectangle2(matrix));
    }
}
