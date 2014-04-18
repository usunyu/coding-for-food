/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/

import java.util.*;

class Solution {
    private boolean isScrambleRec(String s1, String s2, HashMap<Character, Integer> charMap) {
        int length = s1.length();
        if(length == 1) return true;
        // find partation from start of s2
        for(int i = 0; i < length; i++) {
            char ch = s1.charAt(i);
            int num = charMap.get(ch);
            charMap.put(ch, num + 1);
            ch = s2.charAt(i);
            num = charMap.get(ch);
            charMap.put(ch, num - 1);
            if(i < length - 1 && isSameChars(charMap)) {
                if(isScrambleRec(s1.substring(0, i + 1), s2.substring(0, i + 1), charMap)
                        && isScrambleRec(s1.substring(i + 1), s2.substring(i + 1), charMap))
                    return true;
                break;
            }
        }
        // find partation from end of s2
        for(int i = 0; i < length; i++) {
            char ch = s1.charAt(i);
            int num = charMap.get(ch);
            charMap.put(s1.charAt(i), num + 1);
            ch = s2.charAt(length - i - 1);
            num = charMap.get(ch);
            charMap.put(ch, num - 1);
            if(i < length - 1 && isSameChars(charMap)) {
                if(isScrambleRec(s1.substring(0, i + 1), s2.substring(length - i - 1), charMap)
                        && isScrambleRec(s1.substring(i + 1), s2.substring(0, length - i - 1), charMap))
                    return true;
                break;
            }
        }
        return false;
    }

    private boolean isSameChars(HashMap<Character, Integer> charMap) {
        for(int i : charMap.values()) 
            if(i != 0) return false;
        return true;
    }
    
    // recursion solution
    public boolean isScramble(String s1, String s2) {
        // check length
        if(s1.length() != s2.length()) return false;
        // check chars
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        for(int i = 0; i < s1.length(); i++) charMap.put(s1.charAt(i), 0);
        for(int i = 0; i < s1.length(); i++) {
            int num = charMap.get(s1.charAt(i));
            charMap.put(s1.charAt(i), num + 1);
            if(!charMap.containsKey(s2.charAt(i)))
                return false;
            else {
                num = charMap.get(s2.charAt(i));
                charMap.put(s2.charAt(i), num - 1);
            }
        }
        if(!isSameChars(charMap)) return false;
        // check scramble
        return isScrambleRec(s1, s2, charMap);
    }
}

class Solution2 {
    // DP solution
    public boolean isScramble(String s1, String s2) {  
        int len = s1.length();
        if(len != s2.length()) return false;
        if (s1.equals(s2)) return true;

        // a table of matches
        // T[i][j][k] = true iff s2.substring(j,j+k+1) is a scambled string of s1.substring(i,i+k+1)
        boolean[][][] scrambled = new boolean[len][len][len];
        for (int i=0; i < len; ++i) {
            for (int j=0; j < len; ++j) {
                scrambled[i][j][0] = (s1.charAt(i) == s2.charAt(j));
            }
        }

        // dynamically fill up the table
        for (int k=1; k < len; ++k) { // k: length
            for (int i=0; i < len - k; ++i) { // i: index in s1
                for (int j=0; j < len - k; ++j) { // j: index in s2
                    // scrambled[i][j][k] = false;
                    for (int p=0; p < k; ++p) { // p: split into [0..p] and [p+1..k]
                        if ((scrambled[i][j][p] && scrambled[i+p+1][j+p+1][k-p-1])
                            || (scrambled[i][j+k-p][p] && scrambled[i+p+1][j][k-p-1])) {
                            scrambled[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return scrambled[0][0][len-1];
    }
}

/*
    Second Round
*/
// recursion
class Solution3 {
    private boolean isSameChars(String s1, String s2) {
        int[] chars1 = new int[256], chars2 = new int[256];
        for(int i = 0; i < s1.length(); i++) {
            chars1[s1.charAt(i)]++;
            chars2[s2.charAt(i)]++;
        }
        for(int i = 0; i < 256; i++) {
            if(chars1[i] != chars2[i])
                return false;
        }
        return true;
    }
    
    public boolean isScramble(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        if(s1.equals(s2)) return true;
        if(!isSameChars(s1, s2)) return false;
        for(int i = 0; i < s1.length() - 1; i++) {
            String part1 = s1.substring(0, i + 1);
            String part2 = s1.substring(i + 1);
            if(isScramble(part1, s2.substring(0, i + 1)) && isScramble(part2, s2.substring(i + 1))
            || isScramble(part2, s2.substring(0, s1.length() - i - 1)) && isScramble(part1, s2.substring(s1.length() - i - 1)))
                return true;
        }
        return false;
    }
}

// dp solution
class Solution4 {
    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        if(len != s2.length()) return false;
        if(s1.equals(s2)) return true;
        // a table of matches
        // T[i][j][k] = true iff s2.substring(j,j+k+1) is a scambled string of s1.substring(i,i+k+1)
        boolean T[][][] = new boolean[len][len][len];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(s1.charAt(i) == s2.charAt(j)) {
                    T[i][j][0] = true;
                }
            }
        }
        // dynamically fill up the table
        for(int k = 1; k < len; k++) {
            for(int i = 0; i < len - k; i++) {
                for(int j = 0; j < len - k; j++) {
                    for(int p = 0; p < k; p++) {
                        if((T[i][j][p] && T[i + p + 1][j + p + 1][k - p - 1]) ||
                        (T[i][j + k - p][p] && T[i + p + 1][j][k - p - 1])) {
                            T[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return T[0][0][len - 1];
    }
}

class Main {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        System.out.println(solution.isScramble("hobobyrqd", "hbyorqdbo"));
    }
}
