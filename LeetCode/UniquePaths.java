import java.util.*;

class Solution {
    // recursion solution
    // Time Limit Exceeded
    public int uniquePaths(int m, int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(m == 0 || n == 0) return 0;
        if(m == 1 && n == 1) return 1;
        int sum = 0;
        // move right
        sum += uniquePaths(m - 1, n);
        // move down
        sum += uniquePaths(m, n - 1);
        return sum;
    }

    // dynamic programming solution
    public int uniquePaths2(int m, int n) {
        int[][] space = new int[m][n];
        // initial
        for(int i = 0; i < m; i++) space[i][0] = 1;
        for(int i = 0; i < n; i++) space[0][i] = 1;
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                space[i][j] = space[i - 1][j] + space[i][j - 1];
            }
        }
        return space[m - 1][n - 1];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths2(23, 12));
    }
}