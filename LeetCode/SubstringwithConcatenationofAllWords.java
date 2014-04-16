/*
You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) 
in S that is a concatenation of each word in L exactly once and without any intervening characters.

For example, given:
S: "barfoothefoobarman"
L: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

import java.util.*;

class Solution {
    private HashMap<String, Integer> prepStrMap(String[] L) {
        HashMap<String, Integer> words = new HashMap<String, Integer>();
        for(String str : L) {
            if(!words.containsKey(str)) {
                words.put(str, 1);
            }
            else {
                words.put(str, words.get(str) + 1);
            }
        }
        return words;
    }

    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        if(L.length == 0) return indices;
        int len = L[0].length();
        HashMap<String, Integer> words = prepStrMap(L);
        for(int i = 0; i <= S.length() - L.length * len; i++) {
            HashMap<String, Integer> strMap = new HashMap<String, Integer>(words);
            for(int j = i; j <= S.length() - len; j += len) {
                String str = S.substring(j, j + len);
                if(!strMap.containsKey(str)) break;
                if(strMap.get(str) > 1)
                    strMap.put(str, strMap.get(str) - 1);
                else
                    strMap.remove(str);
            }
            if(strMap.isEmpty()) {
                indices.add(i);
            }
        }
        return indices;
    }
}

class Solution2 {
    private HashMap<String, Integer> prepStrMap(String[] L) {
        HashMap<String, Integer> words = new HashMap<String, Integer>();
        for(String str : L) {
            if(!words.containsKey(str)) {
                words.put(str, 1);
            }
            else {
                words.put(str, words.get(str) + 1);
            }
        }
        return words;
    }

    // add cache
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        if(L.length == 0) return indices;
        int len = L[0].length();
        HashMap<String, Integer> words = prepStrMap(L);
        HashSet<Integer> skip = new HashSet<Integer>();
        for(int i = 0; i <= S.length() - L.length * len; i++) {
            HashMap<String, Integer> strMap = new HashMap<String, Integer>(words);
            for(int j = i; j <= S.length() - len; j += len) {
                if(skip.contains(j)) break;
                String str = S.substring(j, j + len);
                if(!words.containsKey(str)) {
                    skip.add(j);
                    break;
                }
                if(!strMap.containsKey(str)) break;
                if(strMap.get(str) > 1)
                    strMap.put(str, strMap.get(str) - 1);
                else
                    strMap.remove(str);
            }
            if(strMap.isEmpty()) {
                indices.add(i);
            }
        }
        return indices;
    }
}
/*
    Second Round
*/
class Solution3 {
    private void addWord(HashMap<String, Integer> map, String str) {
        if(map.containsKey(str)) map.put(str, map.get(str) + 1);
        else map.put(str, 1);
    }
    
    private void removeWord(HashMap<String, Integer> map, String str) {
        if(map.containsKey(str)) {
            int n = map.get(str);
            if(n == 1) map.remove(str);
            else map.put(str, n - 1);
        }
    }
    
    private int moveLeft(HashMap<String, Integer> need, String word, String S, int left, int right) {
        int length = word.length();
        while(left < right) {   // find next == prev
            String prev = S.substring(left, left + length);
            left += length;
            addWord(need, prev);
            if(word.equals(prev)) break;
        }
        return left;
    }
    
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int length = L[0].length(), N = L.length;
        if(S.length() < N * length) return result;
        // word : occurrence
        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
        for(String w : L) addWord(wordMap, w);
        // try to sliding window
        for(int i = 0; i < length; i++) {
            HashMap<String, Integer> need = new HashMap<String, Integer>(wordMap);
            int left = i, right = i;    // current window
            while(right <= S.length() - length) {
                String word = S.substring(right, right + length);
                if(need.containsKey(word)) removeWord(need, word);
                else if(wordMap.containsKey(word)) {
                    left = moveLeft(need, word, S, left, right);
                    right -= length;
                }
                else {
                    left = right + length;
                    need = new HashMap<String, Integer>(wordMap);   // reset
                }
                
                right += length;
                
                if(need.isEmpty()) {
                    result.add(left);
                    if(right <= S.length() - length) {
                        String next = S.substring(right, right + length);
                        if(wordMap.containsKey(next)) { // we can take advantage of prevs
                            left = moveLeft(need, next, S, left, right);
                        }
                        else {
                            right += length;
                            left = right;
                            need = new HashMap<String, Integer>(wordMap);   // reset
                        }
                    }
                }
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        String S = "abababab";
        String[] L = {"a","b","a"};
        System.out.println(solution.findSubstring(S, L));
    }
}
