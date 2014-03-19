class Solution {

    // Time Limit Exceeded
    public String longestPalindrome(String s) {
        String longest = null;
        // from begin
        for(int i = 0; i < s.length(); i++) {
            char b = s.charAt(i);
            // from end
            for(int j = s.length() - 1; j >= i; j--) {
                char e = s.charAt(j);
                // start match
                if(b == e) {
                    int left = i + 1, right = j - 1;
                    while(left <= right) {
                        if(s.charAt(left) == s.charAt(right)) {
                            left++; right--;
                        }
                        else {
                            break;
                        }
                    }
                    if(right - left <= 1) { // success
                        String palindromic = s.substring(i, j + 1);
                        if(longest == null || longest.length() < palindromic.length()) {
                            longest = palindromic;
                            if(longest.length() >= s.length() - i) {
                                return longest;
                            }
                        }
                    }
                }
            }
        }
        return longest;
    }

    // time complexity: O(N^2)
    public String longestPalindrome2(String s) {
        String longest = "*";
        for(int i = 0; i < s.length(); i++) {
            // even
            int left = i, right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--; right++;
            }
            left++; right--;
            int length = right - left + 1;
            if(length >= longest.length()) {
                longest = s.substring(left, right + 1);
            }
            // odd
            left = i; right = i;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--; right++;
            }
            left++; right--;
            length = right - left + 1;
            if(length >= longest.length()) {
                longest = s.substring(left, right + 1);
            }
        }
        return longest;
    }

    // Transform S into T.
    // For example, S = "abba", T = "^#a#b#b#a#$".
    // ^ and $ signs are sentinels appended to each end to avoid bounds checking
    private String preProcess(String s) {
        int n = s.length();
        if (n == 0) return "^$";
        String ret = "^";
        for(int i = 0; i < n; i++) {
            ret += "#" + s.charAt(i);
        }
        ret += "#$";
        return ret;
    }

    // time complexity: O(N)
    // reference: http://discuss.leetcode.com/questions/178/longest-palindromic-substring
    public String longestPalindrome3(String s) {
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for(int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i; // equals to i' = C - (i-C)
            P[i] = (R > i) ? Math.min(R - i, P[mirror]) : 0;
            // Attempt to expand palindrome centered at i
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) P[i]++;
            // If palindrome centered at i expand past R,
            // adjust center based on expanded palindrome.
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }
        // Find the maximum element in P.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n-1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        return s.substring((centerIndex - 1 - maxLen)/2, (centerIndex - 1 + maxLen)/2);
    }
    /*
        Second Round
    */
    public String longestPalindrome4(String s) {
        if(s == null || s.length() == 0) return "";
        int maxLeft = 0, maxRight = 1, maxLength = 1;
        int left, right, length;
        for(int i = 0; i < s.length() - maxLength / 2; i++) {
            // even
            left = i; right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--; right++;
            }
            left++;
            length = right - left;
            if(length > maxLength) {
                maxLeft = left; maxRight = right; maxLength = length;
            }
            // odd
            left = i - 1; right = i + 1;
            while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--; right++;
            }
            left++;
            length = right - left;
            if(length > maxLength) {
                maxLeft = left; maxRight = right; maxLength = length;
            }
        }
        return s.substring(maxLeft, maxRight);
    }
    // TODO: http://www.akalin.cx/longest-palindrome-linear-time
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome4("aaaa"));
    }
}
