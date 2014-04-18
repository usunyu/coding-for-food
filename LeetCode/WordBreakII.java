/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Solution {
    private boolean wordBreak(String s, Set<String> dict, StringBuilder path, ArrayList<String> result, HashSet<String> cache) {
        if(cache.contains(s)) return false;
        boolean ret = false;
        for(int i = 0; i < s.length(); i++) {
            String substr = s.substring(0, i + 1);
            if(dict.contains(substr)) {
                StringBuilder localPath = new StringBuilder(path);
                if(localPath.length() == 0) localPath.append(substr);
                else localPath.append(" " + substr);
                if(i == s.length() - 1) {
                    result.add(localPath.toString());
                    ret = true;
                }
                else {
                    // reminder : cannot write as ret = ret || wordBreak(s.substring(i + 1), dict, localPath, result, cache);
                    // or the recursive part may not execute
                    ret = wordBreak(s.substring(i + 1), dict, localPath, result, cache) || ret;
                }
            }
        }
        if(!ret) cache.add(s);  // record it, not recursive it again
        return ret;
    }
    
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> result = new ArrayList<String>();
        wordBreak(s, dict, new StringBuilder(), result, new HashSet<String>());
        return result;
    }
}
/*
    Second Round
*/
// dynamic programming
class Solution2 {
    private boolean wordBreakPossibile(String s, Set<String> dict) {
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

    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        if(s == null || s.length() == 0) return new ArrayList<String>();
        if(!wordBreakPossibile(s, dict)) return new ArrayList<String>();
        int N = s.length();
        ArrayList<ArrayList<String>> cache = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < N; i++) {
            ArrayList<String> strings = new ArrayList<String>();
            String str = s.substring(0, i + 1);
            if(dict.contains(str)) strings.add(str);
            for(int j = 0; j < i; j++) {
                str = s.substring(j + 1, i + 1);
                if(cache.get(j).size() > 0 && dict.contains(str)) {
                    for(String prev : cache.get(j)) {
                        StringBuilder sb = new StringBuilder(prev);
                        sb.append(" "); sb.append(str);
                        strings.add(sb.toString());
                    }
                }
            }
            cache.add(strings);
        }
        return cache.get(N - 1);
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        Set<String> dict = new HashSet<String>();
        dict.add("a"); dict.add("aa"); dict.add("aaa"); dict.add("aaaa"); dict.add("aaaaa"); dict.add("aaaaaa"); dict.add("aaaaaaa"); dict.add("aaaaaaaa"); dict.add("aaaaaaaaa"); dict.add("aaaaaaaaaa");
        System.out.println(solution.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict));
    }
}