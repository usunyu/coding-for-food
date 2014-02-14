import java.util.*;

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

    // DP + recursion
    public ArrayList<ArrayList<String>> partition2(String s) {
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        partitionHelper(s, 0, buildPalindromeTable(s), new ArrayList<String>(),results);
        return results;
    }

    // DP + DP
    public ArrayList<ArrayList<String>> partition3(String s) {
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

class Main {
    public static void print(ArrayList<ArrayList<String>> lists) {
        for(ArrayList<String> list : lists) {
            for(String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<ArrayList<String>> result = solution.partition3("aab");
        print(result);
    }
}
