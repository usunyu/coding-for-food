/*
Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/

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

    public boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            int l = x / div;
            int r = x % 10;
            if (l != r) return false;
            // get rid of left and right
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
    /*
        Second Round
    */
    public boolean isPalindrome3(int x) {
        if(x < 0) return false;
        long left = 1, right = 1, num = x;
        while(num >= 10) {
            num /= 10;
            left *= 10;
        }
        while(left > right) {
            long lv = (x % (left * 10)) / left;
            long rv = (x / right) % 10;
            if(lv != rv) return false;
            // for next
            left /= 10;
            right *= 10;
        }
        return true;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome3(1410110141));
    }
}
