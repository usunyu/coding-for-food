/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

class Solution {
    // Dynamic programming solution
    // time complexity : O(N)
    public int maxSubArray(int[] A) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int[] workspace = new int[A.length];
        // initial
        workspace[0] = A[0];
        int max = workspace[0];
        for(int i = 1; i < A.length; i++) {
            if(workspace[i - 1] < 0) {
                workspace[i] = A[i];
            }
            else {
                workspace[i] = workspace[i - 1] + A[i];
            }
            if(max < workspace[i]) {
                max = workspace[i];
            }
        }
        return max;
    }
    /*
        Second Round
    */
    public int maxSubArray2(int[] A) {
        if(A == null || A.length == 0) return 0;
        int max = A[0], current = A[0];
        for(int i = 1; i < A.length; i++) {
            if(current < 0) current = 0;
            current += A[i];
            max = Math.max(current, max);
        }
        return max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution.maxSubArray2(A));
    }
}

