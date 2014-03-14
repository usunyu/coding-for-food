/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/

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
    /*
        Second Round
    */
    public String countAndSay2(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            if(i == 1) sb.append(1);
            else if(i == 2) sb.append(1);
            else {
                StringBuilder tmp = new StringBuilder();
                int count = 1;
                char prev = sb.charAt(0);
                for(int j = 1; j < sb.length(); j++) {
                    char current = sb.charAt(j);
                    if(current == prev) {
                        count++;
                    }
                    else {
                        tmp.append(count);
                        tmp.append(prev);
                        count = 1;
                    }
                    prev = current;
                    if(j == sb.length() - 1) {  // clean
                        tmp.append(count);
                        tmp.append(prev);
                    }
                }
                sb = tmp;
            }
        }
        return sb.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countAndSay2(5));
    }
}
