import java.util.*;

class Edit {
    int count;
    int index;
    String word;
    
    public Edit(int c, int i, String w) {
        count = c;
        index = i;
        word = w;
    }
}

class Solution {
    // Time Limit Exceeded
    public int minDistance(String word1, String word2) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        Stack<Edit> stack = new Stack<Edit>();
        // initial
        Edit e = new Edit(0, 0, word1);
        stack.push(e);
        int minSteps = Integer.MAX_VALUE;
        while(!stack.isEmpty()) {
            e = stack.pop();
            int index = e.index;
            int count = e.count;
            String word = e.word;
            while(index < word.length() && index < word2.length()) {
                if(word.charAt(index) == word2.charAt(index)) {
                    index++;
                }
                else break;
            }
            if(index == word.length() || index == word2.length()) {
                count += Math.abs(word.length() - word2.length());
                if(count < minSteps) minSteps = count;
            }
            else {
                char ch = word2.charAt(index);
                // 3 operations
                // add
                String temp = word.substring(0, index) + ch + word.substring(index);
                e = new Edit(count + 1, index + 1, temp);
                stack.push(e);
                // delete
                temp = word.substring(0, index) + word.substring(index + 1);
                e = new Edit(count + 1, index, temp);
                stack.push(e);
                // replace
                temp = word.substring(0, index) + ch + word.substring(index + 1);
                e = new Edit(count + 1, index + 1, temp);
                stack.push(e);
            }
        }
        return minSteps;
    }

    // dynamic programming solution
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if(m == 0 || n == 0) return m + n;
        int[][] space = new int[m + 1][n + 1];
        // initial
        for(int i = 0; i <= m; i++) space[i][0] = i;
        for(int i = 0; i <= n; i++) space[0][i] = i;
        // calculate
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    space[i][j] = space[i - 1][j - 1];
                }
                else {
                    int add = space[i - 1][j] + 1;
                    int del = space[i][j - 1] + 1;
                    int rep = space[i - 1][j - 1] + 1;
                    space[i][j] = Math.min(Math.min(add, del), rep);
                }
            }
        }
        return space[m][n];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String word1 = "sea", word2 = "ate";
        System.out.println(solution.minDistance2(word1, word2));
    }
}

