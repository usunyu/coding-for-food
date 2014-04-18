/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/

import LCLibrary.*;

class Solution {
    private boolean checkColumn(char[][] board, int column) {
        boolean[] flag = new boolean[9];
        for(int i = 0; i < 9; i++) {
            if(board[i][column] == '.') continue;
            else {
                int val = board[i][column] - '0';
                if(flag[val - 1]) return false;
                else flag[val - 1] = true;
            }
        }
        return true;
    }
    
    private boolean checkRow(char[][] board, int row) {
        boolean[] flag = new boolean[9];
        for(int i = 0; i < 9; i++) {
            if(board[row][i] == '.') continue;
            else {
                int val = board[row][i] - '0';
                if(flag[val - 1]) return false;
                else flag[val - 1] = true;
            }
        }
        return true;
    }
    
    private boolean checkNine(char[][] board, int index1, int index2) {
        boolean[] flag = new boolean[9];
        for(int i = index1; i < index1 + 3; i++) {
            for(int j = index2; j < index2 + 3; j++) {
                if(board[i][j] == '.') continue;
                else {
                    int val = board[i][j] - '0';
                    if(flag[val - 1]) return false;
                    else flag[val - 1] = true;
                }
            }
        }
        return true;
    }
    
    public boolean isValidSudoku(char[][] board) {
        // ckeck column and row
        for(int i = 0; i < 9; i++) {
            if(!checkColumn(board, i)) return false;
            if(!checkRow(board, i)) return false;
        }
        // check nine
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!checkNine(board, i * 3, j * 3)) return false;
            }
        }
        return true;
    }
}
/*
    Second Round
*/
class Solution2 {
    static int N;
    
    private boolean checkRow(char[][] board, int row) {
        boolean[] occurred = new boolean[N];
        for(int i = 0; i < N; i++) {
            if(board[row][i] == '.') continue;
            int index = board[row][i] - '1';
            if(occurred[index]) return false;
            else occurred[index] = true;
        }
        return true;
    }
    
    private boolean checkCol(char[][] board, int col) {
        boolean[] occurred = new boolean[N];
        for(int i = 0; i < N; i++) {
            if(board[i][col] == '.') continue;
            int index = board[i][col] - '1';
            if(occurred[index]) return false;
            else occurred[index] = true;
        }
        return true;
    }
    
    private boolean checkNine(char[][] board, int row, int col) {
        boolean[] occurred = new boolean[N];
        for(int i = row; i < row + N / 3; i++) {
            for(int j = col; j < col + N / 3; j++) {
                if(board[i][j] == '.') continue;
                int index = board[i][j] - '1';
                if(occurred[index]) return false;
                else occurred[index] = true;
            }
        }
        return true;
    }
    
    public boolean isValidSudoku(char[][] board) {
        N = board.length;
        // check row
        for(int i = 0; i < N; i++)
            if(!checkRow(board, i)) return false;
        // check column
        for(int i = 0; i < N; i++)
            if(!checkCol(board, i)) return false;
        // check nine
        for(int i = 0; i < N; i += N / 3)
            for(int j = 0; j < N; j+= N / 3)
                if(!checkNine(board, i, j))
                    return false;
        return true;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        char[][] board = Input.buildSudokuBoard();
        System.out.println(solution.isValidSudoku(board));
    }
}
