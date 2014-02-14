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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution.maxSubArray(A));
    }
}

