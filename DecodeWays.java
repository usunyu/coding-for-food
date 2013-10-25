class Solution {
    public int numDecodings2(String s, int start) {
        if(start == s.length()) {
            return 1;
        }
        if(s.length() >= start + 2) {
            String s2 = s.substring(start, start + 2);
            int i2 = Integer.parseInt(s2);
            if(i2 <= 26) {
                return numDecodings2(s, start + 1) + numDecodings2(s, start + 2);
            }
        }
        return numDecodings2(s, start + 1);
    }
    
    // Time Limit Exceeded
    // recursion
    public int numDecodings2(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        return numDecodings2(s, 0);
    }

    // dynamic programming
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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("01"));
    }
}