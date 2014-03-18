/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < strs[0].length(); i++) {
            char prefix = strs[0].charAt(i);
            boolean common = true;
            for(int j = 1; j < strs.length; j++) {
                String str = strs[j];
                if(i >= str.length() || str.charAt(i) != prefix) {
                    common = false;
                    break;
                }
            }
            if(common) builder.append(prefix);
            else break;
        }
        return builder.toString();
    }
    /*
        Second Round
    */
    public String longestCommonPrefix2(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for(int index = 0; index < strs[0].length(); index++) {
            char check = strs[0].charAt(index);
            boolean common = true;
            for(int i = 1; i < strs.length; i++) {
                if(index < strs[i].length()) {
                    char ch = strs[i].charAt(index);
                    if(ch != check) {
                        common = false;
                        break;
                    }
                }
                else common = false;
            }
            if(!common) break;
            sb.append(check);
        }
        return sb.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"ABCD", "ABEE", "ABCA"};
        System.out.println(solution.longestCommonPrefix2(strs));
    }
}
