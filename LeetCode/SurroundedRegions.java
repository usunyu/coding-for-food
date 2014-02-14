import java.util.*;

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

class Main {
    public static void print(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
                            {'X', 'O', 'X', 'O', 'O', 'O', 'O'}, 
                            {'X', 'O', 'O', 'O', 'O', 'O', 'O'},
                            {'X', 'O', 'O', 'O', 'O', 'X', 'O'},
                            {'O', 'O', 'O', 'O', 'X', 'O', 'X'},
                            {'O', 'X', 'O', 'O', 'O', 'O', 'O'},
                            {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                            {'O', 'X', 'O', 'O', 'O', 'O', 'O'},
                         };
        print(board);
        System.out.println();
        solution.solve(board);
        print(board);
    }
}
