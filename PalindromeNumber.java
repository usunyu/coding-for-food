class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        // get the length of integer
        int n = x, length = 0;
        while(n != 0) {
            n /= 10;
            length++;
        }
        if(length == 0) return true;
        // match
        int right = 0, left = length;
        int digit = 1;
        for(int i = 1; i < length; i++) digit *= 10;
        n = x;
        while(left >= right) {
            // get left number
            int l = x / digit % 10;
            // get right number
            int r = n % 10;
            if(l != r) return false;
            n /= 10;
            left--; right++; digit /= 10;
        }
        return true;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(1221));
    }
}
