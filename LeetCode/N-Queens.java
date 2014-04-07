/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a 
queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public boolean checkValid(String[] queens, int row, int column) {
        // check cloumn
        for(int i = 0; i < row; i++) {
            if(queens[i].charAt(column) == 'Q') return false;
        }
        // check hypotenuse
        for(int i = row - 1; i >= 0; i--) {
            int j = row - i;
            if(column - j >= 0 && queens[i].charAt(column - j) == 'Q') return false;
            if(column + j < queens.length && queens[i].charAt(column + j) == 'Q') return false;
        }
        return true;
    }
    
    public String getQueen(int n, int column) {
        String queen = "";
        for(int i = 0; i < n; i++) {
            if(i == column) queen += "Q";
            else queen += ".";
        }
        return queen;
    }
    
    public void solveNQueens(int n, int level, String[] queens, ArrayList<String[]> nQueens) {
        if(level == n) nQueens.add(queens); // found one solution
        for(int i = 0; i < n; i++) {
            if(checkValid(queens, level, i)) {
                String[] validQueens = queens.clone();
                validQueens[level] = getQueen(n, i);
                solveNQueens(n, level + 1, validQueens, nQueens);
            }
        }
    }
    
    public ArrayList<String[]> solveNQueens(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<String[]> nQueens = new ArrayList<String[]>();
        solveNQueens(n, 0, new String[n], nQueens);
        return nQueens;
    }
    /*
        Second Round
    */
    private void set(String[] board, int row, int col, char val) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < board.length; i++) {
            if(i == col) sb.append(val);
            else sb.append('.');
        }
        board[row] = sb.toString();
    }
    
    private boolean valid(String[] board, int row, int col) {
        // check column
        for(int i = 0; i < row; i++) {
            if(board[i].charAt(col) == 'Q') return false;
        }
        // check diagonal
        for(int i = row - 1; i >= 0; i--) {
            int j = row - i;
            if(col - j >= 0 && board[i].charAt(col - j) == 'Q') return false;
            if(col + j < board.length && board[i].charAt(col + j) == 'Q') return false;
        }
        return true;
    }
    
    private void solve(int n, int level, String[] board, ArrayList<String[]> result) {
        if(n == level) {    // find a solution
            result.add(board.clone());
            return;
        }
        for(int i = 0; i < n; i++) {
            if(valid(board, level, i)) {
                set(board, level, i, 'Q');
                solve(n, level + 1, board, result);
                set(board, level, i, '.');   // backtracking
            }
        }
    }
    
    public ArrayList<String[]> solveNQueens2(int n) {
        ArrayList<String[]> result = new ArrayList<String[]>();
        solve(n, 0, new String[n], result);
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<String[]> nQueens = solution.solveNQueens2(4);
        Output.printStringArray(nQueens);
    }
}

