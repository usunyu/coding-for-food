class Solution {
    public int longestValidParentheses(String s) {
        // from left to right
        int count = 0, longest = 0, index = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') count++;
            else count--;
            if(count == 0) {
                int length = i - index + 1;
                longest = Math.max(length, longest);
            }
            if(count < 0) {
                count = 0;
                index = i + 1;
            }
        }
        // from right to left
        count = 0; index = s.length() - 1;
        for(int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) == ')') count++;
            else count--;
            if(count == 0) {
                int length = index - i + 1;
                longest = Math.max(length, longest);
            }
            if(count < 0) {
                count = 0;
                index = i - 1;
            }
        }
        return longest;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses("()(()"));
    }
}
