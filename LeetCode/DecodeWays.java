/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

class Solution {
    public int numDecodings(String s, int start) {
        if(start == s.length()) {
            return 1;
        }
        if(s.length() >= start + 2) {
            String s2 = s.substring(start, start + 2);
            int i2 = Integer.parseInt(s2);
            if(i2 <= 26) {
                return numDecodings(s, start + 1) + numDecodings(s, start + 2);
            }
        }
        return numDecodings(s, start + 1);
    }
    
    // Time Limit Exceeded
    // recursion
    public int numDecodings(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        return numDecodings(s, 0);
    }
}

class Solution2 {
    // dynamic programming
    // time complexity : O(N)
    // space complexity : O(N)
    public int numDecodings(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(s == null || s.length() == 0) {
            return 0;
        }
        int[] space = new int[s.length()];
        // initial
        int first = Integer.parseInt(s.substring(0,1));
        if(first > 0 && first <= 9) {
            space[0] = 1;
        }
        else {
            space[0] = 0;
        }
        for(int i = 1; i < s.length(); i++) {
            String s1 = s.substring(i, i + 1);
            String s2 = s.substring(i - 1, i + 1);
            int i1 = Integer.parseInt(s1);
            int i2 = Integer.parseInt(s2);
            if(i1 > 0 && i1 <= 9 && i2 >= 10 && i2 <= 26) {
                if(i - 2 >= 0) {
                    space[i] = space[i - 1] + space[i - 2];
                }
                else {
                    space[i] = space[i - 1] + 1;
                }
            }
            else if(i2 >= 10 && i2 <= 26) {
                if(i - 2 >= 0) {
                    space[i] = space[i - 2];
                }
                else {
                    space[i] = 1;
                }
            }
            else if(i1 > 0 && i1 <= 9) {
                space[i] = space[i - 1];
            }
        }
        return space[s.length() - 1];
    }
}

class Solution3 {
    // dynamic programming
    // time complexity : O(N)
    // space complexity : O(1)
    public int numDecodings(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(s == null || s.length() == 0) {
            return 0;
        }
        int prev1, prev2 = -1;
        // initial
        int first = Integer.parseInt(s.substring(0,1));
        if(first > 0 && first <= 9) {
            prev1 = 1;
        }
        else {
            prev1 = 0;
        }
        for(int i = 1; i < s.length(); i++) {
            String s1 = s.substring(i, i + 1);
            String s2 = s.substring(i - 1, i + 1);
            int i1 = Integer.parseInt(s1);
            int i2 = Integer.parseInt(s2);
            int temp = 0;
            if(i1 > 0 && i1 <= 9 && i2 >= 10 && i2 <= 26) {
                if(prev2 == -1) {
                    temp = prev1 + 1;
                }
                else {
                    temp = prev1 + prev2;
                }
            }
            else if(i2 >= 10 && i2 <= 26) {
                if(prev2 == -1) {
                    temp = 1;
                }
                else {
                    temp = prev2;
                }
            }
            else if(i1 > 0 && i1 <= 9) {
                temp = prev1;
            }
            // update prev
            prev2 = prev1;
            prev1 = temp;
        }
        return prev1;
    }
}
/*
    Second Round
    DP: p[i] = p[i - 2] (if 1 <= s[i - 1, i] <= 26)
             + p[i - 1] (if 1 <= s[i] <= 9)
*/
class Solution4 {
    public int numDecodings(String s) {
        // initial
        int ret = 0, p1 = 0, p2 = 0;
        if(s.length() >= 1) {
            int val1 = s.charAt(0) - '0';
            if(val1 >= 1 && val1 <= 9) p1 = 1;
            ret = p1;
            if(s.length() >= 2) {
                int val2 = s.charAt(1) - '0';
                if(val1 == 1) p2 = 1;
                else if(val1 == 2 && val2 >= 0 && val2 <= 6) p2 = 1;
                if(val2 >= 1 && val2 <= 9) {
                    p2 += p1;
                }
                ret = p2;
            }
        }
        // dp
        for(int i = 2; i < s.length(); i++) {
            // check current digit
            int p3 = 0;
            int val1 = s.charAt(i) - '0';
            if(val1 >= 1 && val1 <= 9) p3 += p2;
            // check prev digit
            int val2 = s.charAt(i - 1) - '0';
            if(val2 == 1) p3 += p1;
            else if(val2 == 2 && val1 >= 0 && val1 <= 6) p3 += p1;
            // pass
            p1 = p2;
            p2 = p3;
            ret = p2;
        }
        return ret;
    }
}

/*
    Third Round
*/
class Solution5 {
    private boolean valid1(char ch) {
        int val = ch - '0';
        if(val <= 9 && val >= 1)
            return true;
        else
            return false;
    }
    
    private boolean valid2(String str) {
        int val = Integer.parseInt(str);
        if(val <= 26 && val >= 10)
            return true;
        else
            return false;
    }
    
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int p1 = 0, p2 = 0, res = 0;
        // initial
        if(s.length() >= 1) {
            if(valid1(s.charAt(0))) {
                p1 = 1;
                res = p1;
            }
            if(s.length() >= 2) {
                if(valid2(s.substring(0, 2))) {
                    p2 = 1;
                }
                if(valid1(s.charAt(1))) {
                    p2 += p1;
                }
                res = p2;
            }
        }
        // dp
        for(int i = 2; i < s.length(); i++) {
            int p3 = 0;
            if(valid1(s.charAt(i))) {
                p3 += p2;
            }
            if(valid2(s.substring(i - 1, i + 1))) {
                p3 += p1;
            }
            p1 = p2;
            p2 = p3;
            res = p2;
        }
        return res;
    }
}

class Main {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        System.out.println(solution.numDecodings("17234"));
    }
}