/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

import java.util.*;

class CharLocation {
    int row;
    int col;
    char ch;
    int index;
    
    public CharLocation(int row, int col, char ch) {
        this.row = row;
        this.col = col;
        this.ch = ch;
    }

    public CharLocation(CharLocation chLoc) {
        this.row = chLoc.row;
        this.col = chLoc.col;
        this.ch = chLoc.ch;
    }
}

class Solution {
    public void findCharLocation(char[][] board, HashMap<Character, ArrayList<CharLocation>> charLocationMap) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                char ch = board[i][j];
                CharLocation location = new CharLocation(i, j, ch);
                if(charLocationMap.containsKey(ch)) {
                    ArrayList<CharLocation> list = charLocationMap.get(ch);
                    list.add(location);
                }
                else {
                    ArrayList<CharLocation> list = new ArrayList<CharLocation>();
                    list.add(location);
                    charLocationMap.put(ch, list);
                }
            }
        }
    }
    
    // iteration
    // time complexity : O(NM)
    public boolean exist(char[][] board, String word) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(board == null) {
            return false;
        }
        int ROW = board.length;
        if(ROW == 0) {
            return false;
        }
        int COL = board[0].length;
        if(COL == 0) {
            return false;
        }
        HashMap<Character, ArrayList<CharLocation>> charLocationMap = new HashMap<Character, ArrayList<CharLocation>>();
        findCharLocation(board, charLocationMap);
        Stack<CharLocation> chStack = new Stack<CharLocation>();
        // initial
        ArrayList<CharLocation> firstLocationList = charLocationMap.get(word.charAt(0));
        if(firstLocationList == null) {
            return false;
        }
        for(CharLocation chLoc : firstLocationList) {
            // create new
            CharLocation temp = new CharLocation(chLoc);
            temp.index = 0;
            chStack.push(temp);
        }
        boolean[] visited = new boolean[ROW * COL];
        int curIndex = -1;
        Stack<Integer> visiteStack = new Stack<Integer>();
        while(!chStack.isEmpty()) {
            CharLocation chLoc = chStack.pop();
            int index = chLoc.index;

            // go back
            if(curIndex != -1 && index <= curIndex) {
                while(!visiteStack.isEmpty() && curIndex >= index) {
                    int id = visiteStack.pop();
                    visited[id] = false;
                    curIndex--;
                }
            }

            curIndex = index;

            int row = chLoc.row;
            int col = chLoc.col;

            int curId = row * COL + col;
            visited[curId] = true;
            visiteStack.push(curId);

            if(index == word.length() - 1) {
                return true;
            }
            char nextCh = word.charAt(index + 1);
            // search adjacent
            // down
            if(col < COL - 1 && board[row][col + 1] == nextCh) {
                int id = row * COL + col + 1;
                if(!visited[id]) {
                    CharLocation temp = new CharLocation(row, col + 1, nextCh);
                    temp.index = index + 1;
                    chStack.push(temp);
                }
                
            }
            // up
            if(col > 0 && board[row][col - 1] == nextCh) {
                int id = row * COL + col - 1;
                if(!visited[id]) {
                    CharLocation temp = new CharLocation(row, col - 1, nextCh);
                    temp.index = index + 1;
                    chStack.push(temp);
                }
            }
            // right
            if(row < ROW - 1 && board[row + 1][col] == nextCh) {
                int id = (row + 1) * COL + col;
                if(!visited[id]) {
                    CharLocation temp = new CharLocation(row + 1, col, nextCh);
                    temp.index = index + 1;
                    chStack.push(temp);
                }
            }
            // left
            if(row > 0 && board[row - 1][col] == nextCh) {
                int id = (row - 1) * COL + col;
                if(!visited[id]) {
                    CharLocation temp = new CharLocation(row - 1, col, nextCh);
                    temp.index = index + 1;
                    chStack.push(temp);
                }
            }
        }
        return false;
    }
}

class Solution2 {
    public boolean exist(char[][] board, String word) {
        HashMap<Character, ArrayList<Integer>> allNodes = preprocess(board);
        if (!allNodes.containsKey(word.charAt(0)))
            return false;

        boolean[] visited = new boolean[board.length * board[0].length];
        for (int node : allNodes.get(word.charAt(0))) {
            if (expand(allNodes, node, visited, word, 1, board[0].length))
                return true;
        }

        return false;
    }

    private boolean expand(HashMap<Character, ArrayList<Integer>> allNodes,
            int preNode, boolean[] visited, String word, int cur, int m) {
        if (cur == word.length())
            return true;
        if (!allNodes.containsKey(word.charAt(cur)))
            return false;

        visited[preNode] = true;
        for (int node : allNodes.get(word.charAt(cur))) {
            if (!visited[node] && isAdjacent(preNode, node, m)
                    && expand(allNodes, node, visited, word, cur + 1, m))
                return true;
        }
        visited[preNode] = false;

        return false;
    }

    private HashMap<Character, ArrayList<Integer>> preprocess(char[][] board) {
        HashMap<Character, ArrayList<Integer>> allNodes = new HashMap<Character, ArrayList<Integer>>();

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                ArrayList<Integer> nodes;
                if (!allNodes.containsKey(board[i][j])) {
                    nodes = new ArrayList<Integer>();
                    allNodes.put(board[i][j], nodes);
                } else {
                    nodes = allNodes.get(board[i][j]);
                }
                nodes.add(i * board[0].length + j);
            }
        }

        return allNodes;
    }

    private boolean isAdjacent(int n1, int n2, int m) {
        int min = Math.min(n1, n2), max = Math.max(n1, n2);
        return ((max - min) == m || (max - min == 1 && (max % m) != 0));
    }
}
/*
    Second Round
*/
class Solution3 {
    static int M, N;
    
    private boolean exist(char[][] board, String word, int index, int i, int j) {
        if(index == word.length()) return true;
        if(i < 0 || i >= M || j < 0 || j >= N || board[i][j] != word.charAt(index)) return false;
        char tmp = board[i][j];
        board[i][j] = '*';  // make sure we not use this char again

        // check up
        if(exist(board, word, index + 1, i - 1, j)) return true;
        // check down
        if(exist(board, word, index + 1, i + 1, j)) return true;
        // check right
        if(exist(board, word, index + 1, i, j + 1)) return true;
        // check left
        if(exist(board, word, index + 1, i, j - 1)) return true;

        board[i][j] = tmp;  // backtracking
        return false;
    }
    
    public boolean exist(char[][] board, String word) {
        if(board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) return false;
        M = board.length; N = board[0].length;
        char first = word.charAt(0);
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(exist(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        long begin = System.currentTimeMillis();
        System.out.println(solution.exist(board, "ABCESEEEFS"));
        long end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - begin) + " ms");
    }
}


