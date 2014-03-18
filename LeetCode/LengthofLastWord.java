/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/
class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0, last = s.length() - 1;
        while(last >= 0 && s.charAt(last) == ' ') last--;
        while(last >= 0 && s.charAt(last--) != ' ') length++;
        return length;
    }

    /*
        Second Round
    */
    public int lengthOfLastWord2(String s) {
        if(s == null || s.length() == 0) return 0;
        // skip last space
        int i = s.length() - 1;
        while(i >= 0 && s.charAt(i) == ' ') i--;
        // count word
        int length = 0;
        while(i >= 0 && s.charAt(i) != ' ') {
            i--; length++;
        }
        return length;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLastWord("Hello World"));
    }
}
