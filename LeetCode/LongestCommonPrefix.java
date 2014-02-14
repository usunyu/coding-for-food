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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"ABCD", "ABEE", "ABCA"};
        System.out.println(solution.longestCommonPrefix(strs));
    }
}
