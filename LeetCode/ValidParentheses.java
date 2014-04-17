/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

import java.util.*;

class Solution {
    // time complexity: O(N)
    // space complexity: O(N)
    public boolean isValid(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        char[] charArray = s.toCharArray();
        boolean isValid = true;
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            char top;
            switch(ch) {
                case '(':
                case '[':
                case '{':
                    stack.push(ch);
                    break;
                case ')':
                    if(stack.isEmpty()) {
                        isValid = false;
                    }
                    else {
                        top = stack.pop();
                        if(top != '(') {
                            isValid = false;
                        }
                    }
                    break;
                case ']':
                    if(stack.isEmpty()) {
                        isValid = false;
                    }
                    else {
                        top = stack.pop();
                        if(top != '[') {
                            isValid = false;
                        }
                    }
                    break;
                case '}':
                    if(stack.isEmpty()) {
                        isValid = false;
                    }
                    else {
                        top = stack.pop();
                        if(top != '{') {
                            isValid = false;
                        }
                    }
                    break;
                default:
                    break;
            }
            if(!isValid) {
                break;
            }
        }
        if(!stack.isEmpty()) {
            isValid = false;
        }
        return isValid;
    }
}
/*
    Second Round
*/
class Solution2 {
    public boolean isValid(String s) {
        if(s == null || s.length() == 0) return false;
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{') stack.push(ch);
            else if(!stack.isEmpty()) {
                if(ch == ')' && stack.pop() != '(') return false;
                if(ch == ']' && stack.pop() != '[') return false;
                if(ch == '}' && stack.pop() != '{') return false;
            }
            else return false;
        }
        return stack.isEmpty();
    }
}
class Main {

    public static void main(String[] args) {
        String str = "]";
        Solution solution = new Solution();
        boolean isValid = solution.isValid(str);
        System.out.println(isValid);
    }
}