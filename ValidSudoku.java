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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(solution.isValidSudoku(board));
    }
}
