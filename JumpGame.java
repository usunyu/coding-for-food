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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {2,3,1,1,4};
        System.out.println(solution.canJump2(A));
    }
}
