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

    // add cache
    public boolean wordBreak2(String s, Set<String> dict, HashMap<String, Boolean> cache) {
        if(cache.containsKey(s)) return cache.get(s);
        if(dict.contains(s)) return true;
        for(int i = 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(0, i))) {
                if(wordBreak2(s.substring(i), dict, cache)) {
                    cache.put(s, true);
                    return true;
                }
            }
        }
        cache.put(s, false);
        return false;
    }

    public boolean wordBreak2(String s, Set<String> dict) {
        return wordBreak2(s, dict, new HashMap<String, Boolean>());
    }

    // DP Solution
    // possible[i] = true   if s[0...i] in dict
    //                      if possible[k] = true and s[k...i] in dict, 0 < k < i
    //               false  otherwise
    public boolean wordBreak3(String s, Set<String> dict) {
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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Set<String> dict = new HashSet<String>();
        dict.add("a");
        dict.add("aa");
        dict.add("aaa");
        dict.add("aaaa");
        dict.add("aaaaa");
        dict.add("aaaaaa");
        dict.add("aaaaaaa");
        dict.add("aaaaaaaa");
        dict.add("aaaaaaaaa");
        dict.add("aaaaaaaaaa");
        System.out.println(solution.wordBreak3("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict));
    }
}