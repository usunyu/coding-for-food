class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0, last = s.length() - 1;
        while(last >= 0 && s.charAt(last) == ' ') last--;
        while(last >= 0 && s.charAt(last--) != ' ') length++;
        return length;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLastWord("Hello World"));
    }
}
