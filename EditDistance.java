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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String word1 = "dinitrophenylhydrazine", word2 = "acetylphenylhydrazine";
        System.out.println(solution.minDistance(word1, word2));
    }
}

