import java.util.*;

class Solution {
    public String countAndSay(int n) {
        String str = "1";
        for(int i = 0; i < n - 1; i++) {
            // process
            char chr = '*';
            int count = 1;
            ArrayList<Character> charList = new ArrayList<Character>();
            ArrayList<Integer> countList = new ArrayList<Integer>();
            for(int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if(chr == ch) {
                    count++;
                }
                else {
                    if(chr != '*') {
                        charList.add(chr);
                        countList.add(count);
                    }
                    count = 1;
                    chr = ch;
                }
            }
            charList.add(chr);
            countList.add(count);
            // recover
            str = "";
            for(int j = 0; j < charList.size(); j++) {
                count = countList.get(j);
                chr = charList.get(j);
                str += count;
                str += chr;
            }
        }
        return str;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countAndSay(1));
    }
}
