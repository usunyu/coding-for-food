/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/

class Solution {
    private boolean canJump(int[] A, int current) {
        if(current >= A.length - 1) return true;
        while(A[current] > 0) {
            if(canJump(A, current + A[current])) return true;
            A[current]--;
        }
        return false;
    }
    
    // Time Limit Exceeded
    // greedy solution
    public boolean canJump(int[] A) {
        return canJump(A, 0);
    }

    // time complexity: O(n)
    // space complexity: O(1)
    public boolean canJump2(int[] A) {
        int next = A.length - 1;
        for(int i = A.length - 2; i >= 0; i--) {
            if(A[i] >= (next - i)) {
                next = i;
            }
        }
        return next == 0;
    }
    /*
        Second Round
    */
    public boolean canJump3(int[] A) {
        int next = A.length - 1;
        for(int i = A.length - 2; i >= 0; i--) {
            if(A[i] + i >= next) 
                next = i;
        }
        return next == 0;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {2,3,1,1,4};
        System.out.println(solution.canJump2(A));
    }
}
