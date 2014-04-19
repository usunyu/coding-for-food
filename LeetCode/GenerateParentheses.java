/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/

import java.util.*;

class Solution {
    // time complexity : N!
    // space complexity : N!
    public ArrayList<String> generateParenthesis(int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<String> parenthesisList = new ArrayList<String>();
        HashSet<String> duplicateCheck = new HashSet<String>();
        while(n > 0) {
            if(parenthesisList.size() == 0) {
                String initParenthesis = "()";
                parenthesisList.add(initParenthesis);
                duplicateCheck.add(initParenthesis);
            }
            else {
                ArrayList<String> tempList = new ArrayList<String>(parenthesisList);
                for(int i = 0; i < tempList.size(); i++) {
                    String parenthesis = tempList.get(i);
                    for(int j = 0; j < parenthesis.length(); j++) {
                        String newParenthesis = parenthesis.substring(0, j) + "()" + parenthesis.substring(j);
                        if(!duplicateCheck.contains(newParenthesis)) {
                            parenthesisList.add(newParenthesis);
                            duplicateCheck.add(newParenthesis);
                        }
                    }
                    parenthesisList.remove(parenthesis);
                }
            }
            n--;
        }
        return parenthesisList;
    }
}

class Solution2 {
    public void generateParenthesis(int leftRemain, int rightRemain, String parenthesis, ArrayList<String> parenthesisList) {
    if(leftRemain < 0 || rightRemain < 0) {
        return;
    }
        if(leftRemain == 0 && rightRemain == 0) {
            parenthesisList.add(parenthesis);
            return;
        }
        if(leftRemain < rightRemain) {  // we can insert left and right parenthesis
            String temp = parenthesis;
            temp += '(';
            generateParenthesis(leftRemain - 1, rightRemain, temp, parenthesisList);
            temp = parenthesis;
            temp += ')';
            generateParenthesis(leftRemain, rightRemain - 1, temp, parenthesisList);
        }
        else if(leftRemain == rightRemain) {    // we can only insert left parenthesis
            parenthesis += '(';
            generateParenthesis(leftRemain - 1, rightRemain, parenthesis, parenthesisList);
        }
        else {  // we can only insert right parenthesis
            parenthesis += ')';
            generateParenthesis(leftRemain, rightRemain - 1, parenthesis, parenthesisList);
        }
    }

    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> parenthesisList = new ArrayList<String>();
        generateParenthesis(n, n, "", parenthesisList);
        return parenthesisList;
    }
}

/*
    Second Round
*/
class Solution3 {
    private void generateParenthesis(int left, int right, StringBuilder path, ArrayList<String> result) {
        if(left < 0 || right < 0 || left > right) return;
        if(left == 0 && right == 0) {
            result.add(path.toString());
            return;
        }
        
        path.append('(');
        generateParenthesis(left - 1, right, path, result);
        path.deleteCharAt(path.length() - 1);
        
        path.append(')');
        generateParenthesis(left, right - 1, path, result);
        path.deleteCharAt(path.length() - 1);
    }
    
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        generateParenthesis(n, n, new StringBuilder(), result);
        return result;
    }
}

class Main {    
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int n = 3;
        System.out.println(solution.generateParenthesis(n));
    }
}
