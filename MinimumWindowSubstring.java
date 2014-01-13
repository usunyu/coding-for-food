import java.util.*;

class Solution {
    private boolean isMatch(int[] counts, HashMap<Character, Integer> charMap, HashMap<Character, Integer> indexMap) {
        for(char c : charMap.keySet()) {
            int v = charMap.get(c);
            int i = indexMap.get(c);
            if(counts[i] < v) return false;
        }
        return true;
    }

    // Time Limit Exceeded
    public String minWindow(String S, String T) {
        String min = "";
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        HashMap<Character, Integer> indexMap = new HashMap<Character, Integer>();
        for(int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            if(!charMap.containsKey(c)) {
                charMap.put(c, 1);
            }
            else {
                int v = charMap.get(c);
                charMap.put(c, ++v);
            }
        }
        for(int i = 0, j = 0; i < T.length(); i++, j++) {
            char c = T.charAt(i);
            if(!indexMap.containsKey(c)) {
                indexMap.put(c, j);
            }
            else j--;
        }
        int[] counts = new int[indexMap.size()];
        int start = 0, minCount = Integer.MAX_VALUE, minLeft = 0;
        for(int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if(charMap.containsKey(c)) {
                counts[indexMap.get(c)]++;
                if(!charMap.containsKey(S.charAt(start))) {
                    start = i;
                }
            }
            if(isMatch(counts, charMap, indexMap)) { // match
                int length = i - start + 1;
                if(length < minCount) {
                    minCount = length;
                    minLeft = start;
                }
                // turn first char false
                c = S.charAt(start);
                if(indexMap.containsKey(c)) counts[indexMap.get(c)]--;
                // turn last char flase
                c = S.charAt(i);
                if(indexMap.containsKey(c)) counts[indexMap.get(c)]--;
                // traceback
                for(int j = start + 1; j < i; j++) {
                    c = S.charAt(j);
                    if(charMap.containsKey(c)) {
                        start = j;
                        break;
                    }
                }
                i--;
            }
        }
        if(minCount < Integer.MAX_VALUE) min = S.substring(minLeft, minLeft + minCount);
        return min;
    }
}

class Main {
    public static void print(int[] A) {
        for(int i : A) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String S = "ADOBECODEBANC";
        String T = "ABC";
        System.out.println(solution.minWindow(S, T));
    }
}
