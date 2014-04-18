/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/

import java.util.*;
import LCLibrary.*;

class Region {
    int leftMost, rightMost, upMost, downMost;
    HashSet<Integer> containedIdSet;
    int m, n;
    
    public Region(int m, int n) {
        leftMost = Integer.MAX_VALUE;
        rightMost = -1;
        upMost = Integer.MAX_VALUE;
        downMost = -1;
        containedIdSet = new HashSet<Integer>();
        this.m = m;
        this.n = n;
    }
    
    public void addId(int id) {
        containedIdSet.add(id);
        int i = id / n; // row
        int j = id % n; // column
        if(j < leftMost) leftMost = j;
        if(j > rightMost) rightMost = j;
        if(i < upMost) upMost = i;
        if(i > downMost) downMost = i;
    }
    
    public boolean isSurrounded() {
        if(leftMost == 0 || rightMost == n - 1 || upMost == 0 || downMost == m - 1) return false;
        else return true;
    }
}

class Solution {
    public char getCharFromId(char[][] board, int id) {
        int m = board.length, n = board[0].length;
        int i = id / n; // row
        int j = id % n; // column
        return board[i][j];
    }

    public void search(HashSet<Integer> visited, int id, Region region, Stack<Integer> stack, char[][] board) {
        if(!visited.contains(id)) {
            visited.add(id);
            if(getCharFromId(board, id) == 'O') {
                region.addId(id);
                stack.push(id);
            }
        }
    }

    public void solve(char[][] board) {
        if(board.length == 0) return;
        if(board[0].length == 0) return;
        int m = board.length, n = board[0].length;
        HashSet<Integer> visited = new HashSet<Integer>();
        ArrayList<Region> regionList = new ArrayList<Region>();
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j++) {
                int id = i * n + j;
                if(!visited.contains(id)) {
                    visited.add(id);
                    if(board[i][j] == 'O') {
                        Region region = new Region(m, n);
                        regionList.add(region);
                        region.addId(id);
                        Stack<Integer> stack = new Stack<Integer>();
                        stack.push(id);
                        while(!stack.isEmpty()) {
                            id = stack.pop();
                            // search left
                            int left = id - 1;
                            if(id % n != 0) search(visited, left, region, stack, board);
                            // search right
                            int right = id + 1;
                            if(right % n != 0) search(visited, right, region, stack, board);
                            // search down
                            int down = id + n;
                            if(down < m * n) search(visited, down, region, stack, board);
                            // search up
                            int up = id - n;
                            if(up >= 0) search(visited, up, region, stack, board);
                        }
                    }
                }
            }
        }

        for(Region region : regionList) {
            if(region.isSurrounded()) {
                // flip 'O' to 'X'
                for(int id : region.containedIdSet) {
                    int i = id / n; // row
                    int j = id % n; // column
                    board[i][j] = 'X';
                }
            }
        }
    }
}

/*
    Second Round
*/
class Solution2 {
    int M, N;   // board size
    // dfs to fill 'O' to '*', cannot pass online judge, stack overflow
    private void fill(char[][] board, int i, int j) {
        if(i < 0 || i >= M || j < 0 || j >= N || board[i][j] != 'O') return;
        board[i][j] = '*';  // current
        fill(board, i, j - 1);  // left
        fill(board, i, j + 1);  // right
        fill(board, i - 1, j);  // up
        fill(board, i + 1, j);  // down
    }

    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) return;
        M = board.length; N = board[0].length;
        // mark all out connected *
        for(int i = 0; i < M; i++) {
            fill(board, i, 0);
            fill(board, i, N - 1);
        }
        for(int j = 0; j < N; j++) {
            fill(board, 0, j);
            fill(board, M - 1, j);
        }
        // fill 'X', recover 'O'
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] == '*')      board[i][j] = 'O';
                else if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }
}

class Solution3 {
    int M, N;   // board size
    // bfs to fill 'O' to '*'
    private void fill(char[][] board, int i, int j) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(i * N + j);  // initial
        while(!queue.isEmpty()) {
            int id = queue.remove();
            i = id / N; j = id % N;
            if(board[i][j] != 'O') continue;
            board[i][j] = '*';
            if(j - 1 >= 0 && board[i][j - 1] == 'O') queue.add(i * N + (j - 1));    // left
            if(j + 1 < N && board[i][j + 1] == 'O') queue.add(i * N + (j + 1));     // right
            if(i - 1 >= 0 && board[i - 1][j] == 'O') queue.add((i - 1) * N + j);    // up
            if(i + 1 < M && board[i + 1][j] == 'O') queue.add((i + 1) * N + j);     // down
        }
    }

    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) return;
        M = board.length; N = board[0].length;
        // mark all out connected *
        for(int i = 0; i < M; i++) {
            fill(board, i, 0);
            fill(board, i, N - 1);
        }
        for(int j = 0; j < N; j++) {
            fill(board, 0, j);
            fill(board, M - 1, j);
        }
        // fill 'X', recover 'O'
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] == '*')      board[i][j] = 'O';
                else if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        String[] strs = {"XOOOOOOOOOOOOOOOOOOO","OXOOOOXOOOOOOOOOOOXX","OOOOOOOOXOOOOOOOOOOX","OOXOOOOOOOOOOOOOOOXO","OOOOOXOOOOXOOOOOXOOX","XOOOXOOOOOXOXOXOXOXO","OOOOXOOXOOOOOXOOXOOO","XOOOXXXOXOOOOXXOXOOO","OOOOOXXXXOOOOXOOXOOO","XOOOOXOOOOOOXXOOXOOX","OOOOOOOOOOXOOXOOOXOX","OOOOXOXOOXXOOOOOXOOO","XXOOOOOXOOOOOOOOOOOO","OXOXOOOXOXOOOXOXOXOO","OOXOOOOOOOXOOOOOXOXO","XXOOOOOOOOXOXXOOOXOO","OOXOOOOOOOXOOXOXOXOO","OOOXOOOOOXXXOOXOOOXO","OOOOOOOOOOOOOOOOOOOO","XOOOOXOOOXXOOXOXOXOO"};
        char[][] board = Input.buildRegionBoard(strs);
        Output.printMatrix(board);
        System.out.println();
        solution.solve(board);
        Output.printMatrix(board);
    }
}
