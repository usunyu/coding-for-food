/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
*/

import java.util.*;
import LCLibrary.*;

class Position {
    int x;
    int y;
    int bound;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        bound = 0;
    }
}

class Solution {
    private void checkColumn(char[][] board, int column, boolean[] flag) {
        for(int i = 0; i < 9; i++) {
            if(board[i][column] == '.') continue;
            else {
                int val = board[i][column] - '0';
                flag[val - 1] = true;
            }
        }
    }
    
    private void checkRow(char[][] board, int row, boolean[] flag) {
        for(int i = 0; i < 9; i++) {
            if(board[row][i] == '.') continue;
            else {
                int val = board[row][i] - '0';
                flag[val - 1] = true;
            }
        }
    }
    
    private void checkNine(char[][] board, int row, int column, boolean[] flag) {
        row /= 3; row *= 3;
        column /= 3; column *= 3;
        for(int i = row; i < row + 3; i++) {
            for(int j = column; j < column + 3; j++) {
                if(board[i][j] == '.') continue;
                else {
                    int val = board[i][j] - '0';
                    flag[val - 1] = true;
                }
            }
        }
    }
    
    // ret: -1 cannot get only
    private int getOnly(char[][] board, int row, int column) {
        boolean[] flag = new boolean[9];
        checkColumn(board, column, flag);
        checkRow(board, row, flag);
        checkNine(board, row, column, flag);
        int ret = -1, count = 0;
        for(int i = 0; i < 9; i++) {
            if(flag[i] == false) {
                ret = i + 1;
                count++;
            }
        }
        if(count == 1) return ret;
        else return -1;
    }

    private int getValid(char[][] board, int row, int column, int bound) {
        boolean[] flag = new boolean[9];
        checkColumn(board, column, flag);
        checkRow(board, row, flag);
        checkNine(board, row, column, flag);
        int ret = -1;
        for(int i = 0; i < 9; i++) {
            if(flag[i] == false && i + 1 > bound) { // must larger than low bound
                ret = i + 1;
                break;
            }
        }
        return ret;
    }

    private boolean isFinish(char[][] board, ArrayList<Position> emptyList) {
        // ckeck if finish
        boolean finish = true;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    finish = false;
                    Position pos = new Position(i, j);
                    emptyList.add(pos);
                }
            }
        }
        return finish;
    }
    
    public void solveSudoku(char[][] board) {
        // fill positions with only one choice
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    int val = getOnly(board, i, j);
                    if(val > 0) {
                        board[i][j] = (char)(val + '0');
                        i = 0; j = 0;
                    }
                }
            }
        }

        ArrayList<Position> emptyList = new ArrayList<Position>();
        if(!isFinish(board, emptyList)) {  // fill the rest positions
            for(int i = 0; i < emptyList.size(); i++) {
                Position pos = emptyList.get(i);
                int val = getValid(board, pos.x, pos.y, pos.bound);
                // cannot get valid
                if(val < 0) {
                    i -= 2;
                    // reset low bound and board
                    pos.bound = 0;
                    board[pos.x][pos.y] = '.';
                }
                else {
                    board[pos.x][pos.y] = (char)(val + '0');
                    pos.bound = val;
                }
            }
        }
    }
}

/*
    Second Round
*/
class Solution2 {
    int N;
    
    private void checkRow(char[][] board, int row, boolean[] occurred) {
        for(int i = 0; i < N; i++) {
            if(board[row][i] == '.') continue;
            int index = board[row][i] - '0' - 1;
            occurred[index] = true;
        }
    }
    
    
    private void checkCol(char[][] board, int col, boolean[] occurred) {
        for(int i = 0; i < N; i++) {
            if(board[i][col] == '.') continue;
            int index = board[i][col] - '0' - 1;
            occurred[index] = true;
        }
    }
    
    private void checkNine(char[][] board, int i, int j, boolean[] occurred) {
        int length = N / 3;
        int si = (i / length) * length, sj = (j / length) * length;
        for(int k = si; k < si + length; k++) {
            for(int l = sj; l < sj + length; l++) {
                if(board[k][l] == '.') continue;
                int index = board[k][l] - '0' - 1;
                occurred[index] = true;
            }
        }
    }
    
    private boolean[] checkAll(char[][] board, int i, int j) {
        boolean[] occurred = new boolean[N];
        checkRow(board, i, occurred);
        checkCol(board, j, occurred);
        checkNine(board, i, j, occurred);
        return occurred;
    }
    
    // return -1 if no certain number
    private int getCertainNumber(char[][] board, int i, int j) {
        boolean[] occurred = checkAll(board, i, j);
        int number = -1;
        for(int k = 0; k < N; k++) {
            if(occurred[k]) continue;
            else if(number == -1) number = k + 1;
            else {
                number = -1;
                break;
            }
        }
        return number;
    }
    
    // return valid number grater or equal to bound, or return -1
    private int getValid(char[][] board, int i, int j, int bound) {
        boolean[] occurred = checkAll(board, i, j);
        int number = -1;
        for(int k = bound - 1; k < N; k++) {
            if(!occurred[k]) {
                number = k + 1;
                break;
            }
        }
        return number;
    }
    
    public void solveSudoku(char[][] board) {
        N = board.length;
        // try to fill all certain number
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] == '.') {
                    int num = getCertainNumber(board, i, j);
                    if(num != -1) {
                        board[i][j] = (char)(num + '0');
                        i = 0; j = 0;   // restart to fill more number
                    }
                }
            }
        }
        // calculate, back tracking
        Stack<Integer> stack = new Stack<Integer>();    // for back tracking
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] == '.') {
                    int num = getValid(board, i, j, 1);
                    if(num != -1) {
                        int id = i * N + j;
                        stack.push(id); // for next use
                        board[i][j] = (char)(num + '0');
                    }
                    else {  // back tracking
                        while(!stack.isEmpty()) {
                            int id = stack.peek();
                            i = id / N; j = id % N;
                            int bound = board[i][j] - '0' + 1;
                            num = getValid(board, i, j, bound);
                            if(num != -1) {
                                board[i][j] = (char)(num + '0');
                                break;
                            }
                            else {
                                board[i][j] = '.';
                                stack.pop();
                            }
                        }
                    }
                }
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        char[][] board = Input.buildSudokuBoard();
        solution.solveSudoku(board);
        Output.printMatrix(board);
    }
}

