/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

class Solution {
    // P(n) = P(n - 1) + P(n - 2)
    public int climbStairs(int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(n <= 0) {
            return 0;
        }
        int[] P = new int[n];
        // initial
        P[0] = 1;
        if(n >= 2) {
            P[1] = 2;
        }
        for(int i = 2; i < n; i++) {
            P[i] = P[i - 1] + P[i - 2];
        }
        return P[n - 1];
    }

    public int climbStairs2(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;
        int[] stairs = new int[n];
        stairs[0] = 1; stairs[1] = 2;
        for(int i = 2; i < n; i++) {
            stairs[i] = stairs[i - 1] + stairs[i - 2];
        }
        return stairs[n - 1];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 1;
        System.out.println(solution.climbStairs(n));
    }
}