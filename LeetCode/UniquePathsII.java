/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

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

/*
    Second Round
*/
class Solution2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int M = obstacleGrid.length, N = obstacleGrid[0].length;
        for(int i = 0; i < M; i++)
            for(int j = 0; j < N; j++)
                if(obstacleGrid[i][j] == 1) obstacleGrid[i][j] = -1;
        if(obstacleGrid[0][0] == -1) return 0;
        obstacleGrid[0][0] = 1;
        for(int i = 1; i < N; i++) 
            if(obstacleGrid[0][i] == 0) 
                obstacleGrid[0][i] = obstacleGrid[0][i - 1];
        for(int i = 1; i < M; i++)
            if(obstacleGrid[i][0] == 0)
                obstacleGrid[i][0] = obstacleGrid[i - 1][0];
        for(int i = 1; i < M; i++) {
            for(int j = 1; j < N; j++) {
                if(obstacleGrid[i][j] == -1) continue;
                obstacleGrid[i][j] = 
                    (obstacleGrid[i - 1][j] == -1 ? 0 : obstacleGrid[i - 1][j]) + 
                    (obstacleGrid[i][j - 1] == -1 ? 0 : obstacleGrid[i][j - 1]);
            }
        }
        if(obstacleGrid[M - 1][N - 1] == -1) return 0;
        else return obstacleGrid[M - 1][N - 1];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{0,0,0}, {0,1,0}, {0,0,0}};
        System.out.println(solution.uniquePathsWithObstacles(grid));
    }
}