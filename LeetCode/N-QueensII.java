/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
*/

class Solution {
    public boolean checkValid(int[][] board, int row, int col) {
        // check column
        for(int i = 0; i < row; i++) {
            if(board[i][col] == 1) return false;
        }
        // check hypotenuse
        for(int i = row - 1; i >= 0; i--) {
            int dis = row - i;
            if(col + dis < board.length && board[i][col + dis] == 1) return false;
            if(col - dis >= 0 && board[i][col - dis] == 1) return false;
        }
        return true;
    }
    
    public int totalNQueens(int n, int level, int[][] board) {
        if(level == n) return 1;
        int num = 0;
        for(int i = 0; i < n; i++) {
            if(checkValid(board, level, i)) {
                board[level][i] = 1;
                num += totalNQueens(n, level + 1, board);
                board[level][i] = 0;    // recover
            }
        }
        return num;
    }
    
    public int totalNQueens(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        return totalNQueens(n, 0, new int[n][n]);
    }
    /*
        Second Round
    */
    public boolean valid(int[][] board, int row, int col) {
        // check column
        for(int i = 0; i < row; i++) {
            if(board[i][col] == 1) return false;
        }
        // check diagonal
        for(int i = row - 1; i >= 0; i--) {
            int dis = row - i;
            if(col + dis < board.length && board[i][col + dis] == 1) return false;
            if(col - dis >= 0 && board[i][col - dis] == 1) return false;
        }
        return true;
    }
    
    public int solve(int n, int level, int[][] board) {
        if(level == n) return 1;
        int num = 0;
        for(int i = 0; i < n; i++) {
            if(valid(board, level, i)) {
                board[level][i] = 1;
                num += solve(n, level + 1, board);
                board[level][i] = 0;    // recover
            }
        }
        return num;
    }
    
    public int totalNQueens2(int n) {
        return solve(n, 0, new int[n][n]);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int num = solution.totalNQueens(4);
        System.out.println(num);
    }
}