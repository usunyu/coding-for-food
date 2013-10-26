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
            for(int k = 0; k < size; k++) {
                PalindromeSet ps = palindromeList.get(k);
                int lastIndex = ps.lastIndex;
                String palindrome = s.substring(lastIndex + 1, i + 1);
                if(isPalindrome(palindrome)) {
                    PalindromeSet psn = new PalindromeSet();
                    // copy original
                    psn.palindromes = new ArrayList<String>(ps.palindromes);
                    psn.palindromes.add(palindrome);
                    psn.lastIndex = i;
                    palindromeList.add(psn);
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
        ArrayList<ArrayList<String>> result = solution.partition("aab");
        print(result);
    }
}
