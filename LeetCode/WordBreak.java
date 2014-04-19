/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
*/

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

class Solution {
    // Time Limit Exceeded
    public boolean wordBreak(String s, Set<String> dict) {
        if(dict.contains(s)) return true;
        for(int i = 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(0, i))) {
                if(wordBreak(s.substring(i), dict)) return true;
            }
        }
        return false;
    }
}

class Solution2 {
    // add cache
    public boolean wordBreak(String s, Set<String> dict, HashMap<String, Boolean> cache) {
        if(cache.containsKey(s)) return cache.get(s);
        if(dict.contains(s)) return true;
        for(int i = 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(0, i))) {
                if(wordBreak(s.substring(i), dict, cache)) {
                    cache.put(s, true);
                    return true;
                }
            }
        }
        cache.put(s, false);
        return false;
    }

    public boolean wordBreak(String s, Set<String> dict) {
        return wordBreak(s, dict, new HashMap<String, Boolean>());
    }
}

class Solution3 {
    // DP Solution
    // possible[i] = true   if s[0...i] in dict
    //                      if possible[k] = true and s[k...i] in dict, 0 < k < i
    //               false  otherwise
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] possible = new boolean[s.length()];
        for(int i = 0; i < s.length(); i++) {
            if(dict.contains(s.substring(0, i + 1))) possible[i] = true;
            for(int k = 0; k < i; k++) {
                if(possible[i]) break;
                possible[i] = possible[k] && dict.contains(s.substring(k + 1, i + 1));
            }
        }
        return possible[s.length() - 1];
    }
}
/*
    Second Round
*/
// recursion + cache
class Solution4 {
    private boolean wordBreak(String s, Set<String> dict, HashSet<String> cache) {
        if(dict.contains(s)) return true;
        if(cache.contains(s)) return false;
        for(int i = 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(0, i))) {
                if(wordBreak(s.substring(i), dict, cache)) return true;
            }
        }
        cache.add(s);
        return false;
    }
    
    public boolean wordBreak(String s, Set<String> dict) {
        return wordBreak(s, dict, new HashSet<String>());
    }
}

// dynamic programming
class Solution5 {
    public boolean wordBreak(String s, Set<String> dict) {
        if(s == null || s.length() == 0) return false;
        int N = s.length();
        boolean[] cache = new boolean[N];
        for(int i = 0; i < N; i++) {
            if(dict.contains(s.substring(0, i + 1))) {
                cache[i] = true;
                continue;
            }
            for(int j = i - 1; j >= 0; j--) {
                if(cache[j] && dict.contains(s.substring(j + 1, i + 1))) {
                    cache[i] = true;
                    break;
                }
            }
        }
        return cache[N - 1];
    }
}

class Main {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();
        Set<String> dict = new HashSet<String>();
        dict.add("a"); dict.add("aa"); dict.add("aaa"); dict.add("aaaa"); dict.add("aaaaa"); dict.add("aaaaaa"); dict.add("aaaaaaa"); dict.add("aaaaaaaa"); dict.add("aaaaaaaaa"); dict.add("aaaaaaaaaa");
        System.out.println(solution.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict));
    }
}