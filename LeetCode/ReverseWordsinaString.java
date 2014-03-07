/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

click to show clarification.

Clarification:
    What constitutes a word?
    A sequence of non-space characters constitutes a word.
    Could the input string contain leading or trailing spaces?
    Yes. However, your reversed string should not contain leading or trailing spaces.
    How about multiple spaces between two words?
    Reduce them to a single space in the reversed string.
*/

import java.util.Stack;

class Solution {
    public String reverseWords(String s) {
        if(s.length() == 0) return "";
        StringBuilder stringBuilder = new StringBuilder(s);
        // truncate leading and trailing spaces
        int start = 0, end = stringBuilder.length() - 1;
        while(start < stringBuilder.length() && stringBuilder.charAt(start) == ' ') start++;
        while(end >= 0 && stringBuilder.charAt(end) == ' ') end--;
        if(start > end) return "";
        Stack<StringBuilder> stack = new Stack<StringBuilder>();
        while(start <= end) {
            // find word
            int ptr = start;
            StringBuilder word = new StringBuilder();
            while(ptr <= end && stringBuilder.charAt(ptr) != ' ') {
                word.append(stringBuilder.charAt(ptr));
                ptr++;
            }
            // store word in stack
            stack.push(word);
            // skip next space
            while(ptr <= end && stringBuilder.charAt(ptr) == ' ') ptr++;
            start = ptr;
        }
        StringBuilder retBuilder = new StringBuilder();
        while(!stack.isEmpty()) {
            StringBuilder word = stack.pop();
            retBuilder.append(word);
            if(!stack.isEmpty()) retBuilder.append(' ');
        }
        return retBuilder.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "    ";
        System.out.println(str);
        System.out.println(solution.reverseWords(str));
    }
}