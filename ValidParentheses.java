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

class Main {

    public static void main(String[] args) {
        String str = "]";
        Solution solution = new Solution();
        boolean isValid = solution.isValid(str);
        System.out.println(isValid);
    }
}