/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

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
    /*
        Second Round
    */
    public int longestValidParentheses2(String s) {
        int count = 0, begin = 0, longest = 0;
        // scan from left to right
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                count++;
            }
            else {
                count--;
            }
            if(count == 0) {
                longest = Math.max(longest, i - begin + 1);
            }
            else if(count < 0) {
                begin = i + 1;
                count = 0;
            }
        }
        count = 0; begin = s.length() - 1;
        // scan from right to left
        for(int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) == ')') {
                count++;
            }
            else {
                count--;
            }
            if(count == 0) {
                longest = Math.max(longest, begin - i + 1);
            }
            else if(count < 0) {
                begin = i - 1;
                count = 0;
            }
        }
        return longest;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses2("()(()"));
    }
}
