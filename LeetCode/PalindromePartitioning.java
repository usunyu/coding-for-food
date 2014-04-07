/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/

import java.util.*;
import LCLibrary.*;

class PalindromeSet {
    int lastIndex;
    ArrayList<String> palindromes;
    
    public PalindromeSet() {
        palindromes = new ArrayList<String>();
    }
}

class Solution {
    public boolean isPalindrome(String s) {
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    
    public ArrayList<ArrayList<String>> partition(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if(s == null || s.length() == 0) {
            return result;
        }
        ArrayList<PalindromeSet> palindromeList = new ArrayList<PalindromeSet>();
        // initial
        PalindromeSet psi = new PalindromeSet();
        psi.lastIndex = 0;
        psi.palindromes.add(s.substring(0, 1));
        palindromeList.add(psi);
        for(int i = 1; i < s.length(); i++) {
            // check from mid
            int size = palindromeList.size();
            // add cache
            HashSet<Integer> cachePos = new HashSet<Integer>();
            HashSet<Integer> cacheNeg = new HashSet<Integer>();
            for(int k = 0; k < size; k++) {
                PalindromeSet ps = palindromeList.get(k);
                int lastIndex = ps.lastIndex;
                if(cachePos.contains(lastIndex)) {  // in cache indicate it is palindrome
                    String palindrome = s.substring(lastIndex + 1, i + 1);
                    PalindromeSet psn = new PalindromeSet();
                    // copy original
                    psn.palindromes = new ArrayList<String>(ps.palindromes);
                    psn.palindromes.add(palindrome);
                    psn.lastIndex = i;
                    palindromeList.add(psn);
                }
                else if(cacheNeg.contains(lastIndex)) { // in cache indicate it is not palindrome

                }
                else {  // not in cache
                    String palindrome = s.substring(lastIndex + 1, i + 1);
                    if(isPalindrome(palindrome)) {
                        PalindromeSet psn = new PalindromeSet();
                        // copy original
                        psn.palindromes = new ArrayList<String>(ps.palindromes);
                        psn.palindromes.add(palindrome);
                        psn.lastIndex = i;
                        palindromeList.add(psn);
                        cachePos.add(lastIndex);
                    }
                    else {
                        cacheNeg.add(lastIndex);
                    }
                }
            }
            // check from begin
            String palindrome = s.substring(0, i + 1);
            if(isPalindrome(palindrome)) {
                PalindromeSet psn = new PalindromeSet();
                psn.palindromes.add(palindrome);
                psn.lastIndex = i;
                palindromeList.add(psn);
            }
        }
        for(PalindromeSet ps : palindromeList) {
            if(ps.lastIndex == s.length() - 1) {
                result.add(ps.palindromes);
            }
        }
        return result;
    }
}

// DP + recursion
class Solution2 {
    private boolean[][] buildPalindromeTable(String s) {
        // table[i][j] == true iff s[i..j] is a palindrome
        boolean table[][] = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            table[i][i] = true;
            // odd case
            int left = i - 1, right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                table[left--][right++] = true;
            }
            // even case
            left = i; right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                table[left--][right++] = true;
            }
        }
        return table;
    }

    private void partitionHelper(String s, int index, boolean[][] table, ArrayList<String> path,
        ArrayList<ArrayList<String>> results) {
        if(index == s.length()) {
            results.add(new ArrayList<String>(path));
        }
        for(int i = index; i < s.length(); i++) {
            if(table[index][i]) {
                //ArrayList<String> npath = new ArrayList<String>(path);
                path.add(s.substring(index, i + 1));
                partitionHelper(s, i + 1, table, path, results);
                path.remove(path.size() - 1);
            }
        }
    }

    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        partitionHelper(s, 0, buildPalindromeTable(s), new ArrayList<String>(),results);
        return results;
    }
}

// DP + DP
class Solution3 {
    private boolean[][] buildPalindromeTable(String s) {
        // table[i][j] == true iff s[i..j] is a palindrome
        boolean table[][] = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            table[i][i] = true;
            // odd case
            int left = i - 1, right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                table[left--][right++] = true;
            }
            // even case
            left = i; right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                table[left--][right++] = true;
            }
        }
        return table;
    }

    public ArrayList<ArrayList<String>> partition(String s) {
        // build up a table for palindrome substrings  
        boolean[][] table = buildPalindromeTable(s);  
   
        // this map is used to store previous results  
        // preRes.get(i) is all partitions of s[i..len-1]  
        HashMap<Integer, ArrayList<ArrayList<String>>> preRes = new HashMap<Integer, ArrayList<ArrayList<String>>>();  
   
        for (int i = s.length() - 1; i >= 0; --i) {  
            ArrayList<ArrayList<String>> partitions = new ArrayList<ArrayList<String>>();
            for (int j = i; j < s.length(); ++j) {
                if (table[i][j]) {
                    if (j + 1 == s.length()) {
                        ArrayList<String> pp = new ArrayList<String>();
                        pp.add(s.substring(i, j + 1));
                        partitions.add(pp);
                    } else {
                        for (ArrayList<String> p : preRes.get(j + 1)) {
                            ArrayList<String> pp = new ArrayList<String>();
                            pp.add(s.substring(i, j + 1));
                            pp.addAll(p);
                            partitions.add(pp);
                        }
                    }
                }
            }
            preRes.put(i, partitions);
        }
        return preRes.get(0);
    }
}
/*
    Second Round
*/
// Bottom up
class Solution4 {
    public ArrayList<ArrayList<String>> partition(String s) {
        if(s == null || s.length() == 0) return null;
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        // initial base case
        ArrayList<String> base = new ArrayList<String>();
        for(int i = 0; i < s.length(); i++) {
            base.add(s.substring(i, i + 1));
        }
        result.add(base);
        // bottom up
        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>(result);
        HashSet<ArrayList<String>> set = new HashSet<ArrayList<String>>();
        while(temp.size() != 0) {
            ArrayList<ArrayList<String>> cache = new ArrayList<ArrayList<String>>();
            for(ArrayList<String> list : temp) {
                for(int i = 0; i < list.size() - 1; i++) {
                    String left = i > 0 ? list.get(i - 1) : "", mid = list.get(i), right = list.get(i + 1);
                    // even case
                    if(mid.equals(right)) {
                        ArrayList<String> tmp = new ArrayList<String>();
                        for(int j = 0; j < i; j++) tmp.add(list.get(j));
                        tmp.add(mid + right);
                        for(int j = i + 2; j < list.size(); j++) tmp.add(list.get(j));
                        if(!set.contains(tmp)) {
                            cache.add(tmp);
                            result.add(tmp);
                            set.add(tmp);
                        }
                    }
                    // odd case
                    if(left.equals(right)) {
                        ArrayList<String> tmp = new ArrayList<String>();
                        for(int j = 0; j < i - 1; j++) tmp.add(list.get(j));
                        tmp.add(left + mid + right);
                        for(int j = i + 2; j < list.size(); j++) tmp.add(list.get(j));
                        if(!set.contains(tmp)) {
                            cache.add(tmp);
                            result.add(tmp);
                            set.add(tmp);
                        }
                    }
                }
            }
            temp = cache;
        }
        return result;
    }
}

// DP + DP
class Solution5 {
    public ArrayList<ArrayList<String>> partition(String s) {
        if(s == null || s.length() == 0) return null;
        int n = s.length();
        // dp[i][j] = true, if s[i...j] is palindrome
        boolean[][] dp = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
            int left = i - 1, right = i + 1;
            // odd case
            while(left >= 0 && right < n && s.charAt(left) == s.charAt(right))
                dp[left--][right++] = true;
            // even case
            left = i; right = i + 1;
            while(left >= 0 && right < n && s.charAt(left) == s.charAt(right))
                dp[left--][right++] = true;
        }
        // this map is used to store previous results  
        // cache.get(i) is all partitions of s[0...i]  
        HashMap<Integer, ArrayList<ArrayList<String>>> cache = new HashMap<Integer, ArrayList<ArrayList<String>>>();
        for(int i = 0; i < n; i++) {
            ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
            for(int j = i; j >= 0; j--) {
                if(dp[j][i]) {  // it is palindrome
                    if(j > 0) { // we can use previous cache
                        ArrayList<ArrayList<String>> previous = cache.get(j - 1);
                        for(ArrayList<String> list : previous) {
                            ArrayList<String> current = new ArrayList<String>(list);
                            current.add(s.substring(j, i + 1));
                            temp.add(current);
                        }
                    }
                    else {
                        ArrayList<String> first = new ArrayList<String>();
                        first.add(s.substring(j, i + 1));
                        temp.add(first);
                    }
                }
            }
            cache.put(i, temp);
        }
        return cache.get(n - 1);
    }
}

class Main {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();
        ArrayList<ArrayList<String>> result = solution.partition("fff");
        Output.printStringList(result);
    }
}
