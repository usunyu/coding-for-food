class Solution {
    // dynamic programming solution
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int m = obstacleGrid.length;
        if(m == 0) return 0;
        int n = obstacleGrid[0].length;
        if(n == 0) return 0;
        // initial
        int[][] space = new int[m][n];
        for(int i = 0; i < m; i++) {
            if(obstacleGrid[i][0] != 1) space[i][0] = 1;
            else break;
        }
        for(int i = 0; i < n; i++) {
            if(obstacleGrid[0][i] != 1) space[0][i] = 1;
            else break;
        }
        // calculate
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(obstacleGrid[i][j] == 1) {
                    space[i][j] = 0;
                }
                else {
                    space[i][j] = space[i - 1][j] + space[i][j - 1];
                }
            }
        }
        return space[m - 1][n - 1];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{0,0,0}, {0,1,0}, {0,0,0}};
        System.out.println(solution.uniquePathsWithObstacles(grid));
    }
}