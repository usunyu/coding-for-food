/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

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

    private HashMap<Character, Integer> procIndexMap(String T) {
        HashMap<Character, Integer> indexMap = new HashMap<Character, Integer>();
        for(int i = 0, j = 0; i < T.length(); i++, j++) {
            char c = T.charAt(i);
            if(!indexMap.containsKey(c)) {
                indexMap.put(c, j);
            }
            else j--;
        }
        return indexMap;
    }

    private int[] procNeedFind(String T, HashMap<Character, Integer> indexMap) {
        int[] needFind = new int[indexMap.size()];
        for(int i = 0; i < T.length(); i++) {
            char ch = T.charAt(i);
            int index = indexMap.get(ch);
            needFind[index]++;
        }
        return needFind;
    }

    // time complexity : O(n)
    public String minWindow2(String S, String T) {
        HashMap<Character, Integer> indexMap = procIndexMap(T);
        int[] needFind = procNeedFind(T, indexMap);
        int[] hasFind = new int[indexMap.size()];
        int matchCount = indexMap.size();
        // find first match
        int start = -1, end = -1;
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if(indexMap.containsKey(ch)) {
                if(start == -1) start = i;
                int index = indexMap.get(ch);
                hasFind[index]++;
                if(hasFind[index] == needFind[index]) {
                    matchCount--;
                }
                if(matchCount == 0) {
                    end = i;
                    break;
                }
            }
        }
        if(end == -1) return "";
        // search the rest
        int minStart = start, minEnd = end;
        while(end <= S.length()) {
            // check if we can advance start
            char ch = S.charAt(start);
            int index = indexMap.get(ch);
            if(hasFind[index] - 1 >= needFind[index]) { // can advance
                hasFind[index]--;
                while(++start <= end) {
                    ch = S.charAt(start);
                    if(indexMap.containsKey(ch)) {
                        if(end - start < minEnd - minStart) {
                            minStart = start;
                            minEnd = end;
                        }
                        break;
                    }
                }
            }
            else {  // advance end
                while(++end < S.length()) {
                    ch = S.charAt(end);
                    if(indexMap.containsKey(ch)) {
                        index = indexMap.get(ch);
                        hasFind[index]++;
                        break;
                    }
                }
            }
        }
        return S.substring(minStart, minEnd + 1);
    }
    /*
        Second Round
    */
    public String minWindow3(String S, String T) {
        if(S.length() < T.length()) return "";
        // preprocess
        HashMap<Character, Integer> charNeed = new HashMap<Character, Integer>();
        for(int i = 0; i < T.length(); i++) {
            char ch = T.charAt(i);
            if(charNeed.containsKey(ch))
                charNeed.put(ch, charNeed.get(ch) + 1);
            else
                charNeed.put(ch, 1);
        }
        HashMap<Character, Integer> charFound = new HashMap<Character, Integer>();
        // find min window
        String minWindow = "";
        int start;
        // go to first valid char
        for(start = 0; start < S.length(); start++) {
            char ch = S.charAt(start);
            if(charNeed.containsKey(ch)) break;
        }
        int end = start, found = 0;
        // find a match
        while(end < S.length()) {
            char ch = S.charAt(end);
            if(charNeed.containsKey(ch)) {
                if(charFound.containsKey(ch))
                    charFound.put(ch, charFound.get(ch) + 1);
                else
                    charFound.put(ch, 1);
                if(charFound.get(ch) == charNeed.get(ch)) found++;
                if(found == charNeed.size()) {
                    minWindow = S.substring(start, end + 1);
                    break;
                }
            }
            end++;
        }
        if(found != charNeed.size()) return "";
        while(true) {
            while(start < end) {    // shrink
                char ch = S.charAt(start);
                if(charFound.containsKey(ch)) {
                    if(charFound.get(ch) > charNeed.get(ch)) {
                        charFound.put(ch, charFound.get(ch) - 1);
                        start++;
                    }
                    else {
                        break;
                    }
                }
                else start++;
            }
            // update
            if(end - start + 1 < minWindow.length()) minWindow = S.substring(start, end + 1);
            if(end == S.length()) break;
            while(end < S.length()) {   // expand
                end++;
                if(end == S.length()) break;
                char ch = S.charAt(end);
                if(charFound.containsKey(ch)) {
                    charFound.put(ch, charFound.get(ch) + 1);
                    break;
                }
            }
        }
        return minWindow;
    }
    // https://github.com/AnnieKim/LeetCode/blob/master/MinimumWindowSubstring.h
    /*
    Solution: 1. Use two pointers: start and end. 
                 First, move 'end'. After finding all the needed characters, move 'start'.
              2. Use array as hashtable.
    */
    public String minWindow4(String S, String T) {
        if(S.length() < T.length()) return "";
        int[] need = new int[256];
        int[] found = new int[256];
        int unique = 0;
        for(int i = 0; i < T.length(); i++) {
            need[T.charAt(i)]++;
            if(need[T.charAt(i)] == 1) unique++;
        }
        String minWin = "";
        int count = 0;
        for(int start = 0, end = 0; end < S.length(); end++) {
            // expand
            if(need[S.charAt(end)] == 0) continue;
            found[S.charAt(end)]++;
            if(found[S.charAt(end)] == need[S.charAt(end)]) count++;
            if(count < unique) continue;
            if(minWin.equals("") || end - start + 1 < minWin.length())
                minWin = S.substring(start, end + 1);
            // shrink
            for(; start < end; start++) {
                if(need[S.charAt(start)] == 0) continue;
                if(found[S.charAt(start)] > need[S.charAt(start)])
                    found[S.charAt(start)]--;
                else break;
            }
            if(end - start + 1 < minWin.length())
                minWin = S.substring(start, end + 1);
        }
        return minWin;
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
        System.out.println(solution.minWindow4(S, T));
    }
}
