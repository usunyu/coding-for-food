/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right 
corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.
*/

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
}

class Solution2 {
    // dynamic programming solution
    public int uniquePaths(int m, int n) {
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

/*
    Second Round
*/
// http://n00tc0d3r.blogspot.com/search?q=Unique+Paths
class Solution3 {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;

        int x = Math.min(m, n), y = Math.max(m, n);
        double count = 1;
        for (int i=1; i<x; ++i) {
            count *= (y + i - 1);
            count /= i;
        }

        return (int)count;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths2(23, 12));
    }
}