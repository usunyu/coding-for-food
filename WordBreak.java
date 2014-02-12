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
        System.out.println(solution.wordBreak2("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict));
    }
}