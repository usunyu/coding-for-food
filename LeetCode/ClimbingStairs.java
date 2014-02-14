class Solution {
    // P(n) = P(n - 1) + P(n - 2) + 1
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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 1;
        System.out.println(solution.climbStairs(n));
    }
}