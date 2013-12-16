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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCut("amanaplanacanalpanama"));
    }
}