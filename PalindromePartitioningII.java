class Solution {
    // count palindrome #
    private int minCount(String s) {
        if(s.length() == 0) return 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < s.length(); i++) {
            // from mid to check palindrome
            // odd case
            int left = i - 1, right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--; right++;
            }
            int count = minCount(s.substring(0, left + 1)) + minCount(s.substring(right)) + 1;
            min = Math.min(count, min);
            // even case
            left = i; right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--; right++;
            }
            if(left < i) {
                count = minCount(s.substring(0, left + 1)) + minCount(s.substring(right)) + 1;
                min = Math.min(count, min);
            }
        }
        return min;
    }

    // Time Limit Exceeded
    public int minCut(String s) {
        return minCount(s) - 1;
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

    public int minCut2(String s) {
        if(s.length() <= 1) return 0;
        // build up a table of palindrome substrings
        // table[i][j] == true iff s[i..j] is a palindrome
        boolean[][] table = buildPalindromeTable(s);
        // build up a table for minimum cuts of substrings
        // cut[i] = k means the minimum cuts needed for s[i..len-1] is k
        int[] cut = new int[s.length()];
        // initial
        cut[s.length() - 1] = 0;
        for(int i = s.length() - 2; i >= 0; i--) {
            if(table[i][s.length() - 1]) {
                cut[i] = 0;
            }
            else {
                cut[i] = s.length() - i;
                for(int j = i; j < s.length() - 1; j++) {
                    if(table[i][j]) {
                        cut[i] = Math.min(cut[i], cut[j + 1] + 1);
                    }
                }
            }
        }
        return cut[0];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCut2("amanaplanacanalpanama"));
    }
}


