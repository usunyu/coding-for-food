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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isScramble("hobobyrqd", "hbyorqdbo"));
    }
}
