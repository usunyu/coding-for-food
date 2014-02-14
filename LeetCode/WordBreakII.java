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
        System.out.println(solution.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict));
    }
}