/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes 
the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/

class Solution {
    // dynamic programming solution
    public int minPathSum(int[][] grid) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int m = grid.length;
        if(m == 0) return 0;
        int n = grid[0].length;
        if(n == 0) return 0;
        int[][] space = new int[m][n];
        // initial
        space[0][0] = grid[0][0];
        for(int i = 1; i < n; i++) {
            space[0][i] = space[0][i - 1] + grid[0][i];
        }
        for(int i = 1; i < m; i++) {
            space[i][0] = space[i - 1][0] + grid[i][0];
        }
        // calculate
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                int prev = Math.min(space[i - 1][j], space[i][j - 1]);
                space[i][j] = grid[i][j] + prev;
            }
        }
        return space[m - 1][n - 1];
    }
    /*
        Second Round
    */
    public int minPathSum2(int[][] grid) {
        int M = grid.length;
        if(M == 0) return 0;
        int N = grid[0].length;
        if(N == 0) return 0;
        int[][] dp = new int[M][N];
        // initial
        dp[0][0] = grid[0][0];
        for(int i = 1; i < N; i++) dp[0][i] = dp[0][i - 1] + grid[0][i];
        for(int i = 1; i < M; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        // dynamic programming
        for(int i = 1; i < M; i++) {
            for(int j = 1; j < N; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[M - 1][N - 1];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{2,1,3},{3,1,5},{1,2,4}};
        System.out.println(solution.minPathSum(grid));
    }
}
