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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome2("cabbad"));
    }
}
