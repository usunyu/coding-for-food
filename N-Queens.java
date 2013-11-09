import java.util.*;

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
}

class Main {
    public static void print(ArrayList<String[]> nQueens) {
        for(String[] queens : nQueens) {
            for(String queen : queens) {
                System.out.println(queen);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<String[]> nQueens = solution.solveNQueens(4);
        print(nQueens);
    }
}

