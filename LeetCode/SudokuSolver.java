import java.util.*;

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

class Main {
    public static void print(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
            {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
            {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
            {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
            {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
            {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
            {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
            {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
        };
        solution.solveSudoku(board);
        print(board);
    }
}

