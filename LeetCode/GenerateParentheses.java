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

    public void generateParenthesis2(int leftRemain, int rightRemain, String parenthesis, ArrayList<String> parenthesisList) {
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
            generateParenthesis2(leftRemain - 1, rightRemain, temp, parenthesisList);
            temp = parenthesis;
            temp += ')';
            generateParenthesis2(leftRemain, rightRemain - 1, temp, parenthesisList);
        }
        else if(leftRemain == rightRemain) {    // we can only insert left parenthesis
            parenthesis += '(';
            generateParenthesis2(leftRemain - 1, rightRemain, parenthesis, parenthesisList);
        }
        else {  // we can only insert right parenthesis
            parenthesis += ')';
            generateParenthesis2(leftRemain, rightRemain - 1, parenthesis, parenthesisList);
        }
    }

    public ArrayList<String> generateParenthesis2(int n) {
        ArrayList<String> parenthesisList = new ArrayList<String>();
        generateParenthesis2(n, n, "", parenthesisList);
        return parenthesisList;
    }
}

class Main {
    public static void print(ArrayList<String> parenthesisList) {
        for(String parenthesis : parenthesisList) {
            System.out.println(parenthesis);
        }
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        ArrayList<String> parenthesisList = solution.generateParenthesis2(n);
        print(parenthesisList);
    }
}
