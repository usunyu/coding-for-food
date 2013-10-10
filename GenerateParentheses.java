import java.util.*;

class Solution {
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

class Main {
    public static void print(ArrayList<String> parenthesisList) {
        for(String parenthesis : parenthesisList) {
            System.out.println(parenthesis);
        }
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        ArrayList<String> parenthesisList = solution.generateParenthesis(n);
        print(parenthesisList);
    }
}
